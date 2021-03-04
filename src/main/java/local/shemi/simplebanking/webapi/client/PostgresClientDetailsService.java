/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi.client;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author bmg
 */
@Service
public class PostgresClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientRoleRepository clientRoleRepository;
    @Autowired
    private ClientPermissionRepository clientPermissionRepository;
    private final Logger logger = LoggerFactory.getLogger(PostgresClientDetailsService.class);

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientRepository.findByUsername(clientId);
        if (client == null) {
            throw new ClientRegistrationException("Client not found: " + clientId);
        }
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(client.getPassword());
        clientDetails.setAuthorizedGrantTypes(List.of("client_credentials"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        clientRoleRepository.findAllByClientId(clientId).forEach(r -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRole().getName()));
        });
        List<String> permissions = new ArrayList<>();
        clientPermissionRepository.findAllByClientId(clientId).forEach(p -> {
            authorities.add(new SimpleGrantedAuthority(p.getPermission().getName()));
            permissions.add(p.getPermission().getName());
        });
        clientDetails.setScope(permissions);
        clientDetails.setAuthorities(authorities);
        clientDetails.setResourceIds(List.of("simple-banking-webapi"));
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setRefreshTokenValiditySeconds(3600);
        logger.info("Client details: " + clientDetails);
        return clientDetails;
    }

}
