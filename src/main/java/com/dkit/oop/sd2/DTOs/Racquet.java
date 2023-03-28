package com.dkit.oop.sd2.DTOs;

public class Racquet {
    private int spec_id;
    private int playerId;
    private String brand;
    private String model;
    private int weight;
    private int head_size;
    private String string_pattern;

    public Racquet(int spec_id, int playerId, String brand, String model, int weight, int head_size, String string_pattern) {
        this.spec_id = spec_id;
        this.playerId = playerId;
        this.brand = brand;
        this.model = model;
        this.weight = weight;
        this.head_size = head_size;
        this.string_pattern = string_pattern;
    }

    //Getters
    public int getSpec_id() {
        return spec_id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getWeight() {
        return weight;
    }

    public int getHead_size() {
        return head_size;
    }

    public String getString_pattern() {
        return string_pattern;
    }


    //TODO: CompareTo, equals, toString methods
}
