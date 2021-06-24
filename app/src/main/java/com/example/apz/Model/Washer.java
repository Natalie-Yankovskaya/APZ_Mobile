package com.example.apz.Model;

public class Washer {


    private String id;
    private String model;
    private String program;
    private String size;


    public Washer(String id, String model, String program, String size) {
        this.id = id;
        this.model = model;
        this.program = program;
        this.size = size;

    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
