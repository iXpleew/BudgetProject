package com.example.bugdetprojecy;

public class Expense {
    private final String nameOfExpense;
    private final int amountOfExpense;

    public Expense(String nameOfExpense, int amountOfExpense) {
        this.nameOfExpense = nameOfExpense;
        this.amountOfExpense = amountOfExpense;
    }
    public String getNameOfExpense() {
        return nameOfExpense;
    }
    public int getAmountOfExpense() {
        return amountOfExpense;
    }
}
