package com.example.stemify;

public class CounselorData {

    private String name;
    private String information;
    private String experience;
    private String patients;
    private Integer imagedr;


    public CounselorData(String name, String infomation, String experience, String patients, Integer imagedr){
        this.experience = experience;
        this.name = name;
        this.information = infomation;
        this.patients = patients;
        this.imagedr = imagedr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPatients() {
        return patients;
    }

    public void setPatients(String patients) {
        this.patients = patients;
    }

    public Integer getImagedr() {
        return imagedr;
    }

    public void setImagedr(Integer imagedr) {
        this.imagedr = imagedr;
    }
}
