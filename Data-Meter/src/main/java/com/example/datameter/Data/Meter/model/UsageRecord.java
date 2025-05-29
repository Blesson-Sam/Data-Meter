package com.example.datameter.Data.Meter.model;
import lombok.Data;

@Data
public class UsageRecord {
    private String mobileNumber;
    private int data4GHome = 0;
    private int data5GHome = 0;
    private int data4GRoaming = 0;
    private int data5GRoaming = 0;

    public UsageRecord(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void addUsage(int data4G, int data5G, boolean isRoaming) {
        if (isRoaming) {
            data4GRoaming += data4G;
            data5GRoaming += data5G;
        } else {
            data4GHome += data4G;
            data5GHome += data5G;
        }
    }
}
