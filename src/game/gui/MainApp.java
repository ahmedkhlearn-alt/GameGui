package game.gui;
import game.gui.controllers.StartScreenController;
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

        ControlsPanel controls = new ControlsPanel();

        // test methods
        controls.setTurnLabel("Mike", true);
        controls.setDiceResult(2);
        controls.setFrozen(false);

        StackPane root = new StackPane();
        root.getChildren().add(controls.getPanel());

        Scene scene = new Scene(root, 400, 500);

        stage.setScene(scene);
        stage.setTitle("ControlsPanel Test");
        stage.show();
 }
	

 public static void main(String[] args) {
     launch(args);}
 }
	


