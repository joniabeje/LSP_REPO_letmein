package org.howard.edu.lsp.finale.question1;

/**
 * singleton service for generating passwords using different algorithms
 * 
 * Part A and B
 * 
 * @author Jonathan Abeje
 */
public class PasswordGeneratorService {
    
    private static PasswordGeneratorService instance;
    private PasswordAlgorithm algorithm;
    
    private PasswordGeneratorService() {
    }
    
    /**
     * returns the singleton instance
     * 
     * @return the singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * sets the password generation algorithm to use
     * 
     * @param name the name of the algorithm ("basic", "enhanced", or "letters")
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                algorithm = new BasicAlgorithm();
                break;
            case "enhanced":
                algorithm = new EnhancedAlgorithm();
                break;
            case "letters":
                algorithm = new LettersAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
    
    /**
     * generates a password of the specified length
     * 
     * @param length the desired length of the password
     * @return a password string
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException("Algorithm must be set before generating password");
        }
        return algorithm.generate(length);
    }
    
    /*
     * Part C
     * DESIGN PATTERN DOCUMENTATION
     * 
     * Patterns Used:
     * 1. Singleton Pattern
     * 2. Strategy Pattern
     * 
     * Why These Patterns:
     * 
     * Singleton Pattern:
     * - requirement states "only one instance of the service may exist"
     * - provides a single shared access point as required
     * - getInstance() ensures only one instance is created and reused
     * 
     * Strategy Pattern:
     * - requirement states we need "multiple approaches to password generation"
     * - must "allow the caller to select the approach at run time"
     * - must "support future expansion of password-generation approaches"
     * - must "make password-generation behavior swappable"
     * - strategy pattern encapsulates each algorithm (basic, enhanced, letters)
     *   in separate classes that implement a common interface
     * - allows algorithms to be swapped at runtime via setAlgorithm()
     * - new algorithms can be added by creating new strategy classes without
     *   modifying existing client code or the PasswordGeneratorService class
     */
}

