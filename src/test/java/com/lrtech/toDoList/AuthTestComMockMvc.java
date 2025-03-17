package com.lrtech.toDoList;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;




@SpringBootTest
@AutoConfigureMockMvc
public class AuthTestComMockMvc {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  public void authInavalidCredentials() throws Exception{

    URI uri = new URI("/auth/login");
    String json = "{ \"email\": \"merotest1\", \"senha\": \"123456\" }";
    mockMvc.perform(MockMvcRequestBuilders.post(uri)
    .content(json)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(
      MockMvcResultMatchers.status().isForbidden());

    


  }
  @Test
  public void authValidCredentials() throws Exception{

    URI uri = new URI("/auth/login");
    String json = "{ \"email\": \"meroteste\", \"senha\": \"123456\" }";
    mockMvc.perform(MockMvcRequestBuilders.post(uri)
    .content(json)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(
      MockMvcResultMatchers.status().isOk());

    


  }



}
