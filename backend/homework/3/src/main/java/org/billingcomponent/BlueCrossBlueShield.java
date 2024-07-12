package org.billingcomponent;

public class BlueCrossBlueShield implements InsuranceBrand{
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking){
        double[] ans = new double[2];
        if(insurancePlan instanceof PlatinumPlan){
            ans = premium(age, smoking);
        }else if(insurancePlan instanceof GoldPlan){
            ans = gold(age, smoking);
        }else if(insurancePlan instanceof SilverPlan){
            ans = silver(age, smoking);
        }else if(insurancePlan instanceof BronzePlan){
            ans = bronze(age, smoking);
        }
        return ans[0] + ans[1];
    }
    public static double[] premium(int age,boolean smoking){
        double[] arr = new double[2];
        if(age > 55){
            arr[0] = 200;
        }
        if(smoking){
            arr[1] = 100;
        }
        return arr;
    }
    public static double[] gold(int age,boolean smoking){
        double[] arr = new double[2];
        if(age > 55){
            arr[0] = 150;
        }
        if(smoking){
            arr[1] = 90;
        }
        return arr;
    }
    public static double[] silver(int age,boolean smoking){
        double[] arr = new double[2];
        if(age > 55){
            arr[0] = 100;
        }
        if(smoking){
            arr[1] = 80;
        }
        return arr;
    }
    public static double[] bronze(int age,boolean smoking){
        double[] arr = new double[2];
        if(age > 55){
            arr[0] = 50;
        }
        if(smoking){
            arr[1] = 70;
        }
        return arr;
    }
}
