package game.gui.controllers;

import game.gui.views.WinScreen;
import javafx.stage.Stage;

public class WinScreenController {
	private Stage stage;
	private WinScreen view;
	
	public WinScreenController(Stage stage,String winner,String winnerRole,int winnerEnergy,String loser,int loserEnergy,boolean playerWon) {
		this.stage = stage;
        this.view = new WinScreen(stage);
        view.show(winner,winnerRole,winnerEnergy,loser,loserEnergy,playerWon);
        wireButtons();
    }

    private void wireButtons() {
        view.getReturnBtn().setOnAction(e -> {
        	new StartScreenController(stage).show();
        	});
    }

}
