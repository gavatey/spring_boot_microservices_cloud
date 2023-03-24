# Develop a Food Ordering Backend System

This is a system designed in **Spring Boot/Spring Data/Spring Cloud/Microservices architecture** to provide **REST** APIs for food ordering/delivery system.

## Development environment
- **Mongo DB** is running on **docker**, image specified in **docker-compose.yml** file, and started by **docker-compose**.
- **Spring Data Rest** is used for quick DB access and verification.
- **Spring Boot** is used for fast REST API development and independant deployment.
- **Spring Boot Actuator** is used to provide monitoring information. (/health, /metrics... etc.)
- **HAL Browser** is used for quick repository exploration.
- **Lombok** is used to eliminate constructors and getter/setter implementation for cleaner coding style.
- **Spring Cloud** is used to provide infrastructure services.
- **Eureka** is used for microservices registration and discovery.
- **Hystrix** is used as circuit breaker when failure to prevent avalanche in system.
- **RabbitMQ** is used to decouple payment service.
- **WebSocket** is used to send message to UI.
- **RestTemplate** is used to communicate between microservices.

## Features
- User can search a restaurant based on restaurant name.
- User can order food by choosing different menu item, quantity and add a note about his/her diet restrictions and etc.
- User can fill in the delivery address.
- After user places an order, the order should contain the food items user ordered, quantity, price and order time.
- User needs to pay for his/her order by providing credit card number, expiration date and security code.
- After payment is made successfully, it should return payment ID, timesatmp and then the order is considered as completed so the user can see the estimated delivery time.
- Estimated delivery time is a random time between 5 minutes ~ 1 hour.

## REST APIs
### Restaurant Service
Search restaurant by name
```
GET localhost:8081/api/restaurants?name=<restaurant_name>
Return
{
    "id": <restaurant_id>,
    "name": <restaurant_name>
}
```
Create a restaurant
```
POST localhost:8081/api/restaurants
Request Body
{
    "name": <restaurant_name>
}
Return HttpStatus.CREATED
```
Get all menu items by restaurant id
```
GET localhost:8081/api/restaurants/{restaurantId}/menuItem
Return
[
    {
        "id": <menu_item_id>,
        "restaurantId": <restaurantId>,
        "name": <menu_item_name>,
        "description": <menu_item_description>,
        "price": <menu_item_price>
    },
    ...
]
```
Create menu item
```
POST localhost:8081/api/restaurants/{restaurantId>/menuItem
Request Body
{
        "restaurantId": <restaurantId>,
        "name": <menu_item_name>,
        "description": <menu_item_description>,
        "price": <menu_item_price>
}
Return HttpStatus.CREATED
```
Bulk upload menu items
```
POST localhost:8081/api/restaurants/bulk/menuItems
Request Body
[
    {
        "restaurantId": <restaurantId>,
        "name": <menu_item_name>,
        "description": <menu_item_description>,
        "price": <menu_item_price>
    }
]
Return HttpStatus.CREATED
```
### Order Mgmt Service
Create an order
```
POST localhost:8082/api/restaurants/{rid}/orders
{
    "restaurantId": <restaurant_id>,
    "foodItems":
    [
        {
            "name": <menu_item_name>,
            "price": <menu_item_price>,
            "quantity": <# of items>
        },
        ...
    ],
    "userInfo":
    {
        "firstName": <customer_first_name>,
        "lastName": <customer_last_name>,
        "phone": <customer_phone>,
        "address": <customer_address>
    },
    "specialNote": <special_note>
}
Return:
HttpStatus.CREATED

```
After Create order then return response
```
{
    "id": <order_id>,
    "restaurantId": <restaurant_id>,
    "foodItems":
    [
        {
            "name": <menu_item_name>,
            "price": <menu_item_price>,
            "quantity": <# of items>
        },
        ...
    ],
    "userInfo":
    {
        "firstName": <customer_first_name>,
        "lastName": <customer_last_name>,
        "phone": <customer_phone>,
        "address": <customer_address>
    },
    "specialNote": <special_note>,
    "totalPrice": <total_price>,
    "orderTime": <order_time_in_milliseconds>
}

```
Get Order Details by orderid
```

Get : localhost:8082/api/restaurants/{id}
{
    "id": "640d5f6841c7cd3681411738",
    "restaurantId": "640d4ef141c76149fd0df23d",
    "foodItems": [
        {
            "name": "chikan Habab",
            "quantity": 3,
            "price": 35
        },
        {
            "name": "Poha Kadai",
            "quantity": 5,
            "price": 50
        }
    ],
    "totalPrice": 355,
    "orderTime": 1678597991810,
    "deliveryTime": 0,
    "specialNote": "Nice",
    "userInfo": {
        "firstName": "yogesh",
        "lastName": "gavate",
        "address": "pune",
        "phone": "8600165105"
    }
}
```
Order Complete Updater
```
POST localhost:8082/api/orders
{
    "id": <order_id>,
    "foodItems":
    [
        {
            "name": <menu_item_name>,
            "price": <menu_item_price>,
            "quantity": <# of items>
        },
        ...
    ],
    "userInfo":
    {
        "firstName": <customer_first_name>,
        "lastName": <customer_last_name>,
        "phone": <customer_phone>,
        "address": <customer_address>
    },
    "specialNote": <special_note>,
    "totalPrice": <total_order_price>,
    "orderTime": <order_time>,
    "deliveryTime": <food_delivery_time>,
    "paymentId": <payment_id>
}


Note: This API will serialize the order to WebSocket channel: "topic/orders", UI can subscribe to this channel to receive message and display to user.
```
### Oreder API call the Restuarant API for Details
Get : localhost:8082/api/order/restaurants/64114c27cdffd0a251736d1e
```


```
### Check data in MongoDB
Find mongodb container id
```
docker ps
```
Enter mongodb container by typing the first 3 charactters of the container id (ex: '9cd'), then type mongo inside the container to use mongodb shell command.
```
docker exec -it 9cd bash
# mongo                             // open mongo shell
> use test                          // Spring boot use test db as default
> show collections                  // show all collections inside test db
> db.restaurant.find().pretty()     // show all data inside restaurant table
> exit                              // quit mongo shell
> exit                              // exit container shell
```

### Installation
```bash
mvn clean install
```
### Start Eureka
```bash
sh ./start-eureka.sh
```
### Start Restaurant Mgmt Service
```bash
sh ./start-restaurant-mgmt-service.sh
```
### Start Order Mgmt Service
```bash
sh ./start-order-mgmt-service.sh
```
### Upload Test Menu Items
```bash
cd restaurant-mgmt-service
sh ./upload-menu-items.sh

Note: default restaurant id for testing: "11111111-1111-1111-11111111111111111".
```
### Explore by HAL Browser
```bash
http://localhost:8081/browser/index.html

port: 8081 can be changed for different services.

```
### Test work flow with PostMan
Create an order
```
POST localhost:8082/api/restaurants/11111111-1111-1111-11111111111111111/orders
{
    "restaurantId": "11111111-1111-1111-11111111111111111",
    "foodItems": [
        {
            "name": "menuItem 1",
            "price": 11,
            "quantity": 2
        },
        {
            "name": "menuItem 2",
            "price": 12,
            "quantity": 3
        }
    ],
    "userInfo": {
        "firstName": "first1",
        "lastName": "last1",
        "phone": "14081234567",
        "address": "123 stree1 ave, San Jose, CA 95123"
    }
}
Returns:
Returns:
{
    "id": "5903e81327b884525eb9a5be",
    ...
    "totalPrice": 58,
    ...
}

```
Post a payment for the order
```
POST localhost:8003/api/payments
{
    "amount": 58,
    "orderId": "5903e81327b884525eb9a5be",
    "creditCardInfo": {
        "firstName": "first 1",
        "lastName": "last 1",
        "expiredMonth": "02",
        "expiredYear": "2019",
        "securityCode": "231"
    }
}
```


# spring_boot_microservices_cloud
