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
    void parseManyInstructions() {
        String input = "L55\nL56\nR77\n";
        List<GateCode.Instruction> expected = List.of(
                new GateCode.RotateLeftInstruction(55),
                new GateCode.RotateLeftInstruction(56),
                new GateCode.RotateRightInstruction(77)
        );

        List<GateCode.Instruction> result = GateCode.parseInstructions(input);

        assertEquals(expected, result);
    }
}