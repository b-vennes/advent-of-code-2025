package cafe.branden.sillyids;

import java.util.Objects;

public record Subtracking(SubID left, SubID right) {
    public sealed interface SillyCheck {}

    public record Silly() implements SillyCheck {}

    public record PossiblySilly() implements SillyCheck {}

    public record NotSilly() implements SillyCheck {}

    public SillyCheck check() {
        int leftLength = left.value().size();
        int rightLength = right.value().size();

        if (Objects.equals(left, right)) {
            return new Silly();
        } else if (leftLength > rightLength) {
            return new PossiblySilly();
        } else {
            return new NotSilly();
        }
    }

    public Subtracking leftAdd(char value) {
        return new Subtracking(left.add(value), right);
    }

    public Subtracking rightAdd(char value) {
        return new Subtracking(left, right.add(value));
    }
}
