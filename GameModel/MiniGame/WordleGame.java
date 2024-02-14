//package GameModel.MiniGame;
//
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.awt.event.ActionEvent;
//
//public class TestGame implements MiniGame{
//    boolean returnVal = false;
//    public boolean playGame(){
//        this.returnVal = false;
//        Group root = new Group();
//        boolean returnBool = false;
//        Scene scene = new Scene(root,1280,720);
//        final Stage gui = new Stage(); //dialogue box
//        gui.setWidth(400.0);
//        gui.setHeight(400.0);
//
//
//
//
//
//
//        Button item1Button = new Button("click me to return true");
//        item1Button.setAlignment(Pos.CENTER);
//        item1Button.setOnAction(e -> {
//            this.returnVal = true;
//            gui.close();
//        });
//        Button item2Button = new Button("click me to return false");
//        item2Button.setAlignment(Pos.CENTER);
//        item2Button.setOnAction(e -> {
//            this.returnVal = false;
//            gui.close();
//        });
//        item2Button.setLayoutX(40);
//        item2Button.setLayoutY(40);
//
//
//
//
//        root.getChildren().add(item1Button);
//        root.getChildren().add(item2Button);
//        gui.setScene(scene);
//        gui.showAndWait();
//        return returnVal;
//
//    }
//}
