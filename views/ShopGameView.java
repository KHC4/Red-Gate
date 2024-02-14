package views;

import GameModel.Items.Inventory;
import GameModel.Items.Item;
import GameModel.Items.ItemFactory;
import GameModel.Player;
import GameModel.Scores.ScoreBoard;
import GameModel.Scores.ScoreList;
import GameModel.Scores.SingletonScore;
import javafx.geometry.*;
import javafx.scene.AccessibleRole;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class ShopGameView.
 *__________________________
 * This is the Class that will visualize the shop.
 *
 */
public class ShopGameView {
    /** The player reference */
    Player player;
    /** The inventory reference */
    Inventory inventory;
    /** Selected item reference*/
    Item selectedItem = null;
    /** selected item cost reference */
    Integer cost = null;
    /** Label holding currency of the game */
    String currency = "Points";
    /** Light Mode toggle switch */
    private final boolean lightMode;


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

    /**
     * buyItem
     * __________________________
     * Purchases an item from the shop and places it in the player's inventory
     * @param boughtItem the item to buy
     * @param cost the cost of the item
     * @param inventory the inventory of the player
     * @param player the player reference
     */
    public boolean buyItem(Item boughtItem, int cost, Inventory inventory, Player player)
    {
        if (player.getPoints()-cost>0)
        {
            if (inventory.addItem(boughtItem)){
                player.setPoints(player.getPoints()-cost);
                return true;
            }
        }
        return false;
    }

    /**
     * updateItemInfo
     * __________________________
     * Called when the player clicks on an item
     * Updates the description label to the item's description
     * @param item the item the player clicked on
     * @param description the label to update
     */
    private void updateItemInfo(Item item, Label description){
        description.setText(item.getDescription() + "\nCost: " + this.cost);
    }

    /**
     * updateBoughtInfo
     * __________________________
     * Called when the player attempts to buy something
     * Updates the label and picture depending on if the item is bought
     * @param description the label holding the item description
     * @param player the player reference
     * @param bool the boolean representing if the item is bought
     * @param boughtitem the item to buy
     * @param money the label holding the player's current points
     * @param shopView the image to display
     */
    private void updateBoughtInfo(Boolean bool,Item boughtitem, Player player, Label description, Label money, ImageView shopView){

        String shopImage = "src/images/shopkeeperdef.png";

        if (bool){description.setText("You have succesfully bought " + boughtitem.getName() + "!");money.setText(this.player.getPoints() + " " + this.currency);}
        else {description.setText("You are too poor or do not have enough space for this item!");shopImage = "src/images/shopkeepersad.png";}
        Image shopImageFile = new Image(shopImage);
        shopView.setImage(shopImageFile);

    }

    /**
     * ShopGameView
     * __________________________
     * Constructor class
     * Creates and sets the shop view on a new popup
     * @param player the player reference
     * @param lightMode the lightmode toggled boolean
     */
    public ShopGameView(Player player, boolean lightMode) throws FileNotFoundException {
        this.player = player;this.inventory = this.player.inventory;
        this.lightMode = lightMode;

//        player.setPoints(99999);

        Stage stage = new Stage();
        GridPane gameGridPane = new GridPane();

        Label nameLabel = new Label("Welcome to the shop! Click what you want to buy! Please don't steal. :c");
        customizeLabel(nameLabel, 16);



        Label infoLabel = new Label("You haven't bought a item yet. Buy something, please, I am a very poor shopkeeper.");
        customizeLabel(infoLabel, 16);

        Label moneyLabel = new Label(this.player.getPoints() +" " + this.currency);
        customizeLabel(moneyLabel, 16);
        moneyLabel.setTextAlignment(TextAlignment.LEFT);



        gameGridPane = new GridPane();
        gameGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            gameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else{
            gameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }


        Random rand = new Random();
        Integer item1Cost = rand.nextInt(75);
        Integer item2Cost = rand.nextInt(75);
        Integer item3Cost = rand.nextInt(75);

        Item item1 = new ItemFactory().createItem();
        Item item2 = new ItemFactory().createItem();
        Item item3 = new ItemFactory().createItem();

        Label itemDescription = new Label();
        customizeLabel(itemDescription, 16);

        Button item1Button = new Button(item1.getName());
        item1Button.setId(item1.getName().replaceAll("\\s+",""));
        customizeButton(item1Button, 150, 75);
        item1Button.setAlignment(Pos.CENTER);

        String item1Image = item1.getImagePath();
        Image item1ImageFile = new Image(item1Image);
        ImageView item1ImageView = new ImageView(item1ImageFile);
        item1ImageView.setPreserveRatio(true);
        item1ImageView.setFitHeight(50);
        item1Button.setGraphic(item1ImageView);

        item1Button.setContentDisplay(ContentDisplay.TOP);
        item1Button.setText(item1.getName());
        item1Button.setWrapText(true);

        makeButtonAccessible(item1Button, item1.getName(), "Buy "+ item1.getName(), "Click to view the buying details of " + item1.getName());

        item1Button.setOnAction(e -> {
            this.cost = item1Cost;
            updateItemInfo(item1, itemDescription);
            this.selectedItem = item1;
        });

        Button item2Button = new Button(item2.getName());
        item2Button.setId(item2.getName().replaceAll("\\s+",""));
        customizeButton(item2Button, 150, 75);
        item2Button.setAlignment(Pos.CENTER);

        String item2Image = item2.getImagePath();
        Image item2ImageFile = new Image(item2Image);
        ImageView item2ImageView = new ImageView(item2ImageFile);
        item2ImageView.setPreserveRatio(true);
        item2ImageView.setFitHeight(50);
        item2Button.setGraphic(item2ImageView);

        item2Button.setContentDisplay(ContentDisplay.TOP);
        item2Button.setText(item2.getName());
        item2Button.setWrapText(true);

        makeButtonAccessible(item2Button, item2.getName(), "Buy "+ item2.getName(), "Click to view the buying details of " + item2.getName());

        item2Button.setOnAction(e -> {
            this.cost = item2Cost;
            updateItemInfo(item2, itemDescription);
            this.selectedItem = item2;
        });

        Button item3Button = new Button(item3.getName());
        item3Button.setId(item3.getName().replaceAll("\\s+",""));
        customizeButton(item3Button, 150, 75);
        item3Button.setAlignment(Pos.CENTER);

        String item3Image = item3.getImagePath();
        Image item3ImageFile = new Image(item3Image);
        ImageView item3ImageView = new ImageView(item3ImageFile);
        item3ImageView.setPreserveRatio(true);
        item3ImageView.setFitHeight(50);
        item3Button.setGraphic(item3ImageView);

        item3Button.setContentDisplay(ContentDisplay.TOP);
        item3Button.setText(item3.getName());
        item3Button.setWrapText(true);

        makeButtonAccessible(item3Button, item3.getName(), "Buy "+ item3.getName(), "Click to view the buying details of " + item3.getName());

        item3Button.setOnAction(e -> {
            this.cost = item3Cost;
            updateItemInfo(item3, itemDescription);
            this.selectedItem = item3;
        });



        String shopImage = "src/images/shopkeeperdef.png";
        Image shopImageFile = new Image(shopImage);
        ImageView shopImageView = new ImageView(shopImageFile);
        shopImageView.setPreserveRatio(true);
        shopImageView.setFitHeight(300);

        javafx.scene.control.Button cont = new Button("Purchase");
        cont.setId("Purchase");
        customizeButton(cont, 200, 50);
        cont.setAlignment(Pos.CENTER);

        makeButtonAccessible(cont, "Purchase", "Purchase Item", "Click this button to purchase selected item");

        cont.setOnAction(e -> {
            boolean isBought = buyItem(this.selectedItem,this.cost, this.inventory, this.player);
            updateBoughtInfo(isBought, this.selectedItem,this.player,infoLabel,moneyLabel,shopImageView);
        });



        RowConstraints row1 = new RowConstraints(75);
        RowConstraints row2 = new RowConstraints(50);
        RowConstraints row3 = new RowConstraints(300);
        RowConstraints row4 = new RowConstraints(100);
        gameGridPane.getRowConstraints().addAll(row1, row1, row3, row4, row2, row4);

        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(200);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.ALWAYS );
        column1.setHgrow( Priority.ALWAYS );
        gameGridPane.getColumnConstraints().addAll(column1, column2, column2, column2, column3);

        GridPane.setHalignment(item1Button, HPos.CENTER);
        GridPane.setValignment(item1Button, VPos.CENTER);

        GridPane.setValignment(moneyLabel, VPos.CENTER);
        GridPane.setHalignment(moneyLabel, HPos.CENTER);

        GridPane.setValignment(infoLabel, VPos.CENTER);
        GridPane.setHalignment(infoLabel, HPos.CENTER);

        GridPane.setValignment(nameLabel, VPos.CENTER);
        GridPane.setHalignment(nameLabel, HPos.CENTER);

        GridPane.setValignment(cont, VPos.CENTER);
        GridPane.setHalignment(cont, HPos.CENTER);

        GridPane.setValignment(item2Button, VPos.CENTER);
        GridPane.setHalignment(item2Button, HPos.CENTER);

        GridPane.setValignment(item3Button, VPos.CENTER);
        GridPane.setHalignment(item3Button, HPos.CENTER);

        GridPane.setValignment(itemDescription, VPos.CENTER);
        GridPane.setHalignment(itemDescription, HPos.CENTER);



        gameGridPane.setAlignment(Pos.CENTER);

        GridPane.setValignment(shopImageView, VPos.CENTER);
        GridPane.setHalignment(shopImageView, HPos.CENTER);


        gameGridPane.add(moneyLabel, 0,0, 1,1);
        gameGridPane.add(nameLabel, 2, 0,1,1);
        gameGridPane.add(infoLabel, 1, 1, 3, 1);
        gameGridPane.add(shopImageView, 1, 2, 3, 1);
        gameGridPane.add(item1Button, 1, 3, 1, 1);
        gameGridPane.add(item2Button, 2, 3, 1, 1);
        gameGridPane.add(item3Button, 3, 3, 1, 1);
        gameGridPane.add(itemDescription, 1, 4, 3, 1);
        gameGridPane.add(cont, 2, 5, 1, 1);
        gameGridPane.setAlignment(Pos.CENTER);

        Scene gameScene = new Scene(gameGridPane, 900, 720);
        stage.setScene(gameScene);
        stage.show();
    }

    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
    }
}