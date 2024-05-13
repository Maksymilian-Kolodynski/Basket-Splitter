# BasketSplitter

BasketSplitter is a Java library designed to optimally divide products in an online shopping basket into delivery groups. The goal of the library is to minimize the number of required deliveries by creating the largest possible delivery groups.

## Requirements

- Java Development Kit (JDK) version 17 or higher.
- Maven version 3.6.0 or higher for building the project.

## Installation and Running

1. Clone the project repository or download the source files.
2. Open a terminal or command prompt and navigate to the main project directory.
3. Execute the command `mvn clean package` to build the project and create an executable fat JAR.
4. Run the application using the generated JAR:

   ```bash
   java -jar target/BasketSplitter-1.0-SNAPSHOT.jar

## API Usage
To use the BasketSplitter library in your application, create an instance of the BasketSplitter class and invoke the split method, passing a list of product names.

## Tests
The project includes unit tests covering basic functionalities as well as edge cases. To run the tests, execute the command: mvn test

## Author
Maksymilian Kołodyński