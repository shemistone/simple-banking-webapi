/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.client;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author bmg
 */
public interface ClientRepository extends CrudRepository<Client, String> {

    Client findByUsername(String username);

}
