package com.example.sanzharaubakir.bereke;



public class CountryItemData {

    String name;
    String dialCode;
    Integer imageId;
    public CountryItemData(String name, String dialCode, Integer imageId){
        this.name = name;
        this.dialCode = dialCode;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public String getDialCode(){
        return dialCode;
    }

    public Integer getImageId(){
        return imageId;
    }
}
