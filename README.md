# Implementing-Design-Patterns-in-a-Console-Based-Library-Management-System
# Project Overview

## 1. Overview (Design Patterns)

### 1.1 Project Background and Objectives
This project aims to develop a console-based Library Management System (LMS) and apply various design patterns to enhance the system's flexibility, scalability, and maintainability.

### 1.2 Importance and Application of Design Patterns
Design patterns are effective solutions to common software design problems, promoting code reusability and structured development. They are essential skills and tools in software engineering.

### 1.3 Overview of Creational, Structural, and Behavioral Design Patterns
The project will utilize the following main types of design patterns:
- Creational Design Patterns: Singleton, Builder
- Structural Design Patterns: Decorator
- Behavioral Design Patterns: Observer

These design patterns will address specific design challenges during system development, improving overall design quality and maintainability.

## 2. Design Patterns

### 2.1 Singleton Pattern

#### Manifestation of the Singleton Pattern:
In this code snippet, the Singleton pattern is embodied in the implementation of the `DatabaseConnection` class.
1. **Private Constructor**: The constructor is made private, preventing external instantiation of objects.
2. **Static getInstance() Method**: The singleton instance is accessed through the static method `getInstance()`. Within this method, it first checks if `instance` is null. If it is, a new `DatabaseConnection` object is instantiated; otherwise, the existing instance is returned.

**Advantages:**
Thus, the `DatabaseConnection` class ensures that there is only one unique instance of the database connection throughout the entire application, conforming to the implementation requirements of the Singleton pattern.

### 2.2 Observer Pattern

#### Manifestation of the Observer Pattern:
1. **Subject and Observer Interfaces**: The code defines the `Subject` interface and the `Observer` interface, which are core components of the Observer Pattern. The `Subject` interface defines methods for registering, removing, and notifying observers, while the `Observer` interface defines the update method for observers.
2. **Notification of Observers**: When the availability of a book changes, the `Book` class invokes the `notifyObservers` method to notify all registered observers.
3. **Observer Update**: Observers implement the `update` method defined in the `Observer` interface to receive and process notifications. In this code, observers update the availability status of the book upon receiving a notification.

**Advantages:**
The design and implementation of this code adhere to the principles and requirements of the Observer Pattern. By decoupling observers from the observed subject, it allows for independent changes and extensions, thereby enhancing code readability and maintainability.

### 2.3 Builder Pattern

#### Manifestation of the Builder Pattern:
1. **Builder Class**: The `BookBuilder` class acts as the builder, responsible for constructing different parts of the `Book` object.
2. **Building the Object**: The `build` method is used to construct the `Book` object. It passes the previously set properties to the constructor of the `Book` class, creating and returning a complete `Book` object.

**Advantages:**
The main purpose of the builder pattern is to separate the process of building a complex object from its representation, allowing the same building process to create different representations. By employing the Builder pattern, object creation becomes more flexible, allowing properties to be set in a more readable and maintainable manner. Additionally, it facilitates easy extension of the object construction process.

### 2.4 Decorator Pattern

#### Manifestation of the Decorator Pattern:
1. **Base Component**: The `Book` class serves as the base component to be decorated. It represents the basic information and status of a book.
2. **Constructor**: The constructor of the `BookDecorator` class takes a `Book` object as a parameter and stores it in a private variable. This allows `BookDecorator` to manipulate the base component by calling methods of the `Book` object.
3. **Function Extension**: The `BookDecorator` class provides additional methods to extend the functionality of the base component, such as setting overdue status and retrieving overdue fines.
4. **Display Information**: The `displayInfo()` method is used to display basic information about the book, along with additional information based on whether it is overdue and the amount of overdue fines. This approach allows for dynamically extending the functionality and behavior of the base component without modifying it directly.

**Advantages:**
The decorator pattern helps us extend classes without wanting to add many subclasses, providing additional functionality while maintaining the integrity of the class method signature.

## 3. Conclusion
The design patterns implemented in the Library Management System (LMS) significantly enhance its flexibility, scalability, and maintainability. The Singleton pattern ensures a single instance of the `DatabaseConnection` class, optimizing resource usage and system performance, although excessive coupling should be avoided. The Observer pattern facilitates real-time updates between the `Book` class and its observers, promoting modularity and extensibility, thereby enhancing code maintainability and scalability. Additionally, the Builder pattern simplifies `Book` object construction, while the Decorator pattern dynamically extends its functionality, both contributing to code flexibility and readability. Collectively, these design patterns have improved the system's quality and robustness. Continuous evaluation and refinement are necessary to balance complexity and maintainability.
