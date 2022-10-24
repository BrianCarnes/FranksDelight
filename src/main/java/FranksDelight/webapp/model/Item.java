package FranksDelight.webapp.model;

import FranksDelight.webapp.enums.Categories;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Categories category;
    @Column(insertable=false, updatable=false)
    private Long userId;
    private String title;
    @Column(nullable=true)
    private String image;
    private String slug;
    @Column(nullable=true)
    private String summary;
    @Column(nullable=true)
    private String cooking;
    @Column(nullable=true)
    private float price;
    private float quantity;
    private Boolean isAvailable;
    private String instructions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(nullable=true)
    private LocalDateTime deletedAt;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "menuId")
    @ToString.Exclude
    @JsonManagedReference
    private Menu menu;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Order> orders;
}
