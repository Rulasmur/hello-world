package io.github.rulasmur.helloworld.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        mvc.perform(
                post("/hello-world")
                        .param("key", "1")
                        .param("value", "andreas"))
        .andExpect(status().isCreated());

    }

    @After
    public void teardown() throws Exception {
        mvc.perform(
                delete("/hello-world")
                        .param("key", "1"))
                .andExpect(status().isOk());
    }



    @Test
    public void getTest() throws Exception {

        mvc.perform(
                get("/hello-world")
                        .param("key", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello andreas")));
    }

    @Test
    public void updateTest() throws Exception {
        mvc.perform(
                put("/hello-world")
                        .param("key", "1")
                        .param("value", "thomas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        mvc.perform(
                post("/hello-world")
                        .param("key", "2")
                        .param("value", "Thomas"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteTest() throws Exception {
        mvc.perform(
                delete("/hello-world")
                        .param("key", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
}