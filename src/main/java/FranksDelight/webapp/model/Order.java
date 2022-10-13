package FranksDelight.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long userId;
    private String status;
    private String title;
    private String slug;
    private String summary;
    private float subTotal;
    private float itemDiscount;
    private float tax;
    private float shipping;
    private float total;
    @Column(nullable=true)
    private String promo;
    @Column(nullable=true)
    private float promoDiscount;
    private float grandTotal;
    private String firstName;
    @Column(nullable=true)
    private String middleName;
    @Column(nullable=true)
    private String lastName;
    private String mobile;
    @Column(nullable=true)
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String createdAt;
    private String updatedAt;
    @Column(nullable=true)
    private String deletedAt;

    // RELATIONSHIPS
    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Item> item;

    @ManyToMany
    @JoinTable(
            name = "order_transactions",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Transaction> transaction;

    @ManyToMany
    @JoinTable(
            name = "user_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<User> user;
}
