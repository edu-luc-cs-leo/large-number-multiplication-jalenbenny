/**
 * represents a fast queue data structure
 */
public class FastQ {
    // array to store queue elements
    private String[] array;
    // index of the front element
    private int front;
    // index where the next element will be inserted
    private int back;
    // number of elements currently in the queue
    private int used;
    // stores the result of the last operation
    private String result;

    /**
     * constructs a fastq with given capacity
     * @param capacity the maximum number of elements the queue can hold
     */
    public FastQ(int capacity) {
        // initialize the array with given capacity
        array = new String[capacity];
        // set front and back to 0, as queue is empty
        front = 0;
        back = 0;
        // no elements used initially
        used = 0;
        // initialize result as empty string
        result = "";
    }

    /**
     * executes an operation on the queue
     * @param operation the operation to perform ("add", "remove", "isEmpty", "isFull", "size")
     * @param value the value to add (only for "add" operation)
     * @return the result of the operation
     */
    public String execute(String operation, String value) {
        // reset result before each operation
        result = "";
        if (operation.equals("remove")) {
            if (used > 0) {
                // remove element from front of queue
                result = array[front];
                array[front] = null;
                // move front pointer, wrap around if necessary
                front = (front + 1) % array.length;
                used--;
            }
        } else if (operation.equals("add")) {
            if (used < array.length) {
                // add element to back of queue
                array[back] = value;
                // move back pointer, wrap around if necessary
                back = (back + 1) % array.length;
                used++;
                result = "true";
            } else {
                // queue is full, can't add
                result = "false";
            }
        } else if (operation.equals("isEmpty")) {
            // check if queue is empty
            result = Boolean.toString(used == 0);
        } else if (operation.equals("isFull")) {
            // check if queue is full
            result = Boolean.toString(used == array.length);
        } else if (operation.equals("size")) {
            // return current size of queue
            result = Integer.toString(used);
        }
        return result;
    }

    /**
     * main method to demonstrate fastq operations
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // create a queue with capacity 5
        FastQ queue = new FastQ(5);
        // demonstrate add operation
        System.out.println("Add 'A': " + queue.execute("add", "A"));
        System.out.println("Add 'B': " + queue.execute("add", "B"));
        // demonstrate remove operation
        System.out.println("Remove: " + queue.execute("remove", null));
        // check if queue is empty
        System.out.println("Is Empty: " + queue.execute("isEmpty", null));
        // check size of queue
        System.out.println("Size: " + queue.execute("size", null));
    }
}
