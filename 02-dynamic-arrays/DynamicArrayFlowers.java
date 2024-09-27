public class DynamicArrayFlowers {
    /** default size for the underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** underlying array for this class */
    private String[] foundation;

    /** # of elements currently in the array */
    private int size;

    /** 
     * Full constructor. Initializes  underlying array to the specified size. 
     * The size HAS be a positive, non zero value. Otherwise the constructor uses the default size value. 
     */
    public DynamicArrayFlowers(int size) {
        // If size <= 0 use default 
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.size = 0;
    }

    /** Array-based constructor -- used for testing */
    public DynamicArrayFlowers(String[] data) {
        if (data == null) {
            this.foundation = new String[DEFAULT_SIZE];
            this.size = 0;
        } else {
            this.foundation = data;
            this.size = data.length;
        }
    }

    /** 
     * Default constructor 
     */
    public DynamicArrayFlowers() {
        this(DEFAULT_SIZE);
    }

    /**
     * contains method:
     * 
     * function contains(target):
     *     if foundation is null OR target is null:
     *         return false
     *     for each element in foundation from 0 to size-1:
     *         if element equals target:
     *             return true
     *     return false
     */
    public boolean contains(String target) {
        if (foundation == null || target == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (target.equals(foundation[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * get method:
     * 
     * function get(index):
     *     if index < 0 OR index >= size:
     *         return null
     *     return foundation[index]
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return foundation[index];
    }

    /**
     * remove method:
     * 
     * function remove(index):
     *     if index < 0 OR index >= size:
     *         return null
     *     removed = foundation[index]
     *     for i from index to size-2:
     *         foundation[i] = foundation[i+1]
     *     foundation[size-1] = null
     *     size = size - 1
     *     return is removed
     */
    public String remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        String removed = foundation[index];
        for (int i = index; i < size - 1; i++) {
            foundation[i] = foundation[i + 1];
        }
        foundation[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * delete method:
     * 
     * function delete(index):
     *     call remove(index)
     */
    public void delete(int index) {
        remove(index);
    }

    /**
     * insert method:
     * 
     * function insert(str):
     *     if size equals foundation.length:
     *         newFoundation = new array with twice the size of foundation
     *         copy all elements from foundation to newFoundation
     *         foundation = newFoundation
     *     foundation[size] = str
     *     size = size + 1
     */
    public void insert(String str) {
        if (size == foundation.length) {
            // Double the size of the array if it's full
            String[] newFoundation = new String[foundation.length * 2];
            System.arraycopy(foundation, 0, newFoundation, 0, foundation.length);
            foundation = newFoundation;
        }
        foundation[size] = str;
        size++;
    }

    /** Driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";

        // Test data
        String[] testData = {"Java", "Python", "C", "C++", "Fortran"};
        DynamicArrayFlowers test = new DynamicArrayFlowers(testData);
        DynamicArrayFlowers tset = new DynamicArrayFlowers(null);

        String testContainsNullTarget = (!test.contains(null)) ? PASS : FAIL;
        String testContainsEmptyData =  (!tset.contains("Java")) ? PASS : FAIL;
        String testContainsExisting = (test.contains(testData[1])) ? PASS : FAIL;
        String testContainsNonExisting = (!test.contains(NON_EXISTING)) ? PASS : FAIL;
        String testGetNegative = (test.get(-1) == null) ? PASS : FAIL;
        String testGet = (test.get(0).equals(testData[0])) ? PASS : FAIL;
        String testGetOutOfBounds = (test.get(testData.length+1)==null) ? PASS : FAIL;

        System.out.printf("\nTest for contains(null): ............... %s", testContainsNullTarget);
        System.out.printf("\nTest for contains on null foundation: .. %s", testContainsEmptyData);
        System.out.printf("\nTest for contains (existing): .......... %s", testContainsExisting);
        System.out.printf("\nTest for contains (non existing): ...... %s", testContainsNonExisting);
        System.out.printf("\nTest for get(-1): ...................... %s", testGetNegative);
        System.out.printf("\nTest for get(0): ....................... %s", testGet);
        System.out.printf("\nTest for get(out of bounds): ........... %s\n", testGetOutOfBounds);

        // Additional tests for added methods
        String removed = test.remove(1);
        String testRemove = (removed.equals("Python") && test.size == 4) ? PASS : FAIL;
        System.out.printf("Test for remove: ........................ %s\n", testRemove);

        test.delete(0);
        String testDelete = (!test.contains("Java") && test.size == 3) ? PASS : FAIL;
        System.out.printf("Test for delete: ........................ %s\n", testDelete);

        test.insert("Flowers");
        String testInsert = (test.contains("Flowers") && test.size == 4) ? PASS : FAIL;
        System.out.printf("Test for insert: ........................ %s\n", testInsert);
    }
}
