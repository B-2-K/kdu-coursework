package org.billingcomponent;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand  offeredby;
    public void setOfferedBy(InsuranceBrand offeredby){
        this.offeredby = offeredby;
    }
    public InsuranceBrand getOfferedBy(){
        return this.offeredby;
    }
    public void setCoverage(double coverage){
        this.coverage = coverage;
    }
    public double getCoverage(){
        return this.coverage;
    }
    public abstract double computeMonthlyPremium(double salary,int age,boolean smoking);
}
