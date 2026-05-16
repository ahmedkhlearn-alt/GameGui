package game.gui;


import game.engine.Role;
import game.engine.cards.ShieldCard;
import game.engine.cards.SwapperCard;
import game.engine.cells.*;
import game.gui.views.BoardView;
import game.gui.views.CardPopup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        // ── 1. Build fake board data ──────────────────────────────────────
        Cell[][] fakeCells = buildFakeCells();

        // ── 2. Create BoardView and refresh with fake data ────────────────
        BoardView boardView = new BoardView();
        boardView.refresh(fakeCells, 0, 12);  // player at 0, opponent at 12

        // ── 3. Put the board in a root layout ─────────────────────────────
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #0d0d1a;");
        root.setCenter(boardView.getGrid());

        // ── 4. Show the main window ───────────────────────────────────────
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("DoorDasH - Test");
        stage.setScene(scene);
        stage.show();

        // ── 5. Test CardPopup — shows automatically on launch ─────────────
        // Replace ShieldCard with any card type to test different badges
        SwapperCard testCard = new SwapperCard("Position Swapper", "Swaps positions if behind!", 1);
        CardPopup.show(testCard, "Sulley", stage);
    }

    // ── Fake board — same as before ───────────────────────────────────────
    private Cell[][] buildFakeCells() {
        Cell[][] cells = new Cell[10][10];

        int[] monsterIndices  = {2, 18, 34, 54, 82, 88};
        int[] conveyorIndices = {6, 22, 44, 52, 66};
        int[] sockIndices     = {32, 42, 74, 84, 98};
        int[] cardIndices     = {4, 12, 28, 36, 48, 56, 60, 76, 86, 90};

        for (int i = 0; i < 100; i++) {
            int row = i / 10;
            int col = i % 10;

            if (contains(cardIndices, i))
                cells[row][col] = new CardCell("Card Cell");
            else if (contains(conveyorIndices, i))
                cells[row][col] = new ConveyorBelt("Conveyor", 10);
            else if (contains(sockIndices, i))
                cells[row][col] = new ContaminationSock("Sock", -100);
            else if (contains(monsterIndices, i))
                cells[row][col] = new MonsterCell("Monster Cell", null);
            else if (i % 2 == 1)
                cells[row][col] = new DoorCell("Door",
                    i % 4 == 1 ? Role.SCARER : Role.LAUGHER, 100);
            else
                cells[row][col] = new Cell("Normal");
        }

        return cells;
    }

    private boolean contains(int[] arr, int val) {
        for (int x : arr) if (x == val) return true;
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}