/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.shemi.simplebanking.webapi;

import local.shemi.simplebanking.webapi.client.PostgresClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 *
 * @author bmg
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PostgresClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("client_a")
//                .secret(passwordEncoder().encode("password_a"))
//                .authorities("ROLE_A")
//                .scopes("all")
//                .authorizedGrantTypes("client_credentials")
//                .accessTokenValiditySeconds(120)
//                .refreshTokenValiditySeconds(600)
//                .and()
//                .withClient("client_b")
//                .secret(passwordEncoder().encode("password_b"))
//                .authorities("ROLE_B")
//                .scopes("all")
//                .authorizedGrantTypes("client_credentials")
//                .accessTokenValiditySeconds(120)
//                .refreshTokenValiditySeconds(600);
//        clients.withClientDetails((String clientId) -> {
//            Client client = new Client(clientId, passwordEncoder().encode("password_a"));
//            if (client == null) {
//                throw new ClientRegistrationException("Client not found: " + clientId);
//            }
//            BaseClientDetails clientDetails = new BaseClientDetails();
//            clientDetails.setClientId(clientId);
//            clientDetails.setClientSecret(client.getPassword());
//            clientDetails.setAuthorizedGrantTypes(List.of("client_credentials"));
//            clientDetails.setScope(List.of("all"));
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_A"));
//            clientDetails.setAuthorities(authorities);
//            clientDetails.setAccessTokenValiditySeconds(120);
//            clientDetails.setRefreshTokenValiditySeconds(600);
//            return clientDetails;
//        });
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .setClientDetailsService(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
