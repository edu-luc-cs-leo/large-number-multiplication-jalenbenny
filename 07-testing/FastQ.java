/**
 * represents a fast queue data structure
 */
public class FastQ {
    private String[] array;
    private int front;
    private int back;
    private int used;
    private String result;

    /**
     * constructs a fastq with given capacity
     * @param capacity the maximum number of elements the queue can hold
     */
    public FastQ(int capacity) {
        array = new String[capacity];
        front = 0;
        back = 0;
        used = 0;
        result = "";
    }

    /**
     * executes an operation on the queue
     * @param operation the operation to perform ("add", "remove", "isEmpty", "isFull", "size")
     * @param value the value to add (only for "add" operation)
     * @return the result of the operation
     */
    public String execute(String operation, String value) {
        result = "";
        if (operation.equals("remove")) {
            if (used > 0) {
                result = array[front];
                array[front] = null;
                front = (front + 1) % array.length;
                used--;
            }
        } else if (operation.equals("add")) {
            if (used < array.length) {
                array[back] = value;
                back = (back + 1) % array.length;
                used++;
                result = "true";
            } else {
                result = "false";
            }
        } else if (operation.equals("isEmpty")) {
            result = Boolean.toString(used == 0);
        } else if (operation.equals("isFull")) {
            result = Boolean.toString(used == array.length);
        } else if (operation.equals("size")) {
            result = Integer.toString(used);
        }
        return result;
    }

    /**
     * main method to demonstrate fastq operations
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        FastQ queue = new FastQ(5);
        System.out.println("Add 'A': " + queue.execute("add", "A"));
        System.out.println("Add 'B': " + queue.execute("add", "B"));
        System.out.println("Remove: " + queue.execute("remove", null));
        System.out.println("Is Empty: " + queue.execute("isEmpty", null));
        System.out.println("Size: " + queue.execute("size", null));
    }
}
