import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import piano.PianoScene;

import javax.sound.midi.*;

import music.MidiGeneratorPane;
import music.MidiPlayerPane;

public class App extends Application {
  private MidiPlayerPane midiPlayerPane;
  private MidiGeneratorPane midiGeneratorPane;
  private Receiver receiver;
  private Stage stagePiano;
  private Stage stageGenMidi;
  private Stage stagePlayMidi;

  private Scene primaryScene;

  private BorderPane startPane;
  private HBox buttonsBox;
  private ImageView logo;

  private Button btnGenMidi;
  private Button btnPlayMidi;
  private Button btnPiano;
  private Button btnExit;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    stagePiano = new Stage();
    stagePiano.setTitle("Orange Juice Piano");
    stagePiano.setOnCloseRequest(e -> {
      stagePiano.close();
      try {
        receiver.close();
      } catch (Exception ex) {
      }
      primaryStage.show();
    });

    stageGenMidi = new Stage();
    stageGenMidi.setTitle("Orange Juice Midi Generator");
    stageGenMidi.setResizable(false);
    stageGenMidi.setOnCloseRequest(e -> {
      try {
        midiGeneratorPane.getSequencer().close();
      } catch (Exception ex) {
      }
      stageGenMidi.close();
      primaryStage.show();
    });

    stagePlayMidi = new Stage();
    stagePlayMidi.setTitle("Orange Juice Midi Player");
    stagePlayMidi.setResizable(false);
    stagePlayMidi.setOnCloseRequest(e -> {
      try {
        midiPlayerPane.getSequencer().close();
      } catch (Exception ex) {
      }
      stagePlayMidi.close();
      primaryStage.show();
    });

    startPane = new BorderPane();

    buttonsBox = new HBox();

    btnPlayMidi = new Button("Play Midi File");
    btnPlayMidi.setMinWidth(150);
    btnPlayMidi.setMinHeight(50);

    btnGenMidi = new Button("Generate Music");
    btnGenMidi.setMinWidth(150);
    btnGenMidi.setMinHeight(50);

    btnPiano = new Button("Play Piano");
    btnPiano.setMinWidth(150);
    btnPiano.setMinHeight(50);

    btnExit = new Button("Exit");
    btnExit.setMinWidth(150);
    btnExit.setMinHeight(50);

    buttonsBox.getChildren().addAll(btnGenMidi, btnPlayMidi, btnPiano, btnExit);
    startPane.setBottom(buttonsBox);

    try {
      logo = new ImageView(new Image(this.getClass().getResourceAsStream("/music/resources/LogoV3.png")));
      startPane.setCenter(logo);
    } catch (Exception ex) {
    }

    btnExit.setOnAction(e -> {
      primaryStage.close();
    });

    btnPlayMidi.setOnAction(e -> {
      try {
        midiPlayerPane.getSequencer().close();
      } catch (Exception ex) {
      }
      midiPlayerPane = new MidiPlayerPane();
      midiPlayerPane.getExitButton().setOnAction(f -> {
        try {
          midiPlayerPane.getSequencer().close();
        } catch (Exception ex) {
        }
        stagePlayMidi.close();
        primaryStage.show();
      });
      stagePlayMidi.setScene(new Scene(midiPlayerPane));

      try {
        stagePlayMidi.getIcons()
            .add(new Image(this.getClass().getResourceAsStream("/music/resources/TwoEighth.png"), 64, 64, false, true));
      } catch (Exception ex) {
      }

      stagePlayMidi.show();
    });
    btnGenMidi.setOnAction(e -> {
      try {
        midiGeneratorPane.getSequencer().close();
      } catch (Exception ex) {
      }
      midiGeneratorPane = new MidiGeneratorPane();
      midiGeneratorPane.getExitButton().setOnAction(f -> {
        try {
          midiGeneratorPane.getSequencer().close();
        } catch (Exception ex) {
        }
        stageGenMidi.close();
        primaryStage.show();
      });
      stageGenMidi.setScene(new Scene(midiGeneratorPane));
      try {
        stageGenMidi.getIcons()
            .add(new Image(this.getClass().getResourceAsStream("/music/resources/Eighth.png"), 64, 64, false, true));
      } catch (Exception ex) {

      }

      stageGenMidi.setResizable(false);
      stageGenMidi.show();
    });

    btnPiano.setOnAction(e -> {

      try {
        try {
          receiver.close();
        } catch (Exception ex) {
        }
        receiver = MidiSystem.getReceiver();
        PianoScene pianoScene = new PianoScene(1000, 500, receiver);
        pianoScene.getPianoPane().getExitButton().setOnAction(f -> {
          stagePiano.close();
          receiver.close();
          primaryStage.show();
        });

        stagePiano.setScene(pianoScene);
        stagePiano.show();

        try {
          stagePiano.getIcons()
              .add(new Image(this.getClass().getResourceAsStream("/music/resources/Piano.png"), 64, 64, false, true));
        } catch (Exception ex) {
        }

      } catch (Exception ex) {
      }

    });

    primaryScene = new Scene(startPane);
    primaryStage.setTitle("Orange Juice Music Home");
    primaryStage.setResizable(false);
    try {
      primaryStage.getIcons()
          .add(new Image(this.getClass().getResourceAsStream("/music/resources/Quarter.png"), 64, 64, false, true));
    } catch (Exception ex) {
    }
    primaryStage.setScene(primaryScene);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}