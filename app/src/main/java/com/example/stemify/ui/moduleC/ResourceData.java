package com.example.stemify.ui.moduleC;

public class ResourceData {

    private String cardName;
    private String cardDesc;
    private int cardImg;
    private int cardDetails;

    public ResourceData(String cardName, String cardDesc, int cardImg, int cardDetails) {
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.cardImg = cardImg;
        this.cardDetails = cardDetails;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public int getCardImg() {
        return cardImg;
    }

    public int getCardDetails() {
        return cardDetails;
    }
}
