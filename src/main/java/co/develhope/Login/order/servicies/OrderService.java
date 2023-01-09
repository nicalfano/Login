package co.develhope.Login.order.servicies;

import co.develhope.Login.order.entities.Order;
import co.develhope.Login.order.entities.OrderDTO;
import co.develhope.Login.order.repositories.OrdersRepository;
import co.develhope.Login.user.entities.User;
import co.develhope.Login.user.repositories.UserRepository;
import co.develhope.Login.user.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    private UserRepository userRepository;
    public Order save (OrderDTO orderInput)throws Exception{
        if(orderInput == null) return null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(user);
        order.setAddress(orderInput.getAddress());
        order.setCity(orderInput.getCity());
        order.setDescription(orderInput.getDescription());
        order.setState(orderInput.getState());
        order.setNumber(orderInput.getNumber());
        order.setTotalPrice(orderInput.getTotalPrice());
        order.setZipCode(orderInput.getZipCode());
        if(order.getRestaurant() == null) throw new Exception("Restaurant is null");
        Optional<User> restaurant = userRepository.findById(orderInput.getRestaurant());
        if(!restaurant.isPresent() || !Roles.hasRole(restaurant.get(),Roles.RESTAURANT)) throw new Exception("Restaurant not found");
        order.setRestaurant(restaurant.get());
        return ordersRepository.save(order);
    }
    public Order update(Long id,Order orderInput){
        if(orderInput == null) return null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderInput.setId(id);
        orderInput.setCreatedAt(LocalDateTime.now());
        orderInput.setCreatedBy(user);
        return ordersRepository.save(orderInput);

    }

    public boolean canEdit(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = ordersRepository.findById(id);
        if(!order.isPresent()) return false;
        return order.get().getCreatedBy().getId() == user.getId();
    }
}
