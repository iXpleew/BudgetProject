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

import java.io.IOException;

public class HelloApplication extends Application {

    static int incomeAmount = 0;

    private static Text titleText = new Text("Personal Budget Management App");{
        titleText.setLayoutY(40);
        titleText.setWrappingWidth(800);
        titleText.setTextAlignment(TextAlignment.CENTER);
    }
    private static Text enterIncome = new Text("Enter your saved money: ");{
        enterIncome.setLayoutX(20);
        enterIncome.setLayoutY(100);
    }
    public static Text moneyText = new Text("Your money: " + incomeAmount + " PLN");{
        moneyText.setLayoutX(600);
        moneyText.setLayoutY(100);
    }
    private static TextField yourIncome = new TextField("1");{
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
                incomeAmount = Integer.parseInt(yourIncome.getText());
                moneyText.setText("Your money: " + yourIncome.getText() + " PLN");

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

        //adding all the objects to the scene: titletext, incometext, enterText, yourincome, apply, enterExpe;
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
}