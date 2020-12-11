package tacos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "taco")
@NoArgsConstructor
@AllArgsConstructor
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private Date createAt;

    //@Size(min = 5, message = "Komentarz musi składać się z przynajmniej pięciu znaków")
    private String comment;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik")
    private List<Ingredient> ingredients;

    @PrePersist
    void createAt() {
        this.createAt = new Date();
    }

    public Taco(String comment, List<Ingredient> ingredients) {
        this.comment = comment;
        this.ingredients = ingredients;
    }
}
