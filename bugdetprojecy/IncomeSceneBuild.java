package com.example.bugdetprojecy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class IncomeSceneBuild {
    private Scene mainScene;
    private Stage primaryStage;

    public IncomeSceneBuild(Stage primaryStage, Scene mainScene){
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
    }
    public Scene buildIncomeScene() throws IOException {
        AnchorPane incomePane = new AnchorPane();
        Scene incomeScene = new Scene(incomePane, 800, 400);

        final int[] income = {Integer.parseInt(FileManagment.FileChecker(1))};
        final int[] savings = {1};
        final int[] goalMoney = {1};
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

        Text enterIncomeText = new Text("Enter your net income: ");{
            enterIncomeText.setLayoutX(20);
            enterIncomeText.setLayoutY(100);
        }
        TextField enterIncomeTField = new TextField("1000");{
            enterIncomeTField.setLayoutX(20);
            enterIncomeTField.setLayoutY(110);
        }
        Text yourIncomeText = new Text("Your actual net income: " + income[0]);{
            yourIncomeText.setLayoutX(20);
            yourIncomeText.setLayoutY(150);
        }

        Slider incomeSlider = new Slider();{
            incomeSlider.setMin(1);
            incomeSlider.setMax(income[0]);
            incomeSlider.setLayoutX(500);
            incomeSlider.setLayoutY(110);
            incomeSlider.setVisible(false);
        }
        Text calculateText = new Text("Calculate how much time you need to buy something");{
            calculateText.setLayoutX(432);
            calculateText.setLayoutY(80);
            calculateText.setVisible(false);
        }
        Text saveText = new Text("Choose how much money do you want to save monthly:");{
            saveText.setLayoutX(425);
            saveText.setLayoutY(100);
            saveText.setVisible(false);
        }
        Text adjustedSavings = new Text("Current monthly savings: " + incomeSlider.getValue());{
            adjustedSavings.setText("Current monthly savings: " + incomeSlider.getValue());
            adjustedSavings.setLayoutX(495);
            adjustedSavings.setLayoutY(150);
            adjustedSavings.setVisible(false);
        }

        incomeSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            adjustedSavings.setText("Current monthly savings: " + newValue.intValue());
            savings[0] = newValue.intValue();
        });

        //savings UI:
        Text sumText = new Text("Enter money you want to have by saving: ");{
            sumText.setLayoutX(20);
            sumText.setLayoutY(300);
            sumText.setVisible(false);
        }
        TextField sumField = new TextField("1");{
            sumField.setLayoutX(20);
            sumField.setLayoutY(310);
            sumField.setVisible(false);
        }
        Text howMuchTime = new Text();{
            howMuchTime.setLayoutX(500);
            howMuchTime.setLayoutY(310);
            howMuchTime.setVisible(false);
        }
        Button applySum = new Button("Apply");{
            applySum.setLayoutX(180);
            applySum.setLayoutY(310);
            applySum.setVisible(false);
            applySum.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    goalMoney[0] = Integer.parseInt(sumField.getText());
                    int months = 0;
                    int years = 0;
                    for (int i = goalMoney[0]; i>0; i-=savings[0]){
                        if(months == 12) {
                            years++;
                            months = 1;
                        }
                        else months++;
                    }
                    howMuchTime.setText("You will need " + years + " year/s and " + months + " month/s (with no saved money)");
                    howMuchTime.setVisible(true);

                }
            });
        }

        Button applySavings = new Button("Apply");{
            applySavings.setLayoutX(550);
            applySavings.setLayoutY(160);
            applySavings.setVisible(false);
            applySavings.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    sumText.setVisible(true);
                    sumField.setVisible(true);
                    applySum.setVisible(true);

                }
            });
        }
        Button applyIncome = new Button("Apply");{
            applyIncome.setLayoutX(180);
            applyIncome.setLayoutY(110);
            applyIncome.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // o tutaj
                    income [0] = Integer.parseInt(enterIncomeTField.getText());
                    FileManagment.FileChanger(1, "Net income: " + income[0]);
                    yourIncomeText.setText("Your actual net income: " + income[0]);
                    incomeSlider.setMax(income[0]);
                    incomeSlider.setVisible(true);
                    saveText.setVisible(true);
                    adjustedSavings.setVisible(true);
                    calculateText.setVisible(true);
                    applySavings.setVisible(true);
                }
            });
        }

        incomePane.getChildren().addAll(backToMenu, enterIncomeText, enterIncomeTField, applyIncome, yourIncomeText, incomeSlider, saveText, adjustedSavings, calculateText,applySavings, sumText, sumField, applySum, howMuchTime);

        return incomeScene;
    }
}
