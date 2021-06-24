package com.example.apz.Model;

public class Laundry {

    private String idLaundry;
    private String name;
    private String address;
    private String city;

    public Laundry(String idLaundry, String name, String address, String city) {

        this.idLaundry = idLaundry;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
