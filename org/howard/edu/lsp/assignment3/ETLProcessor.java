package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.*;

/**
 * Handles the ETL processing logic with proper separation of concerns.
 * Demonstrates composition and encapsulation by managing the transformation pipeline
 * and statistics tracking separately from the main pipeline orchestration.
 */
public class ETLProcessor {
    private int rowsRead;
    private int rowsTransformed;
    private int rowsSkipped;
    
    /**
     * Constructs an ETLProcessor with zero statistics.
     */
    public ETLProcessor() {
        this.rowsRead = 0;
        this.rowsTransformed = 0;
        this.rowsSkipped = 0;
    }
    
    /**
     * Extracts product data from the CSV file.
     * 
     * @param inputFile path to the input CSV file
     * @return list of Product objects parsed from the CSV
     * @throws IOException if there's an error reading the file
     */
    public List<Product> extractData(String inputFile) throws IOException {
        List<Product> products = new ArrayList<>();
        
        File file = new File(inputFile);
        if (!file.exists()) {
            throw new IOException("Error: Input file '" + inputFile + "' not found. Please ensure the file exists in the data/ directory.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                try {
                    String[] columns = line.split(",");
                    for (int i = 0; i < columns.length; i++) {
                        columns[i] = columns[i].trim();
                    }
                    
                    Product product = Product.fromCsvRow(columns);
                    products.add(product);
                    rowsRead++;
                    
                } catch (Exception e) {
                    System.err.println("Warning: Skipping row " + lineNumber + " due to error: " + e.getMessage());
                    rowsSkipped++;
                }
            }
        }
        
        return products;
    }
    
    /**
     * Transforms the list of products according to Assignment 2 requirements.
     * Applies transformations in the exact same order as the original implementation.
     * 
     * @param products list of products to transform
     * @return list of transformed products
     */
    public List<Product> transformData(List<Product> products) {
        List<Product> transformedProducts = new ArrayList<>();
        
        for (Product product : products) {
            try {
                // Step 1: Convert name to UPPERCASE
                product.setName(product.getName().toUpperCase());
                
                // Step 2: Apply 10% discount if Electronics category
                if ("Electronics".equalsIgnoreCase(product.getCategory())) {
                    double discountedPrice = product.getPrice() * 0.9;
                    product.setPrice(Math.round(discountedPrice * 100.0) / 100.0);
                }
                
                // Step 3: Recategorization if post-discount price > $500 AND original category was Electronics
                if (product.getPrice() > 500.00 && "Electronics".equalsIgnoreCase(product.getCategory())) {
                    product.setCategory("Premium Electronics");
                }
                
                // Step 4: Price range is computed automatically via polymorphism in toCsvRow()
                
                transformedProducts.add(product);
                rowsTransformed++;
                
            } catch (Exception e) {
                System.err.println("Warning: Skipping product " + product.getProductId() + " due to transformation error: " + e.getMessage());
                rowsSkipped++;
            }
        }
        
        return transformedProducts;
    }
    
    /**
     * Loads the transformed product data to the output CSV file.
     * 
     * @param products list of transformed products to write
     * @param outputFile path to the output CSV file
     * @throws IOException if there's an error writing to the file
     */
    public void loadData(List<Product> products, String outputFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("ProductID,Name,Price,Category,PriceRange");
            
            for (Product product : products) {
                String[] csvRow = product.toCsvRow();
                writer.println(String.join(",", csvRow));
            }
        }
    }
    
    /**
     * Prints the pipeline run summary.
     * 
     * @param outputFile the path to the output file
     */
    public void printSummary(String outputFile) {
        System.out.println("\n=== ETL Pipeline Run Summary ===");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputFile);
        System.out.println("Pipeline completed successfully.");
    }
}
