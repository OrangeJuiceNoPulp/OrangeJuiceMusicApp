package piano;

import javax.sound.midi.Receiver;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import music.Notes;

public class FinalCKey extends PianoKey {
    public void changeColorMouse() {
        if (this.getIsMousePressed() == true) {
            this.setFill(Color.WHITE);
        } else {
            this.setFill(Color.WHITE.darker());
        }
    }

    public void changeColorKeyboard() {
        if (this.getIsKeyboardPressed() == true) {
            this.setFill(Color.WHITE);
        } else {
            this.setFill(Color.WHITE.darker());
        }
    }

    public FinalCKey(Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight, KeyCode keyboardKey) {
        super(receiver, note, octave, keyboardKey);

        double OFFSET = noteWidth / 3.0;
        this.setStroke(Color.BLACK);
        this.setFill(Color.WHITE);
        this.getPoints().addAll(new Double[] {
                (leftAnchor), (topAnchor + noteHeight),
                (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight),
                (leftAnchor + 3 * OFFSET), (topAnchor),
                (leftAnchor), (topAnchor)
        });
    }

    public FinalCKey(Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight) {
        this(leftAnchor, topAnchor, receiver, note, octave, noteWidth, noteHeight, null);
    }
}
