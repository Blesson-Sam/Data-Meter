package com.example.datameter.Data.Meter.service;


import com.example.datameter.Data.Meter.model.UsageRecord;
import com.example.datameter.Data.Meter.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingCalculator {

    private final Config config;

    @Autowired
    public BillingCalculator(Config config) {
        this.config = config;
    }

    public double calculateCost(UsageRecord record) {
        double cost = 0;

        // Basic usage
        cost += record.getData4GHome() * config.getRate4g();
        cost += record.getData5GHome() * config.getRate5g();

        // Roaming usage
        cost += record.getData4GRoaming() * config.getRate4g() * config.getFactor4gRoaming();
        cost += record.getData5GRoaming() * config.getRate5g() * config.getFactor5gRoaming();

        // Apply extra charge if total usage exceeds threshold
        int totalUsage = record.getData4GHome() + record.getData5GHome()
                + record.getData4GRoaming() + record.getData5GRoaming();

        if (totalUsage > config.getThreshold()) {
            cost += cost * config.getExtraCharge();
        }

        return Math.round(cost);
    }
}
