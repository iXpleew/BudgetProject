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

import java.io.*;

public class HelloApplication extends Application {

    static int savedMoney = 0;
    File file = new File("user_data.txt");

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


    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane layout = new AnchorPane();
        Scene mainScene = new Scene( layout, 800, 400);
        ExpenseSceneBuild expenseScene = new ExpenseSceneBuild(primaryStage, mainScene);
        IncomeSceneBuild incomeScene = new IncomeSceneBuild(primaryStage, mainScene);

        //connecting the database with the app
        BufferedReader br = new BufferedReader(new FileReader(file));
        savedMoney = Integer.parseInt(FileChecker(br, 0));
        updateMoneyText();

        layout.getChildren().addAll(titleText, moneyText, enterIncome, yourIncome, applyIncome, enterExpensesScene, enterIncomeScene);
        enterExpensesScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(expenseScene.buildExpenseScene());
            }
        });
        enterIncomeScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(incomeScene.buildIncomeScene());
            }
        });
        primaryStage.setTitle("Personal Budget Management");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private String FileChecker(BufferedReader bufferedReader, int deliberateLine) throws IOException {
        int index = 0;
        String line;
        while((line = bufferedReader.readLine()) != null){
            if (index == deliberateLine){
                return line;
            }
            index++;
        }
        return null;
    }

    private void updateMoneyText(){
        moneyText.setText("Your money: " + savedMoney + " PLN");
    }
}
