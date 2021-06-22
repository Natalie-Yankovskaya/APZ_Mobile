package com.example.apz.Model;

public class Washings {
    private Integer washer_id;
    private String status;
    private Integer remaining_time;


    public Washings(Integer washer_id, String status, Integer remaining_time) {
        this.washer_id = washer_id;
        this.status = status;
        this.remaining_time = remaining_time;
    }

    public Integer getWasher_id() {
        return washer_id;
    }

    public void setWasher_id(Integer washer_id) {
        this.washer_id = washer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(Integer remaining_time) {
        this.remaining_time = remaining_time;
    }
}
