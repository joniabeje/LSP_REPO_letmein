package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * enhanced password algorithm using java.security.SecureRandom
 * generates passwords with A-Z, a-z, 0-9
 * 
 * @author Jonathan Abeje
 */
class EnhancedAlgorithm implements PasswordAlgorithm {
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    private SecureRandom secureRandom;
    
    /**
     * constructs a new EnhancedAlgorithm instance
     */
    public EnhancedAlgorithm() {
        secureRandom = new SecureRandom();
    }
    
    /**
     * generates a password with letters and digits
     * 
     * @param length the desired length
     * @return a password string with A-Z, a-z, 0-9
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}

