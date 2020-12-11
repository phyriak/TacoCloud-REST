package tacos.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderHasValidCardNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderHasValidCardNumber {
    public String message() default "invalid card number!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
