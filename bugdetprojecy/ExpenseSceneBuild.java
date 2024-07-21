package com.example.bugdetprojecy;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class ExpenseSceneBuild {
    private Stage primaryStage;
    private Scene mainScene;
    Queue<Expense> listOfExpenses = new LinkedList<>();
    ObservableList<Expense> expenses = FXCollections.observableArrayList(listOfExpenses);

    public ExpenseSceneBuild(Stage primaryStage, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
    }

    public Scene buildExpenseScene() {
        AnchorPane expensesPane = new AnchorPane();
        Scene expensesScene = new Scene(expensesPane, 800, 400);
        Text titleText = new Text("Expenses Scene"); {
            titleText.setLayoutY(40);
            titleText.setWrappingWidth(800);
            titleText.setTextAlignment(TextAlignment.CENTER);
        }
        Text incomeText = new Text("Actual money: " + HelloApplication.incomeAmount + " PLN");{
            incomeText.setLayoutY(100);
            incomeText.setLayoutX(20);
        }
        Text expenseText = new Text("Enter parameters of an Expense: ");{
            expenseText.setLayoutX(600);
            expenseText.setLayoutY(100);
        }

        TextField enterExpenseName = new TextField("Example Name");{
            enterExpenseName.setLayoutX(600);
            enterExpenseName.setLayoutY(110);
        }
        TextField enterExpenseAmount = new TextField("1");{
            enterExpenseAmount.setLayoutX(600);
            enterExpenseAmount.setLayoutY(140);
        }

        //tabele ta trzeba ogarnac
        TableView<Expense> expenseTable = new TableView<>();{
            expenseTable.setLayoutX(250);
            expenseTable.setLayoutY(90);
            expenseTable.setMaxSize(300,200);
            expenseTable.setMinSize(300,200);

            TableColumn<Expense, String> nameOfExpense = new TableColumn<>("Expense name");
            nameOfExpense.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameOfExpense()));

            TableColumn<Expense, Integer> amountOfExpense = new TableColumn<>("Expense amount");
            amountOfExpense.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAmountOfExpense()));

            expenseTable.getColumns().addAll(nameOfExpense, amountOfExpense);
            expenses = FXCollections.observableArrayList(listOfExpenses);
            expenseTable.setItems(expenses);


        }

        Button applyChanges = new Button("Apply");{
            applyChanges.setLayoutX(650);
            applyChanges.setLayoutY(170);
            applyChanges.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String name = enterExpenseName.getText();
                    int amount = Integer.parseInt(enterExpenseAmount.getText());
                    Expense newExpense = new Expense(name, amount);
                    listOfExpenses.add(newExpense);

                    expenses = FXCollections.observableArrayList(listOfExpenses);
                    expenseTable.setItems(expenses);

                    System.out.println("Name: " + name);
                    System.out.println("Amount: " + amount + " PLN");
                    HelloApplication.incomeAmount -= amount;
                    incomeText.setText("Your money: " + HelloApplication.incomeAmount + " PLN");
                    HelloApplication.moneyText.setText("Your money: " + HelloApplication.incomeAmount + " PLN");

                }
            });

        }

        Button backToMenu = new Button("Back");{
            backToMenu.setLayoutX(50);
            backToMenu.setLayoutY(30);
            backToMenu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    primaryStage.setScene(mainScene);
                }
            });

        }
        expensesPane.getChildren().addAll(titleText, incomeText, expenseText, enterExpenseName, enterExpenseAmount, expenseTable, applyChanges, backToMenu);

        return expensesScene;
    }
}
