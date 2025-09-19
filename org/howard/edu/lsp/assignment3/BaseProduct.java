package org.howard.edu.lsp.assignment3;

/**
 * Abstract base class for products that demonstrates inheritance.
 * Provides common product attributes and abstract methods for specialized behavior.
 */
public abstract class BaseProduct {
    private String productId;
    private String name;
    private double price;
    private String category;
    
    /**
     * Constructs a BaseProduct with the specified attributes.
     * 
     * @param productId the unique identifier for the product
     * @param name the product name
     * @param price the product price
     * @param category the product category
     */
    public BaseProduct(String productId, String name, double price, String category) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setCategory(category);
    }
    
    // Protected setters for use by constructors and subclasses
    protected void setProductId(String productId) {
        this.productId = productId;
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    protected void setPrice(double price) {
        this.price = price;
    }
    
    protected void setCategory(String category) {
        this.category = category;
    }
    
    // Public getters for controlled access
    public String getProductId() {
        return productId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    /**
     * Abstract method for computing price range - demonstrates polymorphism.
     * Subclasses must implement their own price range calculation logic.
     * 
     * @return the price range category
     */
    public abstract String computePriceRange();
    
    /**
     * Converts the product to CSV row format for output.
     * 
     * @return string array representing the product in CSV format
     */
    public String[] toCsvRow() {
        String formattedPrice = String.format("%.2f", getPrice());
        return new String[]{getProductId(), getName(), formattedPrice, getCategory(), computePriceRange()};
    }
}
