//Created by: Alexander Rotariu
//IECE213 - Data Structures

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;

public class AddressBookDriver
{
    public static void main(String [] args)
    {
       //Crearing an ArrayList of persons and calling it the addressBook
        ArrayList<Person> addressBook = new ArrayList<Person>();
        //Creating a scanner object
        Scanner userInput = new Scanner(System.in);
        //OptionNum: the variable that stores the users input
        int optionNum = 0;
        
        
        addressBook.add(new Person("Rich", "Rod", "Brownthil 123th", "NY", 62655, "646-124-1535"));
        addressBook.add(new Person("Stefan", "Mihai", "1452 8th", "NY", 12134, "347-523-1556"));
        addressBook.add(new Person("Tiler", "Williams", "2341 Pough St.", "NY", 59012, "782-124-1531"));
        addressBook.add(new Person("Alex", "Rotariu", "79-20 Juniper", "NY", 11379, "917-270-9765"));
        addressBook.add(new Person("Will", "Charla", "213 26th", "NY", 12451, "655-431-4515"));
        addressBook.add(new Person("Matt", "Melendez", "213 26th", "NY", 12451, "655-431-4515"));
        
        //The loop keeps going until a 7 is entered. When a 7 is entered System.exit is called. Otherwise, it performs the address book operations. 
        //If none of these options are entered, it prompts the user to enter a number in the list.
        while(optionNum != 7)
        {
            //Calling the method created simply printing the menu. Print menu is a method in the AddressBookDriver.java file to clean up the code.
            printMenu();
            
            //Asking for the user's input
            optionNum = userInput.nextInt();
            
            //Switch, case statement in order to determine which operation to do
            //Originally was a if, else if chain
            switch(optionNum)
            {
                //If the user enters a 1, add a person to the book by prompting for each field.
                case 1:
                    addPerson(addressBook);
                    break;
                    
                //If the user enters a 2, delete a person based on first and last name entered.
                case 2:
                    System.out.print("Please eneter the first name: ");
                    String firstNameDelete = userInput.next();
                    System.out.print("Please eneter the last name: ");
                    String lastNameDelete = userInput.next();
                    deletePerson(addressBook, firstNameDelete, lastNameDelete);
                    break;
                    
                //If the user enters a 3, prompt for the phone number, and use the phone number as a search key (BINARY SEARCH ALG)
                case 3:
                    System.out.print("Please enter the phone number of the person being modified: ");
                    String phoneNumber = userInput.next();
                    modifyPerson(addressBook, phoneNumber);
                    break;
                    
                //If the user enters a 4, print a single person, using the first and last name as a search key (SEQUENTIAL SEARCH ALG)
                case 4:
                    System.out.print("Please enter the first name: ");
                    String firstName = userInput.next();
                    System.out.print("Please enter the last name: ");
                    String lastName = userInput.next();

                    printOne(addressBook, firstName, lastName);
                    
                    break;
                    
                //If the user enters a 5, print every element in the address book
                case 5:
                    printAll(addressBook);
                    break;
                    
                //If the user enters a 6, export all the data in the address book to a txt file.
                case 6: 
                    exportText(addressBook);
                    break;
                    
                //If the user enters a 7, exit the program
                case 7:
                    System.exit(0);
                    break;
                    
                //If none of these conditions are met, tell the user to enter a number that is on the list!
                default:
                    System.out.println("That number is not on the list!");
            }
        }           
    }
    
    //Print menu prints every line of the menu, is called in the while loop after an operation. 
    public static void printMenu()
    {
        System.out.println("-------------------------------------------------------");
        System.out.println("Please select an option: ");
        System.out.println("1. Add a person");
        System.out.println("2. Delete a person");
        System.out.println("3. Modify a person");
        System.out.println("4. Print one person");
        System.out.println("5. Print all people");
        System.out.println("6. Write all elements to the text file");
        System.out.println("7. Exit");
        System.out.println("-------------------------------------------------------");
    }
    
    //Add person adds a person to the ArrayList of persons called addressbook
    public static void addPerson(ArrayList<Person> book)
    {
        //A new scanner object called userInput is created
        Scanner userInput = new Scanner(System.in);
        
        //Prompting and scanning for the first name of the new person being created
        System.out.print("First name: ");
        String firstName = userInput.nextLine();
        
        //Prompting and scanning for the last name of the new person being created
        System.out.print("Last name: ");
        String lastName = userInput.nextLine();
        
        //Prompting and scanning for the street address of the new person being created
        System.out.print("Street address: ");
        String street = userInput.nextLine();
        
        //Prompting and scanning for the state of the new person being created
        System.out.print("State: ");
        String state = userInput.nextLine();
        
        //Prompting and scanning for the ZIP Code of the new person being created
        System.out.print("ZIP code: ");
        int zip = Integer.parseInt(userInput.nextLine());
        
        //Prompting and scanning for the telephone number of the new person being created
        System.out.print("Telephone number: ");
        String phoneNum  = userInput.nextLine();
        
        //Creating a new person object and adding it to the array list
        book.add(new Person(firstName, lastName, street, state, zip, phoneNum));
    }
    
    //Delete person deletes a person in the arraylist, given the first and last name.
    public static void deletePerson(ArrayList<Person> book, String firstName, String lastName)
    {
        //The loop iterated through the entire arraylist, checking each element for a matching first and last name. If the condition is met, then it removes that element from the arraylist
        for(int i = 0; i < book.size(); i++)
        {
            if(book.get(i).getFirstName().equals(firstName) && book.get(i).getLastName().equals(lastName))
            {
                book.remove(i);
            }
        }
        
        //Previously, the delete function just deleted the last added person. Code shown below
        
//        //Deletes the last person added
//        System.out.println(book.get(book.size()-1).getFirstName()+" "+book.get(book.size()-1).getLastName()+" has been removed!");
//        book.remove(book.size()-1);
    }
    
    //Modifies a person given the phone number of the person using the binary serach algorithm
    public static void modifyPerson(ArrayList<Person> book, String phoneNum)
    {
        //A new scanner object is created to prompt the user for input
        Scanner uI = new Scanner(System.in);
        
        //indexPerson is what is going to be updated later in the method. We are finding the index of this person using the phone number.
        int indexPerson = 0;
       
        //The method will not continue if the phone number entered or address book is null
        if(book != null && phoneNum != null)
        {
            //SORTING FOR BINARY SEARCH
            //Binary search requires a sorted list, therefore the list is being sorted here.
            
            //Lines 180 - 193 is the bubble sort. Bubble sort compares adjacent elements and swaps them if they arent in the correct order.
            //It passes through until the list is sorted.
            /*int len = book.size();

            for(int i = 0; i < len - 1; i++)
            {
                for(int j = 0; j < len-i-1; j++)
                {
                    //If the element current is greater than the one ahead of it, then swap. (String comparisons and ascii values)
                    if(book.get(j).getPhoneNumber().compareTo(book.get(j+1).getPhoneNumber()) > 0)
                    {
                        //Swapping the two elements using a temporary element place holder.
                        Person temp = book.get(j);
                        book.set(j, book.get(j+1));
                        book.set(j+1, temp);
                    }
                }
            }*/
            
            //Another sorting method, using a default java function collections. sort() and creating a comparator to tell java how to compare persons.
            //In this case we are using the person phone number and the String compareTo() method and passing this comparator.
            Comparator<Person> comparator = new Comparator<Person>()
            {
                @Override
                public int compare(Person left, Person right)
                {
                    String leftNumber = left.getPhoneNumber();
                    String rightNumber = right.getPhoneNumber();
                    
                    return leftNumber.compareTo(rightNumber);
                }
            };
            
            Collections.sort(book, comparator);

            //After the list becomes sorted, we perform the binary search. Binary search is a method in the AddressBook.java file
            //Given the list and the phoneNumber, the binary search is conducted and returns the index of the person looking for .
            indexPerson = binarySearch(book, phoneNum);
            
            //Prompting the user for which field they would like to edit.
            System.out.println("What field would you like to modify?");
            System.out.println("1. First name ");
            System.out.println("2. Last name");
            System.out.println("3. Street address");
            System.out.println("4. State");
            System.out.println("5. Zip code");
            System.out.println("6. Telephone number");

            //Scanning for the usesrs input
            int usersInput = uI.nextInt();

            //Switch case statements are used to perform the correct subsitution.
            switch(usersInput)
            {
                //If the user inputs a 1, prompt the user for the new first name and replace it with the setFirstName method from the person class
                case 1:
                    System.out.print("Please enter the new first name: ");
                    String nFirst = uI.next();
                    book.get(indexPerson).setFirstName(nFirst);
                    break;

                //If the user inputs a 2, prompt the user for the new last name and replace it with the setLastName method from the person class
                case 2:
                    System.out.print("Please enter the new last name: ");
                    String nLast = uI.next();
                    book.get(indexPerson).setLastName(nLast);
                    break;

                //If the user inputs a 3, prompt the user for the new street address and replace it with the setStreetAddress method from the person class
                case 3: 
                    System.out.print("Please enter the new street address: ");
                    String nAddress = uI.next();
                    book.get(indexPerson).setStreetAddress(nAddress);
                    break;

                //If the user inputs a 4, prompt the user for the new state and replace it with the setState method from the person class
                case 4:
                    System.out.print("Please enter the new state: ");
                    String nState = uI.next();
                    book.get(indexPerson).setState(nState);
                    break;

                //If the user inputs a 5, prompt the user for the new zip code and replace it with the setZip method from the person class
                case 5:
                    System.out.print("Please enter the new zip: ");
                    int nZip = uI.nextInt();
                    book.get(indexPerson).setZip(nZip);
                    break;

                //If the user inputs a 6, prompt the user for the new telephone number and replace it with the setPhoneNumber method from the person class
                case 6:
                    System.out.print("Please enter the new telephone number: ");
                    String nPhone = uI.next();
                    book.get(indexPerson).setPhoneNumber(nPhone);
                    break;

                //If none of the conditions are meant (user enters a number not on the list, display and error message.)
                default:
                    System.out.println("Invalid option!");

            }
        }
        else
        {
            System.out.println("The phone number or address book is null!");
        }
    }
     
    //The print one method uses the first and last name as a search key and prints a person using the toString made in the Person.java class.
    //Sequential search implemented here
    public static void printOne(ArrayList<Person> book, String firstName, String lastName)
    {   
        //This for loops goes through the entire list and looks for a matching first and last name in each element. Similar to the delete person search method.
        for(int i = 0; i < book.size(); i++)
        {
            //checking if the current elements first and last name matches
            if(book.get(i).getFirstName().equals(firstName) && book.get(i).getLastName().equals(lastName))
            {
                //Displaying the information of the person at this index.
                System.out.println(firstName + " " + lastName+"'s information: ");
                System.out.println(book.get(i));
                //Exiiting the method
                return;
            }
        }
        
        System.out.println("There is no one named "+ firstName+" "+lastName+ " in the address book!");
    }
    
    //Printing all the elements in the arraylist
    public static void printAll(ArrayList<Person> book)
    {
        //Formatting the print
        System.out.println("Address book:");
        System.out.println("-------------------------------------------------------");
        //The for loops iterates through the entire list and prints each element. No sorting or searching required.
        for(int i = 0; i < book.size(); i++)
        {
            System.out.println(book.get(i));
        }
    }
    
    //The exportText method will export all the data from the address book into a text file called "addressBook.txt" in the respective path of the project.
     public static void exportText(ArrayList<Person> book)
     {
        //Try catch block for exporting. Exception if the file isnt found
        try
        {
            //imported FileWriter and FileIO
            //Creating a new FileWriter object called addressBook and creating a new file called "addressbook.txt"
            FileWriter addressBook = new FileWriter(new File("addressBook.txt"));
            
            //In the file, we are printing every element into the file, using the toString method in the Person class.
            //Using a "\n" to format it 
            for(int i = 0; i < book.size(); i ++ )
            {
                addressBook.write(book.get(i).toString()+"\n");            
            }
            
            //After we are done writing to the file, close the file.
            addressBook.close();
            
            //Telling the user, the writing to file is complete.
            System.out.println("Successfully wrote to file.");
        }
        //Exception catching for file writer.
        catch(IOException e)
        {
            System.out.println("An error occured writing to the file.");
        }
     }
     
    //Binary search method used in modify person method.
    public static int binarySearch(ArrayList<Person> book, String phoneNumber)
    {
         
        //The first method I tried was the recursive binary search
        //It seemed to have a lot of bugs and didnt pair very well with the sorting method I chose
        //I decided to make an iterative binary search algorith, the code from the attempted recursive binary search is shown below.
        //Recursive binary search 358-376
        /*if(first > last)
        {
           return -1;
        }

        int middle = (first + last) / 2;

        if(book.get(middle).getPhoneNumber().equals(phoneNumber))
        {
           return middle;
        }
        else if(book.get(middle).getPhoneNumber().compareTo(phoneNumber) < 0)
        {
           return binarySearch(book, middle + 1, last, phoneNumber);
        }
        else
        {
           return binarySearch(book, first, middle-1, phoneNumber);
        }*/
        
        //Iterative binary search
        
        int start = 0;
        int len = book.size()-1;
        
        //While the starting position is less than or equal to the length of the entire array it will continue to iterate.
        while(start <= len)
        {
            //Computing the middle of the list
            //A new middle will be computed every iteration
            int middle = start + (len-1) / 2;
            
            //If the element we are looking for is in the middle, return the index of the middle.
            if(book.get(middle).getPhoneNumber().equals(phoneNumber))
            {
                return middle;
            }
            //if it wasnt in the middle and the middle was less than target, increase the middle, to move up the middle.
            if(book.get(middle).getPhoneNumber().compareTo(phoneNumber) < 0)
            {
                start = middle + 1;
            }
            //If it wasnt less, then it was greater and we should subtract one from the middle.
            else
            {
                len = middle-1;
            }
        }
        
        return -1;
    }
}