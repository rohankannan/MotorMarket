// user class
import java.util.ArrayList;

public class User implements UserInterface {
  // attributes
  private String username;         // stores the unique username of this user
  private String password;         // stores the password for this user
  private double balance;          // stores this user's current balance
  private ArrayList listings;      // stores all the current listings of this user
  private String address;          // stores the user's address for pickup
  
  // constructor
  public User(String username, String password, double balance, ArrayList listings, String address) {
    this.username = username;
    this.password = password;
    this.balance = balance;
    this.listings = listings;
    this.address = address;
  }

  // getters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Listing[] getListings() {
        return listings;
    }

    public void setListings(Listing[] listings) {
        this.listings = listings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // setters

}
