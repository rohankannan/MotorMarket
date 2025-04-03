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

  // getters
  /** the getter/accessor methods for User objects
  * returns the value of each attribute of a User object respectively
  */
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


  /** setter methods to update values of respective fields
  *  one parameter each, of the new value to set each field as
  */
  
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // update arraylist by adding a new listing 
    public void addListing(Listing newListing) {

    }
    
    // remove a listing from the arraylist of listings
    public void removeListing(Listing oldListing) { 

    }
  
    // updates the balance based on a purchase
    // paramaters are the price of the listing and if it is buying or selling
    // true for a buyer, false for a seller
    public void updateBalance(double price, boolean isBuyer) {

    }

    // toString
    @Override
    public String toString() {
        return username + "," + password + "," + balance + "," + listings + "," + address;
    }

}
