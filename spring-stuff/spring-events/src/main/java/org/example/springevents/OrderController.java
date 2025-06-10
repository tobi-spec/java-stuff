package org.example.springevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
