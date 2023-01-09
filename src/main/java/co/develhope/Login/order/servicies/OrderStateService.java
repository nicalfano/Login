package co.develhope.Login.order.servicies;

import co.develhope.Login.auth.servicies.RiderService;
import co.develhope.Login.order.entities.Order;
import co.develhope.Login.order.entities.OrderStateEnum;
import co.develhope.Login.order.repositories.OrdersRepository;
import co.develhope.Login.user.entities.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderStateService {

    @Autowired
    private RiderService riderService;
    @Autowired
    private OrdersRepository ordersRepository;
    public Order setAccept(Order order) throws Exception {
        if(order.getStatus() != OrderStateEnum.CREATED) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");
        order.setState(String.valueOf(OrderStateEnum.ACCEPTED));
        return ordersRepository.save(order);
    }

    public Order setInPreparation(Order order) throws Exception {
        if(order.getStatus() != OrderStateEnum.ACCEPTED) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");

        order.setState(String.valueOf(OrderStateEnum.IN_PREPARATION));
        return ordersRepository.save(order);
    }

    public Order setReady(Order order) throws Exception {
        if(order.getStatus() != OrderStateEnum.IN_PREPARATION) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRestaurant().getId() != user.getId()) throw new Exception("This is not your order");
        User rider = riderService.pickRider();
        order.setRider(rider);
        order.setState(String.valueOf(OrderStateEnum.READY));
        return ordersRepository.save(order);
    }

    public Order setDelivering(Order order) throws Exception {
        if(order.getStatus() != OrderStateEnum.READY) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRider().getId() != user.getId()) throw new Exception("This is not your order");

        order.setState(String.valueOf(OrderStateEnum.DELIVERING));
        return ordersRepository.save(order);
    }

    public Order setComplete(Order order) throws Exception {
        if(order.getStatus() != OrderStateEnum.DELIVERING) throw new Exception("Cannot edit order");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(order.getRider().getId() != user.getId()) throw new Exception("This is not your order");

        order.setState(String.valueOf(OrderStateEnum.COMPLETED));
        return ordersRepository.save(order);
    }
}
