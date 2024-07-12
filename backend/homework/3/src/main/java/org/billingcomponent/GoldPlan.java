package org.billingcomponent;

public class GoldPlan extends HealthInsurancePlan{
    public GoldPlan(){
        this.setCoverage(0.8);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking) {
        return salary * 0.07 + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}

