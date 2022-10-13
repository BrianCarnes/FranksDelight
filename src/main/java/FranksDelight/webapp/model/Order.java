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
    @Column(insertable=false, updatable=false)
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
    @Column(nullable=true)
    private String address;
    @Column(nullable=true)
    private String city;
    @Column(nullable=true)
    private String state;
    @Column(nullable=true)
    private String zip;
    private String createdAt;
    private String updatedAt;
    @Column(nullable=true)
    private String deletedAt;

    // RELATIONSHIPS
    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "itemId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Item> item;

    @ManyToMany
    @JoinTable(
            name = "order_transactions",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "transactionId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Transaction> transaction;

    @ManyToMany
    @JoinTable(
            name = "user_orders",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<User> user;
}
