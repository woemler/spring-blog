Spring-Blog
===========

A generic blog application built using Spring Boot and MongoDB.  A good example application for illustrating Spring Framework features, such as:

- Spring MVC and JSP
- Spring Data with MongoDB repositories.
- Spring Security CSRF and CORS protections.
- Simple content management and media sharing tools.
- Testing and deployment with Spring Boot.

### Requirements

- JDK 7+
- Maven 2+
- MongoDB 2+ (embedded database provided for testing)

### Quick Start
- Clone this repository.
- Make the appropriate changes to the properties files in `src/main/resources`.
- Build the project and run tests by running: 
   - `mvn clean install`
- Run the application in development mode with an embedded database by running:
   - `mvn spring-boot:run` or `java -jar target/spring-blog.jar` 
- Run the application in production mode with a permanent database by running:
   - `java -jar target/spring-blog.jar --spring.profiles.active=prd`
   
### License

This project is licensed under the terms of the MIT License.

>The MIT License (MIT)
>
>Copyright (c) 2017 William Oemler
>
>Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

>The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
