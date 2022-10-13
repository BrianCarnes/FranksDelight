package FranksDelight.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(insertable=false, updatable=false)
    private Long userId;
    @Column(insertable=false, updatable=false)
    private Long orderId;
    private String code;
    private String type;
    private String status;
    private String createdAt;
    private String updatedAt;
    @Column(nullable=true)
    private String deletedAt;
    @Column(nullable=true)
    private String content;

    // Relationships
    @ManyToMany
    @JoinTable(
            name = "user_transactions",
            joinColumns = @JoinColumn(name = "transactionId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonBackReference
    @ToString.Exclude
    private Collection<User> user;

    @ManyToMany
    @JoinTable(
            name = "order_transactions",
            joinColumns = @JoinColumn(name = "transactionId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    @JsonBackReference
    @ToString.Exclude
    private Collection<Order> order;

}
