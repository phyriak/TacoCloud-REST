package tacos.exception;

/**
 * Created by phyriak on 17/12/2020
 */
public class OrderNotFound extends RuntimeException{
    public OrderNotFound(String message) {
        super(message);
    }
}
