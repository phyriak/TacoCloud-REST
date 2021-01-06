package tacos.dto;

import lombok.Data;

/**
 * Created by phyriak on 22/12/2020
 */
@Data
public class OrderDto {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
