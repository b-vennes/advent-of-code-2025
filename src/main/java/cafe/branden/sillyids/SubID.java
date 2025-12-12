package cafe.branden.sillyids;

import java.util.List;
import java.util.stream.Stream;

public record SubID(List<Character> value) {
    public SubID add(char value) {
        return new SubID(Stream.concat(this.value.stream(), Stream.of(value)).toList());
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public static SubID fromString(String s) {
        List<Character> sAsList = s.chars().mapToObj(c -> (char) c).toList();

        return new SubID(sAsList);
    }

    public static SubID empty() {
        return new SubID(List.of());
    }
}
