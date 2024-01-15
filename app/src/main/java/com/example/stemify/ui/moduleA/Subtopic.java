package com.example.stemify.ui.moduleA;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Subtopic {
    String title, subtopicImageName;
    int achievedPoints, totalPoints, imageId;
    List<Section> listOfSections;

    public Subtopic() {
        this.title = "";
        this.subtopicImageName = "sampleimage";
        this.achievedPoints = 0;
        this.totalPoints = 0;
        this.imageId = 0;
        this.listOfSections = new ArrayList<>();
    }

    public Subtopic(String title) {
        this.title = title;
        this.subtopicImageName = "sampleimage";
        this.achievedPoints = 0;
        this.totalPoints = 0;
        this.imageId = 0;
        this.listOfSections = new ArrayList<>();
    }

    public Subtopic(String title, String subtopicImageName, int achievedPoints, int totalPoints, int imageId, List<Section> listOfSections) {
        this.title = title;
        this.subtopicImageName = subtopicImageName;
        this.achievedPoints = achievedPoints;
        this.totalPoints = totalPoints;
        this.imageId = imageId;
        this.listOfSections = listOfSections;
    }

    public String getSubtopicImageName() {
        return subtopicImageName;
    }

    public void setSubtopicImageName(String subtopicImageName) {
        this.subtopicImageName = subtopicImageName;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<Section> getListOfSections() {
        return listOfSections;
    }

    public void setListOfSections(List<Section> listOfSections) {
        this.listOfSections = listOfSections;
    }

    public String assignPoints(){
        return achievedPoints + "/" + totalPoints + " mastery points";
    }

    public void addSection(Section section){
        listOfSections.add(section);
    }

    // To display all of the sections in item_subtopic layout
    public String getSectionTitleList(){
        String listOfSections = "";
        if(this.listOfSections.size() != 0){
            listOfSections += this.listOfSections.get(0).title;
        }
        for (int i = 1; i < this.listOfSections.size(); i++) {
            listOfSections += "\n" + this.listOfSections.get(i).title;
        }
        return listOfSections;
    }

    public int getSectionCount(){
        return this.listOfSections.size();
    }
}
