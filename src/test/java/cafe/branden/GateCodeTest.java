package cafe.branden;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GateCodeTest {

    @Test
    void processInstructionsEmptyReturnsInitial() {
        GateCode gateCode = new GateCode(new Dial(50, 100), 0);

        GateCode expected = gateCode;

        GateCode result = gateCode.processInstructions(List.of());

        assertEquals(expected, result);
    }

    @Test
    void processInstructionsAtLeastTwoYieldsUpdated() {
        GateCode gateCode = new GateCode(new Dial(50, 100), 0);
        List<GateCode.Instruction> instructions = List.of(
                new GateCode.RotateLeftInstruction(45),
                new GateCode.RotateRightInstruction(10),
                new GateCode.RotateLeftInstruction(15),
                new GateCode.RotateLeftInstruction(24),
                new GateCode.RotateRightInstruction(24),
                new GateCode.RotateRightInstruction(110)
        );
        GateCode expected = new GateCode(new Dial(10, 100), 2);

        GateCode result = gateCode.processInstructions(instructions);

        assertEquals(expected, result);
    }

    @Test
    void withDial() {
    }

    @Test
    void withCode() {
    }

    @Test
    void parseIntructionsLeft() {
        String input = "L22";
        List<GateCode.Instruction> expected = List.of(
                new GateCode.RotateLeftInstruction(22)
        );

        List<GateCode.Instruction> result = GateCode.parseInstructions(input);

        assertEquals(expected, result);
    }


    @Test
    void parseIntructionsRight() {
        String input = "R999";
        List<GateCode.Instruction> expected = List.of(
                new GateCode.RotateRightInstruction(999)
        );

        List<GateCode.Instruction> result = GateCode.parseInstructions(input);

        assertEquals(expected, result);
    }


    @Test
    void parseInstructionsMany() {
        String input = "L55\nL56\nR77\n";
        List<GateCode.Instruction> expected = List.of(
                new GateCode.RotateLeftInstruction(55),
                new GateCode.RotateLeftInstruction(56),
                new GateCode.RotateRightInstruction(77)
        );

        List<GateCode.Instruction> result = GateCode.parseInstructions(input);

        assertEquals(expected, result);
    }

    @Test
    void processInstructionsMethod0x434C49434BBasic() {
        List<GateCode.Instruction> instructions = List.of(
                new GateCode.RotateLeftInstruction(55), // 1 | 95
                new GateCode.RotateLeftInstruction(75),  // 1 | 20
                new GateCode.RotateRightInstruction(120), // 2 | 40
                new GateCode.RotateLeftInstruction(40) // 3 | 0
        );
        GateCode initial = GateCode.initial();

        GateCode expected = new GateCode(
                new Dial(0, 100),
                3
        );

        GateCode result = initial.processInstructionsMethod0x434C49434B(instructions);

        assertEquals(expected, result);
    }

    @Test
    void processInstructionsMethod0x434C49434BExample() {
        String input = "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82\n";

        List<GateCode.Instruction> instructions = GateCode.parseInstructions(input);
        GateCode code = GateCode.initial();
        GateCode expected = new GateCode(
                new Dial(32, 100),
                6
        );

        GateCode result = code.processInstructionsMethod0x434C49434B(instructions);

        assertEquals(expected, result);
    }
}