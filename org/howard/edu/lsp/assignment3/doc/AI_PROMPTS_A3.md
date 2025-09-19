# CSCI 363 – Assignment 3: AI Usage Documentation

**AI Usage: Claude 4**

**Summary:** I used AI assistance to help implement portions of my object-oriented design ideas and refine the redesign process from Assignment 2 to Assignment 3. I brainstormed my redesign using OO concepts and class structure, and used AI to help with implementation details and best practices.

## Prompt 1: Initial Design Approach
**Prompt:** "I have an ETL pipeline in a single class with all the logic mixed together. I want to redesign it to be more object-oriented using inheritance, encapsulation, and composition. What's a good approach to separate the concerns while keeping the same functionality?"

**AI Response (excerpt):** "Consider these design patterns: 1. Extract-Transform-Load separation: Create separate classes for each phase. 2. Product abstraction: Use inheritance with BaseProduct abstract class and concrete Product implementation. 3. Composition: Have a main pipeline class that uses other classes rather than doing everything itself. 4. Encapsulation: Make fields private with controlled access through methods."

**How it was used:** I had already planned to use inheritance and composition but needed guidance on the specific structure. This response helped me decide on a 4-class approach (BaseProduct, Product, ETLProcessor, ETLPipeline) instead of trying to create too many classes. I used the suggestion to separate ETL phases into the ETLProcessor class.

## Prompt 2: Inheritance Implementation
**Prompt:** "I want to use inheritance to demonstrate polymorphism in my Product class. Should I create an abstract base class with an abstract method, and how do I make sure it's actually used in the pipeline?"

**AI Response (excerpt):** "Yes, create a BaseProduct abstract class with an abstract computePriceRange() method. Make sure your pipeline actually calls this method on the Product objects rather than using a separate utility method. This way you demonstrate true polymorphism where the same method call behaves differently based on the object type."

**How it was used:** I was planning to use inheritance but wasn't sure about the best way to demonstrate polymorphism in practice. This response helped me structure the BaseProduct with an abstract method and ensure that the ETL process actually uses the polymorphic method call during transformation.

## Prompt 3: Maintaining Identical Functionality
**Prompt:** "How can I make sure my object-oriented redesign produces exactly the same output as my original Assignment 2 implementation, especially with the transformation order and rounding?"

**AI Response (excerpt):** "Keep the transformation sequence identical: 1. Name uppercase, 2. Electronics discount, 3. Category change, 4. Price range. Use the same rounding approach (Math.round(value * 100.0) / 100.0). Test by comparing the output files line by line and verifying the same statistics are reported."

**How it was used:** I knew I needed to maintain the same functionality but wanted to make sure about the implementation details. This response confirmed my approach to keep the transformation order and helped me ensure I used the same rounding logic in my new classes.

## Prompt 4: Encapsulation Best Practices
**Prompt:** "I'm implementing encapsulation with private fields and getters/setters in my BaseProduct class. What's the best way to handle this with inheritance where the subclass needs access to the fields?"

**AI Response (excerpt):** "Use private fields in the base class with protected setter methods for constructor initialization. Provide public getters for external access. In the subclass, use the getter methods rather than direct field access to maintain proper encapsulation principles."

**How it was used:** I was implementing encapsulation but needed clarification on the inheritance details. This response helped me structure the BaseProduct class with proper private fields and protected setters, ensuring good encapsulation practices while allowing inheritance to work correctly.

## Prompt 5: Code Organization and Structure
**Prompt:** "I have my 4 classes designed - BaseProduct, Product, ETLProcessor, and ETLPipeline. How should I organize the responsibilities to best demonstrate composition and single responsibility principle?"

**AI Response (excerpt):** "ETLPipeline should be the orchestrator that just coordinates the process. ETLProcessor should handle all the actual ETL logic and statistics. BaseProduct/Product should focus only on product data and behavior. This way each class has a single, clear responsibility and ETLPipeline demonstrates composition by using ETLProcessor."

**How it was used:** I had the classes designed but wanted to make sure the responsibilities were well-separated. This response helped me finalize the design where ETLPipeline is just a coordinator, ETLProcessor does the heavy lifting, and the Product hierarchy handles data encapsulation and polymorphism.