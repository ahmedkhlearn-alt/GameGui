package game.gui.views;

import game.gui.AppColors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorPopup {
	 public static void show(String title, String reason, Stage ownerStage)
	 {
		 Stage popup = new Stage();
	     popup.initModality(Modality.APPLICATION_MODAL);
	     popup.initOwner(ownerStage);
	     popup.setTitle(title);
		 VBox root = new VBox(16);
		 root.setAlignment(Pos.CENTER);
		 root.setPadding(new Insets(24));
	     root.setStyle(
	         "-fx-background-color: " + AppColors.PANEL_BG + ";" +
	       	 "-fx-border-color: " + AppColors.HIGH_RED + ";" +
	         "-fx-border-width: 2;" +
	         "-fx-border-radius: 12;" +
	         "-fx-background-radius: 12;"
	         );
	     
	     Label icon = new Label("⚠");
	     icon.setStyle(
	             "-fx-text-fill: " + AppColors.HIGH_RED + ";" +
	             "-fx-font-size: 36px;"
	            		 );
	     Label titleLabel = new Label(title);
	        titleLabel.setStyle(
	            "-fx-text-fill: " + AppColors.HIGH_RED + ";" +
	            "-fx-font-size: 16px;" +
	            "-fx-font-weight: bold;"
	            );
	     Label reasonLabel = new Label(reason);
	     reasonLabel.setStyle(
	            "-fx-text-fill: rgba(255,255,255,0.8);" +
	            "-fx-font-size: 12px;"
	            		);
	     reasonLabel.setWrapText(true);
	     reasonLabel.setMaxWidth(260);
	     reasonLabel.setAlignment(Pos.CENTER);
	     
	     Label hint = new Label("Please choose a different action.");
	     hint.setStyle(
	         "-fx-text-fill: rgba(255,255,255,0.4);" +
	         "-fx-font-size: 10px;"
	        		 );
	
	     Button okBtn = new Button("OK");
	        okBtn.setStyle(
	            "-fx-background-color: " + AppColors.HIGH_RED + ";" +
	            "-fx-text-fill: " + AppColors.TEXT + ";" +
	            "-fx-font-weight: bold;" +
	            "-fx-background-radius: 8;" +
	            "-fx-padding: 8 32;"
	            );
	        okBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					popup.close();
					
				}
			});
	        Scene scene = new Scene(root, 300, 200);
	        popup.setScene(scene);
	        popup.setResizable(false);
	        popup.show(); 
	 }
	 
}
