package cafe.branden;

import java.util.List;

public class SecretEntrance implements Problem {
    @Override
    public String solve(String input) {
        GateCode initial = GateCode.initial();

        List<GateCode.Instruction> instructions = GateCode.parseInstructions(input);

        GateCode part1Result = initial.processInstructions(instructions);

        GateCode part2Result = initial.processInstructionsMethod0x434C49434B(instructions);

        return String.format("Part-1=%s,Part-2=%s", part1Result.toString(), part2Result.toString());
    }
}
