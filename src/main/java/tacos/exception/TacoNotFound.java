package tacos.exception;

/**
 * Created by phyriak on 17/12/2020
 */
public class TacoNotFound extends RuntimeException{
    public TacoNotFound(String message) {
        super(message);
    }
}
