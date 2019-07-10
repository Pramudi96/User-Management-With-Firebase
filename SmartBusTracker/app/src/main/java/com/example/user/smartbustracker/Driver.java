package com.example.user.smartbustracker;

public class Driver extends User{

    protected String Email,Name,Phone,Password;
    private String RootNo,BusNo;


    public Driver(){

    }
    public Driver(String Name,String Email,String Phone,String BusNo){
        super(Email,Name,Phone);

        this.Email =Email;
        this.Name = Name;
        this.Phone =Phone;
        this.BusNo =BusNo;


    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String Email) {

        this.Email = Email;
    }

    public String getName() {

        return Name;
    }

    public void setName(String Name) {

        this.Name = Name;
    }


    public String getPhone() {

        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPassword() {

        return Password;
    }

    public void setPassword(String Password) {

        this.Password = Password;
    }

    public String getRootNo() {
        return RootNo;
    }

    public void setRootNo(String rootNo) {
        RootNo = rootNo;
    }

    public String getBusNo() {
        return BusNo;
    }

    public void setBusNo(String busNo) {
        BusNo = busNo;
    }
}
