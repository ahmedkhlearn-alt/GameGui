package game.gui.views;
import game.gui.AppColors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import game.gui.AppColors;

public class MonsterPanel {
	private VBox panel;
    private Label nameLabel;
    private Label typeLabel;
    private Label originalRoleLabel;
    private Label currentRoleLabel;
    private Label energyLabel;
    private Label positionLabel;
    private HBox energyBarBg;
    private HBox energyBarFill;
    private HBox badgeRow;
    private Label shieldBadge;
    private Label freezeBadge;
    private Label confusionBadge;
    private Label momentumBadge;
    private Label focusBadge;
  


	public MonsterPanel(){
		buildPanel();
		
	}
	 private void buildPanel() {
		panel = new VBox(8);
		panel.setPadding(new Insets(12));
        panel.setStyle(
            "-fx-background-color: " + AppColors.PANEL_BG + ";");
	 
	 nameLabel = new Label("???");
     nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
     nameLabel.setTextFill(Color.web(AppColors.HIGH_YELLOW));
     
     typeLabel = new Label("Type: ???");
     typeLabel.setFont(Font.font("Arial", 13));
     typeLabel.setTextFill(Color.web(AppColors.TEXT_HIGH));
     
     HBox rolesRow = new HBox(10);
     rolesRow.setAlignment(Pos.CENTER_LEFT);
     originalRoleLabel = new Label("Role: ???");
     originalRoleLabel.setFont(Font.font("Arial", 13));
     originalRoleLabel.setTextFill(Color.web(AppColors.TEXT)); 
     
     currentRoleLabel = new Label("");
     currentRoleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
     currentRoleLabel.setTextFill(Color.web(AppColors.GLOW_CONFUSED));
     currentRoleLabel.setVisible(false);
     
     rolesRow.getChildren().addAll(originalRoleLabel, currentRoleLabel);
     
     positionLabel = new Label("Position: Cell 0");
     positionLabel.setFont(Font.font("Arial", 13));
     positionLabel.setTextFill(Color.web(AppColors.TEXT));
     
     Label energyTitle = new Label("Energy:");
     energyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 13));
     energyTitle.setTextFill(Color.web(AppColors.TEXT));
     
     HBox energyRow = new HBox(8);
     energyRow.setAlignment(Pos.CENTER_LEFT);


     energyBarFill = new HBox();
	 energyBarFill.setPrefSize(115, 14);
	 energyBarFill.setStyle("-fx-background-color: #e74c3c; -fx-background-radius: 5;");
	 energyBarFill.setTranslateX(-200);
	 ImageView energybar = new ImageView(new Image(getClass().getResourceAsStream("/images/empty energy bar.png")));
     energybar.setFitWidth(250);
     energybar.setFitHeight(20);
     energybar.setPreserveRatio(false);
     
     energyBarFill= new HBox();
     energyBarFill.setPrefSize(50, 14);
     energyBarFill.setMaxHeight(14);
     energyBarFill.setStyle(
    		 "-fx-background-color: #e74c3c;-fx-background-radius: 3;");
	 
	 energyLabel = new Label("0 / 1000");
	
	 energyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	 energyLabel.setTextFill(Color.web(AppColors.HIGH_YELLOW));
	 
	 StackPane barStack = new StackPane();
	 barStack.setAlignment(Pos.CENTER_LEFT);
	 barStack.setPrefSize(250, 20);
	 barStack.getChildren().addAll(energyBarFill, energybar);
	 energyBarFill.setTranslateX(85);
	 energyBarFill.setPrefWidth(0);
	 energyBarFill.setMinWidth(0);
	 energyBarFill.setMaxWidth(0);
	
	 
	 
	 badgeRow = new HBox(6);
     badgeRow.setAlignment(Pos.CENTER_LEFT);

     shieldBadge = createBadge("Shield", AppColors.BADGE_SHIELD, AppColors.GLOW_SHIELD);
     freezeBadge = createBadge("Frozen", AppColors.BADGE_FREEZE, "#aaddff");
     confusionBadge = createBadge("Confused", AppColors.BADGE_CONFUSION, AppColors.GLOW_CONFUSED);
     momentumBadge = createBadge("Momentum", AppColors.INNER_BG, AppColors.HIGH_GREEN);
     focusBadge = createBadge("Focus", AppColors.INNER_BG, AppColors.TEXT_HIGH);
     shieldBadge.setVisible(false);
     freezeBadge.setVisible(false);
     confusionBadge.setVisible(false);
     momentumBadge.setVisible(false);
     focusBadge.setVisible(false);

     badgeRow.getChildren().addAll(shieldBadge, freezeBadge, confusionBadge, momentumBadge, focusBadge);
     
     panel.getChildren().addAll(
             nameLabel,
             typeLabel,
             rolesRow,
             positionLabel,
             energyTitle,
             barStack,
             energyLabel,
             badgeRow
         );
     }
	 
	 private Label createBadge(String text, String bg, String textColor) {
	        Label badge = new Label(text);
	        badge.setFont(Font.font("Arial", FontWeight.BOLD, 11));
	        badge.setTextFill(Color.web(textColor));
	        badge.setPadding(new Insets(3, 8, 3, 8));
	        badge.setStyle(
	            "-fx-background-color: " + bg + ";" +
	            "-fx-background-radius: 5;"
	        );
	        return badge;
	    }
	 public void update(String name, String type, String originalRole,String currentRole, int energy, int position,boolean shielded, boolean frozen,int confusionTurns, int momentumTurns, int focusTurns) {
	nameLabel.setText(name);
	typeLabel.setText("Type: " + type);
	originalRoleLabel.setText("Role: " + originalRole);
	positionLabel.setText("Position: Cell " + position);
	energyLabel.setText(energy + " / 1000");
	double pct = Math.min((double) energy / 1000.0, 1.0);
	double width = pct * 69;
	energyBarFill.setMaxWidth(width);
	energyBarFill.setPrefWidth(width);
	 if (energy >= 800) {
	        energyBarFill.setStyle("-fx-background-color: #2ecc71; -fx-background-radius: 3;");
	        } 
	 else {
	        energyBarFill.setStyle("-fx-background-color: #e74c3c; -fx-background-radius: 3;");
	        }
	 if(confusionTurns>0){
		 currentRoleLabel.setText("Acting as:"+ currentRole+'/'+confusionTurns+"confusion Turns");
		 currentRoleLabel.setVisible(true);
	 }
	 else {
		 currentRoleLabel.setVisible(false);
	 }
	 shieldBadge.setVisible(shielded);
	 freezeBadge.setVisible(true);
	 confusionBadge.setVisible(confusionTurns > 0);
	 confusionBadge.setText("Confused: " + confusionTurns);
	 momentumBadge.setVisible(momentumTurns > 0);
	 momentumBadge.setText("Momentum: " + momentumTurns);
	 focusBadge.setVisible(focusTurns > 0);
	 focusBadge.setText("Focus: " + focusTurns);
	 
	 
	 
	 
	 }
	 
	 
	 
	 
	    public VBox getPanel() { return panel;
	   }
	}

