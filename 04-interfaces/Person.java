import java.util.Random;

public class Person implements Comparable<Person>, SillyActions {
    private static final String DEFAULT_LAST_NAME = "LNU";
    private static final String DEFAULT_FIRST_NAME = "FNU";
    private static final int DEFAULT_YEAR_BORN = 1800;
    private String firstName;
    private String lastName;
    private int yearBorn;

    public Person(String firstName, String lastName, int yearBorn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBorn = yearBorn;
    }

    public Person(String firstName) {
        this(firstName, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    public Person() {
        this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    public int compareTo(Person other) {
        return other.getYearBorn() - this.yearBorn;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", yearBorn=" + yearBorn + "]";
    }

    @Override
    public void makeRandomSound() {
        Random random = new Random();
        int index = random.nextInt(PoemWords.words.length);
        System.out.println("*" + PoemWords.words[index] + "*");
    }

    @Override
    public void performSillyDance() {
        System.out.println(firstName + " is doing a silly little dance:");
        System.out.println("* Takes a step to the left, hops twice backwards, then does one big leap forward*");
    }

    @Override
    public String reciteAlphabetBackwards() {
        StringBuilder sb = new StringBuilder("zyxwvutsrqponmlkjihgfdcba");
        sb.deleteCharAt(8); // Forget one letter
        return sb.toString();
    }

    @Override
    public void countToTenWeirdly() {
        System.out.println("1, 3, 5, 7, 9, 11!");
    }

    @Override
    public String createWhimsicalPoem(String topic) {
        StringBuilder poem = new StringBuilder();
        poem.append("A whimsical poem about ").append(topic).append(":\n");
        poem.append(PoemWords.words[new Random().nextInt(PoemWords.words.length)]).append(" ");
        poem.append(PoemWords.words[new Random().nextInt(PoemWords.words.length)]).append(" ");
        poem.append(PoemWords.words[new Random().nextInt(PoemWords.words.length)]).append(".");
        return poem.toString();
    }

    @Override
    public void winStateLottery() {
        System.out.println("*Drumroll please...* The winning HOOSIER LOTTERY numbers are: 3, 22, 21, 7, 88, 8.");
    }
}

