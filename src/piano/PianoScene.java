package piano;

import javax.sound.midi.Receiver;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import music.Notes;

public class PianoScene extends Scene {

  public PianoScene(double width, double height, Receiver receiver) {
    super(new PianoPane(receiver), width, height);
    this.setFill(Color.BLUE);

    PianoPane pianoPane = (PianoPane) this.getRoot();
    KeyboardPane piano = pianoPane.piano;
    this.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.Z) {
        piano.stepDownOctaveMapping();
      } else if (e.getCode() == KeyCode.X) {
        piano.stepDownNoteMapping();
      } else if (e.getCode() == KeyCode.C) {
        piano.stepUpNoteMapping();
      } else if (e.getCode() == KeyCode.V) {
        piano.stepUpOctaveMapping();
      } else {

        for (PianoKey key : piano.getMappedKeys()) {

          if (key.getKeyboardKey() == e.getCode()) {

            if (key.getIsPressed() != true) {
              key.setIsPressed(true);
              key.startNote(receiver);

              switch (key.getKeyType()) {
                case B_E_KEY:
                case C_F_KEY:
                case C_FINAL:
                case STANDARD:
                  key.setFill(Color.WHITE.darker());
                  break;
                case SHARP_FLAT_FINAL:
                case SHARP_FLAT:
                  key.setFill(Color.DARKGRAY.darker().darker());
                  break;
              }
            }
          }

        }
      }
    });

    this.setOnKeyReleased(e -> {

      for (PianoKey key : piano.getMappedKeys()) {

        if (key.getKeyboardKey() == e.getCode()) {
          if (key.getIsPressed() == true) {
            key.stopNote(receiver);

            switch (key.getKeyType()) {
              case B_E_KEY:
              case C_F_KEY:
              case C_FINAL:
              case STANDARD:
                key.setFill(Color.WHITE);
                break;
              case SHARP_FLAT_FINAL:
              case SHARP_FLAT:
                key.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                break;
            }
            key.setIsPressed(false);
          }
        }
      }

    });

  }
}
