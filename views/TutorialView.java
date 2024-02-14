package views;

import GameModel.AdventureGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.jfr.Event;

/**
 * Class TutorialView.
 *__________________________
 * This is the Class that will visualize the tutorial demo video.
 *
 */
public class TutorialView {
    /**
     * TutorialView Constructor
     * __________________________
     * Initializes attributes
     * Creates popup and plays video
     */
    public TutorialView(){

        final Stage dialog = new Stage(); //dialogue box

        MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("/src/tutorial.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);
        mediaView.setPreserveRatio(true);
        mediaView.setFitWidth(1280);


        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,1280,720);
        dialog.setTitle("Playing Video");
        dialog.setScene(scene);
        dialog.show();
        player.play();

        dialog.setOnHidden(event -> player.stop());
    }
}
