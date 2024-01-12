package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    int achievedPoints, totalPoints;
    List<Material> listOfMaterial;

    public Section() {
        this.title = "";
        this.achievedPoints = 0;
        this.totalPoints = 0;
        this.listOfMaterial = new ArrayList<>();
    }

    public Section(String title) {
        this.title = title;
        this.achievedPoints = 0;
        this.totalPoints = 0;
        this.listOfMaterial = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(int achievedPoints) {
        this.achievedPoints = achievedPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Material> getListOfMaterial() {
        return listOfMaterial;
    }

    public void setListOfMaterial(List<Material> listOfMaterial) {
        this.listOfMaterial = listOfMaterial;
    }

    public String getMasteryPoints(){ // work on the logic later
        return "Points";
    }

    public void addMaterial(Material material){
        listOfMaterial.add(material);
    }
}
