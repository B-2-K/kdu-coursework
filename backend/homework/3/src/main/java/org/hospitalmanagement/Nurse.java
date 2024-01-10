package org.hospitalmanagement;

public class Nurse extends Staff{
    private long nurseId;
    public void setNurseId(long nurseId){
        this.nurseId = nurseId;
    }
    public long getNurseId(){
        return this.nurseId;
    }
}

