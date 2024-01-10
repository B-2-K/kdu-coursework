package org.hospitalmanagement;
import org.billingcomponent.*;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private int age;
    private boolean smoking;
    private HealthInsurancePlan insurancePlan = null;
    public void setId(int id){
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setInsured(boolean insured){
        this.insured = insured;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setSmoking(boolean smoking){
        this.smoking = smoking;
    }
    public int getId(){
        return this.id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getGender(){
        return this.gender;
    }
    public String getEmail(){
        return this.email;
    }
    public boolean getInsured(){
        return this.insured;
    }
    public int getAge(){
        return this.age;
    }
    public boolean getSmoking(){
        return this.smoking;
    }
    public void setInsurancePlan(HealthInsurancePlan insurancePlan){
        this.insurancePlan = insurancePlan;
    }
    public HealthInsurancePlan getInsurancePlan(){
        return this.insurancePlan;
    }
}