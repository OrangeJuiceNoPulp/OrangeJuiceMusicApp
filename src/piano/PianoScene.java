package piano;

import javax.sound.midi.Receiver;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class PianoScene extends Scene {
  private PianoPane pianoPane;

  public PianoPane getPianoPane() {
    return pianoPane;
  }

  public PianoScene(double width, double height, Receiver receiver) {
    super(new PianoPane(receiver), width, height);
    this.setFill(Color.BLUE);

    pianoPane = (PianoPane) this.getRoot();
    KeyboardPane piano = pianoPane.getKeyboardPane();
    this.setOnKeyPressed(e -> {
      if (pianoPane.getVolumeSlider().isValueChanging() == false) {
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

              if (key.getIsKeyboardPressed() != true) {
                key.startNote();
                key.changeColorKeyboard();
                key.setIsKeyboardPressed(true);

              }
            }
          }
        }
      }
    });

    this.setOnKeyReleased(e -> {

      for (PianoKey key : piano.getMappedKeys()) {

        if (key.getKeyboardKey() == e.getCode()) {
          if (key.getIsKeyboardPressed() == true) {
            key.stopNote();
            key.changeColorKeyboard();
            key.setIsKeyboardPressed(false);
          }
        }
      }

    });

  }
}
