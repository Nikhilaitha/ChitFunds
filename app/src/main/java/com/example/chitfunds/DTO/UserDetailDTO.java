package com.example.chitfunds.DTO;

public class UserDetailDTO {

    private String fullName;
    private String email;
    private String password;
    private String userPhoneNumber;
    private String nomineeName;
    private String nomineePhoneNumber;
    private String userAddress;
    private String plans;


    public UserDetailDTO() {
    }
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getNomineePhoneNumber() {
        return nomineePhoneNumber;
    }

    public void setNomineePhoneNumber(String nomineePhoneNumber) {
        this.nomineePhoneNumber = nomineePhoneNumber;
    }

    public String getPlans() {
        return plans;
    }

    public void setPlans(String plans) {
        this.plans = plans;
    }
}
