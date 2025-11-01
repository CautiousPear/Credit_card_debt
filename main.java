import java.util.*;
import java.io.*;
public class LabProgram {

   public static void main(String [] args) {
      Scanner scnr = new Scanner(System.in);
      int usSize, usNameCount, usDebtCount, usDebtFree; //Overall variables
      int stSize, stNameCount, stDebtCount, stDebtFree; //State variabvles
      int debtMin;
      double usHighestDebt;
      double stHighestDebt;
      int i;
      usNameCount = 0;
      usDebtCount = 0;
      usDebtFree  = 0;
      stNameCount = 0;
      stDebtCount = 0;
      stDebtFree  = 0;
      stSize = 0;
      String nameSearch, stateSuffix;
      String usHighestDebtName;
      String stHighestDebtName;
      
      // Input # of customers and create parallel arrays
      usSize = scnr.nextInt();
      debtMin = scnr.nextInt();
      nameSearch = scnr.next();
      stateSuffix = scnr.next();
      String [] names = new String[usSize];
      double [] debt = new double[usSize];
      String [] states = new String[usSize];

      
      // Fill arrays with data from external file (described in another section)
      readCustomerData(names, states, debt);

      usHighestDebt = debt[0];
      usHighestDebtName = names[0];
      for (i = 0; i < debt.length; i++) {
        if (debt[i] > usHighestDebt) {
            usHighestDebt = debt[i];
            usHighestDebtName = names[i];
        }
        if (debt[i] <= 0) usDebtFree++;
        if (debt[i] > debtMin) usDebtCount++;
        if (names[i].toUpperCase().startsWith(nameSearch.toUpperCase())) usNameCount++;
      }
      // Print overall U.S. report
      System.out.printf("U.S. Report\nCustomers: %d\nHighest debt: %s\nCustomer names that start with \"%s\": %d\nCustomers with debt over $%d: %d\nCustomers debt free: %d\n", usSize, usHighestDebtName, nameSearch, usNameCount, debtMin, usDebtCount, usDebtFree);
      System.out.println();

      stHighestDebt = -1;
      stHighestDebtName = "";
      for (i = 0; i < debt.length; i++) {
        if(states[i].equals(stateSuffix)) { //Only entries with same state suffix
            stSize++;
            if (debt[i] > stHighestDebt) {
                stHighestDebt = debt[i];
                stHighestDebtName = names[i];
            }
            if (debt[i] <= 0) stDebtFree++;
            if (debt[i] > debtMin) stDebtCount++;
            if (names[i].toUpperCase().startsWith(nameSearch.toUpperCase())) stNameCount++;
        }
      }
      //Print input state report
      System.out.printf("%s Report\nCustomers: %d\nHighest debt: %s\nCustomer names that start with \"%s\": %d\nCustomers with debt over $%d: %d\nCustomers debt free: %d\n", stateSuffix, stSize, stHighestDebtName, nameSearch, stNameCount, debtMin, stDebtCount, stDebtFree);
      
   }    

   // Read customer information from text file
   // Make no changes to the following code
   public static void readCustomerData(String [] names, String [] states, double [] debt) {
           
      // Read all data from file
      try { 
         File f = new File("CustomerNames.csv");
         Scanner scnr = new Scanner(f);
         scnr.useDelimiter("[, \r\n]+");
            
         for (int index = 0; index < names.length; ++index) {   
            names[index] = scnr.next(); // last name
            states[index] = scnr.next(); // state of residence
            debt[index] = scnr.nextDouble(); // amount of debt  
         }
         scnr.close();
      }
      
      // What if data file not found?
      catch(IOException e) {
         System.out.println("Failed to read the data file: ");
      }
   }
}   
