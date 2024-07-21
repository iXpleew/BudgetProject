package com.example.bugdetprojecy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IncomeSceneBuild {
    private Scene mainScene;
    private Stage primaryStage;

    public IncomeSceneBuild(Stage primaryStage, Scene mainScene){
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
    }
    public Scene buildIncomeScene(){
        AnchorPane incomePane = new AnchorPane();
        Scene incomeScene = new Scene(incomePane, 800, 400);

        //dlaczego nie moge dac static tutaj? a bardzo potrzebuje aby sie zapisalo bo jak znowu wchodze na ta scene t osie nie zapisuje
        //static int income = 0;

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

        //left side of the scene
        Text enterIncomeText = new Text("Enter your net income: ");{
            enterIncomeText.setLayoutX(20);
            enterIncomeText.setLayoutY(100);
        }
        TextField enterIncomeTField = new TextField("1");{
            enterIncomeTField.setLayoutX(20);
            enterIncomeTField.setLayoutY(110);
        }
        Text yourIncomeText = new Text("Your actual net income: 0");{
            yourIncomeText.setLayoutX(20);
            yourIncomeText.setLayoutY(130);
        }
        Button applyIncome = new Button("Apply");{
            applyIncome.setLayoutX(180);
            applyIncome.setLayoutY(110);

            applyIncome.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                }
            });
        }

        incomePane.getChildren().addAll(backToMenu, enterIncomeText, enterIncomeTField, applyIncome, yourIncomeText);

        return incomeScene;
    }
}
