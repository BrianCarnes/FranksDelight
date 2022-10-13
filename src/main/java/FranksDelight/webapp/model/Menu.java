package FranksDelight.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long userId;
    private String title;
    private String slug;
    @Column(nullable=true)
    private String summary;
    private Date createdAt;
    private Date updatedAt;
    @Column(nullable=true)
    private Date deletedAt;
    @Column(nullable=true)
    private String content;

    // RELATIONSHIPS
    @OneToMany
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    @ToString.Exclude
    private Collection<Item> item;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;
}
