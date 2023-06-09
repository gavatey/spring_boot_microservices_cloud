package order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardInfo {
    private String firstName;
    private String lastName;
    private int expiredMonth;
    private int expiredYear;
    private int securityCode;
}
