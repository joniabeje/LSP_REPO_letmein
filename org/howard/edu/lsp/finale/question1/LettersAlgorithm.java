package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * letters-only password algorithm using java.util.Random
 * generates passwords with letters only (A-Z, a-z)
 * 
 * @author Jonathan Abeje
 */
class LettersAlgorithm implements PasswordAlgorithm {
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    private Random random;
    
    /**
     * constructs a new LettersAlgorithm instance
     */
    public LettersAlgorithm() {
        random = new Random();
    }
    
    /**
     * generates a password with letters only
     * 
     * @param length the desired length
     * @return a password string with letters only
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}

