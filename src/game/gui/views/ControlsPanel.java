package game.gui.views;
import game.gui.AppColors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControlsPanel {
	private VBox panel;
	private Button rollBtn;
	private Button powerup;
	private Label turnLabel;
	private Label diceResult;
	private Label freezeLabel;
	private ImageView diceImage;

	
	public ControlsPanel(){
		buildPanel();
	}
	
	private void buildPanel(){
	diceImage = new ImageView();
	diceImage.setFitWidth(60);
	diceImage.setFitHeight(60);
	diceImage.setPreserveRatio(true);
	panel = new VBox(12);
	panel.setAlignment(Pos.CENTER);
	panel.setPadding(new Insets(15));
	panel.setStyle(
	"-fx-background-color: " + AppColors.PANEL_BG+";"+
	"-fx-border-color: " + AppColors.BORDER_DEF+";"+
	"-fx-border-radius: 10;" +
	"-fx-background-radius: 10;");
	
	turnLabel = new Label("YOUR TURN");
    turnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    turnLabel.setTextFill(Color.web(AppColors.HIGH_YELLOW));
    
    freezeLabel = new Label("TURN SKIPPED:FROZEN!");
    freezeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    freezeLabel.setTextFill(Color.web("#aaddff"));
    freezeLabel.setVisible(false);
    
    powerup = new Button("Use Powerup (500 energy)");
    powerup.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    powerup.setTextFill(Color.WHITE);
    powerup.setPrefWidth(220);
    powerup.setPrefHeight(45);
    powerup.setStyle( 
    	"-fx-background-color: " + AppColors.BTN_POWERUP + ";" +
        "-fx-background-radius: 8;" +
        "-fx-cursor: hand;");
    
    rollBtn = new Button("Roll Dice");
    rollBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    rollBtn.setTextFill(Color.WHITE);
    rollBtn.setPrefWidth(220);
    rollBtn.setPrefHeight(50);
    rollBtn.setStyle(
        "-fx-background-color: " + AppColors.BTN_ROLL + ";" +
        "-fx-background-radius: 8;" +
        "-fx-cursor: hand;"
    );
    diceResult = new Label("Roll the dice!");
    diceResult.setFont(Font.font("Arial", 14));
    diceResult.setTextFill(Color.web(AppColors.TEXT_HIGH));
    panel.getChildren().addAll(
            turnLabel,
            freezeLabel,
            powerup,
            rollBtn,
            diceResult,
            diceImage
        );}
    public void setTurnLabel(String playerName, boolean isYourTurn) {
        if (isYourTurn) {
            turnLabel.setText("Your turn -> " + playerName);
            turnLabel.setTextFill(Color.web(AppColors.HIGH_YELLOW));
        } else {
            turnLabel.setText("Opponent's turn ->" + playerName);
            turnLabel.setTextFill(Color.web(AppColors.TEXT_HIGH));
        }}
     public void setDiceResult(int result) {
        diceResult.setText("Rolled: "+result);
        Image img = new Image(getClass().getResourceAsStream("/images/dice"+result +".png"));
        diceImage.setImage(img);
        diceResult.setTextFill(Color.web(AppColors.TEXT));
        } 
     public void setFrozen(boolean frozen) {
    	 freezeLabel.setVisible(frozen);
         rollBtn.setDisable(frozen);
         powerup.setDisable(frozen);
     }
     public void setPowerupDisabled(boolean disabled) {
         powerup.setDisable(disabled);
         if (disabled) {
             powerup.setStyle(
                 "-fx-background-color: " + AppColors.BTN_DISABLED + ";" +
                 "-fx-background-radius: 8;"
             );} 
         else {
             powerup.setStyle(
                 "-fx-background-color: " + AppColors.BTN_POWERUP + ";" +
                 "-fx-background-radius: 8;" +
                 "-fx-cursor: hand;"
             );}}
      public Button getRollBtn() { return rollBtn; }
      public Button getPowerupBtn() { return powerup; }
      public VBox getPanel() { return panel; }  
    }
    
    
    

