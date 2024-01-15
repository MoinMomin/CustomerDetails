# CustomerDetails
this repository consist 2 folders
1> api
2>frontend
api= for java spring boot backend project
in this all api required for this project 
jwt authentication has separate service in realtime project but in this project authentication part are evolve in same service

frontend =for html css and javascript part
------------------------------------------------------------------------------------------------------------
need to install mysql database locally
or
 use amazon rds
 and update all things regarding to d/b i.e. database name,url,username,pass
-----------------------------------------------------------------------------------------------------------------------------
this project need java 11
                  gradle 7.5.1
use IDE intellij/eclipse to check code and run 
project can run directly on jdk 11 or build then run simply like java program 
project run on 8080 port which is default for apache tomcat server
if 8080 not available change the  server.port application.property file
-----------------------------------------------------------------------------------------------------------------------------------
POST  localhost:8080/user/signup
body  {
"name": "moin",
"userName": "momin",
"password": "secure_password",
"role": "USER"
}

use this api for create user after successfully start project name,username,password as per yours
--------------------------------------------------------------------------------------------------------------------------------------
Moin Momin
+91 7798480907
-----------------------------------------------------------------------------------------------------------
