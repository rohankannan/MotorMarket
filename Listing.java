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
}
