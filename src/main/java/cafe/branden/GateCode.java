package cafe.branden;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record GateCode(Dial dial, int code) {
    public GateCode processInstructions(List<Instruction> instructionList) {
        return instructionList.stream()
                .map(Instruction::toGateCodeModifier)
                .reduce(Function::andThen)
                .orElse(gateCode -> gateCode)
                .apply(this);
    }

    public GateCode processInstructionsMethod0x434C49434B(List<Instruction> instructionList) {
        return instructionList.stream()
                .map(Instruction::toGateCodeModifier0x434C49434B)
                .reduce(Function::andThen)
                .orElse(gateCode -> gateCode)
                .apply(this);
    }

    public static GateCode initial() {
        return new GateCode(Dial.initial(), 0);
    }

    public GateCode withDial(Dial dial) {
        return new GateCode(dial, this.code);
    }

    public GateCode withCode(int code) {
        return new GateCode(this.dial, code);
    }

    public static List<Instruction> parseInstructions(String input) {
        Pattern parsingPattern = Pattern.compile(
                "(?<direction>L|R)(?<amount>[0-9]+)"
        );
        Matcher parsed = parsingPattern.matcher(input);

        ArrayList<Instruction> parsedInstructions = new ArrayList<>();

        while (parsed.find()) {
            String parsedDirection = parsed.group("direction");
            String parsedAmount = parsed.group("amount");

            int amount = Integer.parseInt(parsedAmount);

            if (Objects.equals(parsedDirection, "L")) {
                parsedInstructions.add(new RotateLeftInstruction(amount));
            } else {
                parsedInstructions.add(new RotateRightInstruction(amount));
            }
        }

        return parsedInstructions;
    }

    public interface Instruction {
        Function<GateCode, GateCode> toGateCodeModifier();

        Function<GateCode, GateCode> toGateCodeModifier0x434C49434B();
    }

    public record RotateLeftInstruction(int amount) implements Instruction {
        @Override
        public Function<GateCode, GateCode> toGateCodeModifier() {
            return (gateCode) -> {
                Dial nextDial = gateCode.dial.rotateLeft(amount);
                int nextCode = nextDial.location() == 0 ?
                        gateCode.code + 1 :
                        gateCode.code;
                return gateCode.withDial(nextDial).withCode(nextCode);
            };
        }

        @Override
        public Function<GateCode, GateCode> toGateCodeModifier0x434C49434B() {
            return (gateCode) -> {
                Dial.CountingZeroes step = gateCode.dial.rotateLeftCountingZeroes(amount);
                int nextCode = gateCode.code + step.counted();
                return gateCode.withCode(nextCode).withDial(step.dial());
            };
        }
    }

    public record RotateRightInstruction(int amount) implements Instruction {
        @Override
        public Function<GateCode, GateCode> toGateCodeModifier() {
            return (gateCode) -> {
                Dial nextDial = gateCode.dial.rotateRight(amount);
                int nextCode = nextDial.location() == 0 ?
                        gateCode.code + 1 :
                        gateCode.code;
                return gateCode.withDial(nextDial).withCode(nextCode);
            };
        }

        @Override
        public Function<GateCode, GateCode> toGateCodeModifier0x434C49434B() {
            return (gateCode) -> {
                Dial.CountingZeroes step = gateCode.dial.rotateRightCountingZeroes(amount);
                int nextCode = gateCode.code + step.counted();
                return gateCode.withCode(nextCode).withDial(step.dial());
            };
        }
    }
}
