# ZipcodeRangeOptimizer
Java application to optimize Zip code ranges - Where the supplied Zip code ranges overlap or include other ranges, this application combines them to output a minimum number of ranges that cover the same zip codes as covered by the supplied ranges.

#### Example:
Input |	Output
------|--------
[94133,94133] [94200,94299] [94600,94699]|	[94133,94133] [94200,94299] [94600,94699]
[94133,94133] [94200,94299] [94226,94399]|	[94133,94133] [94200,94399]
[80562,99207] [36874,65564] [47892,74123] [92509,93825]|	[36874,74123] [80562,99207]

# JAVA Classes
1. ZipOpMain - Entry point class for the application. Runs a continuous thread to read input and returns optimized output.
1. ZipcodeParser - Parser to validate and convert the input string into Zipcode range objects.
1. ZipcodeOptimizer - Class that takes in a list or zip code range objects and optimizes (reduces) them to form a final list.
1. ZipcodeRange - Model class representing a zip code range. Implements comparator interface for sorting the ranges.
1. ZipcodeException - Application specific exception object to generate dedicated input validation failure error messages.

# Unit Tests
1. RangeParserTest - Tests the parsing logic, i.e. an invalid input is detected and prompted with an appropriate error
1. RangeSorterTest - Tests the zip code range sorting algorith. Uses manual as well as randomly generated zip code ranges.
1. RangeOptimizerTest - Tests the optimizer logic. Uses manual as well as randomly generated zip code ranges.
>Test cases are limited, so more test cases can be added to test all conditions.

# Dependencies
1. JDK1.8.0_144
1. junit-4.11.jar

# Console Output
![Image](/ZipcodeOptimizer.png?raw=true)
