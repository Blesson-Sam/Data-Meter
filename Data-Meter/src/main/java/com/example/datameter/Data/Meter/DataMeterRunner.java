package com.example.datameter.Data.Meter;


import com.example.datameter.Data.Meter.model.UsageRecord;
import com.example.datameter.Data.Meter.service.BillingCalculator;
import com.example.datameter.Data.Meter.service.FileProcessor;
import com.example.datameter.Data.Meter.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.Map;

@Component
public class DataMeterRunner implements CommandLineRunner {

    //  Injects FileProcessor and BillingCalculator bean
    @Autowired
    private FileProcessor fileProcessor;
    @Autowired
    private BillingCalculator calculator;


    @Override
    public void run(String... args) throws Exception {

        // Validate command line argument (Input and Output)
        if (args.length < 2) {
            System.out.println("Usage: java -jar data-meter.jar <input_dir> <output_file>");
            return;
        }

        // Process all .txt in input directory
        Map<String, UsageRecord> records = fileProcessor.processDirectory(args[0]);
        FileWriter fw = new FileWriter(args[1]);

        fw.write("Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost\n");

        // Calculating bill and writing to output for each record.
        for (UsageRecord record : records.values()) {
            double cost = calculator.calculateCost(record);
            fw.write(String.format("%s|%d|%d|%d|%d|%.0f\n",
                    record.getMobileNumber(), record.getData4GHome(), record.getData5GHome(),
                    record.getData4GRoaming(), record.getData5GRoaming(), cost));
        }
        fw.close();
        System.out.println("Report generated at: " + args[1]);
    }
}
