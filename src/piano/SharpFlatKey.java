package piano;

import javax.sound.midi.Receiver;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import music.Notes;

public class SharpFlatKey extends PianoKey {
    public void changeColorMouse() {
        if (this.getIsMousePressed() == true) {
            this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
        } else {
            this.setFill(Color.DARKGRAY.darker().darker());
        }
    }

    public void changeColorKeyboard() {
        if (this.getIsKeyboardPressed() == true) {
            this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
        } else {
            this.setFill(Color.DARKGRAY.darker().darker());
        }
    }

    public SharpFlatKey(Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight, KeyCode keyboardKey) {
        super(receiver, note, octave, keyboardKey);

        double OFFSET = noteWidth / 3.0;
        this.setStroke(Color.BLACK);
        this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
        if (leftAnchor < OFFSET) {
            this.getPoints().addAll(new Double[] {
                    (leftAnchor), (topAnchor),
                    (leftAnchor + OFFSET), (topAnchor),
                    (leftAnchor + OFFSET), (topAnchor + noteHeight / 2.0),
                    (leftAnchor), (topAnchor + noteHeight / 2.0)
            });
        } else {
            this.getPoints().addAll(new Double[] {
                    (leftAnchor - OFFSET), (topAnchor),
                    (leftAnchor + OFFSET), (topAnchor),
                    (leftAnchor + OFFSET), (topAnchor + noteHeight / 2.0),
                    (leftAnchor - OFFSET), (topAnchor + noteHeight / 2.0)
            });
        }
    }

    public SharpFlatKey(Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight) {
        this(leftAnchor, topAnchor, receiver, note, octave, noteWidth, noteHeight, null);
    }
}
