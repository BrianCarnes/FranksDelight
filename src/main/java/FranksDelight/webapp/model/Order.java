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
    @Column(insertable=false, updatable=false, nullable = true)
    private Long userId;
    @Column(nullable=true)
    private String status;
    @Column(nullable=true)
    private String title;
    @Column(nullable=true)
    private String slug;
    @Column(nullable=true)
    private String summary;
    @Column(nullable=true)
    private float subTotal;
    @Column(nullable=true)
    private float itemDiscount;
    private float tax;
    @Column(nullable=true)
    private float shipping;
    private float total;
    @Column(nullable=true)
    private String promo;
    @Column(nullable=true)
    private float promoDiscount;
    private float grandTotal;
    @Column(nullable=true)
    private String firstName;
    @Column(nullable=true)
    private String middleName;
    @Column(nullable=true)
    private String lastName;
    @Column(nullable=true)
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
    @Column(nullable=true)
    private String createdAt;
    @Column(nullable=true)
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
