/**
 * Class that deals with filling of sandwich
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 */
public class SandwichFilling {
    
    /** String value of sandwichfilling type used throughout class */
    private String type;

    /** Int value of number of calories in sandwichfilling used throughout class */
    private int calories; 

    /**
     * Constructor for SandwichFilling class
     * @param filling String used to represent sandwichfilling
     * @param calories Int used to represent amount of calories in sandwichfilling
     */
    public SandwichFilling(String filling, int calories) {
        this.type = filling;
        this.calories = calories; 
    }

    /**
     * Deals with type of sandwichfilling
     * @return Returns String value of sandwichfilling
     */
    public String getType() {
        return type; 
    }

    /**
     * Deals with number of calories of sandwichfilling
     * @return Returns int value of calories in sandwichfilling
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Turns input into String to be outputted
     * @return Returns String format of input
     */
    public String toString() {
        return (type + " (" + calories + " calories per serving)");
    }

    /**
     * Determines if sandwichfilling objects are equal or not
     * @param o Object of sandwichfilling
     * @return Returns true if sandwichfillings are the same, otherwise returns false
     */
    public boolean equals(Object o) {
        if (o instanceof SandwichFilling) {
            SandwichFilling r = (SandwichFilling) o;
            return (type == r.getType() && calories == r.getCalories());
        } else {
            return false;
        }
    }
}
