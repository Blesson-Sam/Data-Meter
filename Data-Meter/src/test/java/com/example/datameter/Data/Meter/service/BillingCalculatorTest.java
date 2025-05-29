package com.example.datameter.Data.Meter.service;

import com.example.datameter.Data.Meter.model.UsageRecord;
import com.example.datameter.Data.Meter.util.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(properties = {
        "rate.4g=0.01",
        "rate.5g=0.02",
        "factor.4g.roaming=1.10",
        "factor.5g.roaming=1.15",
        "threshold=1048576",
        "extra.charge=0.05"
})
public class BillingCalculatorTest {

    @Autowired
    private BillingCalculator calculator;

    @Autowired
    private Config config;

    private UsageRecord record;

    @BeforeEach
    void setUp() {
        record = new UsageRecord("9000000001");
        record.addUsage(1200, 3000, false);  // home usage
        record.addUsage(500, 1000, true);    // roaming usage
    }

    @Test
    void testCalculateCost() {
        double cost = calculator.calculateCost(record);

        // Cost calculation explained:
        // 1200 * RATE_4G (0.01) = 12.0
        // 3000 * RATE_5G (0.02) = 60.0
        // 500 * RATE_4G * ROAMING_4G_FACTOR = 500 * 0.01 * 1.10 = 5.5
        // 1000 * RATE_5G * ROAMING_5G_FACTOR = 1000 * 0.02 * 1.15 = 23.0
        // Total = 12 + 60 + 5.5 + 23 = 100.5 → rounded to 101

        assertEquals(101, Math.round(cost), "Cost calculation should match expected total");
    }
    @Test
    void testConfigValuesLoaded() {
        System.out.println("rate4g: " + config.getRate4g());
        System.out.println("rate5g: " + config.getRate5g());
        System.out.println("roaming4gFactor: " + config.getFactor4gRoaming());
        System.out.println("roaming5gFactor: " + config.getFactor5gRoaming());
        System.out.println("threshold: " + config.getThreshold());
        System.out.println("extraCharge: " + config.getExtraCharge());

        assertEquals(0.01, config.getRate4g());
        assertEquals(0.02, config.getRate5g());
    }
}


