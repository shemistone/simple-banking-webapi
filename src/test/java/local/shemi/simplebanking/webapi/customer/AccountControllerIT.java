/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author bmg
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService service;

    public void setUp() {

    }

    @Test
    @WithMockUser
    public void findCustomerAccount() throws Exception {
        Account mockAccount = new Account("0152345578637", "Kevin M Michael", "254721868821");
        Mockito.when(service.findByAccountNo(Mockito.anyString()))
                .thenReturn(Optional.of(mockAccount));
        RequestBuilder builder = MockMvcRequestBuilders.get("/accounts/0152345578637")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findCustomerAccounts() throws Exception {
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account("0152345578637", "Kevin M Michael", "254721868821"));
        Mockito.when(service.findByCustomerCode(Mockito.anyString())).thenReturn(mockAccounts);
        RequestBuilder builder = MockMvcRequestBuilders.get("/accounts")
                .param("customer-code", "254721868821")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
