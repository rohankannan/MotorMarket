# Project Choice: A marketplace for cars

## 1. 

Currently, the project itself can't necessarily be run because there is no main file to run. However, for test cases you can use a java IDE to run the test cases using the IDE specific test case runner. Note that to test **Client.java**, **Server.java** must be running.

## 2. 

**Rohan Kannan** - Completed DatabaseTestCase class. Worked on general formatting and submitted project on Vocareum workspace. Worked on client and server classes, completed server class.

**Alistair Joseph** - Worked on Database Interface and Database class. Formatted both classes too. Worked on client and server classes, completed client class.

**Lydia Schmucker** - Completed User class, Listing class, and Interfaces for both those classes. Formatted both classes too. Completed the ClientTestCase class and formatting. Worked on the readme.

**Stephen Tushentsov** - Completed DatabaseTestCase class and ListingTestCase class. Worked on repo structure, general formatting, and readme. Completed and formatted the ServerTestCase. Added client and server interfaces.

## 3.

**Database.java** - Class contains four methods total which read and write to the files *Listings.txt* and *Users.txt*. Additionally, it has two static fields which are the paths to both these files. Class implements Database Interface.

**Listing.java** - Class contains nine different fields for the Listing ID, Seller Username, Photo URL, Type of Car, Car color, Car mileage, number of accidents the car has been in, the price of the listing, and an indication if the car is manual. Class has a constructor initializing these nine fields to inputted variables, corresponding getters and setters for each field, and a toString method for file writing. Class implements Listing interface.

**User.java** - Class contains five different fields for the Username, User password, User balance, a list of listings made by the User, and the pickup address of the user. Class has a constructor initializing all fields to inputted variables except for the list of listings where it just gets initialized to an empty list. Class also has corresponding getters and setters for all fields, along with methods to add and remove listings and a method to update the balance based on if the User is a buyer or a seller. There's also a toString method for file writing and the class implements User interface.

**Server.java** - Class opens on port 4242 and runs database interactions 

**Client.java** - 
**Client.java** - Class contains a field for the Socket object called socket. Class has nine methods total, one of which is the main method which runs on port 4242. There is a method which is used to start the client and connect to the server, a method which checks which command a user chooses, a method which prints a menu for the user to choose from, two getter methods, one for listings and one for users, a method which adds a new user, a method which adds a new listing, and a method which exits the client.

**Server.java** - Class contains three fields for the Database object to interact with the database, a ServerSocket object to listen for connections, and a boolean variable to check if the server is or is not running. This class has a constructor which takes no parameters and initializes this Database. Class contains six methods including a main method which runs on port 4242. There is a method which starts the server and listens for connections, a method which connects the server to the client and works with their communication, a method that checks if the clients command is recieved, a method which stops the server, and a getter method for the field isActive. 

**AddListingPanel.java** -

**AncestorListenerAdapter.java** -

**HubPanel.java** -

**LoginPanel.java** -

**MainFrame.java** -

**NewClient.java** -

**RegistrationPanel.java** -
