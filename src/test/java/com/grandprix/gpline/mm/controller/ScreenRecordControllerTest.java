package com.grandprix.gpline.mm.controller;

import com.grandprix.gpline.mm.service.ScreenRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScreenRecordController.class)
public class ScreenRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScreenRecordService screenRecordService;

    @Test
    public void getScreenshotFiles() throws Exception {
        String login = "e@mail";
        Long startDate = 1566905420001L;
        Long endDate = 1566905420001L;
        String fileName1 = "file1";
        String fileName2 = "file2";

        given(screenRecordService.getScreenRecords(login, startDate, endDate)).willReturn(Arrays.asList(fileName1, fileName2));
        mockMvc.perform(get("/screenshotfiles")
                .param("login", login)
                .param("startDate", String.valueOf(startDate))
                .param("endDate", String.valueOf(endDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is(fileName1)))
                .andExpect(jsonPath("$[1]", is(fileName2)));
    }
}