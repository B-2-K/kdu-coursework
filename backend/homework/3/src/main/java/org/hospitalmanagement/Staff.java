package org.hospitalmanagement;

public class Staff extends User{
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;
    public void setStaffId(long staffId){
        this.staffId = staffId;
    }
    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public long getStaffId(){
        return this.staffId;
    }
    public int getYearsOfExperience(){
        return this.yearsOfExperience;
    }
    public String getDescription(){
        return this.description;
    }
    public double getSalary() {
        return this.salary;
    }
}
