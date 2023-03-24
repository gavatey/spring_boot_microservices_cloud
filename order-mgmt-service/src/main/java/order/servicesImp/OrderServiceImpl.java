package order.servicesImp;

import order.dto.OrderDto;
import order.dto.ResponseDto;
import order.dto.RestaurantDto;
import order.model.OrderInfo;
import order.model.Restaurant;
import order.repository.OrderRepository;
import order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository ;
   @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
        orderInfo.setOrderTime(System.currentTimeMillis());
        orderInfo.setTotalPrice(orderInfo.getFoodItems().stream().mapToInt(e -> e.getPrice() * e.getQuantity()).sum());
        return orderRepository.save(orderInfo);
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderInfo findOrderInfoById(String id) {

        return orderRepository.findFirstById(id);
    }

    @Override
    public ResponseDto findOrderInfoByIdWithRestaurantDetails(String id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            OrderInfo orderInfo1 = orderRepository.findFirstById(id);

            String url= "http://localhost:8081/api/order/restaurants/" + orderInfo1.getRestaurantId();

            ResponseEntity<RestaurantDto> responseEntity = restTemplate
                    .getForEntity(url, RestaurantDto.class);
            RestaurantDto restaurantDto = responseEntity.getBody();
            System.out.println(responseEntity.getStatusCode());
            OrderDto orderDto = mapToUser(orderInfo1);
            responseDto.setOrderDto(orderDto);
            responseDto.setRestaurantDto(restaurantDto);

        }
        catch (Exception e){
            e.printStackTrace();
        }

            return responseDto;
    }

    private OrderDto mapToUser(OrderInfo orderInfo1) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderInfo1.getId());
        orderDto.setOrderTime(orderInfo1.getOrderTime());
        orderDto.setUserInfo(orderInfo1.getUserInfo());
        orderDto.setDeliveryTime(orderInfo1.getDeliveryTime());
        orderDto.setSpecialNote(orderInfo1.getSpecialNote());
        orderDto.setPaymentId(orderInfo1.getPaymentId());
        orderDto.setTotalPrice(orderInfo1.getTotalPrice());
        orderDto.setFoodItems(orderInfo1.getFoodItems());
        return orderDto;
    }

}
