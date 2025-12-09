package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Part D and E
 * 
 * @author Jonathan Abeje
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service);
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second);
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        });
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertEquals(10, p.length());
        for (char c : p.toCharArray()) {
            assertTrue(Character.isDigit(c), "basic algorithm should only generate digits");
        }
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertEquals(12, p.length());
        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetterOrDigit(c), "enhanced algorithm should generate letters and digits");
        }
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertEquals(8, p.length());
        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetter(c), "letters algorithm should only generate letters");
        }
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertEquals(10, p1.length());
        for (char c : p1.toCharArray()) {
            assertTrue(Character.isDigit(c));
        }

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertEquals(10, p2.length());
        for (char c : p2.toCharArray()) {
            assertTrue(Character.isLetter(c));
        }

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertEquals(10, p3.length());
        for (char c : p3.toCharArray()) {
            assertTrue(Character.isLetterOrDigit(c));
        }
    }
}

