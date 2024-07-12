package org.hospitalmanagement;

public class Patient extends User {
    private long patientId;
    public void setPatientId(long patientId){
        this.patientId = patientId;
    }
    public long getPatientId(){
        return this.patientId;
    }
}

