package cafe.branden.sillyids;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public record ProductID(long id) {
    public boolean isSilly() {
        TrackingState result = Long.toString(id)
                .chars()
                .mapToObj(c -> (char) c)
                .map(ProductID::toStateModifier)
                .reduce(x -> x, Function::andThen)
                .apply(new Tracking(List.of()));

        IO.println(result);

        return Objects.equals(result, new IsSilly());
    }

    public static Function<TrackingState, TrackingState> toStateModifier(char value) {
        return state -> switch (state) {
            case IsSilly s -> s;
            case Tracking tracking -> tracking.step(value);
        };
    }

    public sealed interface TrackingState permits IsSilly, Tracking {}

    public record IsSilly() implements TrackingState {}

    public record Tracking(List<Subtracking> value) implements TrackingState {
        public TrackingState step(char value) {
            List<Subtracking> next = Stream.of(
                            Stream.of(new Subtracking(new SubID(List.of(value)), new SubID(List.of()))),
                            this.value.stream()
                                    .filter(st -> st.right().isEmpty())
                                    .map(st -> st.leftAdd(value)),
                            this.value.stream().map(st -> st.rightAdd(value)))
                    .flatMap(s -> s)
                    .toList();

            List<Subtracking> ignoringNonSilly = next.stream()
                    .filter(st -> switch (st.check()) {
                        case Subtracking.Silly ignored -> true;
                        case Subtracking.PossiblySilly ignored -> true;
                        case Subtracking.NotSilly ignored -> false;
                    })
                    .toList();

            if (ignoringNonSilly.stream().anyMatch(st -> Objects.equals(st.check(), new Subtracking.Silly()))) {
                return new IsSilly();
            } else {
                return new Tracking(ignoringNonSilly);
            }
        }
    }
}
