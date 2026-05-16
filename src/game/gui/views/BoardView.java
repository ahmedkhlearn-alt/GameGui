package game.gui.views;

import java.io.InputStream;

import game.gui.AppColors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import game.engine.Role;
import game.engine.cells.*;

public class BoardView extends Application{
	
	private GridPane grid;
	private StackPane[] cellViews = new StackPane[100];
	private Cell[] lastKnownCells = new Cell[100];
	
	public BoardView()
	{
        this.grid = new GridPane();
        this.grid.setHgap(3);
        this.grid.setVgap(3);
        this.grid.getStyleClass().add(AppColors.CELL_NORMAL);
        this.grid.setPadding(new Insets(12));//try to remove and add
        
        setupGridConstraints();
        buildEmptyCells();
	}
	
	public GridPane getGrid()
	{
		return grid;
	}
	
	public void setupGridConstraints()//resize the columns and rows to make all of them hv the same size 
	{
		for(int i=0;i<10;i++)
		{
			ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(10);
            grid.getColumnConstraints().add(cc);
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(10);
            grid.getRowConstraints().add(rc);
		}
	}
	
	private void buildEmptyCells()
	{
		for(int i=0 ; i<100 ; i++)
		{
			StackPane cellView = new StackPane();
			cellView.setStyle("-fx-background-color: " + "#000080" + ";");
			cellView.getStyleClass().add("cell-normal"); 
	        cellView.setMinSize(60, 60); //size of each cell
	        cellView.setAlignment(Pos.CENTER);
	        
	        Label indexLabel = new Label(String.valueOf(i));
	        indexLabel.setTextFill(Color.valueOf(AppColors.TEXT));
            StackPane.setAlignment(indexLabel, Pos.TOP_LEFT);
            indexLabel.setTranslateX(3);
            indexLabel.setTranslateY(2);
            cellView.getChildren().add(indexLabel);//index is always child[0]
            cellViews[i] = cellView;
            
            int gameRow = i/10;
            int gameCol = i%10;
            int gridCol;
            if(gameRow%2==0)
            	gridCol = gameCol;
            else 
            	gridCol = (9-gameCol);
            
            int gridRow = 9-gameRow;
            grid.add(cellView,gridCol,gridRow);
            
		}
	}
	
	public void refresh(Cell[][] cells , int playerpos , int opponentPos)
	{
		for(int i = 0; i<100 ; i++)
		{
			int row = i/10;
			int col = i%10;
			Cell cell = cells[row][col];
			lastKnownCells[i] = cell;
			refreshCell(i,cell);
			
		}
		placeToken(playerpos,AppColors.HIGH_YELLOW,"P");
	}
	
	private void placeToken(int pos,String color,String label)
	{
		StackPane cell = cellViews[pos];
		Circle circle = new Circle(8);
		circle.setStyle(
	            "-fx-fill: #1a1a2e;" +
	            "-fx-stroke: " + color + ";" +
	            "-fx-stroke-width: 2;" +
	            "-fx-effect: dropshadow(gaussian," + color + ",10,0.7,0,0);"
	        );
		Label lbl = new Label(label);
        lbl.setStyle(
            "-fx-text-fill:" + color + ";" +
            "-fx-font-size:8px;" +
            "-fx-font-weight:bold;"
        );
        StackPane token = new StackPane(circle, lbl);
        token.setUserData("token");
        token.setMaxSize(18, 18);
        StackPane.setAlignment(token, Pos.BOTTOM_RIGHT);
        token.setTranslateX(-2);
        token.setTranslateY(-2);
        cell.getChildren().add(token);
        
	}
	
	private void refreshCell(int index, Cell cell)
	{
		StackPane cellView = cellViews[index];
		if(cellView.getChildren().size()>1)
		{
			 cellView.getChildren().subList(1, cellView.getChildren().size()).clear();
		}
		
	}
	private String getCellStyle(int index , Cell cell)
	{
		String bg;
		String border = AppColors.BORDER_DEF;
		if(index == 0 || index == 99)
		{
			bg = AppColors.CELL_NORMAL;
		}
		else if(cell instanceof DoorCell )
		{
			if(((DoorCell) cell).isActivated())
			{
				bg = AppColors.PANEL_BG;
				border = "#2a2a42";
			}
			else
			{
				if(((DoorCell) cell).getRole() == Role.SCARER)
					bg=AppColors.CELL_DOOR_SCARER;
				else
					bg=AppColors.CELL_DOOR_LAUGHER;
			}
		}
		else if(cell instanceof CardCell)
			bg = AppColors.CELL_CARD;
		else if(cell instanceof ConveyorBelt)
			bg=AppColors.CELL_CONVEYOR;
		else if(cell instanceof ContaminationSock)
			bg = AppColors.CELL_SOCK;
		else if (cell instanceof MonsterCell)
			bg = AppColors.CELL_MONSTER;
		else 
			bg = AppColors.CELL_NORMAL;

		return buildCellStyle(bg, border);
		
	}
	private String buildCellStyle(String bgColor, String borderColor) 
	{
		return
			"-fx-background-color: " + bgColor + ";" +
            "-fx-background-radius: 6;" +
            "-fx-border-color: " + borderColor + ";" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 6;";
    }
	
	public static String getMonsterImagePath(String name)
	{
	    switch (name) {
	        case "James P. Sullivan":
	            return "/images/sulley.png";
	        case "Mike Wazowski":
	            return "/images/mike.png";
	        case "Randall Boggs":
	            return "/images/randall.png";
	        case "Celia Mae":
	            return "/images/celia.png";
	        case "Roz":
	            return "/images/roz.png";
	        case "Fungus":
	            return "/images/fungus.png";
	        case "Henry J. Waternoose":
	            return "/images/waternoose.png";
	        case "Yeti":
	            return "/images/yeti.png";
	        default:
	            return null;
	    }
	}
	
    private String getCellImagePath(int index, Cell cell)
    {
        if (cell instanceof CardCell)           return "/images/card_cell.png";
        if (cell instanceof ConveyorBelt)       return "/images/belt.png";
        if (cell instanceof ContaminationSock)  return "/images/sock.png";
        if (cell instanceof DoorCell ) {
            return ((DoorCell) cell).getRole() == Role.SCARER
                ? "/images/door_scarer.png"
                : "/images/door_laugher.png";
        }
        if (cell instanceof MonsterCell && ((MonsterCell) cell).getCellMonster() != null) {
            return getMonsterImagePath(((MonsterCell) cell).getCellMonster().getName());
        }
        return null;
    }
    
    private ImageView loadImage(String path, double width, double height) 
    {
        try {
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream == null) return null;
            Image img = new Image(stream, width, height, true, true);
            return new ImageView(img);
        } catch (Exception e) {
            return null;  // never crash for a missing image
        }
    }
    
    public void applyGlow(int cellIndex, String glowColor) 
    {
        StackPane cell = cellViews[cellIndex];
        String baseStyle = getCellStyle(cellIndex, lastKnownCells[cellIndex]);
        cell.setStyle(baseStyle +"-fx-effect: dropshadow(gaussian," + glowColor + ",14,0.6,0,0);" +"-fx-border-color: " + glowColor + ";");
    }
    
    public void removeGlow(int cellIndex) {
        if (lastKnownCells[cellIndex] != null) {
            cellViews[cellIndex].setStyle(getCellStyle(cellIndex, lastKnownCells[cellIndex]));
        }
    }
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene s = new Scene(grid,700,700);
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
