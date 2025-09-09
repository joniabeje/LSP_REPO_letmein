package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ETL Pipeline for CSV product data transformation
 * Reads products.csv, applies transformations, and writes to transformed_products.csv
 */
public class ETLPipeline {
    
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    // Statistics for run summary
    private int rowsRead = 0;
    private int rowsTransformed = 0;
    private int rowsSkipped = 0;
    
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.runPipeline();
    }
    
    /**
     * Main pipeline execution method
     */
    public void runPipeline() {
        try {
            // Extract phase
            List<String[]> data = extract();
            
            // Transform phase
            List<String[]> transformedData = transform(data);
            
            // Load phase
            load(transformedData);
            
            // Print run summary
            printRunSummary();
            
        } catch (Exception e) {
            System.err.println("Pipeline failed: " + e.getMessage());
        }
    }
    
    /**
     * Extract phase: Read CSV file and parse data
     * @return List of string arrays representing CSV rows (including header)
     */
    private List<String[]> extract() throws IOException {
        List<String[]> data = new ArrayList<>();
        
        // Check if input file exists
        File inputFile = new File(INPUT_FILE);
        if (!inputFile.exists()) {
            throw new IOException("Error: Input file '" + INPUT_FILE + "' not found. Please ensure the file exists in the data/ directory.");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] columns = line.split(",");
                
                // Trim whitespace from each column
                for (int i = 0; i < columns.length; i++) {
                    columns[i] = columns[i].trim();
                }
                
                data.add(columns);
                
                if (!isFirstLine) {
                    rowsRead++;
                }
                isFirstLine = false;
            }
        }
        
        return data;
    }
    
    /**
     * Transform phase: Apply all transformations in the specified order
     * @param data Raw CSV data (including header)
     * @return Transformed data with new PriceRange column
     */
    private List<String[]> transform(List<String[]> data) {
        List<String[]> transformedData = new ArrayList<>();
        
        if (data.isEmpty()) {
            return transformedData;
        }
        
        // Add header row with new PriceRange column
        String[] header = {"ProductID", "Name", "Price", "Category", "PriceRange"};
        transformedData.add(header);
        
        // Process data rows (skip header at index 0)
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            
            // Validate row has correct number of columns
            if (row.length != 4) {
                rowsSkipped++;
                continue;
            }
            
            try {
                String[] transformedRow = transformRow(row);
                transformedData.add(transformedRow);
                rowsTransformed++;
            } catch (Exception e) {
                System.err.println("Warning: Skipping row " + (i + 1) + " due to error: " + e.getMessage());
                rowsSkipped++;
            }
        }
        
        return transformedData;
    }
    
    /**
     * Transform a single row according to the requirements
     * Transform order: (1) uppercase name → (2) discount (if Electronics) → (3) recategorization → (4) price range
     */
    private String[] transformRow(String[] row) throws NumberFormatException {
        String productId = row[0];
        String name = row[1];
        String priceStr = row[2];
        String category = row[3];
        
        // Parse price
        double price = Double.parseDouble(priceStr);
        
        // Store original category for recategorization logic
        String originalCategory = category;
        
        // Step 1: Convert name to UPPERCASE
        name = name.toUpperCase();
        
        // Step 2: Apply 10% discount if Electronics category
        if ("Electronics".equalsIgnoreCase(originalCategory)) {
            price = price * 0.9; // Apply 10% discount
        }
        
        // Round price to 2 decimals (round half up)
        price = roundToTwoDecimals(price);
        
        // Step 3: Recategorization - if post-discount price > $500 AND original category was Electronics
        if (price > 500.00 && "Electronics".equalsIgnoreCase(originalCategory)) {
            category = "Premium Electronics";
        }
        
        // Step 4: Compute PriceRange based on final price
        String priceRange = calculatePriceRange(price);
        
        // Format price to 2 decimal places
        String formattedPrice = String.format("%.2f", price);
        
        return new String[]{productId, name, formattedPrice, category, priceRange};
    }
    
    /**
     * Round price to two decimals using round half up
     */
    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Calculate price range based on final price
     */
    private String calculatePriceRange(double price) {
        if (price >= 0.00 && price <= 10.00) {
            return "Low";
        } else if (price >= 10.01 && price <= 100.00) {
            return "Medium";
        } else if (price >= 100.01 && price <= 500.00) {
            return "High";
        } else { // 500.01 and above
            return "Premium";
        }
    }
    
    /**
     * Load phase: Write transformed data to output CSV file
     */
    private void load(List<String[]> transformedData) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            for (String[] row : transformedData) {
                writer.println(String.join(",", row));
            }
        }
    }
    
    /**
     * Print run summary
     */
    private void printRunSummary() {
        System.out.println("\n=== ETL Pipeline Run Summary ===");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + OUTPUT_FILE);
        System.out.println("Pipeline completed successfully.");
    }
}
