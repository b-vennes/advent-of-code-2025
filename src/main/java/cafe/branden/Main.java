package cafe.branden;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    static boolean isHelp(String arg) {
        return Objects.equals(arg, "--help") || Objects.equals(arg, "help") || Objects.equals(arg, "-h");
    }

    private final static String HELP_MESSAGE = "Use 'java -jar advent-of-code-2025 day X input.txt' where X is a number and input.txt is a file path to an input file.";

    static void main(String[] args) {
        if (Arrays.stream(args).anyMatch(Main::isHelp)) {
            IO.println(
                    String.format(
                            "Help requested.  %s",
                            HELP_MESSAGE
                    )
            );
            return;
        }

        if (args.length < 3) {
            IO.println(
                    String.format(
                        "Expected at least three arguments to program.  %s", HELP_MESSAGE
                    )
            );
        }

        int day = Integer.parseInt(args[1]);
        Path input = Paths.get(args[2]);

        IO.println(String.format("Running day %d with input %s.", day, input.toAbsolutePath()));
    }
}
