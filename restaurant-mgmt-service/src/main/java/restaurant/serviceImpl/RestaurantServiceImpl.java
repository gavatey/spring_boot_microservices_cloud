package restaurant.serviceImpl;

import restaurant.model.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void saveRestaurantInfomation(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        return restaurantRepository.findFirstByName(name);
    }


    public Restaurant findRestaurantById(String name) {
        return restaurantRepository.findFirstById(name);
    }
}
