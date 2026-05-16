package game.gui.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class BoardController {
	
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

	    // ── Card tracking ─────────────────────────────────────────────────────
	    // Board.drawCard() is called inside the engine during playTurn().
	    // We track the card deck size BEFORE playTurn() — if it shrank, a card was drawn.
	    private int cardDeckSizeBefore;
}
