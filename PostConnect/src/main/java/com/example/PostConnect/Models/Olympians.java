package com.example.PostConnect.Models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "Olympians")
public class Olympians {
    @Id
    private String olympian_id;
    private String fname;
    private String lname;
    private Date dob;
    private String sex;
    private int gold;
    private int silver;
    private int bronze;
    @ManyToOne
    @JoinColumn(name="countryCode",referencedColumnName = "countryCode")
    private Country country;

    public Olympians() {
    }

    @Override
    public String toString() {
        return "Olympians{" +
                "olympian_id='" + olympian_id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                ", sex='" + sex + '\'' +
                ", gold=" + gold +
                ", silver=" + silver +
                ", bronze=" + bronze +
                ", country=" + country +
                '}';
    }

    public String getOlympian_id() {
        return olympian_id;
    }

    public void setOlympian_id(String olympian_id) {
        this.olympian_id = olympian_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
