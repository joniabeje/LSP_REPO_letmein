package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * basic password algorithm using java.util.Random
 * generates passwords with digits only (0-9)
 * 
 * @author Jonathan Abeje
 */
class BasicAlgorithm implements PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private Random random;
    
    /**
     * constructs a new BasicAlgorithm instance
     */
    public BasicAlgorithm() {
        random = new Random();
    }
    
    /**
     * generates a password with digits only
     * 
     * @param length the desired length
     * @return a password string with digits only
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}

