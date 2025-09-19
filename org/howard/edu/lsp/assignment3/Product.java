package org.howard.edu.lsp.assignment3;

/**
 * Concrete Product class that extends BaseProduct and demonstrates inheritance and polymorphism.
 * Implements the abstract computePriceRange method with specific business logic.
 */
public class Product extends BaseProduct {
    
    /**
     * Constructs a Product with the specified attributes.
     * 
     * @param productId the unique identifier for the product
     * @param name the product name
     * @param price the product price
     * @param category the product category
     */
    public Product(String productId, String name, double price, String category) {
        super(productId, name, price, category);
    }
    
    /**
     * Computes the price range based on the product's price.
     * Demonstrates polymorphism by providing concrete implementation of abstract method.
     * 
     * @return the price range category
     */
    @Override
    public String computePriceRange() {
        double price = getPrice();
        if (price >= 0.00 && price <= 10.00) {
            return "Low";
        } else if (price >= 10.01 && price <= 100.00) {
            return "Medium";
        } else if (price >= 100.01 && price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    /**
     * Creates a Product from a CSV row.
     * 
     * @param csvRow the CSV row as a string array
     * @return a new Product instance
     * @throws NumberFormatException if the price cannot be parsed
     * @throws IllegalArgumentException if the CSV row is invalid
     */
    public static Product fromCsvRow(String[] csvRow) throws NumberFormatException, IllegalArgumentException {
        if (csvRow.length != 4) {
            throw new IllegalArgumentException("CSV row must have exactly 4 columns");
        }
        
        String productId = csvRow[0].trim();
        String name = csvRow[1].trim();
        double price = Double.parseDouble(csvRow[2].trim());
        String category = csvRow[3].trim();
        
        return new Product(productId, name, price, category);
    }
}