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
@WebMvcTest(controllers = TransactionTotalController.class)
@AutoConfigureMockMvc
public class TransactionTotalControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionTotalService service;

    @Test
    @WithMockUser
    public void findTransactionTotal() throws Exception {
        TransactionTotal transactionTotal = new TransactionTotal();
        transactionTotal.setPhoneNo("254721868821");
        transactionTotal.setAmount(new BigDecimal(1000));
        Mockito.doReturn(Optional.of(transactionTotal)).when(service).find(Mockito.anyString(),
                Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/transaction-totals/254721868821")
                .param("processing-code", "750000")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }
    
    @Test
    @WithMockUser
    public void findMissingTransactionTotal() throws Exception {
        TransactionTotal transactionTotal = new TransactionTotal();
        transactionTotal.setPhoneNo("254721868821");
        transactionTotal.setAmount(new BigDecimal(0));
        Mockito.doReturn(Optional.of(transactionTotal)).when(service).find(Mockito.anyString(),
                Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/transaction-totals/254721868821")
                .param("processing-code", "000000")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
