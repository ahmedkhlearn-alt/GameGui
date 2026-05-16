package game.gui.views;

import java.io.InputStream;

import com.sun.javafx.application.LauncherImpl;

import game.engine.cards.*;
import game.gui.AppColors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CardPopup {

	 public static void show(Card card, String monsterName, Stage ownerStage) 
	 {
		 Stage popup = new Stage();
	     popup.initModality(Modality.APPLICATION_MODAL);
	     popup.initOwner(ownerStage);
	     popup.setTitle("Card Drawn!");
	     VBox root = new VBox(14);
	     root.setAlignment(Pos.CENTER);
	     root.setPadding(new Insets(24));
	     root.setStyle(
	          "-fx-background-color: " + AppColors.PANEL_BG + ";" +
	          "-fx-border-color: " + AppColors.BORDER_ACT + ";" +
	          "-fx-border-width: 2;" +
	          "-fx-border-radius: 12;" +
	          "-fx-background-radius: 12;"
	        );
	     ImageView cardImg = loadImage("/images/card_cell.png", 64, 64);
	        if (cardImg != null) 
	        	root.getChildren().add(cardImg);
	        Label header = new Label(monsterName + " drew a card!");
	        header.setStyle(
	            "-fx-text-fill: " + AppColors.TEXT_HIGH + ";" +
	            "-fx-font-size: 13px;" +
	            "-fx-font-weight: bold;"
	        );
	        
	        Label nameLabel = new Label(card.getName());
	        nameLabel.setStyle(
	            "-fx-text-fill: " + AppColors.TEXT + ";" +
	            "-fx-font-size: 18px;" +
	            "-fx-font-weight: bold;"
	        );
	        
	        Label descLabel = new Label(card.getDescription());
	        descLabel.setStyle(
	            "-fx-text-fill: rgba(255,255,255,0.7);" +
	            "-fx-font-size: 12px;"
	        );
	        descLabel.setWrapText(true);
	        descLabel.setMaxWidth(280);
	        descLabel.setAlignment(Pos.CENTER);
	       
	        Label effectBadge = buildEffectBadge(card);
	        
	        Button okBtn = new Button("Got it!");
	        okBtn.setStyle(
	            "-fx-background-color: " + AppColors.BTN_ROLL + ";" +
	            "-fx-text-fill: " + AppColors.TEXT + ";" +
	            "-fx-font-weight: bold;" +
	            "-fx-background-radius: 8;" +
	            "-fx-padding: 8 24;"
	        );
	        
	        okBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					popup.close();
				}
			});
	        
	        root.getChildren().addAll(header, nameLabel, descLabel, effectBadge, okBtn);
	        Scene scene = new Scene(root, 320, 340);
	        popup.setScene(scene);
	        popup.setResizable(false);
	        popup.show(); 
	        
	        
	 }
	 
	 private static Label buildEffectBadge(Card card) {
		 String text;
	     String color = null;
	     if (card instanceof SwapperCard) 
	     {
	            text  = "↔  Positions Swapped!";
	            color = AppColors.HIGH_PURPLE;
	     }
	     else if (card instanceof StartOverCard)
	     {
	    	 if(((StartOverCard)card).isLucky())
	    		 text = "⬅  Opponent sent back to start!";
	    	 else
	    		 text= "⬅  You were sent back to start!";
	     }
	     else if(card instanceof ShieldCard)
	     {
	    	 text  = "🛡  Shield Granted!";
	         color = AppColors.HIGH_BLUE;
	     }
	     else if(card instanceof ConfusionCard)
	     {
	    	 text  = "🌀  Roles confused for " + ((ConfusionCard)card).getDuration() + " turns!";
	    	 color = AppColors.HIGH_PURPLE;
	     }
	     else if (card instanceof EnergyStealCard)
	     {
	    	 text = "⚡  " + ((EnergyStealCard)card).getEnergy() + " energy stolen from opponent!";
	    	 color = AppColors.TEXT;
	     }
	     else
	     {
	    	 text  = "Effect applied!";
	         color = AppColors.TEXT;
	     }
	     
	     Label badge = new Label(text);
	        badge.setStyle(
	            "-fx-background-color: " + color + "22;" +   // 22 = ~13% opacity
	            "-fx-border-color: " + color + ";" +
	            "-fx-border-width: 1;" +
	            "-fx-border-radius: 20;" +
	            "-fx-background-radius: 20;" +
	            "-fx-text-fill: " + color + ";" +
	            "-fx-font-size: 11px;" +
	            "-fx-font-weight: bold;" +
	            "-fx-padding: 6 14;"
	        );
	        return badge;
	 }
	 private static ImageView loadImage(String path, double w, double h)
	 {
		 try
		 {
			 InputStream stream = CardPopup.class.getResourceAsStream(path);
			 if (stream == null) return null;
	            return new ImageView(new Image(stream, w, h, true, true));
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
	 }
	 
}
