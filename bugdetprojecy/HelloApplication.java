package com.example.bugdetprojecy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    static int savedMoney = 0;

    private static Text titleText = new Text("Personal Budget Management App");{
        titleText.setLayoutY(40);
        titleText.setWrappingWidth(800);
        titleText.setTextAlignment(TextAlignment.CENTER);
    }
    private static Text enterIncome = new Text("Enter your saved money: ");{
        enterIncome.setLayoutX(20);
        enterIncome.setLayoutY(100);
    }
    public static Text moneyText = new Text("Your money: " + savedMoney + " PLN");{
        moneyText.setLayoutX(600);
        moneyText.setLayoutY(100);
    }
    private static TextField yourIncome = new TextField("0");{
        yourIncome.setLayoutX(20);
        yourIncome.setLayoutY(110);
    }

    //buttons
    private static Button applyIncome = new Button("Apply");{
        applyIncome.setLayoutX(180);
        applyIncome.setLayoutY(110);

        applyIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                savedMoney = Integer.parseInt(yourIncome.getText());
                FileManagment.FileChanger(0, "Saved money: " + savedMoney);
                updateMoneyText();

            }
        });
    }
    private static Button enterExpensesScene = new Button("Add expense");{
        enterExpensesScene.setLayoutX(360);
        enterExpensesScene.setLayoutY(110);
    }
    private static Button enterIncomeScene = new Button("Adjust your income");{
        enterIncomeScene.setLayoutX(345);
        enterIncomeScene.setLayoutY(170);
    }

    private static Button deleteAllData = new Button("Delete all data and Exit");{
        deleteAllData.setLayoutX(50);
        deleteAllData.setLayoutY(350);
        deleteAllData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                savedMoney = 0;
                FileManagment.FileChanger(0, "Saved money: 0");
                FileManagment.FileChanger(1, "Net income: 0");
                File file = new File("expenses_data.txt");
                if(file.exists()) file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane layout = new AnchorPane();
        Scene mainScene = new Scene( layout, 800, 400);
        ExpenseSceneBuild expenseScene = new ExpenseSceneBuild(primaryStage, mainScene);
        IncomeSceneBuild incomeScene = new IncomeSceneBuild(primaryStage, mainScene);

        savedMoney = Integer.parseInt(FileManagment.FileChecker(0));
        updateMoneyText();

        layout.getChildren().addAll(titleText, moneyText, enterIncome, yourIncome, applyIncome, enterExpensesScene, enterIncomeScene, deleteAllData);

        enterExpensesScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(expenseScene.buildExpenseScene());
            }
        });
        enterIncomeScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    primaryStage.setScene(incomeScene.buildIncomeScene());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        primaryStage.setTitle("Personal Budget Management");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void updateMoneyText(){
        moneyText.setText("Your money: " + savedMoney + " PLN");
    }
}
