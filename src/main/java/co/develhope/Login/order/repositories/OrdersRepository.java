package co.develhope.Login.order.repositories;

import co.develhope.Login.order.entities.Order;
import co.develhope.Login.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long> {
    List<Order> findByCreatedBy(User user);
}
