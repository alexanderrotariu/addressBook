//Created by: Alexander Rotariu
//IECE213 - Data Structures

public class Person
{
    //Instance varables
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String state;
    private int zip;
    private String phoneNumber;
    
    //Default constructor
    public Person()
    {
        this.firstName = null;
        this.lastName = null;
        this.streetAddress = null;
        this.state = null;
        this.zip = 0;
        this.phoneNumber = null;
    }
    
    //Parameterized constructor
    public Person(String firstName, String lastName, String streetAddress, String state, int zip, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }
    
    //To string method to specify how I want a person object to be printed.
    public String toString()
    {
        return ("-------------------------------------------------------"+"\n"+firstName+ " " + lastName + "\n"
        + streetAddress +"\n"+ state + ", "+ zip+ "\n"
        + phoneNumber + "\n"
        + "-------------------------------------------------------");
    }
    
    //Accessor methods from lines 43 - 71
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getStreetAddress()
    {
        return streetAddress;
    }
    
    public String getState()
    {
        return state;
    }
    
    public int getZip()
    {
        return zip;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    
    //Mutator methods from lines 75 - 103
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }
    
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    public void setStreetAddress(String newStreet)
    {
        streetAddress = newStreet;
    }
    
    public void setState(String newState)
    {
        state = newState;
    }
    
    public void setZip(int newZip)
    {
        zip = newZip;
    }
    
    public void setPhoneNumber(String newPhoneNumber)
    {
        phoneNumber = newPhoneNumber; 
    }
}