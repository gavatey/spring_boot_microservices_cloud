package order.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import order.model.FoodItem;
import order.model.UserInfo;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String id;

    private String restaurantId;

    private List<FoodItem> foodItems;

    private int totalPrice;

    private long orderTime;
    private long deliveryTime;

    private String specialNote;

    private String paymentId;

    private UserInfo userInfo;

    public UserInfo getUserInfo(){
        return this.userInfo;
    }
}
