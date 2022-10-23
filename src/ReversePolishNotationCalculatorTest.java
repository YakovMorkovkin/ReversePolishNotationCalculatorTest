import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        assertEquals(9,calculator.calculatePolishNotation("4 5 +"),"");
        assertEquals(1,calculator.calculatePolishNotation("5 4 -"),"");
        assertEquals(20,calculator.calculatePolishNotation("4 5 *"),"");
        assertEquals(20,calculator.calculatePolishNotation("4   5    *"),"");
        assertEquals(4,calculator.calculatePolishNotation("1 2 3 4 5 - + * *"),"");


    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                switch (parts[index]) {
                    case "+":
                        numbers.push(operandOne + operandTwo);
                        break;
                    case "-":
                        numbers.push(operandTwo - operandOne);
                        break;
                    case "*":
                        numbers.push(operandOne * operandTwo);
                        break;
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        return part.equals("+")
                || part.equals("-")
                || part.equals("*");
    }
}