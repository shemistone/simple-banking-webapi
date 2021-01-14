/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.setting;

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
@WebMvcTest(controllers = SettingController.class)
@AutoConfigureMockMvc
public class SettingControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SettingService service;

    public void setUp() {

    }

    @Test
    @WithMockUser
    public void findSettings() throws Exception {
        List<Setting> mockSettings = new ArrayList<>();
        Setting mockSetting = new Setting();
        mockSetting.setId("app_name");
        mockSetting.setValue("Simple Banking");
        mockSettings.add(mockSetting);
        Mockito.doReturn(mockSettings).when(service).findAll();
        RequestBuilder builder = MockMvcRequestBuilders.get("/settings")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).findAll();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findSetting() throws Exception {
        Setting mockSetting = new Setting();
        mockSetting.setId("app_name");
        mockSetting.setValue("Simple Banking");
        Mockito.doReturn(Optional.of(mockSetting)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/settings/app_name")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    public void findMissingSetting() throws Exception {
        Mockito.doReturn(Optional.ofNullable(null)).when(service).find(Mockito.anyString());
        RequestBuilder builder = MockMvcRequestBuilders.get("/settings/app_namex")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Mockito.verify(service, Mockito.times(1)).find(Mockito.anyString());
        Assertions.assertEquals(204, mvcResult.getResponse().getStatus());
    }
    
}
