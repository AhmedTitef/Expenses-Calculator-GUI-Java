package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    int noOFDaysValue;
    double transpCostValue;
    double confCostValue;
    double lodgeCostValue;
    double foodCostValue;
    double foodOwe;
    double lodgeOWe;
    double transpCostReimb;
    double confCostOwe;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Homework1");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        TextField noOFDays = new TextField();
        TextField transpCost = new TextField();
        TextField confCost = new TextField();
        TextField lodgeCost = new TextField();
        TextField foodCost = new TextField();

        gridPane.add(new Label("Number of Days: "), 0,0);
        gridPane.add(noOFDays, 1,0 );
        gridPane.add(new Label("Transportation Cost: (Please Choose the Method Below) "), 0,1);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("","Airfare Cost" , "Miles driven");
        gridPane.add(choiceBox, 0 , 2);


        gridPane.add(transpCost, 1,2 );
        gridPane.add(new Label("Conference registration cost: "), 0,3);
        gridPane.add(confCost, 1,3 );
        gridPane.add(new Label("Lodging Cost: (Per Night) "), 0,4);
        gridPane.add(lodgeCost, 1,4 );
        gridPane.add(new Label("Food Cost: (Total) "), 0,5);
        gridPane.add(foodCost, 1,5 );

        Button button= new Button("Submit");
        Label totalExpenseNo = new Label("0.0");
        Label howMuchOWeNo = new Label("0.0");
        HBox hBox = new HBox(20,
                new Label("Total Expense: "), totalExpenseNo,
                new Label("How Much You Owe: "), howMuchOWeNo);
        hBox.setAlignment(Pos.CENTER);




        VBox vbox = new VBox(20, gridPane , button, hBox);
        vbox.setAlignment(Pos.CENTER);


        //Force the Fields to be numeric Only
        noOFDays.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    noOFDays.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        transpCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    transpCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        confCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    confCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lodgeCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    lodgeCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        foodCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    foodCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        button.setOnAction(event -> {
        noOFDaysValue = Integer.parseInt(noOFDays.getText());

        transpCostValue = Double.parseDouble(transpCost.getText());
        if(choiceBox.getSelectionModel().getSelectedItem() == "Airfare Cost" ){
            transpCostReimb = 0.0;
        }
        else if(choiceBox.getSelectionModel().getSelectedItem()== "Miles driven"){
            transpCostReimb = (transpCostValue * 0.42); // in this case transpCostValue is number of miles driver
        }

        confCostValue = Double.parseDouble(confCost.getText());
        confCostOwe = 0.0; //fully covered


    lodgeCostValue = Double.parseDouble(lodgeCost.getText());
        if (lodgeCost.getText().isEmpty()|| lodgeCost.getText() == null ){
            lodgeCostValue = 0.0;
        }
       else if (lodgeCostValue <= 195.0){

            lodgeOWe = 0.0;
        }else {

            lodgeOWe = lodgeCostValue - 195.0;
        }



        foodCostValue = noOFDaysValue* Double.parseDouble(foodCost.getText());
        if (foodCostValue <= noOFDaysValue * 47.0){
            foodOwe = 0.0;
        }else if (foodCostValue > noOFDaysValue *47.0){
            foodOwe = foodCostValue - noOFDaysValue*47.0;

        }


         double totalExpense = transpCostValue + confCostValue + (lodgeCostValue * noOFDaysValue) + foodCostValue;
            totalExpenseNo.setText(String.valueOf(totalExpense));

         double totalOwe = foodOwe + lodgeOWe - transpCostReimb;
         howMuchOWeNo.setText(String.valueOf(totalOwe));
    });





        primaryStage.setScene(new Scene(vbox, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
