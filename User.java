// user class

public class User implements UserInterface {
  // attributes
  private String username;         // stores the unique username of this user
  private String password;         // stores the password for this user
  private double balance;          // stores this user's current balance
  private Listing[] listings;      // stores all the current listings of this user
  private String address;          // stores the user's address for pickup
  
  // constructor
  public User(String username, String password, double balance, Listing[] listings, String address) {
    this.username = username;
    this.password = password;
    this.balance = balance;
    this.listings = listings;
    this.address = address;
  }

  // getters

  // setters

}
