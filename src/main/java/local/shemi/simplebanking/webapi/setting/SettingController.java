/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.setting;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shemistone
 */
@RestController
public class SettingController {

    @Autowired
    private SettingService service;

    @GetMapping("/settings")
    public List<Setting> get() {
        return service.findAll();
    }

    @GetMapping("/settings/{name}")
    public ResponseEntity<Setting> get(@PathVariable("name") String name) {
        Optional<Setting> setting = service.find(name);
        if (setting.isPresent()) {
            return ResponseEntity.of(setting);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
