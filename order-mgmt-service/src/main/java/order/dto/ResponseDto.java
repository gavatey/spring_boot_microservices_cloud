package order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import order.model.OrderInfo;
import order.model.Restaurant;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
   private OrderDto orderDto;
    private RestaurantDto restaurantDto;

}
