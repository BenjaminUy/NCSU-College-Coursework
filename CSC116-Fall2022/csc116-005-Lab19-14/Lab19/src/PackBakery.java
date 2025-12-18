/**
 * Class that deals with menu presented at the PackBakery
 * @author Benjamin Uy
 * @author Yamini Ramadurai
 */
public class PackBakery {
    
    /** Public variable for menu size */
    public static final int DEFAULT_MENU_SIZE = 3;
    
    /** Private variable for menu */
    private Sandwich[] menu;

    /**
     * Constructor accepts an integer that represents the number of
     * Sandwich objects that should be contained in the menu
     * @param size integer representing the number of Sandwich objects in menu
     */
    public PackBakery(int size) {
        menu = new Sandwich[size];
    }

    /**
     * Constructor method passes the default menu size to the
     * custom constructor
     */
    public PackBakery() {
        this.menu = new Sandwich[DEFAULT_MENU_SIZE];
    }

    /**
     * Method goes through menu array to add Sandwich object
     * in the first open (null) array location
     * @param s Sandwich object that is being added to menu
     * @return boolean for whether Sandwich s is added to menu, false if not
     */
    public boolean addSandwich(Sandwich s) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] == null) {
                menu[i] = s;
                return true;
            }
        }
        return false;
    }

    /**
     * Method goes through menu array to find the Sandwich that is trying
     * to be removed.
     * @param s Sandwich object that is trying to be removed
     * @return boolean for whether the Sandwich is removed from the menu
     */
    public boolean removeSandwich(Sandwich s) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] == s) {
                menu[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Method goes through the menu array and returns the Sandwich
     * that has the given name
     * @param name String representing the name of the Sandwich object
     * @return Sandwich object that matches the given name, returns null if the
     *      Sandwich object does not exist
     */
    public Sandwich getSandwich(String name) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].getName().equals(name)) {
                return menu[i];
            } 
        }
        return null;
    }

    /**
     * Method returns a reference to the menu array
     * @return Returns the menu array
     */
    public Sandwich[] getMenu() {
        return menu;
    }

}
