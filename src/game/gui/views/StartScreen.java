package game.gui.views;
import game.engine.Role;
import game.gui.controllers.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import game.gui.AppColors;

public class StartScreen {
	private Stage stage;
	private Label warningLabel;
	private Button scarerBtn;
	private Button laugherBtn;
	private Button startBtn;
	public StartScreen(Stage stage){
	this.stage = stage;
	
	}
	

	public void show(){
		warningLabel = new Label("Please select a side first!");
		warningLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		warningLabel.setTextFill(Color.RED);
		warningLabel.setVisible(false); 
		warningLabel.setTranslateX(250);
		warningLabel.setTranslateY(-30);
		StackPane root= new StackPane();
		Image image = new Image(getClass().getResourceAsStream("/images/download.png"));
		BackgroundImage bgImage = new BackgroundImage(
		    image,
		    BackgroundRepeat.NO_REPEAT,
		    BackgroundRepeat.NO_REPEAT,
		    BackgroundPosition.CENTER,
		    BackgroundSize.DEFAULT
		);
		root.setBackground(new Background(bgImage));
		VBox content = new VBox(20);
	    content.setAlignment(Pos.CENTER);
	    content.setPadding(new Insets(40));
	    content.setMaxWidth(550);
		Label title = new Label("Door Dash");
		title.setFont(Font.font("Impact", FontWeight.BOLD, 52));
		title.setTextFill(Color.valueOf("#004586"));
		DropShadow glow = new DropShadow();
		glow.setColor(Color.web("#FFFFFF"));
		title.setTranslateY(-275);
		glow.setRadius(10);
		glow.setSpread(0.2);
		title.setEffect(glow);

		VBox instructions = new VBox(8);
        instructions.setAlignment(Pos.CENTER_LEFT); 

        instructions.setPadding(new Insets(20));
		
        Label winTitle = new Label("How to Win:");
        winTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        winTitle.setTextFill(Color.web("#800080"));
        
        String[] rules = {
                "Reach cell 99 (Boo's Door) with 1000+ energy",
                "Roll the dice each turn to move forward",
                "Land on your team's doors to gain energy",
                "Avoid contamination socks,drains energy",
                "Card cells give you random powerful effects",
                "Use your powerup (costs 500 energy)",
                "First to cell 99 with 1000+ energy wins!"
            };
        instructions.getChildren().add(winTitle);
        for (String rule : rules) {
            Label r = new Label(rule);
            r.setFont(Font.font("Arial", 20));
            r.setTextFill(Color.web("#800080"));
            instructions.getChildren().add(r); 
        }
        instructions.setTranslateY(-100);
        Label chooseLabel = new Label("Choose Your Side:");
        chooseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        chooseLabel.setTextFill(Color.web("#ffffff"));
        chooseLabel.setTranslateX(250);
        chooseLabel.setTranslateY(-200);
        HBox buttonRow = new HBox(30);
        buttonRow.setAlignment(Pos.CENTER);
        
        scarerBtn = createSideButton("SCARER", "#5c0a0a", "#ff4444");
        laugherBtn = createSideButton("LAUGHER", "#0a2a5c", "#3498db");
        buttonRow.getChildren().addAll(scarerBtn, laugherBtn);
        scarerBtn.setTranslateX(365);
        scarerBtn.setTranslateY(-150);
        laugherBtn.setTranslateX(135);
        laugherBtn.setTranslateY(-75);
        
       
        startBtn = new Button("Start!");
        startBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startBtn.setTextFill(Color.PINK);
        startBtn.setPrefWidth(300);  
        startBtn.setPrefHeight(45); 
        startBtn.setTranslateX(-250);
        startBtn.setTranslateY(60);
        root.getChildren().addAll(
	            title,
	            instructions,
	            chooseLabel,
	            buttonRow,
	            startBtn,
	            warningLabel
	        );
        stage.setTitle("Door Dash: Scare vs Laugh Touchdown");
		Scene scene = new Scene(root, 900, 691); 
		stage.setScene(scene); 
		stage.show();}
        
        private Button createSideButton(String text, String bg, String glowColor) {
            Button btn = new Button(text);
            btn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            btn.setTextFill(Color.PINK);
            btn.setPrefWidth(200);  
            btn.setPrefHeight(60); 
            return btn;
        }
        public Button getScarerBtn() { return scarerBtn; }
        public Button getLaugherBtn() { return laugherBtn; }
        public Button getStartBtn() { return startBtn; }
        public Label getWarningLabel() { return warningLabel; }
}



        
        
		
		
	
	
	


