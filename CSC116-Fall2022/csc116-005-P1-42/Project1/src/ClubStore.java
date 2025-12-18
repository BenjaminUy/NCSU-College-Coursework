import java.util.Scanner;

/**
 * This ordering program outputs the total cost and arrival date of a user's order 
 * based on various factors like number of items purchased and day of ordering.
 * @author Benjamin Uy
 */
public class ClubStore {

    /**
     * Starts the program
     * @param args from command line
     */
    public static void main (String[] args) {
        userInterface();
    }

    /**
     * Constant representing subtotal range from $0 to $25 where program will prompt for coupon
     */
    public static final int SUBTOTAL_COUPON_LIMIT = 25;  

    /**
     * Constant representing integer value of days that order will arrive for standard shipping
     */
    public static final int STANDARD_SHIPPING_DAYS_ARRIVAL = 5;

    /**
     * Constant representing integer value of days that order will arrive for two-day shipping
     */
    public static final int TWO_DAY_SHIPPING_DAYS_ARRIVAL = 2;

    /**
     * Constant representing integer value of the month of October
     */
    public static final int MONTH_OF_OCTOBER = 10;

    /**
     * Constant representing integer value of the month of November 
     */
    public static final int MONTH_OF_NOVEMBER = 11;

    /**
     * Constant representing integer value of the month of December
     */
    public static final int MONTH_OF_DECEMBER = 12;

    /**
     * Constant representing the total number of days in October
     */
    public static final int DAYS_IN_OCTOBER = 31;

    /**
     * Constant representing the total number of days in November
     */
    public static final int DAYS_IN_NOVEMBER = 30;

    /**
     * Constant representing the current year that orders will be made in
     */
    public static final int YEAR = 2022;

    /**
     * Constant representing the integer value of years needed before a leap year
     */
    public static final int LEAP_YEAR_FREQUENCY = 4;

    /**
     * Constant representing the integer value of the length of monthDayIn String if
     * user inputs one digit for the day
     */
    public static final int ONE_DIGIT_DAYS = 4;

    /**
     * Constant representing the integer value of the length of monthDayIn String if
     * user inputs two digits for days
     */
    public static final int TWO_DIGIT_DAYS = 5;

    /**
     * Constant representing the index value where days should begin in the monthDayIn String
     */
    public static final int BEGIN_DAY_INDEX = 3;

    /**
     * Constant representing the index value where days should end in the monthDayIn String
     * if user input one digit for the day
     */
    public static final int END_ONE_DIGIT_DAY_INDEX = 4;

    /**
     * Constant representing the index value where days should end in the monthDayIn String
     * if user input two digits for the day
     */
    public static final int END_TWO_DIGIT_DAY_INDEX = 5;

    /**
     * Constant representing the integer value for the cost of a water bottle in $
     */
    public static final int COST_BOTTLE = 10;

    /**
     * Constant representing the integer value for the cost of a coffee in $
     */
    public static final int COST_MUG = 12;

    /**
     * Constant representing the integer value for the cost of a tote bag in $
     */
    public static final int COST_BAG = 18;

    /**
     * Constant representing the number of years in a century
     */
    public static final int YEARS_CENTURY = 100;

    /**
     * Constant representing the number of years in four centuries
     */
    public static final int YEARS_FOUR_CENTURIES = 400;

    /**
     * Constant representing the integer length of the longest months
     */
    public static final int MAX_DAYS_IN_MONTH = 31;

    /**
     * Constant representing the number of days in a week
     */
    public static final int DAYS_IN_WEEK = 7;

    /**
     * Constant representing the number of months in a years
     */
    public static final int MONTHS_IN_YEAR = 12;

    /**
     * Completes user interactions by prompting for today's date, number of items to be ordered,
     * type of shipping, and coupon code (the latter depending on user's input)
     */
    public static void userInterface() {
        Scanner in = new Scanner(System.in);
    
        System.out.println();
        System.out.printf("%46s", "Welcome to our Club Store!");
        System.out.println();
        System.out.println("All orders must be placed between October 15 and November 30, 2022.");
        System.out.println("When prompted, please enter today's date and the number of each");
        System.out.println("item you would like to purchase. Please enter S to choose Standard");
        System.out.println("(five-day) shipping or T to choose Two-day shipping. Orders of");
        System.out.println("$25.00 or more receive free Standard shipping. Entering the correct");
        System.out.println("coupon code also entitles you to free Standard shipping! A receipt");
        System.out.println("and the arrival date of your order will be displayed.");
        System.out.println();
        System.out.print("Month Day (e.g., 11 9): ");

        String monthDayIn = in.nextLine();

        String firstDigitOfMonth = monthDayIn.substring(0, 1);
        if (!firstDigitOfMonth.equals("1")) {
            System.out.println("Invalid date");
            System.exit(1);
        }

        String monthsIn = monthDayIn.substring(0, 2);
        int month = Integer.parseInt(monthsIn);

        /*
         * Declare and initialize day variable, set to zero which is already invalid; requires
         * appropriate user input to become valid
         */
        int day = 0; 

        if (monthDayIn.length() == ONE_DIGIT_DAYS) {
            String daysIn = monthDayIn.substring(BEGIN_DAY_INDEX, END_ONE_DIGIT_DAY_INDEX);
            day = Integer.parseInt(daysIn);
        } else if (monthDayIn.length() == TWO_DIGIT_DAYS) {
            String daysIn = monthDayIn.substring(BEGIN_DAY_INDEX, END_TWO_DIGIT_DAY_INDEX);
            day = Integer.parseInt(daysIn);
        } else if (monthDayIn.length() != ONE_DIGIT_DAYS && 
                monthDayIn.length() != TWO_DIGIT_DAYS) {
            System.out.println("Invalid date");
            System.exit(1);
        }

        int numberOfBottles = 0;
        int numberOfMugs = 0; 
        int numberOfBags = 0;

        int orderDay = day;
        int orderMonth = month;
        int numberOfShippingDays = 0;

        if (day > 0) {
            if (isValidDate(month, day) == true) {
                System.out.print("Number of Water Bottles($10.00 each): ");
                numberOfBottles = in.nextInt();
                if (numberOfBottles < 0) {
                    System.out.println("Invalid number");
                    System.exit(1);
                }
                System.out.print("Number of Coffee Mugs($12.00 each): ");
                numberOfMugs = in.nextInt();
                if (numberOfMugs < 0) {
                    System.out.println("Invalid number");
                    System.exit(1);
                }
                System.out.print("Number of Tote Bags($18.00 each): ");
                numberOfBags = in.nextInt();
                if (numberOfBags < 0) {
                    System.out.println("Invalid number");
                    System.exit(1);
                }
            } else {
                System.out.println("Invalid date");
                System.exit(1);
            }
        } else if (day <= 0) {
            System.out.println("Invalid date");
            System.exit(1);
        }

        int subtotal = getSubtotal(numberOfBottles, numberOfMugs, numberOfBags);
        boolean twoDayShipping = false;
        boolean hasValidCoupon = false;
        int shippingCost = getShippingCost(subtotal, twoDayShipping, hasValidCoupon);

        // If user inputs 0 for numberOfBottles, numberOfMugs, and numberOfBags, 
        // skip prompting shipping type
        if ((numberOfBottles + numberOfMugs + numberOfBags == 0) && 
            isValidDate(month, day) == true) {
            System.out.printf("\nSubtotal: $%3d.00\n", subtotal);
            System.out.printf("Shipping: $%3d.00\n", shippingCost);
            System.out.printf("Total:    $%3d.00\n", (shippingCost + subtotal));
        }

        if (numberOfBottles + numberOfMugs + numberOfBags >= 1) {
            System.out.print("Shipping (S-tandard, T-wo Day): ");
            String shippingTypeIn = in.next();
            char shippingTypeChar = shippingTypeIn.toUpperCase().charAt(0);

            if (shippingTypeIn.length() > 1) {
                System.out.println("Invalid shipping");
                System.exit(1);
            }

            if (shippingTypeChar == 'S') {
                twoDayShipping = false;
                numberOfShippingDays = STANDARD_SHIPPING_DAYS_ARRIVAL;
            } else if (shippingTypeChar == 'T') {
                twoDayShipping = true;
                numberOfShippingDays = TWO_DAY_SHIPPING_DAYS_ARRIVAL;
            } else {
                System.out.println("Invalid shipping");
                System.exit(1);
            }
        }

        if (0 < subtotal && subtotal < SUBTOTAL_COUPON_LIMIT && twoDayShipping == false) {
            System.out.print("Coupon (y,n): ");
            String couponPromptIn = in.next();
            char couponYesNo = couponPromptIn.charAt(0);
            String couponCodeIn = "";

            switch (couponYesNo) {
                case 'y':
                    System.out.print("Coupon Code: ");
                    couponCodeIn = in.next();
                    break;
                case 'n':
                    hasValidCoupon = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    hasValidCoupon = false;
            }

            if (!couponCodeIn.equals("")) {
                switch (couponCodeIn) {
                    case "PACK2022":
                        hasValidCoupon = true;
                        break;
                    default: 
                        System.out.println("Invalid code");
                        hasValidCoupon = false;
                }
            }
        }

        if (0 < subtotal) {
            subtotal = getSubtotal(numberOfBottles, numberOfMugs, numberOfBags);
            shippingCost = getShippingCost(subtotal, twoDayShipping, hasValidCoupon);
            
            System.out.printf("\nSubtotal: $%3d.00\n", subtotal);
            System.out.printf("Shipping: $%3d.00\n", shippingCost);
            System.out.printf("Total:    $%3d.00\n", (shippingCost + subtotal));
            System.out.printf("Arrival Date: %s\n", getArrivalDate(orderMonth, orderDay, 
                numberOfShippingDays));
        }
    }

    /**
     * Method returns "true" if the date is a valid date between October 15 and 
     * November 30, inclusive. Otherwise, returns false.
     * 
     * @param month month of date given by user
     * @param day day of date given by user
     * @return true if the date given by user is between October 15 and November 30
     */
    public static boolean isValidDate(int month, int day) {
        final int earlyBoundaryOct15 = 15;
        if (month < MONTH_OF_OCTOBER) {
            return false;
        } else if (month > MONTH_OF_NOVEMBER) {
            return false;
        } else if (day < 1) {
            return false;
        } else if (day > DAYS_IN_OCTOBER) {
            return false;
        } else if (month == MONTH_OF_NOVEMBER && day > DAYS_IN_NOVEMBER) {
            return false;
        } else if (month == MONTH_OF_OCTOBER && day < earlyBoundaryOct15) {
            return false;
        } else
            return true;
    }

    /**
     * Method calculates and returns total cost of the items (subtotal)
     * 
     * @param numberOfBottles number of water bottles ordered by user
     * @param numberOfMugs number of coffee mugs ordered by user
     * @param numberOfBags number of tote bags ordered by user
     * @return integer value of the subtotal cost 
     * @throws IllegalArgumentException if either numberOfBottles, numberOfMugs, 
     *         numberOfBags is negative       
     */
    public static int getSubtotal(int numberOfBottles, int numberOfMugs, 
        int numberOfBags) {
        if (numberOfBottles < 0 || numberOfMugs < 0 || numberOfBags < 0) {
            throw new IllegalArgumentException("Invalid number");
        }

        return (numberOfBottles * COST_BOTTLE + numberOfMugs * COST_MUG + 
            numberOfBags * COST_BAG);        
    }

    /**
     * Method determines and returns shipping cost according to certain conditions, 
     * such as the subtotal cost. For example, if shipping cost is 0, the returned
     * subtotal is 0.
     * 
     * @param subtotal subtotal amount of the items ordered by user
     * @param twoDayShipping boolean determining if user selected two-day shipping
     * @param hasValidCoupon boolean determining if user entered valid coupon code
     * @return integer value of shipping cost
     * @throws IllegalArgumentException if subtotal is negative
     */
    public static int getShippingCost(int subtotal, boolean twoDayShipping, 
        boolean hasValidCoupon) {
        final int STANDARD_SHIPPING_COST = 3;
        final int TWO_DAY_SHIPPING_COST = 5;
        if (subtotal < 0) {
            throw new IllegalArgumentException("Invalid subtotal");
        } else if (subtotal == 0) {
            return 0;
        } else if (twoDayShipping == true) {
            return TWO_DAY_SHIPPING_COST;
        } else if (subtotal >= SUBTOTAL_COUPON_LIMIT && twoDayShipping == false) {
            return 0;
        } else if (subtotal < SUBTOTAL_COUPON_LIMIT && twoDayShipping == false &&
            hasValidCoupon == true) {
            return 0;
        } else if (subtotal < SUBTOTAL_COUPON_LIMIT && twoDayShipping == false) {
            return STANDARD_SHIPPING_COST;
        } else {
            return 0;
        }
    }

    /**
     * Method determines and returns String containing day of the week (Sun, Mon, Tue, Wed, Thu,
     * Fri, Sat), month, day, and year an order will arive based on the orderMonth, orderDay, the
     * numberOfShippingDays, and a year of 2022.
     * 
     * @param orderMonth the current month that user is ordering in
     * @param orderDay the current day that user is ordering in
     * @param numberOfShippingDays integer value determining how many days will order arrive
     * @return String containing day of the week, month, day, and year the order will arrive
     * @throws IllegalArgumentException if orderMonth/orderDay is not a valid date between 
     *      October 15 and November 30, inclusive
     * @throws IllegalArgumentException if numberOfShippingDays is less than 1 or greater than 5
     */
    public static String getArrivalDate(int orderMonth, int orderDay, int numberOfShippingDays) {
        int month = orderMonth;
        int day = orderDay;

        if (isValidDate(month, day) == false) {
            throw new IllegalArgumentException("Invalid date");
        } else if (numberOfShippingDays < 1 || numberOfShippingDays > 
            STANDARD_SHIPPING_DAYS_ARRIVAL) {
            throw new IllegalArgumentException("Invalid days");
        }
        
        int arrivalDay = orderDay + numberOfShippingDays;
        int arrivalMonth = 0;

        if (orderMonth == MONTH_OF_OCTOBER && arrivalDay > DAYS_IN_OCTOBER) {
            arrivalDay = arrivalDay - DAYS_IN_OCTOBER;
            arrivalMonth = MONTH_OF_NOVEMBER;
        } else if (orderMonth == MONTH_OF_NOVEMBER && arrivalDay > DAYS_IN_NOVEMBER) {
            arrivalDay = arrivalDay - DAYS_IN_NOVEMBER;
            arrivalMonth = MONTH_OF_DECEMBER;
        } else
            arrivalMonth = orderMonth;
        
        int w; 
        int x;
        int z; 
        int dayOfWeek;
        String dayAbbreviation = "";

        w = YEAR - ((MONTHS_IN_YEAR + 2) - arrivalMonth) / MONTHS_IN_YEAR;
        x = w + w / LEAP_YEAR_FREQUENCY - w / YEARS_CENTURY + w / YEARS_FOUR_CENTURIES;
        z = arrivalMonth +  MONTHS_IN_YEAR * (((MONTHS_IN_YEAR + 2) - arrivalMonth) / 
            MONTHS_IN_YEAR) - 2;
        dayOfWeek = (arrivalDay + x + (MAX_DAYS_IN_MONTH * z) / MONTHS_IN_YEAR) % DAYS_IN_WEEK;

        final int ZellerOutputforThu = 4;
        final int ZellerOutputforFri = 5;
        final int ZellerOutputForSat = 6;
        
        switch (dayOfWeek) {
            case 0:
                dayAbbreviation = "Sun";
                break;
            case 1:
                dayAbbreviation = "Mon";
                break;
            case 2:
                dayAbbreviation = "Tue";
                break;
            case 3:
                dayAbbreviation = "Wed";
                break;
            case ZellerOutputforThu:
                dayAbbreviation = "Thu";
                break;
            case ZellerOutputforFri:
                dayAbbreviation = "Fri";
                break;
            case ZellerOutputForSat:
                dayAbbreviation = "Sat";
                break;
            default:
        }

        return (dayAbbreviation + ", " + arrivalMonth + "/" + arrivalDay + "/" + YEAR);
    }
}