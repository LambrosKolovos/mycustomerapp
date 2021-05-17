package com.example.mycontactapp;

public class CustomerInfo {
    private int _id;
    private String _name;
    private String _lastname;
    private String _email;
    private String _number;
    private String _birthday;

    public CustomerInfo(){

    }
    public CustomerInfo(int id,String name,String lastname,String email,String number,String birthday){
        this.setID(id);
        this._name=name;
        this._lastname=lastname;
        this._email=email;
        this._number=number;
        this._birthday=birthday;
    }

    public CustomerInfo(String name,String lastname,String email,String number,String birthday){
        this._name=name;
        this._lastname=lastname;
        this._email=email;
        this._number=number;
        this._birthday=birthday;
    }

    public int getID(){
        return _id;
    }

    public void setID(int _id){
        this._id=_id;
    }

    public void setName(String name){
        this._name=name;
    }

    public String getName(){
        return this._name;
    }

    public void setLastName(String lastname){
        this._lastname=lastname;
    }

    public String getLastName(){
        return this._lastname;
    }

    public void setEmail(String email){
        this._email=email;
    }

    public String getEmail(){
        return this._email;
    }

    public void setNumber(String number){
        this._number=number;
    }

    public String getNumber(){
        return this._number;
    }

    public void setBirthday(String birthday){
        this._birthday=birthday;
    }

    public String getBirthday(){
        return this._birthday;
    }
}
