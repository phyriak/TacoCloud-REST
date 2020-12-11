package tacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by phyriak on 10/12/2020
 */
@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProp {
    @Min(value=5, message="Wartość musi mieścić się w przedziale od 5 do 25.")
    @Max(value=25, message="Wartość musi mieścić się w przedziale od 5 do 25.")
    private int pageSize = 20;

}
