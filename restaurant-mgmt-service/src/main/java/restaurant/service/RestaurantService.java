package restaurant.service;

import restaurant.model.Restaurant;

public interface RestaurantService {

    void saveRestaurantInfomation(Restaurant restaurant);

    Restaurant findRestaurantByName(String name);

}
