package restaurant.restController;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import restaurant.model.MenuItem;
import restaurant.model.Restaurant;
import restaurant.service.RestaurantService;
import restaurant.serviceImpl.MenuItemServiceImpl;
import restaurant.serviceImpl.RestaurantServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class RestaurantMgmtServiceRestControllerTest {

    @InjectMocks
    RestaurantMgmtServiceRestController restaurantMgmtServiceRestController;


    private MenuItemServiceImpl menuItemService;
    private RestaurantServiceImpl restaurantService;


    Restaurant restaurant = new Restaurant();
    MenuItem menuItem = new MenuItem();



    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        restaurant.setId("2323");
        restaurant.setAddress("pune");
        restaurant.setName("Shivaji");
        menuItem.setRestaurantId("tett77");
        menuItem.setName("malashree");
        menuItem.setPrice(30);
        menuItem.setId("2312tt");
        menuItem.setDescription("testy");
    }
   @Test
    public void testUploadRestaurantInfo() {
       RestaurantService restaurantService1 = mock(RestaurantService.class);
       doNothing().when(restaurantService1).saveRestaurantInfomation(restaurant);
       RestaurantMgmtServiceRestController restaurantMgmtServiceRestController1 = mock(RestaurantMgmtServiceRestController.class);
       doNothing().when(restaurantMgmtServiceRestController1).uploadRestaurantInfo(restaurant);

    }
@Test
    public void testGetRestaurantByName() {
    RestaurantServiceImpl restaurantService =mock(RestaurantServiceImpl.class);
    when(restaurantService.findRestaurantByName("tesy")).thenReturn(restaurant);
    RestaurantMgmtServiceRestController restaurantMgmtServiceRestController1 = mock(RestaurantMgmtServiceRestController.class);
    when(restaurantMgmtServiceRestController1.getRestaurantByName(restaurant.getName())).thenReturn(restaurant);
    }
@Test
    public void testUploadMenuItem() {
    MenuItemServiceImpl MenuItemService1 = mock(MenuItemServiceImpl.class);
    doNothing().when(MenuItemService1).saveMenuItem(menuItem);
    RestaurantMgmtServiceRestController restaurantMgmtServiceRestController1 = mock(RestaurantMgmtServiceRestController.class);
    doNothing().when(restaurantMgmtServiceRestController1).uploadMenuItem(menuItem);

    }
@Test
    public void testUploadMenuItemList() {
    List<MenuItem> menuItemList = new ArrayList<MenuItem>();
    menuItemList.add(menuItem);
    MenuItemServiceImpl MenuItemService1 = mock(MenuItemServiceImpl.class);
    doNothing().when(MenuItemService1).saveMenuItemList(menuItemList);
    RestaurantMgmtServiceRestController restaurantMgmtServiceRestController1 = mock(RestaurantMgmtServiceRestController.class);
    doNothing().when(restaurantMgmtServiceRestController1).uploadMenuItemList(menuItemList);
    }
@Test
    public void testGetAllMenusByRestaurantId() {
    List<MenuItem> menuItemList = new ArrayList<MenuItem>();
    menuItemList.add(menuItem);
    MenuItemServiceImpl MenuItemService1 = mock(MenuItemServiceImpl.class);
    when(MenuItemService1.getAllMenuItemsByRestaurantId("test123")).thenReturn(menuItemList);
    RestaurantMgmtServiceRestController restaurantMgmtServiceRestController1 = mock(RestaurantMgmtServiceRestController.class);
    when(restaurantMgmtServiceRestController1.getAllMenusByRestaurantId("test123")).thenReturn(menuItemList);


    }
}