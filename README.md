 # cinema-project(**int progress**)
 
 ####MovieController
 Add movie - POST: /movies
 Get all movies - GET: /movies
 ####MovieSessionController
 Add moviesession - POST: /moviesessions
 Get all movie sessions - GET: /moviesessions/available?movieId=1&date=29.02.2020
 ####CinemaHallController
 Add cinema hall - POST: /cinemahalls
 Get all cinema halls - GET: /cinemahalls
#### OrderController
 Complete order - POST: /orders/complete
 Get orders history for user - GET: /orders?userId
 #####UserController
 Get user by email - GET: /users/byemail?email
#### ShoppingCartController
 Add movie session - POST: /shoppingcarts/addmoviesession?userId <— will remove this param in the future
 Get by user - GET: /shoppingcarts/byuser?userId
 ####AuthenticationController
 Login - POST: /login
 Register - POST: /register
