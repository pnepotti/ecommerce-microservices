package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ConfirmOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.DeliverOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetAllOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetCustomerOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.RefundOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ShipOrderUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Endpoints para gestionar pedidos en el sistema de comercio electrónico con microservicios.")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final GetCustomerOrdersUseCase getCustomerOrdersUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final RefundOrderUseCase refundOrderUseCase;
    private final ShipOrderUseCase shipOrderUseCase;
    private final DeliverOrderUseCase deliverOrderUseCase;

    

}
