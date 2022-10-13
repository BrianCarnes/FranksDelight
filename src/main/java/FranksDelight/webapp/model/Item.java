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
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long userId;
    private String title;
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
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    // RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "menu_id")
    @ToString.Exclude
    @JsonManagedReference
    private Menu menu;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    @ToString.Exclude
    @JsonBackReference
    private Collection<Order> orders;
}