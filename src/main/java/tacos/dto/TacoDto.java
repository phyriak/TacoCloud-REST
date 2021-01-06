package tacos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by phyriak on 19/12/2020
 */
@Getter
@Setter
public class TacoDto {
    private String comment;
        private List<Long> ingredients;

}
