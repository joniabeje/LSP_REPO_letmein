package org.howard.edu.lsp.assignment3;

import java.util.*;

/**
 * Main ETL Pipeline orchestrator that demonstrates composition by using ETLProcessor.
 * Maintains the same interface and behavior as Assignment 2 while using object-oriented design.
 */
public class ETLPipeline {
    
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    private final ETLProcessor processor;
    
    /**
     * Constructs an ETLPipeline with a composed ETLProcessor.
     * Demonstrates composition pattern.
     */
    public ETLPipeline() {
        this.processor = new ETLProcessor();
    }
    
    /**
     * Main entry point for the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.runPipeline();
    }
    
    /**
     * Executes the complete ETL pipeline process.
     * Orchestrates the extract, transform, and load phases using the composed processor.
     */
    public void runPipeline() {
        try {
            // Extract phase
            List<Product> products = processor.extractData(INPUT_FILE);
            
            // Transform phase
            List<Product> transformedProducts = processor.transformData(products);
            
            // Load phase
            processor.loadData(transformedProducts, OUTPUT_FILE);
            
            // Print run summary
            processor.printSummary(OUTPUT_FILE);
            
        } catch (Exception e) {
            System.err.println("Pipeline failed: " + e.getMessage());
        }
    }
}