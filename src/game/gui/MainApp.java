package game.gui;
import game.gui.controllers.StartScreenController;
import game.gui.controllers.WinScreenController;
import game.gui.views.ControlsPanel;
import game.gui.views.MonsterPanel;
import game.gui.views.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.gui.AppColors;

public class MainApp extends Application {
	@Override
    public void start(Stage stage) {
		new WinScreenController(stage, "Sulley", "SCARER", 1200, "Mike", 450, true);
 }
	

 public static void main(String[] args) {
     launch(args);}
 }
	


