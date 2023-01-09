package co.develhope.Login.order.controllers;

import co.develhope.Login.order.entities.Order;
import co.develhope.Login.order.repositories.OrdersRepository;
import co.develhope.Login.order.servicies.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/orders/{id}/state")
public class OrderStateController {

    @Autowired
    private OrderStateService orderStateService;

    @Autowired
    private OrdersRepository ordersRepository;

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/accepted")
    public ResponseEntity<Order> accept(@PathVariable Long id) throws Exception {
        Optional<Order> order = ordersRepository.findById(id);
        if (!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setAccept(order.get()));
    }
    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/in-preparation")
    public ResponseEntity<Order> inPreparation(@PathVariable Long id) throws Exception {
        Optional<Order> order = ordersRepository.findById(id);
        if (!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setInPreparation(order.get()));

    }
    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PutMapping("/ready")
    public ResponseEntity<Order> ready(@PathVariable Long id) throws Exception {
        Optional<Order> order = ordersRepository.findById(id);
        if (!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setReady(order.get()));

    }
    @PreAuthorize("hasRole('ROLE_RIDER')")
    @PutMapping("/delivering")
    public ResponseEntity<Order> delivering(@PathVariable Long id) throws Exception {
        Optional<Order> order = ordersRepository.findById(id);
        if (!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setDelivering(order.get()));

    }
    @PreAuthorize("hasRole('ROLE_RIDER')")
    @PutMapping("/complete")
    public ResponseEntity<Order> complete(@PathVariable Long id) throws Exception {
        Optional<Order> order = ordersRepository.findById(id);
        if (!order.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderStateService.setComplete(order.get()));

    }
}
