/** Description of Class 
 *
 * @author (team names here)
 * @version April 3, 2025
 */
import java.util.ArrayList;

public class User implements UserInterface {
  // attributes
  private String username;              // stores the unique username of this user
  private String password;              // stores the password for this user
  private double balance;               // stores this user's current balance
  private ArrayList<Listing> listings;  // stores all the current listings of this user
  private String address;               // stores the user's address for pickup
  
  /** constructor for new User objects
  * 4 parameters for each of the 4 attributes of a User object
  * in format: String, String, double, String
  * values: username, password, balance of money, address to meet
  */
  public User(String username, String password, double balance, String address) {
    this.username = username;
    this.password = password;
    this.balance = balance;
    this.address = address;
    listings = null;
  }

  // getters / setters

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

    public ArrayList<Listing> getListings() {
        return listings;
    }

    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    // toString
    @Override
    public String toString() {
        return username + "," + password + "," + balance + "," + listings + "," + address;
    }

}
