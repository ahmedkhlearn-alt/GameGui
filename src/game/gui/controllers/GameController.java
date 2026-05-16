package game.gui.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.engine.Board;
import game.engine.Game;
import game.engine.Role;
import game.engine.cards.Card;
import game.engine.cells.Cell;
import game.engine.exceptions.InvalidMoveException;
import game.engine.exceptions.OutOfEnergyException;
import game.engine.monsters.*;
import game.gui.AppColors;
import game.gui.views.*;

public class GameController {
	
	private Game game;
	private BoardView boardView;
	private MonsterPanel playerPanel;
	private MonsterPanel opponentPanel;
	private Button rollBtn;
	private Button powerupBtn;
    private Label  turnLabel;
    private Label  diceResultLabel;
    private Label  statusLabel;    	    
    private Stage stage;
    private int cardDeckSizeBefore;
    
    public GameController(Role chosenRole ,Stage stage) throws IOException{
    	this.stage=stage;
    	this.game = new Game(chosenRole);
    	this.boardView = new BoardView();
    	this.playerPanel   = new MonsterPanel();
        stage.setScene(buildScene());
        stage.show();
        
        cardDeckSizeBefore = Board.getCards().size();
        refreshAllviews();
    }
    
    private Scene buildScene()
    {
    	VBox boardWrapper = new VBox(8);
    	boardWrapper.setAlignment(Pos.CENTER);
        boardWrapper.getChildren().add(boardView.getGrid());
        boardWrapper.getChildren().add(buildControlsRow());
        VBox left = new VBox(playerPanel.getPanel());
        left.setPadding(new Insets(10));
        VBox right = new VBox(opponentPanel.getPanel());
        right.setPadding(new Insets(10));
        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(boardWrapper);
        root.setRight(right);
        root.setStyle("-fx-background-color: " + AppColors.WINDOW_BG + ";");
        root.setPadding(new Insets(12));
        return new Scene(root, 1100, 720);
    }
    
    private HBox buildControlsRow()
    {
    	turnLabel = new Label("Turn 1 - Your move");
    	turnLabel.setStyle(
                "-fx-text-fill: " + AppColors.TEXT_HIGH + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
            );
    	diceResultLabel = new Label("");
        diceResultLabel.setStyle(
            "-fx-text-fill: " + AppColors.TEXT + ";" +
            "-fx-font-size: 12px;"
        );
        statusLabel = new Label("");
        statusLabel.setStyle(
            "-fx-text-fill: rgba(255,255,255,0.6);" +
            "-fx-font-size: 11px;"
        );
        powerupBtn = new Button("⚡ Use Powerup");
        styleButton(powerupBtn, AppColors.BTN_POWERUP);
        powerupBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				onPowerupClicked();
			}
		});
        
        rollBtn = new Button("🎲 Roll Dice");
        styleButton(rollBtn, AppColors.BTN_ROLL);
        rollBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				onRollClicked();
			}
        	
		});
        HBox row = new HBox(16,
                powerupBtn, rollBtn,
                turnLabel, diceResultLabel, statusLabel
            );
            row.setAlignment(Pos.CENTER);
            row.setPadding(new Insets(10, 0, 0, 0));
            return row;
    }
    
    private void onRollClicked()
    {
    	Monster currentBefore = game.getCurrent();
        boolean wasFrozen = currentBefore.isFrozen();
        Card cardBefore = null;
        
        if(!Board.getCards().isEmpty())
        {
        	cardBefore = Board.getCards().get(0);
        }
        cardDeckSizeBefore = Board.getCards().size();
        
        
        try
        {
        	game.playTurn();
        }
        catch(InvalidMoveException e)
        {
        	ErrorPopup.show("Invalid Move", e.getMessage(), stage);
            return;
        }
        if(wasFrozen)
        {
        	 statusLabel.setText("❄ " + currentBefore.getName() + " was frozen — turn skipped!");
             refreshAllviews();
             return;
        }
        if (Board.getCards().size() < cardDeckSizeBefore && cardBefore != null)
        {
        	CardPopup.show(cardBefore, currentBefore.getName(), stage);
            statusLabel.setText("🃏 Card drawn!");
        }
        else
        	statusLabel.setText("");
        refreshAllviews();
        Monster winner = game.getWinner();
        if (winner != null)
        	showWinScreen(winner);
    }
    
    
    private void onPowerupClicked()
    {
    	try
    	{
    		game.usePowerup();
    		
    	}
    	catch(OutOfEnergyException e)
    	{
    		ErrorPopup.show("Not Enough Energy", e.getMessage(), stage);
            return;
    	}
    	 statusLabel.setText("Powerup used!");
    	 refreshAllviews();
    }
    
    private void refreshAllviews()
    {
    	Monster playerM = game.getPlayer();
        Monster oppM    = game.getOpponent();
        Monster current = game.getCurrent();
        Cell[][] cells = game.getBoard().getBoardCells();
        boardView.refresh(cells, playerM.getPosition(), oppM.getPosition());
        boardView.applyGlow(current.getPosition(), AppColors.GLOW_ACTIVE);
        
        playerPanel.update(
        	    playerM.getName(),
        	    playerM.getClass().getSimpleName(),        
        	    playerM.getOriginalRole().toString(),      
        	    playerM.getRole().toString(),               
        	    playerM.getEnergy(),
        	    playerM.getPosition(),
        	    playerM.isShielded(),
        	    playerM.isFrozen(),
        	    playerM.getConfusionTurns(),
        	    0,   
        	    0    
        	);
        opponentPanel.update(
        	    oppM.getName(),
        	    oppM.getClass().getSimpleName(),
        	    oppM.getOriginalRole().toString(),
        	    oppM.getRole().toString(),
        	    oppM.getEnergy(),
        	    oppM.getPosition(),
        	    oppM.isShielded(),
        	    oppM.isFrozen(),
        	    oppM.getConfusionTurns(),
        	    0,
        	    0
        	);
        String whose;
        if(current == playerM)
        	whose = "Your turn";
        else 
        	whose = "Opponent's turn";
        turnLabel.setText(whose + " — " + current.getName());
    }
    
    private void showWinScreen(Monster winner)
    {
        rollBtn.setDisable(true);
        powerupBtn.setDisable(true);
        Monster loser = winner == game.getPlayer() ? game.getOpponent() : game.getPlayer();
        WinScreen.show(
        	    winner.getName(),
        	    winner.getRole().toString(),
        	    winner.getEnergy(),

        	    loser.getName(),
        	    loser.getRole().toString(),
        	    loser.getEnergy(),

        	    stage,

        	    new Runnable() {
        	        @Override
        	        public void run() {
        	            try {
        	                new GameController(game.getPlayer().getOriginalRole(), stage);
        	            } catch (IOException e) {
        	                ErrorPopup.show(
        	                    "Error",
        	                    "Could not restart game: " + e.getMessage(),
        	                    stage
        	                );
        	            }
        	        }
        	    }
        	);
        
    }
    private Monster getOpponentOf(Monster m)
    {
    	return m == game.getPlayer() ? game.getOpponent() : game.getPlayer();
    }
    private void styleButton(Button btn, String bgColor) 
    {
    	btn.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: " + AppColors.TEXT + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 20;" +
                "-fx-cursor: hand;"
            );
    }
    
    
    
    
}
