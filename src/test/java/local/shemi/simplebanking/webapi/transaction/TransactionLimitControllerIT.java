/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.transaction;

import java.math.BigDecimal;
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
@WebMvcTest(controllers = TransactionLimitController.class)
@AutoConfigureMockMvc
public class TransactionLimitControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionLimitService service;

    public TransactionLimitControllerIT() {
    }

    public void setUp() {
    }

    @Test
    @WithMockUser
    public void findTransactionLimit() throws Exception {
        TransactionLimit transactionLimit = new TransactionLimit();
        transactionLimit.setId("mpesa_topup");
        transactionLimit.setMin(new BigDecimal(1000));
        transactionLimit.setMax(new BigDecimal(1000000));
        Mockito.doReturn(Optional.of(transactionLimit)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/transaction-limits/mpesa_topup")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findMissingTransactionLimit() throws Exception {
        TransactionLimit transactionLimit = new TransactionLimit();
        transactionLimit.setId("mpesax_topup");
        transactionLimit.setMin(new BigDecimal(1000));
        transactionLimit.setMax(new BigDecimal(400000));
        Mockito.doReturn(Optional.of(transactionLimit)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/transaction-limits/mpesax_topup")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
