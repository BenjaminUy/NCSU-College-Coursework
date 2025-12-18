/**
 * Class that deals with breads for sandwich
 * @author Yamini Ramadurai
 * @author Benjamin Uy
 */
public class Bread {
    
    /** String value of type of bread used throughout class */
    private String type;

    /** Int value of calories for slice of bread used throughout class */
    private int calories;

    /** 
     * Constructor for Bread class
     * @param bread String value of bread
     * @param caloriesPerSlice Int value of number of calories per slice of bread
     */
    public Bread(String bread, int caloriesPerSlice) {
        this.type = bread;
        this.calories = caloriesPerSlice;
    }

    /**
     * Deals with type of Bread object
     * @return Returns String of type of Bread object
     */
    public String getType() {
        return type;
    }

    /**
     * Deals with amount of calories in Bread object
     * @return Returns amount of calories in Bread object 
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Turns response into String format
     * @return Returns String in format "type (X calories per slice)"
     */
    public String toString() {
        return (type + " (" + calories + " calories per slice)");
    }

    /**
     * Determines if both Bread objects have same type and number of calories
     * @param o Object for Bread
     * @return Returns true if both Bread objects have the same type and number 
     * of calories, false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Bread) {
            Bread r = (Bread) o;
            return (type == r.getType() && calories == r.getCalories());
        } else {
            return false;
        }
    
    }
}
