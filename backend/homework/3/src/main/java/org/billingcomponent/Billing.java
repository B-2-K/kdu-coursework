package org.billingcomponent;
import org.hospitalmanagement.*;

public class Billing {
    private Billing(){
    }
    public static double[] computePaymentAmount(Patient patient,double amount){
        HealthInsurancePlan healthInsurancePlan = patient.getInsurancePlan();
        double[] answer = new double[2];
        if(healthInsurancePlan == null){
            answer[0] = 0;
            answer[1] = amount - calculatePlan(patient.getInsurancePlan());
            return answer;
        }
        double temp = healthInsurancePlan.getCoverage(); //0.9
        answer[0] = temp * amount; //900
        double userCurrent = amount - answer[0]; //100
        double discount = calculatePlan(patient.getInsurancePlan());
        answer[1] = userCurrent - discount;
        return answer;
    }
    public static double calculatePlan(HealthInsurancePlan healthInsurancePlan){
        if(healthInsurancePlan instanceof PlatinumPlan){
            return 50;
        }else if(healthInsurancePlan instanceof GoldPlan){
            return 40;
        }else if(healthInsurancePlan instanceof SilverPlan){
            return 30;
        }else if(healthInsurancePlan instanceof BronzePlan){
            return 25;
        }else{
            return 20;
        }
    }
}
