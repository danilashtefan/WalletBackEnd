package com.thesis.wallet.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.thesis.wallet.tests.requestsAndResponses.LoginRequest;
import com.thesis.wallet.tests.requestsAndResponses.TokensResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mvc;



    @Test
    public void getAllCategoriesResponse() throws Exception {
        TokensResponse tokensResponse = performLogin();
        MvcResult result = mvc.perform(get("/api/expanseCategories2").contentType(APPLICATION_JSON_UTF8).header("Authorization", "Bearer " + tokensResponse.getAccess_token())
        ).andReturn();
        String json = result.getResponse().getContentAsString();
        Assert.assertTrue(!json.isEmpty());
    }

    @Test
    public void getTopExpenseCategory() throws Exception {
        TokensResponse tokensResponse = performLogin();
        String minDate ="2002-11-03";

        String maxDate = "2023-11-05";

        MvcResult result = mvc.perform(get("/api/expanseCategories2/topExpenseCategory").contentType(APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + tokensResponse.getAccess_token())
                .param("minDate", minDate).param("maxDate",maxDate)).andReturn();
        String json = result.getResponse().getContentAsString();
        Assert.assertTrue(!json.isEmpty());
    }

    @Test
    public void getTopIncomeCategory() throws Exception {
        TokensResponse tokensResponse = performLogin();
        String minDate ="2002-11-03";

        String maxDate = "2023-11-05";

        MvcResult result = mvc.perform(get("/api/expanseCategories2/topIncomeCategory").contentType(APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + tokensResponse.getAccess_token())
                .param("minDate", minDate).param("maxDate",maxDate)).andReturn();
        String json = result.getResponse().getContentAsString();
        Assert.assertTrue(!json.isEmpty());
    }


    private TokensResponse performLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("jack");
        loginRequest.setPassword("1234");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(loginRequest);

        MvcResult result = mvc.perform(post("/api/login").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson)).andReturn();

        String json = result.getResponse().getContentAsString();
        return new Gson().fromJson(json, TokensResponse.class);
    }
}
