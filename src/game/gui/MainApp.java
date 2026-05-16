package game.gui;
import game.gui.controllers.StartScreenController;
import game.gui.views.MonsterPanel;
import game.gui.views.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.gui.AppColors;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
	        new StartScreenController(stage).show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}
	


