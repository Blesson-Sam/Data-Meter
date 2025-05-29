package com.example.datameter.Data.Meter.service;

import com.example.datameter.Data.Meter.model.UsageRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class FileProcessor {
    public Map<String, UsageRecord> processDirectory(String directoryPath) throws IOException {
        Map<String, UsageRecord> usageMap = new HashMap<>();

        File dir = new File(directoryPath);

        // Get all files that ends with ".txt"
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files == null) throw new IOException("No input files found");

        for (File file : files) {
            try (Scanner scanner = new Scanner(file)) {
                // Read each line in the current file
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    try {
                        // Split line using pipe delimiter
                        String[] parts = line.split("\\|");

                        // Validate expected number of fields
                        if (parts.length != 5) throw new IllegalArgumentException("Invalid line");

                        // Extracting field
                        String mobile = parts[0];
                        int data4G = Integer.parseInt(parts[2]);
                        int data5G = Integer.parseInt(parts[3]);
                        boolean roaming = parts[4].equalsIgnoreCase("Yes");

                        // Retrieve or create a UsageRecord for the mobile number
                        UsageRecord record = usageMap.getOrDefault(mobile, new UsageRecord(mobile));

                        // add usage and update map
                        record.addUsage(data4G, data5G, roaming);
                        usageMap.put(mobile, record);
                    } catch (Exception e) {
                        System.err.println("Skipping malformed line: " + line);
                    }
                }
            }
        }
        return usageMap;
    }
}
