#Project Choice: A market place for cars

##1. 
Currently, the project itself can't necessarily be run because there is no main file to run. However, for test cases you can use a java IDE to run the test cases using the IDE specific test case runner.

##2. 
**Rohan Kannan** - Completed DatabaseTestCase class. Worked on general formatting and submitted project on Vocareum workspace.

**Alistair Joseph** - Worked on Database Interface and Database class. Formatted both classes too.

**Lydia Schmucker** - Completed User class, Listing class, and Interfaces for both those classes. Formatted both classes too.

**Stephen Tushentsov** - Completed DatabaseTestCase class and ListingTestCase class. Worked on repo structure, general formatting, and readme.

##3.
**Database.java** - Class contains four methods total which read and write to the files *Listings.txt* and *Users.txt*. Additionally, it has two static fields which are the paths to both these files. Class implements Database Interface.

**Listing.java** - Class contains nine different fields for the Listing ID, Seller Username, Photo URL, Type of Car, Car color, Car mileage, number of accidents the car has been in, the price of the listing, and an indication if the car is manual. Class has a constructor initializing these nine fields to inputted variables, corresponding getters and setters for each field, and a toString method for file writing. Class implements Listing interface.

**User.java** - Class contains five different fields for the Username, User password, User balance, a list of listings made by the User, and the pickup address of the user. Class has a constructor initializing all fields to inputted variables except for the list of listings where it just gets initialized to an empty list. Class also has corresponding getters and setters for all fields, along with methods to add and remove listings and a method to update the balance based on if the User is a buyer or a seller. There's also a toString method for file writing and the class implements User interface.

