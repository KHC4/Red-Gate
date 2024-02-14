package views;

import GameModel.Items.Item;
import GameModel.Scores.ScoreBoard;
import GameModel.Scores.ScoreList;
import GameModel.Scores.SingletonScore;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class InventoryView.
 *__________________________
 * This is the Class that will visualize the inventory.
 *
 */
public class InventoryView {
    /** adventuregameview reference */
    private AdventureGameView adventureGameView;
    /** Main Gridpane of inventoryview */
    private GridPane sceneGridPane;
    /** Label describing current weapon */
    private Label weaponSelected;
    /** Button holding current weapon */
    private Button currWeapon;
    /** Gridpane holding the items in inventory */
    private GridPane inventoryGridPane;
    /** Label holding the inventory guide */
    private Label inventoryDescription;
    /** Light Mode toggle switch */
    private boolean lightMode;


    /**
     * InventoryView Constructor
     * __________________________
     * Initializes attributes
     * @param adventureGameView reference to adventuregameview.
     */
    public InventoryView(AdventureGameView adventureGameView){
        this.adventureGameView = adventureGameView;
        lightMode = adventureGameView.lightMode;

        final Stage invGUI = new Stage();

        inventoryDescription = new Label();
        customizeLabel(inventoryDescription, 16);
        String invDesc = "Left Click on an item to use or equip\n\nRight Click an item to drop it\n\nHover over the item to see name and description";
        inventoryDescription.setText(invDesc);

        sceneGridPane = new GridPane();
        sceneGridPane.setPadding(new Insets(20));
        sceneGridPane.setHgap(20.0);
        if (!lightMode) {
            sceneGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else{
            sceneGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(300);

        ColumnConstraints col1 = new ColumnConstraints(500);
        ColumnConstraints col2 = new ColumnConstraints(100);

        sceneGridPane.getRowConstraints().addAll(row1, row2);
        sceneGridPane.getColumnConstraints().addAll(col1,col2);

        weaponSelected = new Label("Equipped Weapon");
        customizeLabel(weaponSelected, 16);

        currWeapon = new Button();
        customizeButton(currWeapon, 75, 75);

        if (adventureGameView.model.player.inventory.getWeapon() == null){
            weaponSelected.setText("No weapon equipped");

            Image itemImageFile = new Image("src/images/null.png");
            ImageView itemImageView = new ImageView(itemImageFile);
            itemImageView.setPreserveRatio(true);
            itemImageView.setFitHeight(75);
            currWeapon.setGraphic(itemImageView);

        }else{
            weaponSelected.setText("Equipped: " + adventureGameView.model.player.inventory.getWeapon().getName());
            String itemImage = adventureGameView.model.player.getInventory().getWeapon().getImagePath();
            Image itemImageFile = new Image(itemImage);
            ImageView itemImageView = new ImageView(itemImageFile);
            itemImageView.setPreserveRatio(true);
            itemImageView.setFitHeight(75);
            currWeapon.setGraphic(itemImageView);

            currWeapon.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    adventureGameView.model.getCurrentRoom().addItem(adventureGameView.model.player.inventory.removeWeapon());
                }
                update();
            });
        }

        HBox equipped = new HBox(currWeapon, weaponSelected);
        equipped.setSpacing(20);

        inventoryGridPane = new GridPane();
        RowConstraints row3 = new RowConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(125);
        inventoryGridPane.setPadding(new Insets(25));
        inventoryGridPane.getRowConstraints().addAll(row3, row3, row3);
        inventoryGridPane.getColumnConstraints().addAll(col3, col3, col3, col3);
        inventoryGridPane.setGridLinesVisible(true);
        if (!lightMode) {
            sceneGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#202020"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else{
            sceneGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        inventoryGridPane.getChildren().clear();

        for (int i =0; i < 4; i++){
            for (int j = 0; j< 3; j++){
                Button object = new Button();
                customizeButton(object, 75, 75);

                Item item = adventureGameView.model.player.inventory.getItem(i, j);
                String itemImage = null;
                if (item == null){
                    itemImage = "src/images/null.png";
                }else{
                    itemImage = item.getImagePath();
                }

                Image itemImageFile = new Image(itemImage);
                ImageView itemImageView = new ImageView(itemImageFile);
                itemImageView.setPreserveRatio(true);
                itemImageView.setFitHeight(75);

                object.setAlignment(Pos.CENTER);
                object.setGraphic(itemImageView);
                int finalI = i;
                int finalJ = j;

                if (item != null) {
                    object.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            adventureGameView.model.player.inventory.useItem(finalI, finalJ);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            adventureGameView.model.getCurrentRoom().addItem(adventureGameView.model.player.inventory.removeItem(finalI, finalJ));
                        }
                        update();
                    });
                    object.setTooltip(new Tooltip(item.getName() + "\n" + item.getDescription()));
                }
                inventoryGridPane.add(object, i, j, 1, 1);
            }
        }

        sceneGridPane.add(equipped, 0, 0, 1, 1);
        sceneGridPane.add(inventoryGridPane, 0, 1,1, 1);
        sceneGridPane.add(inventoryDescription, 1, 0, 1, 2);

        GridPane.setHalignment(inventoryDescription, HPos.CENTER);
        GridPane.setValignment(inventoryDescription, VPos.CENTER);

        Scene scene = new Scene(sceneGridPane, 700, 450);
        invGUI.setScene(scene);
        invGUI.show();


        invGUI.setOnHidden(event -> adventureGameView.updateScene());

    }
    /**
     * update
     * __________________________
     * updates the inventory view if an item has been dropped or picked up
     */
    private void update(){

        inventoryGridPane.getChildren().clear();

        for (int i =0; i < 4; i++){
            for (int j = 0; j< 3; j++){
                Button object = new Button();
                customizeButton(object, 75, 75);

                Item item = adventureGameView.model.player.inventory.getItem(i, j);
                String itemImage = null;
                if (item == null){
                    itemImage = "src/images/null.png";
                }else{
                    itemImage = item.getImagePath();
                }

                Image itemImageFile = new Image(itemImage);
                ImageView itemImageView = new ImageView(itemImageFile);
                itemImageView.setPreserveRatio(true);
                itemImageView.setFitHeight(75);

                object.setAlignment(Pos.CENTER);
                object.setGraphic(itemImageView);
                int finalI = i;
                int finalJ = j;

                if (item != null) {
                    object.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            adventureGameView.model.player.inventory.useItem(finalI, finalJ);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            adventureGameView.model.getCurrentRoom().addItem(adventureGameView.model.player.inventory.removeItem(finalI, finalJ));
                        }
                        update();
                    });
                    object.setTooltip(new Tooltip(item.getName() + "\n" + item.getDescription()));
                }

//                set hover property to display item info
                inventoryGridPane.add(object, i, j, 1, 1);
            }
        }

        if (adventureGameView.model.player.inventory.getWeapon() == null){
            weaponSelected.setText("No weapon equipped");

            Image itemImageFile = new Image("src/images/null.png");
            ImageView itemImageView = new ImageView(itemImageFile);
            itemImageView.setPreserveRatio(true);
            itemImageView.setFitHeight(75);
            currWeapon.setGraphic(itemImageView);

        }else{
           weaponSelected.setText("Equipped: " + adventureGameView.model.player.inventory.getWeapon().getName());
           String itemImage = adventureGameView.model.player.getInventory().getWeapon().getImagePath();
           Image itemImageFile = new Image(itemImage);
           ImageView itemImageView = new ImageView(itemImageFile);
           itemImageView.setPreserveRatio(true);
           itemImageView.setFitHeight(75);
           currWeapon.setGraphic(itemImageView);

            currWeapon.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    adventureGameView.model.getCurrentRoom().addItem(adventureGameView.model.player.inventory.removeWeapon());
                }
                update();
            });
        }
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Times New Roman", 20));
        if (!lightMode){
            inputButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        } else {
            inputButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: black;");
        }
    }
    /**
     * customizeLabel
     * __________________________
     *
     * @param inputLabel the button to make stylish :)
     * @param size font size
     */
    private void customizeLabel(Label inputLabel, int size){
        inputLabel.setAlignment(Pos.CENTER);
        inputLabel.setFont(new Font("Times New Roman", size));
        if (!lightMode){
            inputLabel.setStyle("-fx-text-fill: white;");
        }else {
            inputLabel.setStyle("-fx-text-fill: black;");
        }
        inputLabel.setTextAlignment(TextAlignment.CENTER);
        inputLabel.setWrapText(true);
    }
}