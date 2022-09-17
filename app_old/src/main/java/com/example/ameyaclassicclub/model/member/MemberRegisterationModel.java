package com.example.ameyaclassicclub.model.member;

public class MemberRegisterationModel {
    public String firstName,lastName,mobile,emailId,gender;
    public String memberShipDuration;
    public MemberRegisterationModel(){
    }
    public MemberRegisterationModel(String firstName, String lastName, String mobile,String emailId,String memberShipDuration, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.mobile=mobile;
        this.memberShipDuration=memberShipDuration;
        this.gender=gender;
    }
}
