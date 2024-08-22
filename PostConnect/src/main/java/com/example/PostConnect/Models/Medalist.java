package com.example.PostConnect.Models;

import lombok.Data;

@Data
public class Medalist {

    private String olympianId;
    private String name;
    private String countryCode;
    private int gold;
    private int silver;
    private int bronze;

    public Medalist() {
    }

    @Override
    public String toString() {
        return "Medalist{" +
                "olympianId='" + olympianId + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", gold=" + gold +
                ", silver=" + silver +
                ", bronze=" + bronze +
                '}';
    }

    public String getOlympianId() {
        return olympianId;
    }

    public void setOlympianId(String olympianId) {
        this.olympianId = olympianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
}
