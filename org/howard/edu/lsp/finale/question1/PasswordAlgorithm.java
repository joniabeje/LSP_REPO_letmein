package org.howard.edu.lsp.finale.question1;

/**
 * strategy interface for password generation algorithms
 * 
 * @author Jonathan Abeje
 */
interface PasswordAlgorithm {
    /**
     * generates a password of the specified length
     * 
     * @param length the desired length
     * @return a password string
     */
    String generate(int length);
}

