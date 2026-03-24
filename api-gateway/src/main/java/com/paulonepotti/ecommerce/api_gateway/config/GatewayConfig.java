package com.paulonepotti.ecommerce.api_gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            // Lógica PRE-filtro (antes de mandar la petición al microservicio)
            System.out.println("Petición interceptada: " + exchange.getRequest().getPath());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Lógica POST-filtro (cuando el microservicio responde)
                System.out.println("Respuesta enviada con status: " + exchange.getResponse().getStatusCode());
            }));
        };
    }
}