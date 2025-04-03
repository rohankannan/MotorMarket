/** Description of Class 
 *
 * @author (team names here)
 * @version April 3, 2025
 */
public class Listing implements ListingInterface {
  // fields
    private String listingID;        // the identification number for each listing
    private String sellerUsername;   // the username of the seller for this listing
    private String photoURL;         // the URL for a display photo
    private String carType;          // the type of car in this listing (make/model)
    private String color;            // the color of the car in this listing
    private int mileage;             // the current mileage for the car in this listing
    private int accidents;           // how many accidents this car has been in
    private double price;            // the current price of this listing
    private boolean manual;          // whether or not the car being sold is manual

  // constructor
    public Listing(String sellerUsername, String photoURL, String carType, String color, int mileage, int accidents, double price, boolean manual, String listingID) {
        this.sellerUsername = sellerUsername;
        this.photoURL = photoURL;
        this.carType = carType;
        this.color = color;
        this.mileage = mileage;
        this.accidents = accidents;
        this.price = price;
        this.manual = manual;
        this.listingID = listingID;
  }
  
  // getters
  
    public String getListingID() {
      return listingID;
    }
    public String getPhotoURL() {
      return photoURL;
    }

    public String getCarType() {
      return carType;
    }

    public String getColor() {
      return color;
    }

    public String getSeller() { 
      return sellerUsername;
    }

    public int getMileage() {
      return mileage;
    }

    public int getAccidents() {
      return accidents;
    }

    public double getPrice() {
      return price;
    }

    public boolean isManual() {
      return manual;
    }
  
  // setters
    public void setListingID(String id) {
      listingID = id;
    }
    public void setPhotoURL(String url) {
      photoURL = url;
    }

    public void setCarType(String carType) {
      this.carType = carType;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public void setSeller(String sellerUsername) {
      this.sellerUsername = sellerUsername;
    }

    public void setMileage(int mileage) {
      this.mileage = mileage;
    }

    public void setAccidents(int accidents) {
      this.accidents = accidents;
    }

    public void setPrice(double price) {
      this.price = price;
    }
    
    public void setManual(boolean manual) {
      this.manual = manual;
    }

  //other methods
  @Override
    public String toString() {
        return sellerUsername + "," + photoURL + "," + carType + "," + color + ","
            + mileage + "," + accidents + "," + price + "," + manual;
    }
}
