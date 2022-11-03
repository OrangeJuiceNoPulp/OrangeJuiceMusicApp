package piano;

import java.util.Map;
import static java.util.Map.entry;

import javax.sound.midi.*;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import music.Note;
import music.Notes;

public class PianoPane extends Pane {

    public static Map<Integer, KeyCode> regularKeyMap = Map.ofEntries(
        entry(0, KeyCode.A),
        entry(1, KeyCode.S),
        entry(2, KeyCode.D),
        entry(3, KeyCode.F),
        entry(4, KeyCode.G),
        entry(5, KeyCode.H),
        entry(6, KeyCode.J),
        entry(7, KeyCode.K),
        entry(8, KeyCode.L),
        entry(9, KeyCode.SEMICOLON)
    );
    public static Map<Integer, KeyCode> sharpKeyMap = Map.ofEntries(
        entry(0, KeyCode.Q),
        entry(1, KeyCode.W),
        entry(2, KeyCode.E),
        entry(3, KeyCode.R),
        entry(4, KeyCode.T),
        entry(5, KeyCode.Y),
        entry(6, KeyCode.U),
        entry(7, KeyCode.I),
        entry(8, KeyCode.O),
        entry(9, KeyCode.P),
        entry(10, KeyCode.OPEN_BRACKET)
    );


    

    private int bottomNoteOctave = 3;
    private Notes bottomNote = Notes.A;
    private Receiver receiver;


    //Delete this method once the program is working
    private void mapKeyBindings() {
        this.setOnKeyPressed(e -> {
            Notes note = this.bottomNote;
            int octave = this.bottomNoteOctave;
            for(int i = 0; i < 10; i++) {
                startNote(this.receiver, octave, note);
                switch (note) {
                    case A: note = Notes.B; break;
                    case B: note = Notes.C; octave++; break;
                    case C: note = Notes.D; break;
                    case D: note = Notes.E; break;
                    case F: note = Notes.F; break;
                    case E: note = Notes.G; break;
                    case G: note = Notes.A; break;
                    default: break;
                }
            }

        });
    }
    //Delete this method once the program is working
    private void clearKeyBindings() {
        this.setOnKeyPressed(e -> {});
        this.setOnKeyReleased(e -> {});
    }

    //Delete this method once the program is working
    private void startNote(Receiver receiver, int octave, Notes note) {

        try {
            int noteValue = 12 * octave + (Note.getNoteMap().get(note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteValue, 100);

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }
    
    //Delete this method once the program is working
    private void stopNote(Receiver receiver, int octave, Notes note) {

        try {
            int noteValue = 12 * octave + (Note.getNoteMap().get(note)) + Note.getOffset();
            ShortMessage a = new ShortMessage();
            a.setMessage(128, 1, noteValue, 100);

            receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can

        } catch (Exception ex) {
        }

    }






    private void paintPiano() {
        Notes note = this.bottomNote;
        int octave = this.bottomNoteOctave;
        double noteWidth = getWidth() / 10.0;
        double noteHeight = getHeight();
        int i;
        if ((octave < 6) || ((octave == 6) && (note != Notes.B))) {

            for (i = 0; i < 10; i++) {

                switch (note) {
                    case A:
                        if (octave != 0) {
                            if (i != 0) {
                                this.getChildren().add(new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0,
                                        this.receiver, Notes.Ab, octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                            } else {
                                this.getChildren().add(new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0,
                                        this.receiver, Notes.Ab, octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                            }

                            this.getChildren().add(
                                    new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        } else if (i == 0) {
                            this.getChildren().add(new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0,
                                    this.receiver, Notes.A, octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        }
                        note = Notes.B;
                        break;
                    case B:
                        if (i != 0) {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        } else {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        }
                        this.getChildren().add(
                                new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.C;
                        octave++;
                        break;
                    case C:
                        this.getChildren().add(
                                new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.D;
                        break;
                    case D:
                        if (i != 0) {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        } else {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        }
                        this.getChildren().add(
                                new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.E;
                        break;
                    case E:
                        if (i != 0) {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        } else {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        }
                        this.getChildren().add(
                                new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.F;
                        break;
                    case F:
                        this.getChildren().add(
                                new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.G;
                        break;
                    case G:
                        if (i != 0) {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        } else {
                            this.getChildren().add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                        }
                        this.getChildren().add(
                                new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight, regularKeyMap.get(i)));
                        note = Notes.A;
                        break;
                    default:
                        // this.getChildren().add(new Label("Error"));
                        break;

                }

            }

            switch (bottomNote) {
                case A:
                    if (this.bottomNoteOctave != 6) {
                        this.getChildren().add(
                                new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                        octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                    }
                    break;
                case B:
                    this.getChildren().add(
                            new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                    octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                    break;
                case D:
                    this.getChildren().add(
                            new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                    octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                    break;
                case E:
                    this.getChildren().add(
                            new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Ab,
                                    octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                    break;
                case F:
                    this.getChildren().add(
                            new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                    octave, noteWidth, noteHeight, sharpKeyMap.get(i)));
                    break;
                default:
                    break;
            }


            


        }

    }

    public PianoPane(Receiver receiver) {
        super();
        this.receiver = receiver;
        paintPiano();
        
    }

    public PianoPane(Notes note, int octave, Receiver receiver) {
        super();
        this.receiver = receiver;
        this.bottomNote = note;
        this.bottomNoteOctave = octave;
        
        paintPiano();
       
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        this.getChildren().clear();
        paintPiano();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        this.getChildren().clear();
        paintPiano();
    }

}

/*
 * switch (note) {
 * case A:
 * 
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 0.0, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.STANDARD, 0.0, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.B_E_KEY, noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.C_F_KEY, 2*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 3*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.STANDARD, 3*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 4*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.B_E_KEY, 4*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.C_F_KEY, 5*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 6*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.STANDARD, 6*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 7*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.STANDARD, 7*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.SHARP_FLAT, 8*noteWidth, 0.0);
 * PianoKey A0 = new PianoKey(KeyType.B_E_KEY, 8*noteWidth, 0.0);
 * 
 * this.getChildren().addAll(
 * new PianoKey(KeyType.SHARP_FLAT, 0.0, 0.0, receiver),
 * new PianoKey(KeyType.STANDARD, 0.0, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.B_E_KEY, noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.C_F_KEY, 2 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, 3 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.STANDARD, 3 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, 4 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.B_E_KEY, 4 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.C_F_KEY, 5 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, 6 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.STANDARD, 6 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, 7 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.STANDARD, 7 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.SHARP_FLAT, 8 * noteWidth, 0.0, receiver),
 * new PianoKey(KeyType.B_E_KEY, 8 * noteWidth, 0.0, receiver)
 * 
 * );
 * 
 * break;
 * case B:
 * 
 * case C:
 * case D:
 * case E:
 * case F:
 * case G:
 * 
 * }
 */