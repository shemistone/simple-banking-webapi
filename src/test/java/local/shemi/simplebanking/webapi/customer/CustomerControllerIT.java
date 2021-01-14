/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.customer;

import java.io.StringWriter;
import java.util.Optional;
import org.codehaus.jackson.map.ObjectMapper;
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
@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService service;

    public void setUp() {

    }

    @Test
    @WithMockUser
    public void findCustomer() throws Exception {
        Customer mockCustomer = new Customer();
        mockCustomer.setId("254721868821");
        mockCustomer.setLang("en");
        mockCustomer.setFirstName("John");
        mockCustomer.setMiddleName("M");
        mockCustomer.setLastName("Doe");
        Mockito.doReturn(Optional.of(mockCustomer)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/customers/254721868821")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findMissingCustomer() throws Exception {
        Mockito.doReturn(Optional.ofNullable(null)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/customers/254721868821")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(204, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void updateCustomer() throws Exception {
        String json = "{\n"
                + "    \"id\": \"0152345578637\",\n"
                + "    \"name\": \"Kevin M Michael\",\n"
                + "    \"customerCode\": \"254721868821\",\n"
                + "    \"atmCards\": []\n"
                + "}";
        Mockito.doNothing().when(service).update(Mockito.any());
        RequestBuilder builder = MockMvcRequestBuilders.put("/customers/254721868821")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).update(Mockito.any());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
