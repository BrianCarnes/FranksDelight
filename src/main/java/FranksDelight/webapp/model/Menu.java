package FranksDelight.webapp.model;

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
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(insertable=false, updatable=false)
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
    @JoinColumn(name = "itemId")
    @JsonBackReference
    @ToString.Exclude
    private Collection<Item> item;

    @OneToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
