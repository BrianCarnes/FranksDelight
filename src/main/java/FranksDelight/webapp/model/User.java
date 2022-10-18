package FranksDelight.webapp.model;

import FranksDelight.webapp.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String firstName;
    @Column(nullable=true)
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Date createdDate;
    private Date updatedDate;
    @Column(nullable=true)
    private Date lastLoginDate;
    @Column(nullable=true)
    private String intro;
    @Column(nullable=true)
    private String profile;
    private boolean active;

    // RELATIONSHIPS
    @OneToOne
    @JoinColumn(name = "menuId")
    @ToString.Exclude
    @JsonIgnoreProperties("user")
    private Menu menu;

    @ManyToMany
    @JoinTable(
            name = "user_orders",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "user_transactions",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "transactionId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Transaction> transactions;
}
