# BlogsMVCJDBC
Blogs web application is a Spring MVC web app where a blogger can add a blog and see recent blogs.

# Description
`home.jsp` and `blogs.jsp` are **views** that welcome users, display recent blogs and provide a form to add a new blog.

`BlogsController` and `HomeController` are **controllers** that handle requests and process blog form. `HomeController`'s `home` method returns view name. `BlogsController`'s `blogs` method places recent blogs on the **model** and returns view name. The view names are resolved by `InternalResourceViewResolver`. When views are JSP, the model data are placed to the request as request attributes and rendered in JSP page. `BlogsController`'s `saveBlog` method saves a blog to the database.

`JdbcBlogRepository` and `BlogRepository` are **Data Access Object (DAO)** that fetch a list of blogs and save a blog via JDBC templates. Spring's JDBC framework abstracts away the boilerplate data access code with template classes, i.e. `JdbcTemplate`.
`JdbcTemplate` provides simple access to a database via JDBC and indexed-parameter queries.

The blogs database contains `blog` table with columns `id`, `message`, and `created_at`.

# Getting Started
## Prerequisites
- JDK 1.8
- Spring 4.3.10
- H2 Database 1.4.196
- JSTL 1.2
- Apache Commons Lang 3.1

## Installing
- Create a local respository
```
git clone git@github.com:lnpeng/BlogsMVCJDBC.git
cd BlogsMVCJDBC
```
- Start Tomcat server
  - Switch to **Java EE perspective**
  - Click on **Servers** tab
  - Click on **No servers are available. Click this link to create a new server...**
  - Click **Tomcat Server** and **Next**
  - Select **Tomcat Installation Directory** and click **Finish**
  - You should see **Tomcat v9.0 Server at localhost [Stopped, Republish]** under **Servers** tab. Double click on it verify HTTP ports information. By default HTTP port is 8080.
  - Right click on **Server** and click **Start**
  
- Run the project on Tomcat server
  
## Running the tests
### Test the Blogs web application in Web Browser
- Launch the application from a web browser.
- Add blogs
![Blogs](https://github.com/lnpeng/BlogsMVCJDBC/blob/master/Screen%20Shot%202018-12-23%20at%2010.07.40%20AM.png)

## Deployment
Deploy the project on [Tomcat](http://tomcat.apache.org/) server in Eclipse IDE.

# Build
- [Eclipse](https://www.eclipse.org/ide/) Dynamic Web Project
