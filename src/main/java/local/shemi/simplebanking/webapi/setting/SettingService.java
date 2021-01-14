/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.setting;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class SettingService {

    @Autowired
    private SettingRepository repository;

    public List<Setting> findAll() {
        return repository.findAll();
    }

    public Optional<Setting> find(String name) {
        return repository.findByName(name);
    }

}
