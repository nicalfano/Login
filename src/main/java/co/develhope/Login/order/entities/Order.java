package co.develhope.Login.order.entities;

import co.develhope.Login.utils.entities.BaseEntitiy;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private String address;
    private String number;
    private String city;
    private String state;
    private String zipCode;
    private double totalPrice;
}
