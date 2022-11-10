import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import music.Notes;
import piano.PianoKey;

import piano.PianoScene;
import piano.KeyType;
import piano.KeyboardPane;

import javax.sound.midi.*;

public class App extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a scene and place a button in the scene
    Scene scene = new Scene(new Button("OK"), 200, 250);
    primaryStage.setTitle("MyJavaFX"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    // primaryStage.show(); // Display the stage

    Stage stagePiano = new Stage(); // Create a new stage
    stagePiano.setTitle("Piano"); // Set the stage title
    // Set a scene with a button in the stage
    // PianoKey key = new PianoKey(KeyType.STANDARD, 0.0, 0.0);

    try {

      Receiver receiver = MidiSystem.getReceiver();
      Pane pane = new Pane();
      pane.setPrefSize(1000, 1000);
      /* 
      KeyboardPane piano = new KeyboardPane(Notes.A, 3, receiver);

      Scene scene1 = new Scene(piano, 1000, 500);

      
      scene1.setOnKeyPressed(e -> {

        for (Node key : piano.getChildren()) {
          PianoKey key1 = (PianoKey) key;
          if (key1.getKeyboardKey() == e.getCode()) {

            if (key1.getIsPressed() != true) {
              key1.setIsPressed(true);
              key1.startNote(receiver);

              switch (key1.getKeyType()) {
                case B_E_KEY:
                case C_F_KEY:
                case STANDARD:
                  key1.setFill(Color.WHITE.darker());
                  break;
                case SHARP_FLAT:
                  key1.setFill(Color.DARKGRAY.darker().darker());
                  break;
              }
            }
          }

        }

      });
      scene1.setOnKeyReleased(e -> {

        for (Node key : piano.getChildren()) {
          PianoKey key1 = (PianoKey) key;
          if (key1.getKeyboardKey() == e.getCode()) {
            if (key1.getIsPressed() == true) {
              key1.stopNote(receiver);

              switch (key1.getKeyType()) {
                case B_E_KEY:
                case C_F_KEY:
                case STANDARD:
                  key1.setFill(Color.WHITE);
                  break;
                case SHARP_FLAT:
                  key1.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                  break;
              }
              key1.setIsPressed(false);
            }
          }
        }

      });
*/
      stagePiano.setScene(new PianoScene(1000, 500, receiver));
      stagePiano.show(); // Display the stage

      //stagePiano.getIcons().add(new Image()); //FIX ME: ADD IMAGE FILE PATH AND COMPLETE

    } catch (Exception ex) {

    }

  }
  

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}