package order.services;

import order.dto.ResponseDto;
import order.model.OrderInfo;

public interface OrderService {

    OrderInfo saveOrderInfo(OrderInfo orderInfo);

    void deleteById(String id);


    OrderInfo findOrderInfoById(String id);
    ResponseDto findOrderInfoByIdWithRestaurantDetails(String id);
}
