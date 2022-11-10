package piano;

import javax.sound.midi.*;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import music.Notes;
import music.Note;

public class PianoKey extends Polygon {

    private KeyCode keyboardKey;
    private KeyType type;
    private int octave;
    private Notes note;
    private int volume = 100;
    private boolean isPressed = false;

    private Receiver receiver;

    // FIX ME: See if it is possible to use the receiver from the class's field
    // rather than pass one as a parameter
    public void startNote(Receiver receiver) {

        try {
            int noteValue = 12 * (this.octave) + (Note.getNoteMap().get(this.note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteValue, volume);

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }

    public void stopNote(Receiver receiver) {

        try {
            int noteValue = 12 * (this.octave) + (Note.getNoteMap().get(this.note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(128, 1, noteValue, volume);

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }

    // FIX ME: Make two separate methods, one to set the proper actions, one to
    // change the color depending on key type //NEVERMIND, LEAVE AS IS
    private void setChangeColor() {
        switch (this.type) {
            case STANDARD:
            case C_FINAL:
            case B_E_KEY:
            case C_F_KEY:

                this.setOnDragDetected(e -> {
                    this.startNote(receiver);
                    this.setFill(Color.WHITE.darker());
                    this.startFullDrag();
                });
                this.setOnMousePressed(e -> {
                    e.setDragDetect(true);
                });
                this.setOnMouseReleased(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.WHITE);
                    e.setDragDetect(false);
                });
                this.setOnMouseDragEntered(e -> {
                    this.startNote(receiver);
                    this.setFill(Color.WHITE.darker());
                });
                this.setOnMouseDragExited(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.WHITE);
                });
                this.setOnMouseDragReleased(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.WHITE);
                });
                break;
            case SHARP_FLAT_FINAL:
            case SHARP_FLAT:

                this.setOnDragDetected(e -> {
                    this.startNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker());
                    this.startFullDrag();
                });
                this.setOnMousePressed(e -> {
                    e.setDragDetect(true);
                    this.startNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker());
                });
                this.setOnMouseReleased(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                    e.setDragDetect(false);
                });
                this.setOnMouseDragEntered(e -> {
                    this.startNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker());
                });
                this.setOnMouseDragExited(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                });
                this.setOnMouseDragReleased(e -> {
                    this.stopNote(receiver);
                    this.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                });
                break;
        }
    }

    public PianoKey(KeyType type, Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight, int volume) {
        super();
        this.keyboardKey = null;
        this.type = type;
        this.receiver = receiver;
        this.note = note;
        this.octave = octave;
        this.volume = volume;
        double OFFSET = noteWidth / 3.0;
        switch (type) {
            case STANDARD:
                this.setStroke(Color.BLACK);
                this.setFill(Color.WHITE);
                this.getPoints().addAll(new Double[] {
                        (leftAnchor), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor + 2 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor + 2 * OFFSET), (topAnchor),
                        (leftAnchor + 1 * OFFSET), (topAnchor),
                        (leftAnchor + 1 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor), (topAnchor + noteHeight / 2.0)
                });
                this.setChangeColor();
                break;
            case B_E_KEY:
                this.setStroke(Color.BLACK);
                this.setFill(Color.WHITE);
                this.getPoints().addAll(new Double[] {
                        (leftAnchor), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor),
                        (leftAnchor + 1 * OFFSET), (topAnchor),
                        (leftAnchor + 1 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor), (topAnchor + noteHeight / 2.0)
                });
                this.setChangeColor();
                break;
            case C_F_KEY:
                this.setStroke(Color.BLACK);
                this.setFill(Color.WHITE);
                this.getPoints().addAll(new Double[] {
                        (leftAnchor), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor + 2 * OFFSET), (topAnchor + noteHeight / 2.0),
                        (leftAnchor + 2 * OFFSET), (topAnchor),
                        (leftAnchor), (topAnchor)
                });
                this.setChangeColor();
                break;
            case C_FINAL:
                this.setStroke(Color.BLACK);
                this.setFill(Color.WHITE);
                this.getPoints().addAll(new Double[] {
                        (leftAnchor), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor + noteHeight),
                        (leftAnchor + 3 * OFFSET), (topAnchor),
                        (leftAnchor), (topAnchor)
                });
                this.setChangeColor();
                break;
            case SHARP_FLAT:
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
                this.setChangeColor();
                break;
            case SHARP_FLAT_FINAL:
                this.setStroke(Color.BLACK);
                this.setFill(Color.DARKGRAY.darker().darker().darker().darker());

                this.getPoints().addAll(new Double[] {
                        (leftAnchor - OFFSET), (topAnchor),
                        (leftAnchor), (topAnchor),
                        (leftAnchor), (topAnchor + noteHeight / 2.0),
                        (leftAnchor - OFFSET), (topAnchor + noteHeight / 2.0)
                });

                this.setChangeColor();
                break;

        }
    }

    public PianoKey(KeyType type, Double leftAnchor, Double topAnchor, Receiver receiver, Notes note, int octave,
            double noteWidth, double noteHeight, int volume, KeyCode keyboardKey) {
        this(type, leftAnchor, topAnchor, receiver, note, octave, noteWidth, noteHeight, volume);
        this.keyboardKey = keyboardKey;
    }

    public KeyCode getKeyboardKey() {
        return this.keyboardKey;
    }

    public KeyType getKeyType() {
        return this.type;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public void setIsPressed(boolean pressed) {
        this.isPressed = pressed;
    }

    public Notes getNote() {
        return this.note;
    }

    public int getOctave() {
        return this.octave;
    }

    /*
     * public PianoKey(KeyType type, Double leftAnchor, Double topAnchor, Note note)
     * {
     * this(type, leftAnchor, topAnchor);
     * 
     * 
     * 
     * 
     * 
     * 
     * }
     */

}
