# Spiny App (a community information system project)

This repository was created on February 26, 2024 for Bogazici University Software Engineering SWE 573 course. 

* Click for application demonstration video = https://drive.google.com/file/d/10A5jiklVpAXi-0J4nwjyjE-vkw51_n1q/view?usp=sharing

  - Project name deployment URL: https://spinyapp.xyz
  - Git repository URL: https://github.com/AliSerGok/SWE573-AliserGok.git
  - Git tag version URL: https://github.com/AliSerGok/SWE573-AliserGok/releases/tag/v0.9
#

## 2. Installation Steps:
* 2.1 Clone the repository:
git clone <https://github.com/AliSerGok/SWE573-AliserGok.git>
* 2.2 Start the MySQL database:
To create the "spiny-database" schema and tables in our database, open and run the
sql file named "01-spiny-directory" in the project file.
* 2.3 Connect Database to Java Spring Boot project:<br>
  if you run application local please change:<br>
   - application.properties:<br>
  
     - spring.datasource.url=jdbc:mysql://{DATABASE_URL}/spiny-database
     - spring.datasource.username={USERNAME_NAME}
     - spring.datasource.password={PASSWORD}

* 2.4 Access the application in browser: http://localhost:8080/showMyLoginPage
# 

## 3. How to log in system
* Usernames to log into the system:
mary, susan, john, michael, emma
* Password:
‘fun123’ (Bcrypt encryption)
#

## 4. Some app features
* login
* registration
* community creation/update
* user profile creation/update
* community follow
* basic search
* advanced search
* creating template
* creating a post


