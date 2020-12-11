package tacos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tacos.validator.OrderHasValidCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "taco_order")
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "name")
    @NotBlank(message = "Podanie imienia i nazwiska jest obowiązkowe.")
    private String name;

    @Column(name = "street")
    @NotBlank(message = "Podanie ulicy jest obowiązkowe")
    private String street;

    @Column(name = "city")
    @NotBlank(message = "Podanie miasta jest obowiązkowe")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "Podanie województwa jest obowiązkowe")
    private String state;

    @Column(name = "zip")
    @NotBlank(message = "Podanie kodu pocztowego jest obowiązkowe")
    private String zip;

    @Column(name = "cc_number")
    @NotBlank(message = "Podanie numeru karty jest obowiązkowe")
    @OrderHasValidCardNumber
    private String ccNumber;

    @Column(name = "cc_expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Wartość musi być w formacie MM/RR.")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV.")
    private String ccCVV;

    @JoinColumn(name = "tacos_id", referencedColumnName = "id")
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.createAt = new Date();
    }
}
