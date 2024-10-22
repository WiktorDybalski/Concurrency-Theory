package Lab3;

public class Meal {
    private final String name;


    public Meal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                '}';
    }
}
