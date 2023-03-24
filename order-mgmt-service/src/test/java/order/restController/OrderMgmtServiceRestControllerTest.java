package order.restController;

import junit.framework.TestCase;
import order.model.FoodItem;
import order.model.OrderInfo;
import order.model.UserInfo;
import order.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class OrderMgmtServiceRestControllerTest {
    @InjectMocks
    OrderMgmtServiceRestController orderMgmtServiceRestController;

    @Mock
    OrderService orderService;

    @Mock
    private SimpMessagingTemplate template;
    @Mock
    OrderInfo orderInfo = new OrderInfo();

    @Mock
    UserInfo userInfo;

    private List<FoodItem> foodItems;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        orderMgmtServiceRestController=new OrderMgmtServiceRestController(orderService);
        foodItems = new ArrayList<FoodItem>();
        orderInfo.setOrderTime(123);
        orderInfo.setTotalPrice(123);
        orderInfo.setOrderTime(123);
        orderInfo.setDeliveryTime(1234);
        orderInfo.setSpecialNote("1233");
        orderInfo.setFoodItems(foodItems);
        orderInfo.setUserInfo(userInfo);
        orderInfo.setId("qew");
        orderInfo.setPaymentId("123");
        orderInfo.setRestaurantId("324345");


    }
@Test
    public void testUpload() {
   when(orderService.saveOrderInfo(orderInfo)).thenReturn(orderInfo);
   when(orderMgmtServiceRestController.upload(orderInfo)).thenReturn(orderInfo);

    }

    @Test
    public void testDeleteById() {
        OrderService orderService1 = mock(OrderService.class);
        doNothing().when(orderService1).deleteById(isA(String.class));
        OrderMgmtServiceRestController orderMgmtServiceRestController1 = mock(OrderMgmtServiceRestController.class);
        doNothing().when(orderMgmtServiceRestController1).deleteById(isA(String.class));
    }
@Test
    public void testGetOrderInfoById() {
    when(orderService.findOrderInfoById(orderInfo.getId())).thenReturn(orderInfo);
    when(orderMgmtServiceRestController.getOrderInfoById(orderInfo.getId())).thenReturn(orderInfo);
    }
}