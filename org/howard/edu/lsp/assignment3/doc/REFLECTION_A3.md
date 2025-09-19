# Assignment 3 Reflection: Object-Oriented Redesign

## What's Different Between Assignment 2 and Assignment 3?

Looking back at my Assignment 2 code, everything was crammed into one huge `ETLPipeline` class. It was like having one person trying to do every job in a company - the same class was reading files, transforming data, writing output, and tracking statistics. It worked, but it was messy and hard to understand.

For Assignment 3, I broke things apart into 4 classes that each have their own job:
- **BaseProduct** and **Product**: Handle product data and behavior
- **ETLProcessor**: Does all the actual ETL work
- **ETLPipeline**: Just coordinates everything

It's like going from a one-person company to having different departments that specialize in what they do best.

## How Assignment 3 is More Object-Oriented

The biggest difference is that Assignment 3 actually *uses* object-oriented concepts instead of just having them exist:

**Encapsulation**: In Assignment 2, everything was basically public or accessible to everything else. Now the Product classes hide their data behind private fields, and you have to use getters and setters to access them. This means the data is protected and can't be messed with accidentally.

**Inheritance**: I created a `BaseProduct` abstract class with a `computePriceRange()` method that the concrete `Product` class has to implement. This wasn't just for show - the ETL process actually calls this method, so inheritance is actively being used.

**Composition**: Instead of the main pipeline doing everything itself, it now uses an `ETLProcessor` object to do the heavy lifting. The pipeline doesn't know how the processing happens, it just asks the processor to do its job.

**Polymorphism**: When the code calls `product.computePriceRange()`, it doesn't know or care that it's calling the specific implementation in the Product class. It just knows it's calling the method on a BaseProduct, and the right implementation gets executed automatically.

## Object-Oriented Concepts in Action

**Object and Class**: Each class represents something real and specific. Product represents actual product data, ETLProcessor represents the transformation logic, and ETLPipeline represents the overall coordination.

**Encapsulation**: All the important data in BaseProduct is private. You can't just randomly change a product's price or category - you have to go through the proper methods. This prevents bugs and makes the code more reliable.

**Inheritance**: The abstract BaseProduct class defines what all products must be able to do (like computing their price range), and the concrete Product class provides the actual implementation. This makes it easy to add new types of products later if needed.

**Polymorphism**: The coolest part is that when ETLProcessor creates the CSV output, it calls `product.toCsvRow()` which internally calls `computePriceRange()`. The processor doesn't know which specific implementation it's calling - it just works automatically.

## Testing to Make Sure Everything Still Works

I was really worried about breaking something when I redesigned the code, so I tested it carefully:

**Output Comparison**: I ran both Assignment 2 and Assignment 3 on the same input data and compared the output files character by character. They're identical, which means all the transformations are happening in the same order with the same results.

**Statistics Check**: Both versions report the exact same numbers - 6 rows read, 6 transformed, 0 skipped. This tells me the error handling and processing logic is working the same way.

**Transformation Order**: I made sure the transformations still happen in the right sequence:
1. Name gets converted to uppercase
2. Electronics items get their 10% discount  
3. Expensive electronics get recategorized to "Premium Electronics"
4. Price ranges get calculated based on the final price

**Error Handling**: I tested with missing files and bad data to make sure the error messages and handling are the same as Assignment 2.

## What I Learned

The biggest thing I learned is that object-oriented design isn't just about using classes and inheritance - it's about organizing code so that each piece has a clear job and they work together cleanly. My Assignment 2 code worked, but it was all tangled up. Assignment 3 does the exact same thing, but now if I wanted to change how price ranges are calculated, I'd only need to modify the Product class. If I wanted to add a new transformation, I'd just modify the ETLProcessor.

The inheritance and polymorphism concepts that seemed kind of abstract in class actually make sense now that I've used them to solve a real problem. Having the abstract method in BaseProduct forced me to think about what all products need to be able to do, and implementing it in Product made the code more flexible.

Overall, Assignment 3 feels much more professional and maintainable while doing exactly the same job as Assignment 2.