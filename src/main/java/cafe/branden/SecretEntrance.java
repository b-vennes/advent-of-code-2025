package cafe.branden;

import java.util.List;

public class SecretEntrance implements Problem {
    @Override
    public String solve(String input) {
        GateCode initial = GateCode.initial();

        List<GateCode.Instruction> instructions = GateCode.parseInstructions(input);

        GateCode result = initial.processInstructions(instructions);

        return String.format(
                "Part-1=%s",
                result.toString()
        );
    }
}
