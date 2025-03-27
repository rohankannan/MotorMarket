
public Interface UserInterface {
  
  // getters
  String getUsername();
  String getPassword();
  String getAddress();
  double getBalance();
  ArrayList<Listing> getListings();
  
  // setters
  void setUsername(String username);
  void setPassword(String password);
  void setAddress(String address);
  void setBalance(double balance);

  // update array by adding a new listing 

}
}
