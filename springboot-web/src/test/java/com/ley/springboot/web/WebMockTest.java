package com.ley.springboot.web;

import com.ley.springboot.web.controller.HelloController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {WebApplication.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class WebMockTest {

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }


    @Test
    public void index() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        log.info("test mvc result: {}", result.getResponse().getContentAsString());
    }

}
