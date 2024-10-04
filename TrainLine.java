/**
 * represents a train line with stations
 * this class manages a linked list of train stations
 */
public class TrainLine {

    private String name;
    private TrainStation head;
    private TrainStation tail;
    private int numberOfStations;

    /**
     * constructs a trainline with a name and an initial station
     *
     * @param name the name of the train line
     * @param head the first station in the line
     */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            this.numberOfStations = 1;
        }
        this.tail = head;
    }

    /**
     * constructs an empty trainline with only a name
     *
     * @param name the name of the train line
     */
    public TrainLine(String name) {
        this(name, null);
    }

    /**
     * adds a new station to the end of the train line
     *
     * @param name the name of the new station
     */
    public void add(String name) {
        TrainStation newStation = new TrainStation(name);
        if (this.head == null) {
            this.head = newStation;
        } else {
            this.tail.setNext(newStation);
            newStation.setPrevious(this.tail);
        }
        this.tail = newStation;
        this.numberOfStations++;
    }

    /**
     * returns the number of stations in the train line
     *
     * @return the number of stations
     */
    public int getNumberOfStations() {
        return numberOfStations;
    }

    /**
     * checks if a station with the given name exists in the train line
     *
     * @param name the name of the station to search for
     * @return true if the station exists, false otherwise
     */
    public boolean contains(String name) {
        TrainStation current = head;
        while (current != null) {
            if (current.getName().equals(name)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * finds the index of a station with the given name
     *
     * @param name the name of the station to find
     * @return the index of the station if found, -1 otherwise
     */
    public int indexOf(String name) {
        TrainStation current = head;
        int index = 0;
        while (current != null) {
            if (current.getName().equals(name)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    /**
     * returns a string representation of the train line in reverse order
     *
     * @return a string with station names in reverse order, each on a new line
     */
    public String reverseList() {
        if (head == null) {
            return "";
        }
        return reverseListHelper(tail);
    }

    /**
     * helper method for reverselist
     *
     * @param station the current station in the recursion
     * @return a string representation of stations from the current to the head
     */
    private String reverseListHelper(TrainStation station) {
        if (station == null) {
            return "";
        }
        return station.getName() + "\n" + reverseListHelper(station.getPrevious());
    }

    /**
     * checks if the train line is empty
     *
     * @return true if the train line has no stations, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * main method to test the trainline class functionality
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Guard tests
        redLineSB.indexOf(null);
        redLineSB.contains(null);
        // Test indexOf on existing values
        boolean indexOfTestExisting = true;
        for (int i = 0; i < stationNames.length; i++) {
            indexOfTestExisting = (indexOfTestExisting && (redLineSB.indexOf(stationNames[i]) == i));
        }
        // Test indexOf for non existing station
        boolean indexOfTestNotExisting = (redLineSB.indexOf(randomName) == -1);
        // Test indexOf on empty line
        boolean indexOfTestingEmpty = (brownLineSB.indexOf(stationNames[0]) == -1);
        // Test contains for existing stations
        boolean containsTestExisting = true;
        for (String station : stationNames) {
            containsTestExisting = (containsTestExisting && redLineSB.contains(station));
        }
        // Test contains for non existing values
        boolean containsTestNonExisting = (!redLineSB.contains(randomName));
        // Test reverse list
        String expectedReverseList = "";
        for (int i = stationNames.length - 1; i >= 0; i--) {
            expectedReverseList = expectedReverseList + stationNames[i] + "\n";
        }
        boolean reverseListTest = redLineSB.reverseList().equals(expectedReverseList);
        // Reporting strings
        final String PASS = "Pass";
        final String FAIL = "Fail";
        String reportIndexOfTestExisting = (indexOfTestExisting) ? PASS : FAIL;
        String formatIndexOfTestExisting = "\n\nindexOf test for existing values: ......... %s";
        String reportIndexOfTestNonExisting = (indexOfTestNotExisting) ? PASS : FAIL;
        String formatIndexOfTestNonExisting = "\nindexOf test for non existing values: ..... %s";
        String reportIndexOfTestEmpty = (indexOfTestingEmpty) ? PASS : FAIL;
        String formatIndexOfTestEmpty = "\nindexOf test for empty object: ............ %s";
        String reportContaisTestExisting = (containsTestExisting) ? PASS : FAIL;
        String formatContainsTestExisting = "\ncontains test for existing values: ........ %s";
        String reportContainsTestNonExisting = (containsTestNonExisting) ? PASS : FAIL;
        String formatContainsTestNonExisting = "\ncontains test for non existing values: .... %s";
        String reportReverseListTest = (reverseListTest) ? PASS : FAIL;
        String formatReverseListTest = "\nreverseList test: ......................... %s\n\n";
        System.out.printf(formatIndexOfTestExisting, reportIndexOfTestExisting);
        System.out.printf(formatIndexOfTestEmpty, reportIndexOfTestEmpty);
        System.out.printf(formatIndexOfTestNonExisting, reportIndexOfTestNonExisting);
        System.out.printf(formatContainsTestExisting, reportContaisTestExisting);
        System.out.printf(formatContainsTestNonExisting, reportContainsTestNonExisting);
        System.out.printf(formatReverseListTest, reportReverseListTest);
        System.out.println(redLineSB.reverseList());
    }
}
