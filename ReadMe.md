# Miková Petra - Espatrip - your Spanish adventure

## Documentation for final version:
(the UML diagram and the GIF are also available in the Documentation/media repository.)

## Project objective
The desktop app EspaTrip allows a user to register as a customer who wants to go on a trip to Spain, or a Spanish local who offers a stay for people from all around the world. For now, there are three favorite Spanish destinations available - Madrid, Barcelona and Valencia. 

**As a customer** - you decide on a city and your preferences on a local, and after that you will be offered to choose from multiple restaurants and sights, depending on what you want to see and what food you want to try. After all this, you get a price offer and a summary of your trip , which you can reject or accept and send in the order. Then your order is pending and if a sufficient local accepts it, you are good to go!

**As a local** - within your initial sign up, you fill in the registration form created for locals and your information will be stored, and then in your profile you will see all the created trips from customer whose criteria you meet. You can either reject or accept the trip, and if you accept it it will move into your accepted trips part of the profile and you can start preparing for the adventrure!

## Project requirements
The project runs on Gradle. The Java source compatibility for this project is 18, so it is important that in your IDE you are able to run SDK either Java 18.0.2 or Java Amazon Corretto 18.0.2. Gradle should show you the option to download this SDK if your IDE is not recognizing it or you do not have it set up in the IDE.

![image](https://github.com/OOP-FIIT/oop-2023-stv-13-a-dakic-xmikova/assets/61084407/9f3860bb-6938-4ed6-87f0-39f8d254a541)

When the Gradle build is all set up, you can either run it with gradle run configuration or directly by running the Boot class which is located in the org.example.app package. To run the JAR file, please build it with fatJar configuration and then it is possible to run it even outside the IDE.

![image](https://github.com/OOP-FIIT/oop-2023-stv-13-a-dakic-xmikova/assets/61084407/e25022cf-b68a-4419-8341-9c74d757642e)

## UML Diagram
![image](https://github.com/OOP-FIIT/oop-2023-stv-13-a-dakic-xmikova/assets/61084407/79254b7b-2f33-4288-96dc-47ee1a5d6d00)

!The code snippets for the upcoming parts are available in the PDF version of documentation!
## Main criteria fullfilment
**1. inheritance**
 
First case of inheritance is by classes Customer and Local from super class User, as they use the same functionalities, but have some additional attributes each. Second case of inheritance is by classes BarcelonaPlacesController, MadridPlacesController, and ValenciaPlacesController, which inherit from PlacesController. They all use the same methods, but have different attributes which represent places in each location.

**2. polymorphism**

Polymorphism is used in both exceptions classes – RegistrationAndLoginExceptions and OrderExceptions. The default message is „Error“ and then every nested class of those which represents a specific excpetion overrides it and displays its own message based on the specific error.

**3. aggregation**

Aggregation is used in the Customer class, where we have an attribute preferences, whose type is CustomerPreferences (another class).

**4. encapsulation**

All classes in the org.example.objectclasses use encapsulation – so have private attributes wrapped into public getters and setters. Besides these, the classes UserManager and OrderManager (Singleton classes) use encapsulation as well (org.example.tools).

**5. code organization (packages)**

The main package (also a main module) is the org.example. This package is then efficiently organized into 5 packages. The GUI is in resources directory in FXML folder. The app package includes the booting classes. The controllers package includes all the controllers that handle user interaction with GUI. The exceptions package includes all the custom exceptions classes. The package objectclasses includes all the objects used in app – User, Order, Place. Lastly, the tools include other essential miscellaneous classes for handling the functionality like managers using Singleton or class for deserializing JSON database.  The FXML in resources includes all the .fxml files for functional GUI of the app.

## Secondary criteria
**1. design patterns**

In my implementation, I use the **Singleton design pattern** in classes UserManager -  uses the Singleton design pattern to ensure that only one instance of the class can exist at any given time, and provides access to a single instance of the currentUser object of type T which extends the User class, and OrderManager - uses the Singleton design pattern to ensure that there is only one instance of the class at any given time, which provides the ability to work with one order object throughout the whole order creation even in different controllers.

Besides this design pattern, my implementation also uses the **MVC (Model-View-Controller) design pattern**: 
Model: The objectclasses, tools, exceptions and app packages that contain the application 	logic and represent the data and logic of my application.
View: The FXML directory in resources folder with user interface (UI) files. These files 	describe the structure and appearance of the UI and how the UI interacts with the user.
Controller: The classes in the controllers package handle user input, manage the state of 	the UI, and communicate with the model to update the data and logic.

**2. own exceptions**

My implementation also includes handling exceptional states using own exceptions. For this, I have implemented two classes for handling exceptions during registration and login, and during the order creation – RegistrationAndLoginExceptions and OrderExceptions. These inherit from the builtin Java Exception class and have several subclasses for specific exceptions which are then thrown in the application process.

**3. GUI separated from application logic**

My GUI is fully separated from the logic as I already mentioned. All the GUI .fxml files are in the resources directory and the interaction from user is handled via controllers using the FXML functions with parameter ActionEvent. The controllers are in the controllers package of my app.

**4. using generics in own classes**

The generic type parameter T is declared in the class signature as UserManager<T extends User>, and it is used as the type of the currentUser field, which is declared as private T currentUser = null. The generic type T must extend the User class, which means that any object of the UserManager class can work with a specific subtype of User.
 
**5. explicit use of RTTI**
 
In the PlacesController class in method checkBoxesSetSelectedPlaces I use RTTI (instanceof) to determine the type of an object and retrieve chosen places by user in the GUI (lines 73 and 87)
 
**6. using nested classes and interfaces**
 
In my implementation, I use nested classes in both the exceptions classes – RegistrationAndLoginExceptions and Order exceptions. Both of these have several nested static classes which extend the main one and override displaying the message.

I also use one interface in my implementation, called AfterLoginScreen, which declares basic methods that apply to both customer and local after they login into account. The classes OrderController and LocalOrderController implement this interface.
 
**7. using lambda expressions or method references**
 
I use lambda expressions in method ordersForReview in LocalOrderController class (lines 100 and 104).
 
**8. using serialization**

In the User class, I use serialization to read and write user data to a binary file called "users.dat". In the getUsers() method, I create an ObjectInputStream that reads the data from the "users.dat" file and deserializes it into an ArrayList of User objects. Then I return this list of users. In the setUsers() method, I take an ArrayList of User objects as an argument and create an ObjectOutputStream. Finally, I serialize the users ArrayList and write it to the "users.dat" file.
 
## Functionality of the app
The functionality in words is provided in PDF version of documentation.
There is the gif representing functionality visually:

 ![ezgif com-video-to-gif (1) (1)](https://github.com/OOP-FIIT/oop-2023-stv-13-a-dakic-xmikova/assets/61084407/9bcdae3a-8291-4e65-bfb6-c62a2bfd1d6f)

## Main GitHub versions
**Commits on Mar 20, 2023**
•	included gradle into project
•	javafx plugin working

**Commits on Mar 23, 2023**
•	essential classes added
•	simple GUI

**Commits on Apr 3, 2023**
•	working login and registration with functional GUI
•	exceptions for login and registration

**Commits on Apr 11, 2023**
•	Working Program Version
•	order creation process, setting the preferences on Local
•	redirection to page with places in chosen city, and provided description of every place
•	redirection to trip summary page (was a WIP)

**Commits on Apr 27, 2023**
•	SQLite for orders added
•	modified build.gradle to be fully functional
•	module info added

**Commits on May 2, 2023**
•	functional SQLite communication and customer being able to accept or reject the order and thus change the status in the database

**Commits on May 8, 2023**
•	interaction with order from local’s side functional
•	log out and go back to previous page buttons
•	GUI finalised
•	division of code into packages
 
**Commits on May 13, 2023**
•	final commit
•	fully completed application
•	documentation + JavaDoc added into Documentation repo
•	ReadMe finalised


## Conclusion
A travel app, as it was stated in the general topic, covers the connection between people who want to travel to three locations in Spain and people from Spain who offer stay at their home.

It is provided with authentification of the user (login) and offers possibility for customer to create a trip exactly how they want, so that their preferences are fullfiled. Then it is up to review for local and if they accept it as well, the adventure can begin! 

For storing the information, I used serialization into .dat file for login information, JSON database for beforehand stored places for each location, and SQLite database for storing orders and updating their status.

My assignment fulfilled the main criteria of OOP which were inheritance, aggregation, polymorphism, encapsulation, and division into packages. Besides that, it fullfiled many of the secondary criteria, which have been described earlier in this documentation. Thus, I would state that the app fulfills the assignment and subject requirements.
