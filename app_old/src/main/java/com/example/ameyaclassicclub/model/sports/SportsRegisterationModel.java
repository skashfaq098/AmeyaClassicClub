package com.example.ameyaclassicclub.model.sports;

public class SportsRegisterationModel {
    String sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees;

    public SportsRegisterationModel(){
    }
    public SportsRegisterationModel(String sportsName, String sportsId, String sportsDaysInAweek,String sportsTimeSlot,String sportsCoachingFees) {
        this.sportsName = sportsName;
        this.sportsId = sportsId;
        this.sportsDaysInAweek = sportsDaysInAweek;
        this.sportsCoachingFees=sportsCoachingFees;
        this.sportsTimeSlot=sportsTimeSlot;
    }
}
