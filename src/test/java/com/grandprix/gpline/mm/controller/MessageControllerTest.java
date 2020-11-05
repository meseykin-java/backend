package com.grandprix.gpline.mm.controller;

import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

//    @Test
//    public void getMessages() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Message message1 = new Message();
//        message1.setMessageText("111");
//
//        Message message2 = new Message();
//        message2.setMessageText("222");
//
//        Message message3 = new Message();
//        message3.setMessageText("333");
//
//        given(messageService.getMessagesByContactId("1")).willReturn(Arrays.asList(message1));
//        mockMvc.perform(get("/messages/contact/{contactId}", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].messageText", is(message1.getMessageText())));
//
//        given(messageService.getMessagesByContactId("2")).willReturn(Arrays.asList(message2, message3));
//        mockMvc.perform(get("/messages/contact/{contactId}", "2")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].messageText", is(message2.getMessageText())))
//                .andExpect(jsonPath("$[1].messageText", is(message3.getMessageText())));
//    }

    @Test
    public void getMessage() throws Exception {
        Message message1 = new Message();
        message1.setMessageText("111");
        message1.setCreateDate(1566905420000L);

        given(messageService.findByContactIdAndCreateDate("1", 1566905420001L)).willReturn(message1);
        mockMvc.perform(get("/message/contactId/{contactId}/date/{date}", "1", 1566905420001L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("messageText", is(message1.getMessageText())));

        given(messageService.findByContactIdAndCreateDate("1", 1566905420000L)).willReturn(null);
        mockMvc.perform(get("/message/contactId/{contactId}/date/{date}", "1", 1566905420000L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        given(messageService.findByContactIdAndCreateDate("2", 1566905420001L)).willReturn(null);
        mockMvc.perform(get("/message/contactId/{contactId}/date/{date}", "2", 1566905420001L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Привет! Приложение работает"));
    }
}