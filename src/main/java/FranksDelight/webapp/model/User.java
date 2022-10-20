package FranksDelight.webapp.model;

import FranksDelight.webapp.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;



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
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    @Column(nullable=true)
    @LastModifiedDate
    private LocalDateTime lastLoginDate;
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
