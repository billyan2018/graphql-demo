package com.demo.profiledemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.lang3.CharSet;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GraphQLControllerTest {
    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper mapper;

    private void ok(final String json) throws Exception {
        perform(json)
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

    private ResultActions perform(final String json) throws Exception {
        return mockmvc.perform(post("/graphql")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
    }

    private void queryByJsonFile(String fileName) throws Exception {
        final String json =  Resources.toString(Resources.getResource(fileName), Charsets.UTF_8);
        ok(json);
    }
    @Test
    public void queryShouldWork() throws Exception {
        queryByJsonFile("userById");
    }

    @Test
    public void createMailShouldWork() throws Exception {
        queryByJsonFile("createEmail");
    }
}
