package game.gui.controllers;
import game.engine.Role;
import game.gui.views.StartScreen;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class StartScreenController {
	private Stage stage;
	private StartScreen view;
	private Role chosenRole = null;
	
	 public StartScreenController(Stage stage) {
	        this.stage = stage;
	        this.view = new StartScreen(stage);
	    }
	  public void show() {
	        view.show();       
	        wireButtons();     
	    }

	private void wireButtons() {
		 view.getScarerBtn().setOnAction(e -> {
		        chosenRole = Role.SCARER;
		    });
		 view.getLaugherBtn().setOnAction(e -> {
		        chosenRole = Role.LAUGHER;
		 });
		 
		 view.getStartBtn().setOnAction(e -> {
			    System.out.println("START clicked");
			    System.out.println("chosenRole = " + chosenRole);
			    System.out.println("warningLabel = " + view.getWarningLabel());
			    
			    if (chosenRole == null) {
			        view.getWarningLabel().setVisible(true);
			        return;
			    }
		        try {
		        	 // new GameController(chosenRole, stage).show();
		        	
		        }
		  catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    });
	}}
		        	
		        
		 

