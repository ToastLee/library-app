package com.group.libraryapp.dto.calculator.response;

public class CalculationResponse {
    private int add;
    private int minus;
    private int multiply;


    public CalculationResponse(int add, int minus, int multiply) {
        this.add = add;
        this.minus = minus;
        this.multiply = multiply;
    }

    public CalculationResponse() {

    }

    public void setAdd(int add) {
        this.add = add;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public void setMultiply(int multiply) {
        this.multiply = multiply;
    }

    public int getAdd() {
        return add;
    }

    public int getMinus() {
        return minus;
    }

    public int getMultiply() {
        return multiply;
    }
}
