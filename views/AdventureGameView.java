package views;

import GameModel.AdventureGame;
import GameModel.Items.Item;
import GameModel.Items.ItemFactory;
import GameModel.MiniGame.*;
import GameModel.Room.HealingRoom;
import GameModel.Room.RegularRoom;
import GameModel.Scores.ScoreBoard;
import GameModel.Scores.SingletonScore;
import GameModel.TTSClass;
import Jampack.H;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.AccessibleRole;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class AdventureGameView.
 *__________________________
 * This is the Class that will visualize the model.
 *
 */
public class AdventureGameView {
    /** The model of the game */
    AdventureGame model;
    /** The stage on which all is rendered */
    Stage stage;
    /** Main menu scene */
    Scene mainMenu;
    /** Pregame scene */
    Scene setupScene;
    /** Main game scene */
    Scene gameScene;
    /** Game End Scene */
    Scene endGame;
    /** To hold images and buttons for main menu */
    GridPane mainmMenuGridPane = new GridPane();
    /** GridPane for the perk and name select scene */
    GridPane preGameGridPane;
    /** GridPane for the base game */
    GridPane gameGridPane;
    /** Hbox to hold room items */
    HBox objectsInRoom = new HBox();
    /** Label to hold events currently happening */
    Label events = new Label();
    /** MaryTTS instance */
    TTSClass tts = new TTSClass();
    /** TTS toggle switch */
    public boolean ttstoggle = false;
    /** Light Mode toggle switch */
    public boolean lightMode = false;

    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes and the UI
     */
    public AdventureGameView(AdventureGame model, Stage stage) {
        this.model = model;
        this.stage = stage;
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Red Gate");
        setMainMenuScene();
        this.stage.setResizable(true);
        this.stage.show();
    }

    /**
     * setMainMenuScene
     * __________________________
     * Creates the main menu, and changes the current scene of the game to the Main Menu
     *
     */
    public void setMainMenuScene(){
        if (this.model == null) {this.model = new AdventureGame();}
        else if (model.gameEnded) {this.model = new AdventureGame();}

        events = new Label();
        customizeLabel(events, 22);
        events.setTextAlignment(TextAlignment.LEFT);

        mainmMenuGridPane = new GridPane();

        mainmMenuGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            mainmMenuGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        } else {
            mainmMenuGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        // Row constraints
        RowConstraints row1 = new RowConstraints(400);
        RowConstraints row2 = new RowConstraints(210);
        RowConstraints row3 = new RowConstraints(100);
        row1.setVgrow(Priority.SOMETIMES);

        mainmMenuGridPane.getRowConstraints().addAll(row1, row2, row3);

        ColumnConstraints col1 = new ColumnConstraints(220);
        ColumnConstraints col2 = new ColumnConstraints(600);
        ColumnConstraints col3 = new ColumnConstraints(120);

        mainmMenuGridPane.getColumnConstraints().addAll(col3, col1, col2, col1, col3);

        // Buttons
        Button startButton = new Button("Start");
        startButton.setId("Start");
        customizeButton(startButton, 200, 50);
        startButton.setOnAction(e -> {
            setupGame();
        });
        makeButtonAccessible(startButton, "Start", "Start Game", "Click on the Start Button to begin the game");

        Button tutorialButton = new Button("Tutorial");
        tutorialButton.setId("tutorial");
        customizeButton(tutorialButton, 200, 50);
        tutorialButton.setOnAction(e -> {
            setTutorialScene();
        });
        makeButtonAccessible(tutorialButton, "Tutorial", "Open Tutorial", "Click on the tutorial button to see the tutorial");

        Button leaderboardButton = new Button("Leaderboards");
        leaderboardButton.setId("Leaderboards");
        customizeButton(leaderboardButton, 200, 50);
        leaderboardButton.setOnAction(e -> {
            try {
                setLeaderboardScene();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        makeButtonAccessible(leaderboardButton, "LeaderBoard", "Open Leaderboard", "Click on the Leaderboard button to view the leaderboard");


        Button mainExitButton = new Button("Exit");
        mainExitButton.setId("Exit");
        customizeButton(mainExitButton, 200, 50);
        mainExitButton.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        makeButtonAccessible(mainExitButton, "Exit", "Click to exit", "Click on the exit button to exit the game");

        VBox buttonVbox = new VBox();
        buttonVbox.setSpacing(25);
        buttonVbox.getChildren().add(startButton);
        buttonVbox.getChildren().add(tutorialButton);
        buttonVbox.getChildren().add(leaderboardButton);
        buttonVbox.getChildren().add(mainExitButton);
        buttonVbox.setAlignment(Pos.CENTER);

        mainmMenuGridPane.add(buttonVbox, 2, 1, 1, 2);


        String titleImage = "src/Red_Gate.png";
        Image titleImageFile = new Image(titleImage);
        ImageView titleImageView = new ImageView(titleImageFile);
        titleImageView.setPreserveRatio(true);
        titleImageView.setFitWidth(600);
        titleImageView.setFitHeight(150);

        mainmMenuGridPane.add(titleImageView, 2, 0, 1, 1);
        mainmMenuGridPane.setAlignment(Pos.CENTER);

        String ttsImage = "src/images/tts.png";
        Image ttsImageFile = new Image(ttsImage);
        ImageView ttsImageView = new ImageView(ttsImageFile);
        ttsImageView.setPreserveRatio(true);
        ttsImageView.setFitWidth(30);

        Button ttsToggleButton = new Button();
        customizeButton(ttsToggleButton, 50, 50);
        makeButtonAccessible(ttsToggleButton, "TTS Toggle", "Turn on or turn off TTS", "Click this button to toggle text to speech throughout the game");
        ttsToggleButton.setTextAlignment(TextAlignment.CENTER);
        ttsToggleButton.setGraphic(ttsImageView);
        ttsToggleButton.setOnAction(e -> {
            if (ttstoggle) {
                ttstoggle = false;
                tts.say("Text to speech has been disabled");
            }else {
                ttstoggle = true;
                checktts("Text to speech has been enabled");
            }
        });

        String themeImage = "src/images/theme.png";
        Image themeImageFile = new Image(themeImage);
        ImageView themeImageView = new ImageView(themeImageFile);
        themeImageView.setPreserveRatio(true);
        themeImageView.setFitWidth(30);

        Button lightModeToggleButton = new Button();
        customizeButton(lightModeToggleButton, 50, 50);
        makeButtonAccessible(lightModeToggleButton, "Light Mode Toggle", "Switch between Light and Dark Mode", "Click this button to toggle Light Mode");
        lightModeToggleButton.setTextAlignment(TextAlignment.CENTER);
        lightModeToggleButton.setGraphic(themeImageView);
        lightModeToggleButton.setOnAction(e -> {
            if (lightMode) {
                lightMode = false;
                checktts("Light mode has been disabled");
                setMainMenuScene();
            }else {
                lightMode = true;
                checktts("Light mode has been enabled");
                setMainMenuScene();
            }
        });

        HBox toggleButtons = new HBox();
        toggleButtons.setSpacing(10);
        toggleButtons.getChildren().add(lightModeToggleButton);
        toggleButtons.getChildren().add(ttsToggleButton);
        mainmMenuGridPane.add(toggleButtons, 4, 2, 1, 1);
        GridPane.setValignment(toggleButtons, VPos.BOTTOM);


        GridPane.setHalignment(titleImageView, HPos.CENTER);
        GridPane.setHalignment(buttonVbox, HPos.CENTER);

        mainMenu = new Scene(mainmMenuGridPane, 1280, 720);
        this.stage.setScene(mainMenu);
    }

    /**
     * setLeaderboardScene
     * __________________________
     * Initializes the leaderboard gui
     *
     */
    public void setLeaderboardScene() throws FileNotFoundException {
        LeaderboardView leaderboardView = new LeaderboardView(model.player.getName(), lightMode);
    }

    /**
     * setTutorialScene
     * __________________________
     * Initializes the tutorial gui
     *
     */
    public void setTutorialScene(){
        TutorialView tutorialView = new TutorialView();
    }

    /**
     * setInventoryScene
     * __________________________
     * Initializes the inventory gui
     *
     */
    public void setInventoryScene(){
        InventoryView inventoryView = new InventoryView(this);
    }

    /**
     * setShopScene
     * __________________________
     * Initializes the Shop gui
     *
     */
    public void setShopScene() throws FileNotFoundException {
        ShopGameView shopGameView = new ShopGameView(model.player, lightMode);
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
     * setupGame
     * __________________________
     * Creates the pregame menu and switches the current scene to the pregame menu
     *
     */
    public void setupGame(){
        Label nameLabel = new Label("Enter Your Desired Name");
        customizeLabel(nameLabel, 20);

        TextField inputTextField = new TextField();
        inputTextField.setAlignment(Pos.CENTER);
        if (lightMode){
            inputTextField.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#A0A0A0"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }
        preGameGridPane = new GridPane();
        preGameGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            preGameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        } else{
            preGameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        Label perkLabel = new Label("Select Your Desired Perk\nIf you don't want one, just hit continue");
        customizeLabel(perkLabel, 20);

        Label perkDescription = new Label();
        customizeLabel(perkDescription, 20);
        perkDescription.setId("");

        Button perk1 = new Button();
        perk1.setId("Greater Max Health");
        customizeButton(perk1, 100, 100);
        perk1.setAlignment(Pos.CENTER);
        makeButtonAccessible(perk1, "Greater Max Health", "Start with more health", "Begin the game with 20 additonal Health points");

        Image perk1ImageFile = new Image("src/images/Max Health.png");
        ImageView perk1ImageView = new ImageView(perk1ImageFile);
        perk1ImageView.setPreserveRatio(true);
        perk1ImageView.setFitHeight(100);
        perk1.setGraphic(perk1ImageView);

        perk1.setOnAction(e -> {
            updatePerkDescription("Begin the game with 20 more maximum health points. Great for staying alive longer.", perkDescription);
            perkDescription.setId("Perk 1");
        });

        Button perk2 = new Button();
        perk2.setId("Random Item");
        customizeButton(perk2, 100, 100);
        perk2.setAlignment(Pos.CENTER);
        makeButtonAccessible(perk2, "Random Item", "start with a random item", "Begin the game with a random item added to your inventory");

        Image perk2ImageFile = new Image("src/images/Question.png");
        ImageView perk2ImageView = new ImageView(perk2ImageFile);
        perk2ImageView.setPreserveRatio(true);
        perk2ImageView.setFitHeight(100);
        perk2.setGraphic(perk2ImageView);

        perk2.setOnAction(e -> {
            updatePerkDescription("Begin the game with a random item added to your inventory. Could be a Flaming Sword or a Tin Can and everything in between.\nAre you willing to test your luck", perkDescription);
            perkDescription.setId("Perk 2");
        });

        Button perk3 = new Button();
        perk3.setId("Starting Points");
        customizeButton(perk3, 100, 100);
        perk3.setAlignment(Pos.CENTER);
        makeButtonAccessible(perk3, "Starter Points", "Start with a certain number of points", "Begin the game with 75 additional points");

        Image perk3ImageFile = new Image("src/images/Points.png");
        ImageView perk3ImageView = new ImageView(perk3ImageFile);
        perk3ImageView.setPreserveRatio(true);
        perk3ImageView.setFitHeight(100);
        perk3.setGraphic(perk3ImageView);

        perk3.setOnAction(e -> {
            updatePerkDescription("Begin the game with 75 points, can be used to buy something from the shop, or just as a headstart to beat your highscore.", perkDescription);
            perkDescription.setId("Perk 3");
        });

        Button cont = new Button("Continue");
        cont.setId("Continue");
        customizeButton(cont, 200, 50);
        cont.setAlignment(Pos.CENTER);
        cont.setOnAction(e -> {
            this.model.player.setName(inputTextField.getText());
            switch (perkDescription.getId().trim()) {
                case "Perk 1" -> { this.model.player.setMaxHealth(120); this.model.player.addHealth(20); }
                case "Perk 2" -> {ItemFactory itemFactory = new ItemFactory(); model.player.addToInventory(itemFactory.createItem());}
                case "Perk 3" -> this.model.player.addPoints(75);
                default -> this.model.player.setMaxHealth(100);
            }
            updateScene();
        });

        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(150);
        preGameGridPane.getRowConstraints().addAll(row1, row1, row1, row2, row2, row1);

        ColumnConstraints column1 = new ColumnConstraints(190);
        ColumnConstraints column2 = new ColumnConstraints(300);
        ColumnConstraints column3 = new ColumnConstraints(190);
        column3.setHgrow( Priority.ALWAYS );
        column1.setHgrow( Priority.ALWAYS );
        preGameGridPane.getColumnConstraints().addAll(column1, column2, column2, column2, column3);


        preGameGridPane.add(nameLabel, 1, 0,3,1);
        preGameGridPane.add(inputTextField, 1, 1, 3, 1);
        preGameGridPane.add(perkLabel, 1, 2, 3, 1);
        preGameGridPane.add(perk1, 1, 3, 1, 1);
        preGameGridPane.add(perk2, 2, 3, 1, 1);
        preGameGridPane.add(perk3, 3, 3, 1, 1);
        preGameGridPane.add(perkDescription, 1, 4, 3, 1);
        preGameGridPane.add(cont, 2, 5, 1, 1);
        preGameGridPane.setAlignment(Pos.CENTER);

        GridPane.setHalignment(nameLabel, HPos.CENTER);
        GridPane.setHalignment(inputTextField, HPos.CENTER);
        GridPane.setHalignment(perkLabel, HPos.CENTER);
        GridPane.setHalignment(perk1, HPos.CENTER);
        GridPane.setHalignment(perk2, HPos.CENTER);
        GridPane.setHalignment(perk3, HPos.CENTER);
        GridPane.setHalignment(perkDescription, HPos.CENTER);
        GridPane.setHalignment(cont, HPos.CENTER);


        setupScene = new Scene(preGameGridPane, 1280, 720);
        stage.setScene(setupScene);
        checktts("Enter your desired name in the textbox, then select your desired perk by selecting one of the buttons");
    }

    /**
     * updatePerkDescription
     * __________________________
     * Updates the perkDescription label based on given text
     * @param perk the text we are updating the label to
     * @param description the label itself
     */
    private void updatePerkDescription(String perk, Label description){
        description.setText(perk);
    }

    /**
     * updateScene
     * __________________________
     * Update the scene based on current events happening in the game
     *
     */
    public void updateScene(){

        if (model.getCurrentRoom().getEnemiesInRoom().isEmpty()){
            setNoEnemyView();
        }else{
            setEnemyView();
        }

        gameScene = new Scene(gameGridPane, 1280, 720);
        stage.setScene(gameScene);
    }

    /**
     * setEnemyView
     * __________________________
     * Sets the scene to enemy view if there are enemies in the room
     *
     */
    public void setEnemyView(){
        GridPane enemiesGridPane = new GridPane();

        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(400);
        enemiesGridPane.getRowConstraints().addAll(row1, row2, row1, row1);

        ColumnConstraints col1 = new ColumnConstraints(320);
        enemiesGridPane.getColumnConstraints().addAll(col1, col1, col1, col1);

        Label playerStats = new Label();
        customizeLabel(playerStats, 20);
        playerStats.setTextAlignment(TextAlignment.LEFT);
        playerStats.setWrapText(false);
        playerStats.setText(model.player.getStats());

        Label roomNum = new Label();
        customizeLabel(roomNum, 20);
        roomNum.setText("Room: " + model.getCurrentRoom().getRoomNum());

        Label roomtype = new Label();
        customizeLabel(roomtype, 20);
        if (model.getCurrentRoom() instanceof HealingRoom){
            roomtype.setText("Healing Room");
        }else{
            roomtype.setText("Regular Room");
        }

        Label enemiesRemaining = new Label();
        customizeLabel(enemiesRemaining, 20);
        Integer enemyNum = model.getCurrentRoom().getEnemiesInRoom().size();
        enemiesRemaining.setText("Enemies Remaining: "+ enemyNum);


        Label enemyStats = new Label();
        customizeLabel(enemyStats, 20);
        enemyStats.setTextAlignment(TextAlignment.LEFT);
        String enemystat = "Enemy Health: " + model.getCurrentRoom().getEnemiesInRoom().get(0).getHealth() + "\n";
        enemystat += "Enemy Damage: " + model.getCurrentRoom().getEnemiesInRoom().get(0).getDamage();
        enemyStats.setText(enemystat);

        Button attackButton = new Button("Attack");
        attackButton.setId("Attack");
        customizeButton(attackButton, 200, 70);
        attackButton.setAlignment(Pos.CENTER);
        attackButton.setOnAction(e -> {
            boolean monsterAlive = model.playerAttack(ttstoggle);
            updateScene();
            if (monsterAlive){
                ArrayList<GameGUI> minigames = new ArrayList<GameGUI>();
                minigames.add(new MemoryGameGUI());
                minigames.add(new HangMan());
                Random rand = new Random();
                int index = rand.nextInt(2);
                GameGUI tempGame = minigames.get(index);
                boolean wonGame = tempGame.playGame();
                if (!wonGame){ model.player.takeDamage(model.getCurrentRoom().getEnemiesInRoom().get(0).getDamage()); checktts("You failed to avoid the enemy attack");model.stats.addStat("Damage Taken", model.getCurrentRoom().getEnemiesInRoom().get(0).getDamage());}
                else{checktts("You successfully avoided the enemy attack");}

                updateScene();
                if (!this.model.player.isAlive()){this.model.endGame(ttstoggle) ;setEndGameView(); }
            }
        });
        makeButtonAccessible(attackButton, "Attack", "Attack an Enemy", "Attack the current enemy, dealing damage according to current weapon and effects");

        Button fleeButton = new Button("Flee");
        fleeButton.setId("Flee");
        customizeButton(fleeButton, 200, 70);
        fleeButton.setAlignment(Pos.CENTER);
        fleeButton.setOnAction(e -> {
            if (model.player.flee()){
                model.fleeFromEnemy();
                checktts("You successfully fled from the enemy");
            }else{
                events.setText("You have failed to flee from the enemy.");
                checktts("You have failed to flee from the enemy");
                ArrayList<GameGUI> minigames = new ArrayList<GameGUI>();
                minigames.add(new MemoryGameGUI());
                minigames.add(new HangMan());
                Random rand = new Random();
                int index = rand.nextInt(2);
                GameGUI tempGame = minigames.get(index);
                boolean wonGame = tempGame.playGame();
                if (!wonGame){ model.player.takeDamage(model.getCurrentRoom().getEnemiesInRoom().get(0).getDamage()); checktts("You failed to avoid the enemy attack");model.stats.addStat("Damage Taken", model.getCurrentRoom().getEnemiesInRoom().get(0).getDamage());}
                else{checktts("You successfully avoided the enemy attack");}

                updateScene();
                if (!this.model.player.isAlive()){this.model.endGame(ttstoggle) ;setEndGameView(); }
            }
            updateScene();
        });
        makeButtonAccessible(fleeButton, "Flee", "Flee from the enemy", "Click the button to attempt to flee from the enemy");

        Button inventoryButton = new Button("Inventory");
        inventoryButton.setId("Inventory");
        customizeButton(inventoryButton, 200, 70);
        inventoryButton.setAlignment(Pos.CENTER);
        inventoryButton.setOnAction(e -> {
            setInventoryScene();
        });
        makeButtonAccessible(inventoryButton, "Inventory", "Open Inventory", "Click this button to open current inventory");

        String enemyName = model.getCurrentRoom().getEnemiesInRoom().get(0).getName();
        String enemyImage = "src/images/"+ enemyName + ".png";
        Image enemyImageFile = new Image(enemyImage);
        ImageView enemyImageView = new ImageView(enemyImageFile);
        enemyImageView.setPreserveRatio(true);
        enemyImageView.setFitHeight(400);

        enemiesGridPane.add(roomNum, 0, 0, 1, 1);
        enemiesGridPane.add(roomtype, 1, 0, 2, 1);
        enemiesGridPane.add(enemiesRemaining, 3, 0, 1, 1);
        enemiesGridPane.add(playerStats, 0, 1, 3, 1);
        enemiesGridPane.add(enemyImageView, 1, 1, 3, 1);
        enemiesGridPane.add(attackButton, 1, 3, 1, 1);
        enemiesGridPane.add(fleeButton, 2, 3, 1, 1);
        enemiesGridPane.add(inventoryButton, 3, 3, 1, 1);

        enemiesGridPane.add(events, 1, 2, 2, 1);
        enemiesGridPane.add(enemyStats, 3, 2, 1, 1);

        GridPane.setHalignment(enemyImageView, HPos.CENTER);
        GridPane.setHalignment(roomtype, HPos.CENTER);

        enemiesGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            enemiesGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        } else{
            enemiesGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        gameGridPane = enemiesGridPane;
    }

    /**
     * setNoEnemyView
     * __________________________
     * Updates the scene once there are no enemies in the room
     *
     */
    public void setNoEnemyView(){
        GridPane noEnemiesGridPane = new GridPane();


        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(400);
        noEnemiesGridPane.getRowConstraints().addAll(row1, row2, row1, row1);

        ColumnConstraints col1 = new ColumnConstraints(320);
        noEnemiesGridPane.getColumnConstraints().addAll(col1, col1, col1, col1);

        Label playerStats = new Label();
        customizeLabel(playerStats, 20);
        playerStats.setWrapText(false);
        playerStats.setTextAlignment(TextAlignment.LEFT);
        playerStats.setText(model.player.getStats());

        Label roomNum = new Label();
        customizeLabel(roomNum, 20);
        roomNum.setTextAlignment(TextAlignment.LEFT);
        roomNum.setText("Room: " + model.getCurrentRoom().getRoomNum());

        Label roomtype = new Label();
        customizeLabel(roomtype, 20);
        if (model.getCurrentRoom() instanceof HealingRoom){
            roomtype.setText("Healing Room");
        }else{
            roomtype.setText("Regular Room");
        }

        Label enemiesRemaining = new Label();
        customizeLabel(enemiesRemaining, 20);
        enemiesRemaining.setTextAlignment(TextAlignment.LEFT);
        Integer enemyNum = model.getCurrentRoom().getEnemiesInRoom().size();
        enemiesRemaining.setText("Enemies Remaining: "+ enemyNum);

        Button inventoryButton = new Button("Inventory");
        inventoryButton.setId("Inventory");
        customizeButton(inventoryButton, 200, 70);
        inventoryButton.setAlignment(Pos.CENTER);
        inventoryButton.setOnAction(e -> {
            setInventoryScene();
        });
        makeButtonAccessible(inventoryButton, "Inventory", "Open Inventory", "Click this button to open current inventory");

        Button shopButton = new Button("Shop");
        shopButton.setId("Shop");
        customizeButton(shopButton, 200, 70);
        shopButton.setAlignment(Pos.CENTER);
        shopButton.setOnAction(e -> {
            try {
                setShopScene();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        makeButtonAccessible(shopButton, "Shop", "Open Shop", "Click this button to open the shop");

        Button continueButton = new Button("Continue");
        continueButton.setId("Continue");
        customizeButton(continueButton, 200, 70);
        continueButton.setAlignment(Pos.CENTER);
        continueButton.setOnAction(e -> {
            this.model.movePlayer(ttstoggle);
            events.setText("");
            updateScene();
        });
        makeButtonAccessible(continueButton, "Continue", "Continue to next room", "Click this button to continue to the next room");

        ScrollPane objectsScrollPane = updateItems();

        Label objectInRoomLabel = new Label();
        objectInRoomLabel.setId("Objects In Room");
        customizeLabel(objectInRoomLabel, 20);

        if (objectsInRoom.getChildren().size() == 0) {
            objectInRoomLabel.setText("This room does not contain any items");
        }else {
            objectInRoomLabel.setText("Current Objects in Room:");
        }
        objectInRoomLabel.setWrapText(true);

        String gateImage = "src/images/Gate.png";
        Image gateImageFile = new Image(gateImage);
        ImageView gateImageView = new ImageView(gateImageFile);
        gateImageView.setPreserveRatio(true);
        gateImageView.setFitHeight(400);

        noEnemiesGridPane.add(roomNum, 0, 0, 1, 1);
        noEnemiesGridPane.add(roomtype, 1, 0, 2, 1);
        noEnemiesGridPane.add(enemiesRemaining, 3, 0, 1, 1);
        noEnemiesGridPane.add(playerStats, 0, 1, 3, 1);
        noEnemiesGridPane.add(gateImageView, 1, 1, 3, 1);
        noEnemiesGridPane.add(shopButton, 1, 3, 1, 1);
        noEnemiesGridPane.add(continueButton, 2, 3, 1, 1);
        noEnemiesGridPane.add(inventoryButton, 3, 3, 1, 1);
        noEnemiesGridPane.add(objectInRoomLabel, 1, 2, 1, 1);
        noEnemiesGridPane.add(objectsScrollPane, 2, 2, 2, 1);

        GridPane.setHalignment(gateImageView, HPos.CENTER);
        GridPane.setHalignment(objectsScrollPane, HPos.CENTER);
        GridPane.setHalignment(roomtype, HPos.CENTER);

        noEnemiesGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            noEnemiesGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else{
            noEnemiesGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        gameGridPane = noEnemiesGridPane;
    }

    /**
     * updateItems
     * __________________________
     *
     * The method populates the objectsInRoom Hbox.
     * The Hbox contain a collection of Buttons each representing a different object
     *
     * Images of each object are in the assets
     * folders of the given adventure game.
     */
    public ScrollPane updateItems() {

        objectsInRoom.getChildren().clear();
        ScrollPane scO = new ScrollPane(objectsInRoom);
        objectsInRoom.setAlignment(Pos.CENTER);
        scO.setPadding(new Insets(10));
        if (!lightMode) {
            scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        }else {
            scO.setStyle("-fx-background: #FFFFFF; -fx-background-color:transparent;");
        }

        for (Item object: model.player.getCurrentRoom().getItems()){

            Button objectButton = new Button(object.getName());
            objectButton.setId(object.getName());
            customizeButton(objectButton, 150, 60);

            String itemImage = object.getImagePath();
            Image itemImageFile = new Image(itemImage);
            ImageView itemImageView = new ImageView(itemImageFile);
            itemImageView.setPreserveRatio(true);
            itemImageView.setFitHeight(50);

            objectButton.setGraphic(itemImageView);
            objectButton.setContentDisplay(ContentDisplay.TOP);
            objectButton.setText(object.getName());
            objectButton.setWrapText(true);

            objectButton.setContentDisplay(ContentDisplay.TOP);
            objectButton.setText(object.getName());
            objectButton.setOnAction(e -> {
                model.player.inventory.addItem(object);
                model.getCurrentRoom().removeItem(object);
                updateItems();
            });
            makeButtonAccessible(objectButton, object.getName(), "Click to pickup " + object.getName(), "Click this button to add " + object.getName() + " to your inventory");
            objectsInRoom.getChildren().add(objectButton);
            objectsInRoom.setSpacing(20);

        }
        return scO;
    }

    /**
     * setEndGameView
     * __________________________
     * Switches the scene to the gameEnd when the player's health reaches 0
     *
     */
    public void setEndGameView(){
        GridPane endGameGridPane = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints(320);
        RowConstraints row1 = new RowConstraints(75);
        RowConstraints row2 = new RowConstraints(100);

        endGameGridPane.getColumnConstraints().addAll(col1, col1, col1, col1);
        endGameGridPane.getRowConstraints().addAll(row2, row2, row1, row1, row1, row1, row2);

        Label diedTitle = new Label();
        diedTitle.setId("YOU DIED");
        customizeLabel(diedTitle, 20);
        diedTitle.setText("YOU DIED");

        Label gameStatsTitle = new Label();
        customizeLabel(gameStatsTitle, 20);
        gameStatsTitle.setText("GAME STATS");

        HashMap<String, Double> statistics = model.stats.getStats();

        Label enemiesDefeatedName = new Label("Enemies Defeated");
        customizeLabel(enemiesDefeatedName, 20);
        enemiesDefeatedName.setText("Enemies Defeated");

        Label enemiesDefeatedstat = new Label();
        customizeLabel(enemiesDefeatedstat, 20);
        enemiesDefeatedstat.setText(statistics.get("Enemies Defeated").toString());

        Label damageDealtName = new Label("Damage Dealt");
        customizeLabel(damageDealtName, 20);
        damageDealtName.setText("Damage Dealt");

        Label damageDealtstat = new Label();
        customizeLabel(damageDealtstat, 20);
        damageDealtstat.setText(statistics.get("Damage Dealt").toString());

        Label damageTakenName = new Label("Damage Taken");
        customizeLabel(damageTakenName, 20);
        damageTakenName.setText("Damage Taken");

        Label damageTakenstat = new Label();
        customizeLabel(damageTakenstat, 20);
        damageTakenstat.setText(statistics.get("Damage Taken").toString());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("Main Menu");
        customizeButton(mainMenuButton, 200, 50);
        mainMenuButton.setOnAction((ActionEvent event) -> {
            setMainMenuScene();
        });
        makeButtonAccessible(mainMenuButton, "Main Menu", "Go to Main Menu", "Click to go back to the Main menu");

        Button mainExitButton = new Button("Exit");
        mainExitButton.setId("Exit");
        customizeButton(mainExitButton, 200, 50);
        mainExitButton.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        makeButtonAccessible(mainExitButton, "Exit", "Exit Game", "Click this button to exit the game");

        endGameGridPane.add(diedTitle, 1, 0, 2, 1);
        endGameGridPane.add(gameStatsTitle, 1, 1, 2, 1);
        endGameGridPane.add(enemiesDefeatedName,1, 2, 1, 1 );
        endGameGridPane.add(enemiesDefeatedstat,2, 2, 1, 1 );
        endGameGridPane.add(damageDealtName,1, 3, 1, 1 );
        endGameGridPane.add(damageDealtstat,2, 3, 1, 1 );
        endGameGridPane.add(damageTakenName,1, 4, 1, 1 );
        endGameGridPane.add(damageTakenstat,2, 4, 1, 1 );
        endGameGridPane.add(mainMenuButton, 1, 6, 1, 1);
        endGameGridPane.add(mainExitButton, 2, 6, 1, 1);

        endGameGridPane.setPadding(new Insets(20));
        if (!lightMode) {
            endGameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }else{
            endGameGridPane.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#FFFFFF"),
                    new CornerRadii(0),
                    new Insets(0)
            )));
        }

        GridPane.setHalignment(diedTitle, HPos.CENTER);
        GridPane.setHalignment(gameStatsTitle, HPos.CENTER);

        GridPane.setHalignment(enemiesDefeatedName, HPos.CENTER);
        GridPane.setHalignment(enemiesDefeatedstat, HPos.CENTER);
        GridPane.setHalignment(damageDealtName, HPos.CENTER);
        GridPane.setHalignment(damageDealtstat, HPos.CENTER);
        GridPane.setHalignment(damageTakenName, HPos.CENTER);
        GridPane.setHalignment(damageTakenstat, HPos.CENTER);

        GridPane.setHalignment(mainMenuButton, HPos.CENTER);
        GridPane.setHalignment(mainExitButton, HPos.CENTER);

        endGame = new Scene(endGameGridPane, 1280, 720);
        stage.setScene(endGame);
    }

    /**
     * checktts
     * __________________________
     * Checks if tts is toggled and speaks the text accordingly
     * @param text the text to speak
     *
     */
    private void checktts(String text){
        if (ttstoggle){
            tts.say(text);
        }
    }
}
