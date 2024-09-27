/**
 * a  class to demonstrate dynamic behavior with arrays objects of this
 * class store strings in an array that grows to match the demand for storage
 * 
 * the class is based on an underlying string array objects can be initialized
 * to any size; otherwise they'll be initialized to the default size for
 * example
 * 
 * moredynamicarray da1 = new moredynamicarray(10)
 * 
 * will have initially room for 10 strings, while
 * 
 * moredynamicarray da2 = new moredynamicarray()
 * 
 * will have initially room for 4 strings
 */
public class MoreDynamicArray {

    /** default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** the underlying array for this class */
    private String[] foundation;

    /** measures how many places in the array are in use */
    private int occupancy;

    /**
     * full constructor initializes the underlying array to the specified size the
     * size must be a positive, non zero value otherwise the constructor uses the default size value
     */
    public MoreDynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.occupancy = 0;
    }

    /**
     * array-based constructor -- used for testing
     * 
     * warning: shallow array copy
     * 
     * @param data
     */
    public MoreDynamicArray(String[] data) {
        this(DEFAULT_SIZE);
        if (data != null) {
            this.foundation = data;
            this.occupancy = data.length;
        }
    }

    /** default constructor */
    public MoreDynamicArray() {
        this(DEFAULT_SIZE);
    }

    /**
     * checks if the specified string is present in the dynamic array
     * 
     * @param target the string to search for in the array
     * @return true if the string is found, false otherwise
     */
    public boolean contains(String target) {
        boolean found = false;
        if (target != null && this.foundation != null) {
            int i = 0;
            while (i < this.occupancy && !found) {
                found = this.foundation[i] != null && this.foundation[i].equals(target);
                i++;
            }
        }
        return found;
    }

    /**
     * retrieves the string at the specified index in the array
     * 
     * @param index the index of the string to retrieve
     * @return the string at the specified index, or null if the index is invalid
     */
    public String get(int index) {
        String string = null;
        if (index >= 0 && this.foundation != null && index < this.foundation.length) {
            string = this.foundation[index];
        }
        return string;
    }

    /**
     * removes the string at the specified index in the array and sets its position
     * to null then it moves every element to the right of the removed element, one
     * position to the left the position of the last element to be copied to the
     * left is then emptied out (null)
     * 
     * @param index the index of the string to remove
     * @return the string that was removed, or null if the index is invalid
     */
    public String remove(int index) {
        String removed = null;
        if (this.occupancy > 0 && index >= 0 && index < this.foundation.length) {
            removed = this.foundation[index];
            this.foundation[index] = null;
            for (int i = index; i < occupancy - 1; i++) {
                this.foundation[i] = this.foundation[i + 1];
            }
            this.foundation[occupancy - 1] = null;
            this.occupancy--;
        }
        return removed;
    }

    /**
     * deletes the string at the specified index in the array
     * 
     * this method uses this.remove and simply ignores the returned string
     * 
     * @param index the index of the string to delete
     */
    public void delete(int index) {
        String whatEver = remove(index);
    }

    /**
     * resizes the underlying array by increasing its capacity by 1
     * 
     * this method is called internally when the current array reaches its capacity
     * and a new element needs to be inserted
     */
    private void resize() {
        String[] temp = new String[this.foundation.length + 1];
        for (int i = 0; i < this.occupancy; i++) {
            temp[i] = this.foundation[i];
        }
        this.foundation = temp;
    }

    /**
     * inserts a new string into the dynamic array
     * 
     * if the string is not null and the array is full, it will be resized to
     * accommodate the new element
     * 
     * @param string the string to insert into the array
     */
    public void insert(String string) {
        if (string != null) {
            if (this.occupancy == this.foundation.length) {
                this.resize();
            }
            this.foundation[this.occupancy] = string;
            this.occupancy++;
        }
    }

    /**
     * returns a string representation of the dynamicarray
     * @return a string containing the occupancy, capacity, and non-null elements of the array
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MoreDynamicArray: [");
        sb.append("Occupancy: ").append(occupancy).append(", ");
        sb.append("Capacity: ").append(foundation.length).append(", ");
        sb.append("Elements: ");
        
        for (int i = 0; i < occupancy; i++) {
            sb.append(foundation[i]);
            if (i < occupancy - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }

    /**
     * returns the index of the first occurrence of the specified string in the array
     * @param string the string to search for
     * @return the index of the first occurrence of the string, or -1 if not found
     */
    public int index(String string) {
        if (string == null) {
            return -1;
        }
        
        for (int i = 0; i < occupancy; i++) {
            if (string.equals(foundation[i])) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * calculates the usage percentage of the array (non-null elements)
     * @return the usage percentage with two decimal places
     */
    public double usage() {
        if (foundation.length == 0) {
            return 0.0;
        }
        
        double rawUsage = (double) occupancy / foundation.length;
        return Math.round(rawUsage * 100.0) / 100.0;
    }

    /** driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = { "Java", "Python", "C", "C++", "Fortran" };
        MoreDynamicArray test = new MoreDynamicArray(testData);
    }

}
