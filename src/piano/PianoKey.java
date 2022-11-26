package piano;

import javax.sound.midi.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;
import music.Notes;
import music.Note;
import music.NoteUtil;

public abstract class PianoKey extends Polygon {

    private KeyCode keyboardKey;
    private int octave;
    private Notes note;
    private static IntegerProperty volume = new SimpleIntegerProperty();
    private boolean isMousePressed = false;
    private boolean isKeyboardPressed = false;

    private Receiver receiver;

    public static IntegerProperty getVolumeProperty() {
        return volume;
    }

    public void startNote() {

        try {
            int noteValue = 12 * (this.octave) + (NoteUtil.getNoteMap().get(this.note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteValue, volume.get());

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }

    public void stopNote() {

        try {
            int noteValue = 12 * (this.octave) + (NoteUtil.getNoteMap().get(this.note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(128, 1, noteValue, volume.get());

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }

    public abstract void changeColorMouse();

    public abstract void changeColorKeyboard();

    protected void setMouseHandling() {

        this.setOnDragDetected(e -> {
            this.startNote();
            this.setIsMousePressed(false);
            this.changeColorMouse();
            this.setIsMousePressed(true);
            this.startFullDrag();
        });
        this.setOnMousePressed(e -> {
            e.setDragDetect(true);
        });
        this.setOnMouseReleased(e -> {
            this.stopNote();
            this.setIsMousePressed(true);
            this.changeColorMouse();
            this.setIsMousePressed(false);
            e.setDragDetect(false);
        });
        this.setOnMouseDragEntered(e -> {
            this.startNote();
            this.setIsMousePressed(false);
            this.changeColorMouse();
            this.setIsMousePressed(true);
        });
        this.setOnMouseDragExited(e -> {
            this.stopNote();
            this.setIsMousePressed(true);
            this.changeColorMouse();
            this.setIsMousePressed(false);
        });
        this.setOnMouseDragReleased(e -> {
            this.stopNote();
            this.setIsMousePressed(true);
            this.changeColorMouse();
            this.setIsMousePressed(false);
        });

    }

    public PianoKey(Receiver receiver, Notes note, int octave, KeyCode keyboardKey) {
        this.keyboardKey = keyboardKey;
        this.receiver = receiver;
        this.note = note;
        this.octave = octave;
        this.setMouseHandling();
    }

    public KeyCode getKeyboardKey() {
        return this.keyboardKey;
    }

    public boolean getIsMousePressed() {
        return this.isMousePressed;
    }

    public void setIsMousePressed(boolean pressed) {
        this.isMousePressed = pressed;
    }

    public boolean getIsKeyboardPressed() {
        return this.isKeyboardPressed;
    }

    public void setIsKeyboardPressed(boolean pressed) {
        this.isKeyboardPressed = pressed;
    }

    public Notes getNote() {
        return this.note;
    }

    public int getOctave() {
        return this.octave;
    }

}
