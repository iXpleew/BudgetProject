package com.example.bugdetprojecy;

import java.time.LocalDate;

public class Expense {
    private final String nameOfExpense;
    private final int amountOfExpense;
    private final LocalDate dateOfExpense;

    public Expense(String nameOfExpense, int amountOfExpense, LocalDate dateOfExpense) {
        this.nameOfExpense = nameOfExpense;
        this.amountOfExpense = amountOfExpense;
        this.dateOfExpense = dateOfExpense;
    }
    public String getNameOfExpense() {
        return nameOfExpense;
    }
    public int getAmountOfExpense() {
        return amountOfExpense;
    }
    public LocalDate getDateOfExpense(){return dateOfExpense;}
}
