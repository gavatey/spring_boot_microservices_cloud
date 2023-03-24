package order.restController;

import order.dto.ResponseDto;
import order.model.OrderInfo;
import order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class OrderMgmtServiceRestController {

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private OrderService orderService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    public OrderMgmtServiceRestController(OrderService orderService){
        this.orderService = orderService;
    }

    @RequestMapping(value = "/restaurants/{rid}/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfo upload(@RequestBody  OrderInfo orderInfo){
        return this.orderService.saveOrderInfo(orderInfo);
    }

    @RequestMapping(value = "/restaurants/purge/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id){
        orderService.deleteById(id);
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
    public OrderInfo getOrderInfoById(@PathVariable String id){

        return orderService.findOrderInfoById(id);
    }
   //Call to the Restaurant API details Restaurant
    @RequestMapping(value = "/order/restaurants/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findOrderInfoByIdWithRestaurantDetails(@PathVariable String id){
        ResponseDto responseDto = orderService.findOrderInfoByIdWithRestaurantDetails(id);
        return ResponseEntity.ok(responseDto);
    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void orderComplete(@RequestBody OrderInfo order) {
        //log.info("Receive order = " + order.toString());
        if (order.getUserInfo().getId() == null) {
            order.getUserInfo().setId("");
        }
        if (order.getPaymentId() == null) {
            order.setPaymentId("");
        }
        if (order.getSpecialNote() == null) {
            order.setSpecialNote("");
        }
        template.convertAndSend("/topic/orders", order);
    }

    @RequestMapping(value = "/errors", method = RequestMethod.POST)
    public void orderComplete(@RequestBody String errorMessage) {
       // log.info("Receive error = " + errorMessage);
        template.convertAndSend("/topic/orders", errorMessage);
    }

}
