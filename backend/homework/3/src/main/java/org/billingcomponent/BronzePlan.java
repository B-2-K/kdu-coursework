package org.billingcomponent;

public class BronzePlan extends HealthInsurancePlan{
    public BronzePlan(){
        this.setCoverage(0.6);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking) {
        return salary * 0.05 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}

