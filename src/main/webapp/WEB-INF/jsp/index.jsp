<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Restaurant Voting (by Ivan Kropachev)</title>
</head>
<body>
<h3>Project "Restaurant Voting" (by Ivan Kropachev)</h3>
<hr>
<br>Admin requests:
<br>
<br>Dishes:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/dishes -u admin@gmail.com:admin - get all dishes
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/dishes/100016 -u admin@gmail.com:admin - get dish with id 100016
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/dishes/100016 -u admin@gmail.com:admin - delete dish with id 100016
<br>curl -X POST --data "{\"name\":\"New_Dish\",\"price\":250}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/dishes/100008 -u admin@gmail.com:admin - add dish for menu with id 100008
<br>curl -X PUT --data "{\"id\":100017,\"name\":\"Updated_Dish\",\"price\":200}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/dishes/100009 -u admin@gmail.com:admin - update dish for menu with id 100009
<br>
<br>Restaurants:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/restaurants -u admin@gmail.com:admin - get all restaurants
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/restaurants/100001 -u admin@gmail.com:admin - get restaurant with id 100001
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/restaurants/100003 -u admin@gmail.com:admin - delete restaurant with id 100003
<br>curl -X POST --data "{\"name\":\"New_Restaurant\"}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/restaurants -u admin@gmail.com:admin - add new restaurant
<br>curl -X PUT --data "{\"name\":\"Updated_Restaurant\"}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/restaurants/100002 -u admin@gmail.com:admin - update restaurant with id 100002
<br>
<br>Users:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/users -u admin@gmail.com:admin - get all users
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/users/100005 -u admin@gmail.com:admin - get user with id 100005
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/users/100006 -u admin@gmail.com:admin - delete user with id 100006
<br>curl -X POST --data "{\"name\":\"New_User\",\"email\":\"newuser@gmail.com\",\"password\":\"new_pass\",\"enabled\":true,\"registered\":\"2021-12-01T15:02:00.000+00:00\",\"roles\":[\"USER\"]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/users -u admin@gmail.com:admin - add new user
<br>curl -X PUT --data "{\"name\":\"Updated_User\",\"email\":\"updateduser@gmail.com\",\"password\":\"updateduser_pass\",\"enabled\":true,\"registered\":\"2021-12-01T15:02:00.000+00:00\",\"roles\":[\"USER\"]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/users/100007 -u admin@gmail.com:admin - update user with id 100007
<br>
<br>Votes:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/votes -u admin@gmail.com:admin - get all votes
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/votes/100027 -u admin@gmail.com:admin - get vote with id 100027
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/votes/100023 -u admin@gmail.com:admin - delete vote with id 100023
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/votes/100002 -u admin@gmail.com:admin - add/update vote for restaurant with id 100002
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/votes/100001 -u admin@gmail.com:admin - update previous vote
<br>
<br>Menus:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus -u admin@gmail.com:admin - get all menus
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus/date/2021-12-03 -u admin@gmail.com:admin - get menus by date
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus/100001/?date=2021-12-03 -u admin@gmail.com:admin - get menu for restaurant with id 100001 by date
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/menus/100001/delete/100008 -u admin@gmail.com:admin - delete menu with id 100008 for restaurant with id 100001
<br>curl -X POST --data "{\"date\":\"2021-12-01\",\"dishes\":[{\"name\":\"New_Burger\",\"price\":50}, {\"name\":\"New_Chicken_Burger\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/100001/menu -u admin@gmail.com:admin - add menu with date 2021-12-01 for restaurant with id 100001
<br>curl -X PUT --data "{\"date\":\"2021-12-03\",\"dishes\":[{\"name\":\"New_Put_Burger\",\"price\":50}, {\"name\":\"New_Put_Chicken_Burger\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/100002/menu/100009 -u admin@gmail.com:admin - update menu with id 100009 for restaurant with id 100002 for date 21-12-03
<br>curl -X POST --data "{\"dishes\":[{\"name\":\"New_Burger\",\"price\":50}, {\"name\":\"New_Chicken_Burger\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/100000/menu -u admin@gmail.com:admin - add menu for current date for restaurant with id 100000
<br>Admin also can refresh menu for current date for restaurant, if it's present:
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish\",\"price\":50}, {\"name\":\"New_Put_Dish_2\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/{restaurant_id}/menu/{menu_id} -u admin@gmail.com:admin
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish\",\"price\":50}, {\"name\":\"New_Put_Dish_2\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/100001/menu/100011 -u admin@gmail.com:admin
<br>Or without menu id:
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish\",\"price\":50}, {\"name\":\"New_Put_Dish_2\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/{restaurant_id}/menu/ -u admin@gmail.com:admin
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish\",\"price\":50}, {\"name\":\"New_Put_Dish_2\",\"price\":60}]}" --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/100001/menu/ -u admin@gmail.com:admin
<br>
<br>
<br>User requests:
<br>
<br>Menus:
<br>curl -s http://localhost:8080/restaurant_voting/rest/user/menus -u user@gmail.com:user  - get all menus (for current date)
<br>
<br>Votes:
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/user/votes/{restaurantId} -u user@gmail.com:user - add/update vote
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/user/votes/{restaurantId}  -u user@gmail.com:user - update previous vote
<br>Examples:
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/user/votes/100002 -u user@gmail.com:user - add/update vote for restaurant with id 100002
<br>curl -X POST --header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/user/votes/100001  -u user@gmail.com:user - update previous vote
</body>
</html>