package Lab5;

public record Pair<T, U>(T first, U second) {
    @Override
    public String toString() {
        return "(" + first + ", " + second + ')';
    }
}

