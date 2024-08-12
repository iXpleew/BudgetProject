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

import java.io.*;
import java.time.LocalDate;
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
        File file = new File("expenses_data.txt");

        Text titleText = new Text("Expenses Scene"); {
            titleText.setLayoutY(40);
            titleText.setWrappingWidth(800);
            titleText.setTextAlignment(TextAlignment.CENTER);
        }
        Text incomeText = new Text("Actual money: " + HelloApplication.savedMoney + " PLN");{
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

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                int colonIndex = line.indexOf(":");
                int semiColonIndex = line.indexOf(";");

                String expenseName = line.substring(0, colonIndex);
                int expenseAmount = Integer.parseInt(line.substring(colonIndex + 1, semiColonIndex));
                LocalDate expenseDate = LocalDate.parse(line.substring(semiColonIndex + 1));

                listOfExpenses.add(new Expense(expenseName, expenseAmount, expenseDate));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TableView<Expense> expenseTable = new TableView<>();{
            expenseTable.setLayoutX(250);
            expenseTable.setLayoutY(90);
            expenseTable.setMaxSize(300,200);
            expenseTable.setMinSize(300,200);

            TableColumn<Expense, String> nameOfExpense = new TableColumn<>("Name");
            nameOfExpense.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameOfExpense()));
            nameOfExpense.setPrefWidth(130);

            TableColumn<Expense, Integer> amountOfExpense = new TableColumn<>("Amount");
            amountOfExpense.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAmountOfExpense()));
            amountOfExpense.setPrefWidth(70);

            TableColumn<Expense, LocalDate> dateOfExpense = new TableColumn<>("Date");
            dateOfExpense.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateOfExpense()));
            dateOfExpense.setPrefWidth(110);

            expenseTable.getColumns().addAll(nameOfExpense, amountOfExpense, dateOfExpense);
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
                    LocalDate date = LocalDate.now();

                    Expense newExpense = new Expense(name, amount, date);
                    listOfExpenses.add(newExpense);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
                        bw.write(name + ':' + amount + ';' + date);
                        bw.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    expenses = FXCollections.observableArrayList(listOfExpenses);
                    expenseTable.setItems(expenses);

                    HelloApplication.savedMoney -= amount;
                    FileManagment.FileChanger(0,"Saved money: " + HelloApplication.savedMoney);
                    incomeText.setText("Your money: " + HelloApplication.savedMoney + " PLN");
                    HelloApplication.moneyText.setText("Your money: " + HelloApplication.savedMoney + " PLN");

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
