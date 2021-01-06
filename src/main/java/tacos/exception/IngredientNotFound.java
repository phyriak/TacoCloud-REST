package tacos.exception;

/**
 * Created by phyriak on 17/12/2020
 */
public class IngredientNotFound extends RuntimeException{
    public IngredientNotFound(String message) {
        super(message);
    }
}
