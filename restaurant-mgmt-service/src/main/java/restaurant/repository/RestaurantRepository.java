package restaurant.repository;

import restaurant.model.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "restaurants")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, String> {

    @RestResource(rel = "by-name", description = @Description("Find by Restaurant name"))
    Restaurant findFirstByName(@Param("name") String name);

    @RestResource(rel = "find-by-id", description = @Description("Find-by-id"))
    Restaurant findFirstById(@Param("id") String id);


}
