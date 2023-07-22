package com.mateusjose98.ossystem.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@Sql({ "/create.sql", "/data.sql" })
public class AuthTests {

  @Value("${security.client-id}")
  private String clientId;

  @Value("${security.client-secret}")
  private String clientSecret;

  final String loginEndpoint = "/oauth2/token";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("it should return 401 when no credentials provided")
  public void testLoginFailWithNoCredentials() throws Exception {
    mockMvc.perform(post(loginEndpoint))
        .andExpect(status().isUnauthorized());
  }

  public MultiValueMap<String, String> buildLoginRequest(String username, String password) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("username", username);
    params.add("password", password);
    return params;
  }

  @Test
  @DisplayName("it should return 400 when no credentials provided")
  public void testLoginFailWithWrongCredentials() throws Exception {
    mockMvc
        .perform(post(loginEndpoint)
            .params(buildLoginRequest("bob@bob.gmail.com", "123"))
            .with(httpBasic(clientId, clientSecret))
            .accept("application/json;charset=UTF-8"))
        .andExpect(status().is4xxClientError());

  }

  @Test
  @DisplayName("it should return a access token when right credentials provided")
  public void testLoginSuccessFull() throws Exception {

    ResultActions result = mockMvc
        .perform(post(loginEndpoint)
            .params(buildLoginRequest("maria@gmail.com", "123456"))
            .with(httpBasic(clientId, clientSecret))
            .accept("application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
    String resultString = result.andReturn().getResponse().getContentAsString();
    JacksonJsonParser jsonParser = new JacksonJsonParser();
    var token = jsonParser.parseMap(resultString).get("access_token").toString();
    System.out.println(token);
    assertTrue(token.length() > 0);
  }

}
