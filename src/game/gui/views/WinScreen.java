package game.gui.views;
import game.gui.AppColors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WinScreen {
	 private Stage stage;
	 private Button returnBtn;

	 public WinScreen(Stage stage) {
		 this.stage = stage;
		 }
	 
	 public void show(String winner,String winnerRole,int winnerEnergy,String loser,int loserEnergy,boolean playerWon){
		 VBox root = new VBox(20);
	     root.setAlignment(Pos.CENTER);
	     root.setStyle("-fx-background-color:"+AppColors.WINDOW_BG+";");
	     root.setPadding(new Insets(40));
	     Label resultLabel;
	     
	     if(playerWon) {
	         resultLabel = new Label("YOU WIN :)");
	         resultLabel.setTextFill(Color.web(AppColors.HIGH_YELLOW));
	         DropShadow glow = new DropShadow();
	         glow.setColor(Color.web(AppColors.GLOW_ACTIVE));
	         glow.setRadius(20);
	         glow.setSpread(0.4);
	         resultLabel.setEffect(glow);
	     } 
	     else {
	         resultLabel = new Label("YOU LOSE :(");
	         resultLabel.setTextFill(Color.web(AppColors.HIGH_RED));
	     }
	     
	     
	     resultLabel.setFont(Font.font("Arial",FontWeight.BOLD,48));
	     
	     Label winnerLabel = new Label(winner +" wins!");
	     winnerLabel.setFont(Font.font("Arial",FontWeight.BOLD,28));
	     winnerLabel.setTextFill(Color.web(AppColors.HIGH_GREEN));
	     Label winnerRoleLabel = new Label("Role: "+winnerRole);
	     winnerRoleLabel.setFont(Font.font("Arial",18));
	     winnerRoleLabel.setTextFill(Color.web(AppColors.TEXT));
	     
	     VBox energyBox = new VBox(10);
	     energyBox.setAlignment(Pos.CENTER);
	     energyBox.setPadding(new Insets(20));
	     energyBox.setMaxWidth(400);
	     energyBox.setStyle(
	            "-fx-background-color: " + AppColors.PANEL_BG + ";" +
	            "-fx-border-color: " + AppColors.BORDER_DEF + ";" +
	            "-fx-border-radius: 10;" +
	            "-fx-background-radius: 10;"
	        );

	     Label finalTitle = new Label("Final Energies:");
	     finalTitle.setFont(Font.font("Arial",FontWeight.BOLD,16));
	     finalTitle.setTextFill(Color.web(AppColors.HIGH_YELLOW));  
	     
	     Label winnerEnergyLabel = new Label(winner+": "+winnerEnergy+" energy");
	     winnerEnergyLabel.setFont(Font.font("Arial",FontWeight.BOLD,15));
	     winnerEnergyLabel.setTextFill(Color.web(AppColors.HIGH_GREEN));

	     Label loserEnergyLabel = new Label(loser +": "+loserEnergy+" energy");
	     loserEnergyLabel.setFont(Font.font("Arial",15));
	     loserEnergyLabel.setTextFill(Color.web(AppColors.TEXT_HIGH));

	     energyBox.getChildren().addAll(finalTitle, winnerEnergyLabel, loserEnergyLabel);
	     returnBtn = new Button("Return to Start");
	     returnBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	     returnBtn.setTextFill(Color.WHITE);
	     returnBtn.setPrefWidth(220);
	     returnBtn.setPrefHeight(50);
	     returnBtn.setStyle(
	    		 "-fx-background-color: " + AppColors.BTN_ROLL + ";" +
	             "-fx-background-radius: 10;" +
	             "-fx-cursor: hand;"
	        );

	        root.getChildren().addAll(
	            resultLabel,
	            winnerLabel,
	            winnerRoleLabel,
	            energyBox,
	            returnBtn
	        );

	        Scene scene = new Scene(root, 900, 691);
	        stage.setScene(scene);
	        stage.show();
	    }

	    public Button getReturnBtn() { return returnBtn; }
	
	 }
	


