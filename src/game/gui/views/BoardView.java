package game.gui.views;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import game.engine.Role;
import game.engine.cells.*;

public class BoardView {
	private GridPane grid;
	private StackPane[] cellViews = new StackPane[100];
	
	public BoardView()
	{
        this.grid = new GridPane();
        this.grid.setHgap(3);
        this.grid.setVgap(3);
        this.grid.getStyleClass().add("board-background");
        this.grid.setPadding(new Insets(12));//try to remove and add
        
        setupGridConstraints();
        buildEmptyCells();
	}
	
	public GridPane getGrid()
	{
		return grid;
	}
	
	public void setupGridConstraints()
	{
		
	}
}
