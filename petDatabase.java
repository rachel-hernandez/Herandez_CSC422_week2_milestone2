/*
 * Rachel Hernandez 
 * CSC 422 Software Engineering 
 * week 2 assignment 2, part 2
 * milestone 2: Handle errors:
              - There can only be at most 5 pets in the database
              - A pets age can only be between 1 and 20
              - When adding a pet only to values are to be enterned: name, age
              - The ID input by the user must an index of the ArrayList
 */

//importing needed utilitis 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Rachel
 */
public class petDatabase {
    
    //creating 2D arrayList
    static ArrayList<ArrayList<String>> pets = new ArrayList<ArrayList<String>>();
    
    //int to store the users choice
    static int choice;
    
    //int to count the number of rows in the 2D arrayList
    static int row;
    
    //declaring a scanner
    static Scanner s = new Scanner (System.in);


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Pet Database Program.");
         //Try ... Catch to open and read a file line by line
        try {
            File myObj = new File("petDatabase.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                int row = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    //splitting the line of the file at the "|" character 
                    String[] fileLine = data.trim().split("\\|");
                    
                    //if the value is not null it is added to the 2D arrayList
                    if (fileLine != null) {
                        int col = 0;
                        for (String str : fileLine) {
                            
                            if (str != null) {
                                pets.add(new ArrayList<>());
                                pets.get(row).add(col, str);
                                col++;
                            }

                        }
                        row++;
                    }

                   
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        
       //If statements to remove null elements from the arrayList
        int petCount = pets.size();
        for (int i = 0; i < petCount; i++) {
            int infoCount = pets.get(i).size();
            
            if (infoCount == 0) {
                pets.remove(i);
                petCount = pets.size();
                i = 0;
            } else {
                
            }
        }
        
        //System.out.println(pets);
        
        //options menu
        //loop will break when user enters 4
        while(true && choice !=4){
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Remove an existing pet");
            System.out.println("4) Exit program");
            System.out.println("Your choice: ");
            choice = s.nextInt(); 
            System.out.println(" ");
                
            //Switch statment to determine action based on users choice 
            switch(choice){
                //if user chooses 1 all pets will be displayed 
                case 1 -> {
                    //calling method to display all the pets 
                    viewPets();
                }

                //if user chooses 2 the user can add more pets
                case 2 -> {
                    //calling method to add more pets 
                    addPets();
                }
                
                //if user chooses 3 to remove a pet
                case 3 -> {
                    //calling method to remove a pet
                    removePet();
                }
                
           
                //if user chooses 4 to end the program 
                case 4 -> exitProgram();
                    
                default -> exitProgram();
            }
        }
        
    }
    
    //method to display all the pets
    static boolean viewPets(){
        System.out.println(" ");
        System.out.printf("\n+-------------------+");
        System.out.printf("\n| %-3s| %-8s| %-3s|%n", "ID", "Name", "Age");
        System.out.printf("+-------------------+");
        int count = 0;
        int petCount = pets.size();
        //for loop to loop throudh all the rows of the 2D arrayList
        for (int i = 0; i < petCount; i++) {            
            String petID = pets.get(i).get(0);
            String name = pets.get(i).get(1);
            String age = pets.get(i).get(2);
            //displaying the values in the arrayList
            System.out.printf("\n| %-3s| %-8s| %-3s|%n", petID, name, age);
            count++;
        }
        System.out.println("+-------------------+");
        System.out.println(count + " rows in set.");
        return true;
    }
    
    //method to add more pets
    //this method is recursive and will stop when there are 5 pets in the database or the user enters "done"
    static boolean addPets() {
        
        int petCount = pets.size();

        //method stops when there are 5 pets in the dataBase
        if (petCount == 5) {
            System.out.println("Error: Database is full.");
            return true;
            
        } else {

            System.out.println("add pet (name, age) or done to stop: ");
            String petInfo = s.nextLine();
           
            //method will stop when user enters "done"
            if (petInfo.equalsIgnoreCase("done")) {
                return true;
               
            } else {
                String[] petData = petInfo.split(" ");
                if (petData.length != 2) {
                    System.out.println("Error: " + Arrays.toString(petData) + " is not a valid input.");
                    addPets();
                } else {
                    String petName = petData[0];
                    int age = Integer.parseInt(petData[1]);

                    if (age < 0 || age > 20) {
                        System.out.println("Entered age is invalid. Please reenter.");
                        addPets();
                    } else {
                        String petAge = Integer.toString(age);
                        String newPetID = Integer.toString(petCount);
                        pets.add(petCount, new ArrayList<>(Arrays.asList(newPetID, petName, petAge)));
                        addPets();
                    }
                }

            }
        }

        //}
        return true;

    }

    
    
   
    //method to remove an existing pet
    static boolean removePet(){
        System.out.println(" ");
        System.out.printf("\n+-------------------+");
        System.out.printf("\n| %-3s| %-8s| %-3s|%n", "ID", "Name", "Age");
        System.out.printf("+-------------------+");
        int count = 0;
        int petCount = pets.size();
        //for loop to loop throudh all the rows of the 2D arrayList
        for (int i = 0; i < petCount; i++) {            
            String petID = pets.get(i).get(0);
            String name = pets.get(i).get(1);
            String age = pets.get(i).get(2);
            //displaying the values in the arrayList
            System.out.printf("\n| %-3s| %-8s| %-3s|%n", petID, name, age);
            count++;
        }
        System.out.println("+-------------------+");
        System.out.println(count + " rows in set.");
        System.out.println(" ");
        //Getting the ID of the pet to be removed
        System.out.println("Enter the pet ID to remove: ");
        int petID = s.nextInt();

        if (petID > pets.size() - 1 || petID <0) {
            System.out.println("Error: ID "+petID +" does not exist.");
        } else {

            //Getting that pets name and age
            String name = pets.get(petID).get(1);
            String age = pets.get(petID).get(2);
            //displaying removed pets name and age
            System.out.println(name + " " + age + " is removed");
            //removing pet form the 2d arrayList
            pets.remove(petID);

            for (int i = 0; i < pets.size(); i++) {
                String updateID = Integer.toString(i);
                pets.get(i).set(0, updateID);
            }

        }
        //the arrayList automatically updates row ID numbers
        return true;
    }

    //method to end the program and save pets ArrayList to file
    static boolean exitProgram() {
        try {
            try (FileWriter updateFile = new FileWriter("petDatabase.txt")) {

                int petCount = pets.size();
                //for loop to loop throudh all the rows of the 2D arrayList
                for (int i = 0; i < petCount; i++) {
                    String petID = pets.get(i).get(0);
                    String name = pets.get(i).get(1);
                    String age = pets.get(i).get(2);
                    //writting data to the file
                    updateFile.write(petID +"|"+name+"|"+age +"\n");
                }
                updateFile.close();
            }
            //System.out.println("It worked");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            //e.printStackTrace();
        }
        System.out.println("Goodbye!");
        return false;
    }


}

/*
0|Kitty|7
1|Mikey|15
2|Blue|4
3|Fido|3
4|Hunter|18
*/