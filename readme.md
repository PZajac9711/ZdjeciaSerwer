# Share Images

This project allow you to share images with other people.

#What this server provides

This server provides many features like:
* Creating user account and storage them in h2 database
* Login option
* Generate JWT for logged users 
* Allow you to send images on server and storage it in public folder
* Provides private end points with are achievable only when you got Token
* Server automatically run you need only java

#End points

* /login - used for generate JWT, status:200 if data is correct, status:401 in other situation
* /register - used for create users, status:201 if created, status:400 in other situation
* /random - used for getting random image from server, required header with JWT 
* /score - used to increase/reduce score for specific image, required header with JWT
* /page - used for getting pages, page = 0 gets five first images from DB, page = 1 gets 5-10....
* /uploadFile - allow you to send image from your pc on server. 

#Instalation

Project should run by himself you need only java 11 and maven


#Important
* Server automatically create and clear database after every run, if you wanna change it you need to modify application.properties
