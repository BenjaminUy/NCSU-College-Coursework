import java.util.Scanner;

/**
 * The class PaceCalculator determines the race pace or average mile time
 * based on the race type and total time input by the user.
 * @author Benjamin Uy and Emma Gould
 */
public class PaceCalculator {

        /** Starts the program
         * @param args arguments from command line
         */

    public static void main (String[] args){
        Scanner in = new Scanner(System.in); 

        // Establish race types and their length in miles
        final double miles_Marathon = 26.2;
        final double miles_HalfMarathon = 13.1;
        final double miles_TenK = 6.2;
        final double miles_FiveK = 3.1;
        final double miles_Mile = 1;

        /* Establish number of seconds in hours and minutes, used for
         * computations of total time and pace time 
         */
        final int seconds_per_hour = 3600;
        final int seconds_per_minute = 60;

        /* Initialize the variable which will change depending on user's
         * input of race type 
         */
        double mileage;

        /* Constant used to determine if the race type input by user
         * is invalid
         */
        final int invalid = 0;

        System.out.println("Race Distance Menu:");
        System.out.println("- M: Marathon");
        System.out.println("- H: Half-Marathon");
        System.out.println("- T: 10K");
        System.out.println("- F: 5K");
        System.out.println("- I: Mile");

        System.out.print("Race Distance: ");
        String raceType = in.next();

        switch (raceType) {
            case "M": case "m":
                mileage = miles_Marathon;
                break;
            case "H": case "h":
                mileage = miles_HalfMarathon;
                break;
            case "T": case "t":
                mileage = miles_TenK;
                break;
            case "F": case "f":
                mileage = miles_FiveK;
                break;
            case "I": case "i":
                mileage = miles_Mile;
                break;
            default:
                mileage = invalid;
                System.out.println("Invalid distance");
        }

        // Mileage or length of race in miles is established

        if (mileage != invalid) {
            /* If condition is true, the race type inputted by user was
             * one of the five race types 
             */
            System.out.print("Race time (H:MM:SS): ");
            String raceTime = in.next();

            /* Initalize variables that will change depending on string
             * length of the race time input by user
             */
            char firstColon = 0;
            char secondColon = 0;
        
            /* Constant used to determine string length of raceTime input
             * by user and positions of first and second colons
             */
            final int oneDigitHours = 7;
            final int twoDigitHours = 8;

            /* Establish the positions of the first and second colons
             * based on the string length of raceTime
             */ 

            // expected indexes of the colons in time output when the hour is one digit
            final int first_colon_index_one = 1;
            final int second_colon_index_one = 4;

            // expected indexes of the colons in time output when the hour is two digits
            final int first_colon_index_two = 2;
            final int second_colon_index_two = 5;


            if (raceTime.length() == oneDigitHours) {
                /* If raceTime has 7 characters, the first and second colons 
                 * should be located at index 1 and 4, respectively
                 */  
                firstColon = raceTime.charAt(first_colon_index_one);
                secondColon = raceTime.charAt(second_colon_index_one);

            } else if (raceTime.length() == twoDigitHours) {
                /* If raceTime has 8 characters, the first and second colons 
                 * should be located at index 2 and 5, respectively
                 */  
                firstColon = raceTime.charAt(first_colon_index_two);
                secondColon = raceTime.charAt(second_colon_index_two);
            }
            
            // Initialize and break up race time into components

            String minutesIn = "0";
            String secondsIn = "0";
            String hoursIn = "0";

            /* Determines the substrings containing the number of hours based
             * on string length of raceTime
             */
            switch (firstColon) {
                case ':': 
                    if(raceTime.length() == oneDigitHours){
                        hoursIn = raceTime.substring(0, 1);
                        break;
                    } else if (raceTime.length() == twoDigitHours) {
                        hoursIn = raceTime.substring(0, 2);
                        break;
                    }
                default:
                    hoursIn = "0";      
            }
            
            /* Determines the substrings containing the number of minutes and 
             * seconds based on string length of raceTime
             */

            // start and end indexes of the minutes and seconds values, when the hour is one digit
            final int beginMinutes_indexOne = 2;
            final int endMinutes_indexOne = 4;
            final int beginSeconds_indexOne = 5;
            final int endSeconds_indexOne = 7;

            // start and end indexes of the minutes and seconds values, when the hour is two digits
            final int beginMinutes_indexTwo = 3;
            final int endMinutes_indexTwo = 5;
            final int beginSeconds_indexTwo = 6;
            final int endSeconds_indexTwo = 8;



            switch (secondColon) {
                case ':':
                    if(raceTime.length() == oneDigitHours){
                        minutesIn = raceTime.substring(beginMinutes_indexOne, endMinutes_indexOne);
                        secondsIn = raceTime.substring(beginSeconds_indexOne, endSeconds_indexOne);
                        break;
                    } else if (raceTime.length() == twoDigitHours) {
                        minutesIn = raceTime.substring(beginMinutes_indexTwo, endMinutes_indexTwo);
                        secondsIn = raceTime.substring(beginSeconds_indexTwo, endSeconds_indexTwo);
                        break;
                    }
                default:
                    minutesIn = "0";
                    secondsIn = "0";    
            }

            // Establish the substrings of hours, minutes, and seconds as integers
            int hoursNum = Integer.parseInt(hoursIn);
            int minutesNum = Integer.parseInt(minutesIn);
            int secondsNum = Integer.parseInt(secondsIn);

            /* Constant used to determine if input race time format is incorrect.
             * Displayed time does not include "60" or above; for example, "0:01:00" is more
             * appropriate than "0:00:60"
             */
            final int maxTimeFormat = 59;

            if ((firstColon != ':') || (secondColon != ':')) {
                /* If condition is true, the race time does not match correct format of 
                 * H:MM:SS or HH:MM:SS
                 */
                System.out.println("Invalid time");

            } else if ((minutesNum > maxTimeFormat) || (secondsNum > maxTimeFormat)) {
                /* If any of the two variables contain numbers above 59, the race time
                 * format is incorrect
                 */ 
                System.out.println("Invalid time");

            } else if ((hoursNum < 0) || (minutesNum < 0) || (secondsNum < 0)) {
                // If there are negative numbers, the race time format is incorrect
                System.out.println("Invalid time");

            } else {
                /* At this point, the input race time is valid and pace time can be
                 * calculated; start with calculating total number of seconds
                 */
                int secondsTotal = (hoursNum * seconds_per_hour) + 
                    (minutesNum * seconds_per_minute) + secondsNum;

                double racePace = secondsTotal / mileage;

                int paceHours = (int) (racePace / seconds_per_hour);
                int paceMinutes = (int) ((racePace - (paceHours * seconds_per_hour)) /
                    seconds_per_minute);
                int paceSeconds = (int) (racePace - (paceHours * seconds_per_hour + 
                    paceMinutes * seconds_per_minute));

                // Placeholder zeroes when paceMinutes or paceSeconds are one-digit
                char minutesZero = 0;
                char secondsZero = 0; 

                /* Establish minimum integer value needed to take up two digits,
                 * used to determine if pace time output requires placeholder zeroes
                 */
                final int minValueTwoDigits = 10;

                if (paceMinutes < minValueTwoDigits) {
                    minutesZero = '0';
                }

                if (paceSeconds < minValueTwoDigits) {
                    secondsZero = '0';
                }

                System.out.println(paceHours + ":" + minutesZero + paceMinutes + ":" +
                    secondsZero + paceSeconds);
            }                          
        } 
    }
}