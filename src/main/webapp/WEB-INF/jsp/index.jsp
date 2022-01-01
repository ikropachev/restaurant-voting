<html>
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
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/dishes/100016 -u admin@gmail.com:admin - get dish with id
100016
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/dishes/100016 -u admin@gmail.com:admin - delete
dish with id 100016
<br>curl -X POST --data "{\"name\":\"New_Dish\",\"price\":250}" --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/dishes/?menu-id=100008 -u admin@gmail.com:admin - add dish for menu
with id 100008
<br>
<br>Restaurants:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/restaurants -u admin@gmail.com:admin - get all
restaurants
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/restaurants/100001 -u admin@gmail.com:admin - get
restaurant with id 100001
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/restaurants/100003 -u admin@gmail.com:admin -
delete restaurant with id 100003
<br>curl -X POST --data "{\"name\":\"New_Restaurant\"}" --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/restaurants -u admin@gmail.com:admin - add new restaurant
<br>curl -X PUT --data "{\"name\":\"Updated_Restaurant\"}" --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/restaurants/100002 -u admin@gmail.com:admin - update restaurant with
id 100002
<br>
<br>Users:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/users -u admin@gmail.com:admin - get all users
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/users/100005 -u admin@gmail.com:admin - get user with id
100005
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/users/by-email?email=user@gmail.com -u
admin@gmail.com:admin - get user by e-mail
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/users/100006 -u admin@gmail.com:admin - delete
user with id 100006
<br>curl -X POST --data
"{\"name\":\"New_User\",\"email\":\"newuser@gmail.com\",\"password\":\"new_pass\",\"enabled\":true,\"registered\":\"2021-12-01T15:02:00.000+00:00\",\"roles\":[\"USER\"]}"
--header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/users -u
admin@gmail.com:admin - add new user
<br>curl -X PUT --data
"{\"name\":\"Updated_User\",\"email\":\"updateduser@gmail.com\",\"password\":\"updateduser_pass\",\"enabled\":true,\"registered\":\"2021-12-01T15:02:00.000+00:00\",\"roles\":[\"USER\"]}"
--header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/users/100007 -u
admin@gmail.com:admin - update user with id 100007
<br>
<br>Votes:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/votes -u admin@gmail.com:admin - get all votes
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/votes/100027 -u admin@gmail.com:admin - get vote with id
100027
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/votes/100023 -u admin@gmail.com:admin - delete
vote with id 100023
<br>curl -X POST --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/votes/?restaurant-id=100002 -u admin@gmail.com:admin - to vote for
restaurant with id 100002
<br>curl -X POST --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/votes/?restaurant-id=100001 -u admin@gmail.com:admin - update
previous vote
<br>
<br>Menus:
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus -u admin@gmail.com:admin - get all menus
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus/by-date -u admin@gmail.com:admin - get all menus
for today
<br>curl -s http://localhost:8080/restaurant_voting/rest/admin/menus/by-date?date=2021-12-03 -u admin@gmail.com:admin -
get all menus by date
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/admin/menus/restaurant/100001/?menu-id=100008 -u
admin@gmail.com:admin - delete menu with id 100008 for restaurant with id 100001
<br>curl -X POST --data "{\"dishes\":[{\"name\":\"New_Dish1\",\"price\":50}, {\"name\":\"New_Dish2\",\"price\":60}]}"
--header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/admin/menus/restaurant/100000 -u
admin@gmail.com:admin - add menu for current date for restaurant with id 100000
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish\",\"price\":50},
{\"name\":\"New_Put_Dish_2\",\"price\":60}]}" --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/menus/restaurant/100002/date/ -u admin@gmail.com:admin - update menu
for restaurant 100002 for today
<br>curl -X PUT --data "{\"dishes\":[{\"name\":\"New_Put_Dish_3\",\"price\":50},
{\"name\":\"New_Put_Dish_4\",\"price\":60}]}" --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/admin/menus/restaurant/100002/date/?date=2021-12-03 -u
admin@gmail.com:admin - update menu for restaurant 100002 by date 2021-12-03
<br>
<br>
<br>User requests:
<br>
<br>Menus:
<br>curl -s http://localhost:8080/restaurant_voting/rest/user/menus -u user@gmail.com:user - get all menus (for current
date)
<br>
<br>Votes:
<br>curl -X POST --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/user/votes?restaurant-id=100002 -u user@gmail.com:user - add vote for
restaurant with id 100002
<br>curl -X POST --header "Content-Type:application/json"
http://localhost:8080/restaurant_voting/rest/user/votes?restaurant-id=100001 -u user@gmail.com:user - update previous
vote
<br>
<br>
<br>Profile controller:
<br>curl -s http://localhost:8080/restaurant_voting/rest/profile -u user@gmail.com:user - get current user info
<br>curl -X POST --data "{\"name\":\"New_User\",\"email\":\"newuser@gmail.com\",\"password\":\"new_pass\"}" --header
"Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/profile - registration of new user
<br>curl -X PUT --data "{\"name\":\"Updated_Name\",\"email\":\"updated@gmail.com\",\"password\":\"updated_password\"}"
--header "Content-Type:application/json" http://localhost:8080/restaurant_voting/rest/profile -u user@gmail.com:user -
update current user
<br>curl -s -X DELETE http://localhost:8080/restaurant_voting/rest/profile -u third@gmail.com:third_pass - delete
current user
</body>
</html>