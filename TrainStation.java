public class TrainStation {
    /** Name of the station */
    private String name;
    /** Pointer to the next station object */
    private TrainStation next;
    /** Pointer to the previous station object */
    private TrainStation previous;

    /**
     * Basic constructor. All stations are created without stations to point to;
     * i.e, their next and previous fields are set to null. This allows us to create stations
     * first, link them later.
     * 
     * @param name String with name of station to create
     */
    public TrainStation(String name) {
        this.name = name;
        this.next = null;
        this.previous = null;
    }

    /** 
     * Setter for a station's next station.
     */
    public void setNext(TrainStation next) {
        this.next = next;
    }

    /** 
     * Setter for a station's previous station.
     */
    public void setPrevious(TrainStation previous) {
        this.previous = previous;
    }

    /**
     * Getter for the station's name.
     */
    public String getName() {
        return this.name;
    }

    /** 
     * Predicate accessor for next station
     */
    public boolean hasNext() {
        return this.next != null;
    }

    /** 
     * Getter for next station
     */
    public TrainStation getNext() {
        return this.next;
    }

    /** 
     * Getter for previous station
     */
    public TrainStation getPrevious() {
        return this.previous;
    }

    /**
     * Main method for demonstration purposes
     */
    public static void main(String[] args) {
        // Create a few stations
        TrainStation station1 = new TrainStation("Howard");
        TrainStation station2 = new TrainStation("Loyola");
        TrainStation station3 = new TrainStation("Granville");

        // Link the stations
        station1.setNext(station2);
        station2.setPrevious(station1);
        station2.setNext(station3);
        station3.setPrevious(station2);

        // Demonstrate traversal
        TrainStation current = station1;
        while (current != null) {
            System.out.println("Station: " + current.getName());
            current = current.getNext();
        }
    }
}
