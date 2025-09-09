# Assignment 2 - CSV ETL Pipeline

## Overview
Java program that processes product CSV data with transformations: discount Electronics items, uppercase names, recategorize high-value items, and assign price ranges.

## How to Run
```bash
javac org/howard/edu/lsp/assignment2/ETLPipeline.java
java -cp . org.howard.edu.lsp.assignment2.ETLPipeline
```

## Files
- **Input**: `data/products.csv`
- **Output**: `data/transformed_products.csv`

## Transformations Applied
1. Convert names to UPPERCASE
2. Apply 10% discount to Electronics items
3. Recategorize to "Premium Electronics" if price > $500 after discount
4. Add price range: Low/Medium/High/Premium

## Error Handling
- Missing input file: displays error and exits
- Empty input file: creates output with header only
- Invalid rows: skips and continues processing

## Design Notes
- Uses BigDecimal for precise rounding (round half up)
- Processes transformations in exact order specified
- Maintains relative file paths from project root
