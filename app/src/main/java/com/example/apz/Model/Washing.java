package com.example.apz.Model;

public class Washing {
    private String status;
    private String mode;
    private String conditioner;
    private String price;

    public Washing(String status, String mode, String conditioner, String price) {
        this.status = status;
        this.mode = mode;
        this.conditioner = conditioner;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getConditioner() {
        return conditioner;
    }

    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
