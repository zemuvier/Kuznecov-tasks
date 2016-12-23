package com.lungesoft.larch.models;

public class Factors {
    private double factor1;
    private double factor2;
    private double factor3;
    private double factor4;

    public Factors() {
    }

    public Factors(double factor1, double factor2, double factor3, double factor4) {
        this.factor1 = factor1;
        this.factor2 = factor2;
        this.factor3 = factor3;
        this.factor4 = factor4;
    }

    public double getFactor1() {
        return factor1;
    }

    public void setFactor1(double factor1) {
        this.factor1 = factor1;
    }

    public double getFactor2() {
        return factor2;
    }

    public void setFactor2(double factor2) {
        this.factor2 = factor2;
    }

    public double getFactor3() {
        return factor3;
    }

    public void setFactor3(double factor3) {
        this.factor3 = factor3;
    }

    public double getFactor4() {
        return factor4;
    }

    public void setFactor4(double factor4) {
        this.factor4 = factor4;
    }
}
