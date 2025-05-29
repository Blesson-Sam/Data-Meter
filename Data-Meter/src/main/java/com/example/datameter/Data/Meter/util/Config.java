package com.example.datameter.Data.Meter.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Getter
@Setter
public class Config {
    private double rate4g;
    private double rate5g;
    private double factor4gRoaming;
    private double factor5gRoaming;
    private int threshold;
    private double extraCharge;

}
