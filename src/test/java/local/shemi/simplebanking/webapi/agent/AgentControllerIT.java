/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.agent;

import java.util.Optional;
import local.shemi.simplebanking.webapi.agent.Agent;
import local.shemi.simplebanking.webapi.agent.AgentController;
import local.shemi.simplebanking.webapi.agent.AgentService;
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
@WebMvcTest(controllers = AgentController.class)
@AutoConfigureMockMvc
public class AgentControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AgentService service;

    public void setUp() {

    }

    @Test
    @WithMockUser
    public void findAgent() throws Exception {
        Agent mockAgent = new Agent();
        mockAgent.setId("783294");
        Mockito.doReturn(Optional.of(mockAgent)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/agents/783294")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findMissingAgent() throws Exception {
        Mockito.doReturn(Optional.ofNullable(null)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/agents/783294")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(204, mvcResult.getResponse().getStatus());
    }

}
