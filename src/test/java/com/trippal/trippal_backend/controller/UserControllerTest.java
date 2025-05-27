package com.trippal.trippal_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trippal.trippal_backend.dtos.UserInfoDto;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.JwtService;
import com.trippal.trippal_backend.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserInfoService userInfoService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @Test
    public void testCreateUser_ValidUser() throws Exception {
        UserInfo user = new UserInfo();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("1234");
        user.setRoles("USER");

        UserInfoDto mockDto = new UserInfoDto();
        mockDto.setName("John");
        mockDto.setEmail("john@gmail.com");

        Mockito.when(userInfoService.createUser(any(UserInfo.class))).thenReturn(mockDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/createUser")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
