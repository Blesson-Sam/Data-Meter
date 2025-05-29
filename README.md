# Data Meter
------------

## A. Steps to Run the Project

### 1. Clone the Repository -> git clone https://github.com/Blesson-Sam/Data-Meter.git

### 2. Open Cloned Data-Meter folder in IntelliJ IDEA or any preferred Java IDE.

### 3. Navigate to : src/main/java/com/example/datameter/Data/Meter/DataMeterApplication.java 
       Run the DataMeterApplication class.
       After running, the CLI will display -> Usage: java -jar data-meter.jar <input_dir> <output_file>

### 4. Create the Executable JAR
      Open terminal (Alt+F12 in intelliJ)
      Run -> mvn clean package
      This cmd will create target folder which have JAR file

### 5. Run JAR file
      In same terminal Run -> java -jar ./target/Data-Meter-0.0.1-SNAPSHOT.jar ./input/ ./output/result.csv
      Note: You have to specify file name and extenson for output file (eg. result.csv, data.txt, etc)

## B. Running Test

### I have written test code only for billing calculator. You can either navigate to "src/test/java/com/example/datameter/Data/Meter/service/BillingCalculatorTest.java" to check full code and run from there, or simply run "mvn test" in the terminal.

## C. Input Format

### Input file should placed inside input/ directory and should have .txt extension
### Format : <mobile_number>|<unused_field>|<4G_data>|<5G_data>|<Roaming (Yes/No)>

## D. Output

### Any file format (.csv, .txt, etc) will be create and placed automatically in output/ directory
### Format : Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost

## E. Dependencies

### Java 17+
### Maven
### Spring Boot









