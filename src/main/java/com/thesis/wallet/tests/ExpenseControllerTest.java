package com.thesis.wallet.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.tests.requestsAndResponses.AllExpenseResponse;
import com.thesis.wallet.tests.requestsAndResponses.LoginRequest;
import com.thesis.wallet.tests.requestsAndResponses.TokensResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllExpensesResponse() throws Exception {
        TokensResponse tokensResponse = performLogin();
        MvcResult result = mvc.perform(get("/api/expanses2").contentType(APPLICATION_JSON_UTF8).header("Authorization", "Bearer " + tokensResponse.getAccess_token())
        ).andReturn();
        String json = result.getResponse().getContentAsString();
        Assert.assertTrue(!json.isEmpty());

    }



    @Test
    public void userLoginSuccessful() throws Exception {
        TokensResponse tokensResponse = performLogin();
        Assert.assertTrue(!tokensResponse.getAccess_token().isEmpty());
        Assert.assertTrue(!tokensResponse.getRefresh_token().isEmpty());

    }

//    @Test
//    public void topExpenseTransactionGet() throws Exception {
//        TokensResponse tokensResponse = performLogin();
//        MvcResult result = mvc.perform(get("/api/expanses2/topExpenseTransaction").contentType(APPLICATION_JSON_UTF8)
//                .header("Authorization", "Bearer " + tokensResponse.getAccess_token())
//        ).andReturn();
//        String json = result.getResponse().getContentAsString();
//        System.out.println();
//    }

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

