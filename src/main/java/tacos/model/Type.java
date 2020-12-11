package tacos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by phyriak on 01/12/2020
 */

@Data
@Entity
@Table(name = "type")
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    // WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
}
