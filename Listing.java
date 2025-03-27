// listing class
public class Listing implements ListingInterface {
  // fields
  private String sellerUsername;
  private String photoURL;
  private String carType;
  private String color;
  private int mileage;
  private int accidents;
  private double price;
  private boolean manual;

  // constructor
  public Listing(String sellerUsername, String photoURL, String carType, String color, int mileage, int accidents, double price, boolean manual) {
    this.sellerUsername = sellerUsername;
    this.photoURL = photoURL;
    this.carType = carType;
    this.color = color;
    this.mileage = mileage;
    this.accidents = accidents;
    this.price = price;
    this.manual = manual;
  }
  
  // getters
  

  // setters

  //other methods
}
