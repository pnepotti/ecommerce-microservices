package com.paulonepotti.ecommerce.order_microservice.infrastructure.config;

import com.paulonepotti.ecommerce.order_microservice.application.port.out.OrderRepositoryPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.out.ProductPort;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ConfirmOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.DeliverOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetAllOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetCustomerOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.RefundOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ShipOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.service.OrderService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableTransactionManagement
public class OrderConfig {

    @Bean
    @Transactional
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, ProductPort productPort) {
        return new OrderService(orderRepositoryPort, productPort);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderService orderService) {
        return orderService::createOrder;
    }

    @Bean
    public ConfirmOrderUseCase confirmOrderUseCase(OrderService orderService) {
        return orderService::confirmOrder; 
    }

    @Bean
    public CancelOrderUseCase cancelOrderUseCase(OrderService orderService) {
        return orderService::cancelOrder;
    }

    @Bean
    public DeliverOrderUseCase deliverOrderUseCase(OrderService orderService) {
        return orderService::deliverOrder;
    }

    @Bean
    public RefundOrderUseCase refundOrderUseCase(OrderService orderService) {
        return orderService::refundOrder;
    }

    @Bean
    public ShipOrderUseCase shipOrderUseCase(OrderService orderService) {
        return orderService::shipOrder;
    }

    @Bean
    public GetAllOrdersUseCase getAllOrdersUseCase(OrderService orderService) {
        return orderService::getAllOrders;
    }

    @Bean
    public GetOrderUseCase getOrderUseCase(OrderService orderService) {
        return orderService::getOrderById;
    }

    @Bean
    public GetCustomerOrdersUseCase getCustomerOrdersUseCase(OrderService orderService) {
        return orderService::getCustomerOrders;
    }


}
