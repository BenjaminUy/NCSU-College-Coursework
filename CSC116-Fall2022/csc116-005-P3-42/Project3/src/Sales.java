import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Program that, when run, will read in the inventory from a text file
 * and allow the user to list, search, and purchase items--the latter
 * decreasing the item quantities. When the user quits, the updated 
 * inventory will be output to a file.
 * 
 * @author Benjamin Uy
 */
public class Sales {

    /** 
     * Constant representing the proper length of an item ID--six characters
     */
    public static final int NUM_OF_CHARACTERS_IN_ID = 6;

    /**
     * Starts the program for Sales
     * 
     * @param args string array from command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        /* Check to make sure there are two arguments on command line */
        if (args.length != 2) {
            System.out.print("Usage: java -cp bin Sales infile outfile");
            System.exit(1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        /* Check if input file can be accessed and exists */
        Scanner in = getInput(inputFilename);

        /* Check if output file already exists and prompts if an overwrite is allowed */
        Path pathOutput = Path.of(outputFilename);
        if (Files.exists(pathOutput)) {
            System.out.println(args[1] + " exists - OK to overwrite");
            System.out.print("(y,n)?: ");

            String overwrite = console.next();

            /* If input doesn't begin with "y" or "Y", program stops */
            if (!("" + overwrite.charAt(0)).equalsIgnoreCase("Y")) {
                System.exit(1);
            }
        }

        /* Check for FileNotFoundException when attempting to open an output file */
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFilename);
        } catch (FileNotFoundException e) {
            System.out.print("Cannot create output file");
            System.exit(1);
        }

        /* 
         * Scanner in determines how many lines are in input file; each line should
         * correspond to a unique item with an ID, name, and quantity.
         */
        int numberOfLines = getNumberOfLines(in);
        String[] itemIds = new String[numberOfLines];
        String[] itemNames = new String[numberOfLines];
        int[] itemQuantities = new int[numberOfLines];

        /* Reset scanner after counting number of lines in input file */
        in = getInput(inputFilename);

        String option = "";

        /* 
         * Check to see if input is missing any of the three items, quantity is invalid,
         * or two or more items have the same item number
         */
        if (inputInventory(in, itemIds, itemNames, itemQuantities) == false) {
            System.out.println("Invalid input file");
        } else {
            /* Loop continues to prompt user for input until "Q" is entered */
            while (!option.equalsIgnoreCase("Q")) {
                /* Call method that displays menu of Sales Program */
                displayMenu();
                option = console.next();
                
                if (option.equalsIgnoreCase("L")) {
                    System.out.println("\n  ID            Name          Quantity");
                    System.out.print(getList(itemIds, itemNames, itemQuantities));

                } else if (option.equalsIgnoreCase("S")) {
                    System.out.print("Item name (is/contains): ");
                    String itemName = "";
                    if (console.hasNext() && console.hasNextLine()) {
                        itemName = console.next();
                        itemName = itemName + console.nextLine();
                    }
                    System.out.println("\n  ID            Name          Quantity");
                    System.out.print(searchByName(itemIds, itemNames, itemQuantities, itemName));
                    if (searchByName(itemIds, itemNames, itemQuantities, itemName).equals("")) {
                        System.out.print("\n");
                    }

                } else if (option.equalsIgnoreCase("P")) {
                    System.out.print("Item id: ");
                    String itemId = console.next();
                    
                    /* Check if user-input itemId exists in the itemIds array */
                    if (itemIdExists(itemIds, itemId) == false) {
                        System.out.println("Invalid item");
                    } else { 
                        /* 
                        * Declare and initialize itemQuantity to -1 to prevent calling makePurchase 
                        * method should user input an invalid quantity
                        */
                        int itemQuantity = -1;
                        System.out.print("Quantity: ");
                        if (console.hasNextInt()) {
                            itemQuantity = console.nextInt();
                        } else {
                            String token = console.next();
                        }

                        /* Check if itemQuantity is at least 0 */
                        if (itemQuantity >= 0 && itemQuantity != -1) {
                            System.out.println(makePurchase(itemIds, itemQuantities, itemId, 
                                itemQuantity));
                        } else {
                            System.out.println("Invalid quantity");
                        }
                    }

                } else if (!option.equalsIgnoreCase("Q")) {
                    System.out.println("Invalid option");
                }

                /* Reset scanner in every time user inputs something */
                in = getInput(inputFilename);
            }
            PrintWriter out = new PrintWriter(outputStream);
            outputInventory(out, itemIds, itemNames, itemQuantities);
            console.close();
            in.close();
            out.close();
        }
    }    

    /**
     * Method determines if the input file given by the user can be accessed,
     * and returns scanner for input file. If FileNotException is caught, prints
     * error message and program exits
     * 
     * @param inputFilename string representing the name of the input file
     * @return scanner for input file
     */
    public static Scanner getInput(String inputFilename) {
        Scanner scan = null;
        try {
            FileInputStream input = new FileInputStream(inputFilename);
            scan = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.print("Unable to access input file: " + inputFilename);
            System.exit(1);
        }
        return scan;
    } 
    
    /**
     * This method outputs to the console the menu of the Sales Program--showing
     * the options that the user can do such as searching for and purchasing items.
     */
    public static void displayMenu() {
        System.out.println("\nSales Program - Please choose an option");
        System.out.println();
        System.out.println("L - List items");
        System.out.println("S - Search by item name");
        System.out.println("P - Purchase item");
        System.out.println("Q - Quit");
        System.out.println();
        System.out.print("Option: ");  
    }

    /**
     * Returns the number of lines in the file
     * 
     * @param in scanner used to count lines in file
     * @return integer for number of lines in the file
     * @throws IllegalArgumentException if in is null
     */
    public static int getNumberOfLines(Scanner in) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }
        
        int numOfLines = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            numOfLines++;
        }
        return numOfLines;
    }

    /**
     * Method reads each line of the input file and stores the item number, name, 
     * and quantity in the appropriate array parameter. Returns a boolean if
     * the procedure was successful.
     * 
     * @param in scanner for input file 
     * @param itemIds string array containing the item IDs
     * @param itemNames string array containing the item names
     * @param itemQuantities integer array containing the number of items in inventory
     * @return boolean for whether or not method successfully read input file inventory
     * @throws IllegalArgumentException if in is null
     * @throws IllegalArgumentException if itemIds, itemNames, and/or itemQuantities are/is null
     * @throws IllegalArgumentException if the lengths of itemIds, itemNames, and/or 
     *      itemQuantities are not the same or if the number of lines in the file is not 
     *      the same as the length of the itemIds, itemNames, itemQuantities
     */
    public static boolean inputInventory(Scanner in, String[] itemIds, String[] itemNames, 
        int[] itemQuantities) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        } else if (itemIds == null || itemNames == null || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        } else if (itemIds.length != itemNames.length || itemIds.length != itemQuantities.length 
            || itemNames.length != itemQuantities.length) {
            throw new IllegalArgumentException("Invalid array length");
        }

        boolean storeSuccess = true; 

        /* Counter variable starting at index 0 going until there is no line */
        int i = 0;

        int numOfTokensInLine = 0;

        while (in.hasNextLine() && storeSuccess == true) {
            /* Create scanner that searches for lines in input file */
            String line = in.nextLine();
            Scanner lineScan = new Scanner(line);
            lineScan.useDelimiter(",");

            while (lineScan.hasNext()) {
                if (numOfTokensInLine == 0) {
                    itemIds[i] = lineScan.next();
                    numOfTokensInLine++;
                    /* Check to see if item ID is six characters */
                    if (itemIds[i].length() != NUM_OF_CHARACTERS_IN_ID) {
                        storeSuccess = false;
                    }
                } else if (numOfTokensInLine == 1) {
                    /* Next token is the item name */
                    itemNames[i] = lineScan.next();
                    numOfTokensInLine++;
                } else if (numOfTokensInLine == 2) {
                    if (lineScan.hasNextInt()) {
                        itemQuantities[i] = lineScan.nextInt();
                        numOfTokensInLine++;
                        if (itemQuantities[i] < 0) {
                            storeSuccess = false;
                        }
                    } else {
                        String token = lineScan.next();
                        storeSuccess = false;
                    }
                } else {          
                    /* At this point, if line has more tokens, it is incorrectly formatted */
                    String token = lineScan.next();
                    storeSuccess = false;
                }
            }
            lineScan.close();

            if (numOfTokensInLine != 3) {
                storeSuccess = false;
            } else {
                numOfTokensInLine = 0;
            }
            i++;
        }
        
        /* Loop checks if there are duplicate elements in the itemIds array */
        if (storeSuccess == true) {
            for (int j = 0; j < itemIds.length; j++) {
                for (int k = 0; k < itemIds.length; k++) {
                    if (k != j) {
                        if (itemIds[j].compareTo(itemIds[k]) == 0) {
                            storeSuccess = false;
                        }
                    }
                }
            }
        }
        return storeSuccess;
    }

    /**
     * Returns a string with each item id, name, and quantity in the appropriate format
     *  
     * @param itemIds string array containing the item IDs
     * @param itemNames string array containing the item names
     * @param itemQuantities integer array containing the number of items in inventory
     * @return string with each item id, name, and quantity with a newline character in the end
     * @throws IllegalArgumentException if itemIds, itemNames, and/or itemQuantities are/is null
     * @throws IllegalArgumentException if lengths of itemIds, itemNames, and itemQuantities 
     *      arrays are not the same
     */
    public static String getList(String[] itemIds, String[] itemNames, 
        int[] itemQuantities) {
        if (itemIds == null || itemNames == null || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        } else if (itemIds.length != itemNames.length || itemIds.length != itemQuantities.length 
            || itemNames.length != itemQuantities.length) {
            throw new IllegalArgumentException("Invalid array length");
        }

        String list = "";
        for (int i = 0; i < itemIds.length; i++) {
            String itemId = itemIds[i];
            String itemName = itemNames[i];
            int itemQuantity = itemQuantities[i];
            list += toString(itemId, itemName, itemQuantity) + "\n";
        }
        return list;
    }

    /**
     * Method returns a string with each item whose name is/contains itemName, disregarding case.
     * The string will include item id, name, and quantity and newline characters separating
     * different items.
     * 
     * @param itemIds string array containing the item IDs
     * @param itemNames string array containing the item names
     * @param itemQuantities integer array containing the number of items in inventory
     * @param itemName string representing the name of the item that is being searched for
     * @return string containing the all of items whose name is/contains itemName along with
     *      other inventory-related info
     * @throws IllegalArgumentException if itemIds, itemNames, and/or itemQuantities are/is null
     * @throws IllegalArgumentException if lengths of itemIds, itemNames, and itemQuantities 
     *      arrays are not the same
     */
    public static String searchByName(String[] itemIds, String[] itemNames, 
        int[] itemQuantities, String itemName) {
        if (itemIds == null || itemNames == null || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        } else if (itemIds.length != itemNames.length || itemIds.length != itemQuantities.length 
            || itemNames.length != itemQuantities.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        String foundItems = "";
        for (int i = 0; i < itemNames.length; i++) {
            /* Ignore case sensitivity */
            if ((itemNames[i].toUpperCase()).contains(itemName.toUpperCase()) == true) {
                String foundID = itemIds[i];
                String foundName = itemNames[i];
                int foundQuantity = itemQuantities[i];
                foundItems += toString(foundID, foundName, foundQuantity) + "\n";
            }
        }
        return foundItems;
    }

    /**
     * Method returns formatted string for item
     * 
     * @param itemId string for id of the item
     * @param itemName string for the name of the item
     * @param itemQuantity integer for number of the item in inventory
     * @return string in format showing itemId, itemName, and itemQuantity
     */
    public static String toString(String itemId, String itemName, int itemQuantity) {
        return String.format("%6s  %-25s %4d", itemId, itemName, itemQuantity);
    }

    /**
     * Method determines if the requested number of items is more than or less than the number
     * of items in inventory and outputs a string in response.
     * 
     * @param itemIds string array containing the item IDs
     * @param itemQuantities integer array containing the number of items in inventory
     * @param itemId string for id of the item
     * @param itemQuantity integer for the number of items desired to be purchased
     * @return string "Insufficient quantity" if the item's quantity is less than itemQuantity
     *      or "Successful purchase" if item's quantity is more than itemQuantity
     * @throws IllegalArgumentException if itemIds or itemQuantities is/are null
     * @throws IllegalArgumentException if lengths of itemIds and itemQuantities are not equal
     * @throws IllegalArgumentException if itemId does not exist
     * @throws IllegalArgumentException if itemQuantity is less than 0
     */
    public static String makePurchase(String[] itemIds, int[] itemQuantities, String itemId, 
        int itemQuantity) {
        if (itemIds == null || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        } else if (itemIds.length != itemQuantities.length) {
            throw new IllegalArgumentException("Invalid array length");
        } else if (itemIdExists(itemIds, itemId) == false) {
            throw new IllegalArgumentException("Invalid item");
        } else if (itemQuantity < 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        String purchaseMessage = "";

        for (int i = 0; i < itemIds.length; i++) {
            /* 
             * Loop modifies array item quantity element if, at a certain index, 
             * user-input itemId equals an array element
             */
            if (itemIds[i].compareTo(itemId) == 0) {
                if (itemQuantities[i] < itemQuantity) {
                    purchaseMessage = "Insufficient quantity";
                } else {
                    purchaseMessage = "Successful purchase";
                    itemQuantities[i] = itemQuantities[i] - itemQuantity;
                }
            }
        }
        return purchaseMessage;
    }

    /**
     * This method goes through and compares the elements of the itemIds string array
     * to the user-input itemId. 
     * 
     * @param itemIds string array containing all itemIds in store inventory
     * @param itemId user-input string meant to be an itemId
     * @return boolean for whether user-input itemId exists in itemIds array
     */
    public static boolean itemIdExists(String itemIds[], String itemId) {
        boolean itemIdExists = false;
        for (int i = 0; i < itemIds.length; i++) {
            if (itemIds[i].compareTo(itemId) == 0) {
                itemIdExists = true;
            }
        }
        return itemIdExists;
    }

    /**
     * Method outputs the inventory data to the file appropriate format
     * 
     * @param out Printwriter used to change contents of the the output file
     * @param itemIds string array containing the item IDs
     * @param itemNames string array containing the item names
     * @param itemQuantities integer array containing the number of items in inventory
     * @throws IllegalArgumentException if out is null
     * @throws IllegalArgumentExcpetion if itemIds, itemNames, and/or itemQuantities are/is null
     * @throws IllegalArgumentException if lengths of itemIds, itemNames, and itemQuantities 
     *      arrays are not the same
     */
    public static void outputInventory(PrintWriter out, String[] itemIds, String[] itemNames, 
        int[] itemQuantities) {
        if (out == null) {
            throw new IllegalArgumentException("Null file");
        } else if (itemIds == null || itemNames == null || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        } else if (itemIds.length != itemNames.length || itemIds.length != itemQuantities.length 
            || itemNames.length != itemQuantities.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        String outputToFile = "";
        for (int i = 0; i < itemIds.length; i++) {
            outputToFile += itemIds[i] + "," + itemNames[i] + "," + itemQuantities[i];
            if (i < itemIds.length - 1) {
                outputToFile += "\n";
            }
        }
        out.print(outputToFile);
    }
}



