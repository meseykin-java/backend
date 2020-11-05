package com.grandprix.gpline.mm.controller;

import com.grandprix.gpline.mm.service.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

//    @Test
//    void getAllRequests() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Request request1 = new Request();
//        request1.setCustomerClass("class1");
//
//        Request request2 = new Request();
//        request2.setCustomerClass("class2");
//
//        given(requestService.getAllRequests()).willReturn(Arrays.asList(request1, request2));
//        mockMvc.perform(get("/requests/all")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].customerClass", is(request1.getCustomerClass())))
//                .andExpect(jsonPath("$[1].customerClass", is(request2.getCustomerClass())));
//    }

//    @Test
//    void getAllRequestsCount() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Long count = 5L;
//        given(requestService.countAllRequests()).willReturn(count);
//        mockMvc.perform(get("/requests/all/count")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", equalTo(count.intValue())));
//    }

//    @Test
//    void getRequests() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Request request1 = new Request();
//        request1.setCustomerClass("class1");
//
//        Request request2 = new Request();
//        request2.setCustomerClass("class2");
//
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "e@mail";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 1L;
//        String messageText = "hello";
//
//        given(requestService.getRequests(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText)).willReturn(Arrays.asList(request1, request2));
//        //mockMvc.perform(get("/requests?channelId=1&filialId=1")
//        mockMvc.perform(get("/requests?channelId=" + channelId + "&filialId=" + filialId + "&operatorLogin=" + operatorLogin + "&closeStatus=" + closeStatus +
//                    "&regDateStart=" + regDateStart + "&regDateEnd=" + regDateEnd + "&messageText=" + messageText)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].customerClass", is(request1.getCustomerClass())))
//                .andExpect(jsonPath("$[1].customerClass", is(request2.getCustomerClass())));
//    }

//    @Test
//    void getRequestsCount() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Long count = 5L;
//
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "e@mail";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 1L;
//        String messageText = "hello";
//
//        given(requestService.countRequests(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText)).willReturn(count);
//        mockMvc.perform(get("/requests/count?channelId=" + channelId + "&filialId=" + filialId + "&operatorLogin=" + operatorLogin + "&closeStatus=" + closeStatus +
//                "&regDateStart=" + regDateStart + "&regDateEnd=" + regDateEnd + "&messageText=" + messageText)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", equalTo(count.intValue())));
//    }

//    @Test
//    void getAllRequestStatuses() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        String status1 = "status1";
//        String status2 = "status2";
//
//        given(requestService.getAllRequestStatuses()).willReturn(Arrays.asList(status1, status2));
//        mockMvc.perform(get("/statuses")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0]", is(status1)))
//                .andExpect(jsonPath("$[1]", is(status2)));
//    }

//    @Test
//    void getRequestsByChannel() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Integer id = 1;
//
//        Request request1 = new Request();
//        request1.setCustomerClass("class1");
//
//        Request request2 = new Request();
//        request2.setCustomerClass("class2");
//
//        given(requestService.getRequestsByChannel(id)).willReturn(Arrays.asList(request1, request2));
//        mockMvc.perform(get("/requests/channel/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].customerClass", is(request1.getCustomerClass())))
//                .andExpect(jsonPath("$[1].customerClass", is(request2.getCustomerClass())));
//    }

//    @Test
//    void getRequestsByFilter() {    // этот метод rest-сервиса в приложении не используется
//    }
}