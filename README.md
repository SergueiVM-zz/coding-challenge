# Interview Challenge (Poker Planning Application)

**Author**: Carlos García Villard (cgarcia.villard@gmail.com)

**Technological environment:**
- Spring Boot 2.6.3
- Spring Security 5.6.1
- Lombok 1.18.22
- Mapstruct 1.4.2.Final
- Mockito 3.3.3
- Gradle 7.3.3

As you can see, the application uses **Lombok** and **Mapstruct**. It's possible that your IDE requires installing a plugin or configuring an annotation processor in order to compile it. In this case, the simplest thing would be to avoid any configuration and directly run the application jar as follows:
1. Go to the root directory of the application.
2. gradlew clean
3. gradlew build
4. java -jar build/libs/poker-planning-0.0.1-SNAPSHOT.jar

The API is secured with **Basic HTTP Authentication**. You can access with the users "**user**" or "**admin**". In both cases the password is "**liberty**". Only the "admin" user has permissions to delete user stories.

In the solution provided, some **unit and integration tests** have been implemented as an example. In a real application all functionalities should be tested.