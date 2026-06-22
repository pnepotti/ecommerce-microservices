package com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.controller;

import com.paulonepotti.ecommerce.order_microservice.application.port.in.CancelOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ConfirmOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.CreateOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.DeliverOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetAllOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetCustomerOrdersUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.GetOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.RefundOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.application.port.in.ShipOrderUseCase;
import com.paulonepotti.ecommerce.order_microservice.domain.model.Order;
import com.paulonepotti.ecommerce.order_microservice.domain.model.PageResponse;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderRequest;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.dto.OrderResponse;
import com.paulonepotti.ecommerce.order_microservice.infrastructure.adapter.in.rest.mapper.OrderMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
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
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity <OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        Order order = orderMapper.toDomain(request);
        Order createdOrder = createOrderUseCase.createOrder(order);
        return ResponseEntity.ok(orderMapper.toResponse(createdOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        Order order = getOrderUseCase.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));    
    }    

    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> getAllOrders(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<Order> orderPage = getAllOrdersUseCase.getAllOrders(page, size);
        return ResponseEntity.ok(orderMapper.toResponsePage(orderPage));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByCustomerId(
        @PathVariable Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<Order> orderPage = getCustomerOrdersUseCase.getCustomerOrders(id, page, size);
        return ResponseEntity.ok(orderMapper.toResponsePage(orderPage));
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable Long id) {
        Order order = confirmOrderUseCase.confirmOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }

    @PatchMapping("/{id}/ship")
    public ResponseEntity<OrderResponse> shipOrder(@PathVariable Long id) {
        Order order = shipOrderUseCase.shipOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<OrderResponse> deliverOrder(@PathVariable Long id) {
        Order order = deliverOrderUseCase.deliverOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }

    @PatchMapping("/{id}/refund")
    public ResponseEntity<OrderResponse> refundOrder(@PathVariable Long id) {
        Order order = refundOrderUseCase.refundOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id, @RequestBody String reason) {
        Order order = cancelOrderUseCase.cancelOrder(id, reason);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }
}