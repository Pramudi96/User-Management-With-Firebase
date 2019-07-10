package com.example.user.smartbustracker;

public class Customer extends User{

protected String Email,Name,Phone,Password;



    public Customer(String Name,String Email,String Phone){
        this.Email =Email;
        this.Name = Name;
        this.Phone =Phone;
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


}
