package piano;

import java.util.Map;
import static java.util.Map.entry;

import java.util.ArrayList;

import javax.sound.midi.*;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import music.NoteUtil;
import music.Notes;

public class KeyboardPane extends Pane {

    private static Map<Integer, String> sharpKeyLabelMap = Map.ofEntries(
            entry(0, "Q"),
            entry(1, "W"),
            entry(2, "E"),
            entry(3, "R"),
            entry(4, "T"),
            entry(5, "Y"),
            entry(6, "U"),
            entry(7, " I"),
            entry(8, "O"),
            entry(9, "P"),
            entry(10, "["));

    private static Map<Integer, String> regularKeyLabelMap = Map.ofEntries(
            entry(0, "A"),
            entry(1, "S"),
            entry(2, "D"),
            entry(3, "F"),
            entry(4, "G"),
            entry(5, "H"),
            entry(6, "J"),
            entry(7, "K"),
            entry(8, "L"),
            entry(9, " ;"));

    private static Map<Integer, KeyCode> regularKeyMap = Map.ofEntries(
            entry(0, KeyCode.A),
            entry(1, KeyCode.S),
            entry(2, KeyCode.D),
            entry(3, KeyCode.F),
            entry(4, KeyCode.G),
            entry(5, KeyCode.H),
            entry(6, KeyCode.J),
            entry(7, KeyCode.K),
            entry(8, KeyCode.L),
            entry(9, KeyCode.SEMICOLON));
    private static Map<Integer, KeyCode> sharpKeyMap = Map.ofEntries(
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
            entry(10, KeyCode.OPEN_BRACKET));

    private int numRegularKeys = 10;
    private int bottomNoteDisplayedOctave = 3;
    private Notes bottomNoteDisplayed = Notes.A;
    private int bottomNoteMappedOctave = 3;
    private Notes bottomNoteMapped = Notes.A;
    private Receiver receiver;

    private ArrayList<PianoKey> mappedKeys = new ArrayList<PianoKey>();
    private ArrayList<PianoKey> normalKeys = new ArrayList<PianoKey>();
    private ArrayList<Text> mappingLabels = new ArrayList<Text>();
    private ArrayList<Text> keyLabels = new ArrayList<Text>();
    private boolean displayMappingLabels = false;
    private boolean displayKeyLabels = false;

    public void setDisplayKeyLabels(boolean display) {
        this.displayKeyLabels = display;
        paintPiano();
    }

    public void setDisplayMappingLabels(boolean display) {
        this.displayMappingLabels = display;
        paintPiano();
    }

    private void stepUpOctaveDisplayed() {
        bottomNoteDisplayedOctave++;
    }

    private void stepDownOctaveDisplayed() {
        bottomNoteDisplayedOctave--;
    }

    private void stepUpOctaveMapped() {
        bottomNoteMappedOctave++;
    }

    private void stepDownOctaveMapped() {
        bottomNoteMappedOctave--;
    }

    private void stepUpNoteMapped() {
        switch (this.bottomNoteMapped) {
            case A:
                this.bottomNoteMapped = Notes.B;
                break;
            case B:
                this.bottomNoteMapped = Notes.C;
                this.bottomNoteMappedOctave++;
                break;
            case C:
                this.bottomNoteMapped = Notes.D;
                break;
            case D:
                this.bottomNoteMapped = Notes.E;
                break;
            case E:
                this.bottomNoteMapped = Notes.F;
                break;
            case F:
                this.bottomNoteMapped = Notes.G;
                break;
            case G:
                this.bottomNoteMapped = Notes.A;
                break;
            default:
                break;
        }
    }

    private void stepDownNoteMapped() {
        switch (this.bottomNoteMapped) {
            case A:
                this.bottomNoteMapped = Notes.G;
                break;
            case B:
                this.bottomNoteMapped = Notes.A;
                break;
            case C:
                this.bottomNoteMapped = Notes.B;
                this.bottomNoteMappedOctave--;
                break;
            case D:
                this.bottomNoteMapped = Notes.C;
                break;
            case E:
                this.bottomNoteMapped = Notes.D;
                break;
            case F:
                this.bottomNoteMapped = Notes.E;
                break;
            case G:
                this.bottomNoteMapped = Notes.F;
                break;
            default:
                break;
        }
    }

    private void stepUpNoteDisplayed() {
        switch (this.bottomNoteDisplayed) {
            case A:
                this.bottomNoteDisplayed = Notes.B;
                break;
            case B:
                this.bottomNoteDisplayed = Notes.C;
                this.bottomNoteDisplayedOctave++;
                break;
            case C:
                this.bottomNoteDisplayed = Notes.D;
                break;
            case D:
                this.bottomNoteDisplayed = Notes.E;
                break;
            case E:
                this.bottomNoteDisplayed = Notes.F;
                break;
            case F:
                this.bottomNoteDisplayed = Notes.G;
                break;
            case G:
                this.bottomNoteDisplayed = Notes.A;
                break;
            default:
                break;
        }
    }

    private void stepDownNoteDisplayed() {
        switch (this.bottomNoteDisplayed) {
            case A:
                this.bottomNoteDisplayed = Notes.G;
                break;
            case B:
                this.bottomNoteDisplayed = Notes.A;
                break;
            case C:
                this.bottomNoteDisplayed = Notes.B;
                this.bottomNoteDisplayedOctave--;
                break;
            case D:
                this.bottomNoteDisplayed = Notes.C;
                break;
            case E:
                this.bottomNoteDisplayed = Notes.D;
                break;
            case F:
                this.bottomNoteDisplayed = Notes.E;
                break;
            case G:
                this.bottomNoteDisplayed = Notes.F;
                break;
            default:
                break;
        }
    }

    private static int compareNotes(Notes note1, int octave1, Notes note2, int octave2) {
        if (octave1 > octave2) {
            return 1;
        } else if (octave1 < octave2) {
            return -1;
        }
        // else octave1 must == octave2
        else {
            if (NoteUtil.getNoteMap().get(note1).intValue() > NoteUtil.getNoteMap().get(note2).intValue()) {
                return 1;
            } else if (NoteUtil.getNoteMap().get(note1).intValue() < NoteUtil.getNoteMap().get(note2).intValue()) {
                return -1;
            }
            // else note1's int value must == note2's int value
            else {
                return 0;
            }
        }

    }

    private void paintPiano() {
        stopAllNotes();
        this.getChildren().clear();
        this.mappedKeys.clear();
        this.normalKeys.clear();
        this.mappingLabels.clear();
        this.keyLabels.clear();
        Notes note = this.bottomNoteDisplayed;
        int octave = this.bottomNoteDisplayedOctave;
        double noteWidth = (double) getWidth() / numRegularKeys;
        double noteHeight = getHeight();
        int i;

        for (i = 0; i < numRegularKeys; i++) {

            if (compareNotes(note, octave, this.bottomNoteMapped, this.bottomNoteMappedOctave) == 0) {
                int j;
                for (j = 0; j < 10; j++, i++) {

                    switch (note) {
                        case A:
                            if (octave != 0) {
                                if (i != 0) {
                                    this.mappedKeys.add(new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0,
                                            this.receiver, Notes.Ab, octave, noteWidth, noteHeight,
                                            sharpKeyMap.get(j)));
                                    Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                            noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                } else {
                                    this.mappedKeys.add(new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0,
                                            this.receiver, Notes.Ab, octave, noteWidth, noteHeight,
                                            sharpKeyMap.get(j)));
                                    Text label = new Text(i * noteWidth, noteHeight / 2.0 - noteHeight / 20.0,
                                            sharpKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                }

                                this.mappedKeys.add(
                                        new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                                octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                                if (displayKeyLabels == true) {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                } else {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                }

                            } else if (i == 0) {
                                this.mappedKeys.add(new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0,
                                        this.receiver, Notes.A, octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                                if (displayKeyLabels == true) {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                } else {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                }
                            }
                            note = Notes.B;
                            break;
                        case B:
                            if (i != 0) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth, noteHeight / 2.0 - noteHeight / 20.0,
                                        sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);

                            }
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                            if (displayKeyLabels == true) {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            note = Notes.C;
                            octave++;
                            break;
                        case C:
                            if (compareNotes(note, octave, Notes.C, 8) == 0) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.C_FINAL, i * noteWidth, 0.0, this.receiver, note,
                                                octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                                if (displayKeyLabels == true) {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                } else {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                }
                                note = Notes.D;
                            } else {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                                octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                                if (displayKeyLabels == true) {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                } else {
                                    Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                            noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                    label.setFont(new Font(noteWidth / 3.0));
                                    label.setFill(Color.RED);

                                    this.mappingLabels.add(label);
                                }
                                note = Notes.D;
                            }
                            break;
                        case D:
                            if (i != 0) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth, noteHeight / 2.0 - noteHeight / 20.0,
                                        sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                            if (displayKeyLabels == true) {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            note = Notes.E;
                            break;
                        case E:
                            if (i != 0) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth, noteHeight / 2.0 - noteHeight / 20.0,
                                        sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                            if (displayKeyLabels == true) {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            note = Notes.F;
                            break;
                        case F:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                            if (displayKeyLabels == true) {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            note = Notes.G;
                            break;
                        case G:
                            if (i != 0) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth, noteHeight / 2.0 - noteHeight / 20.0,
                                        sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight, regularKeyMap.get(j)));
                            if (displayKeyLabels == true) {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - 3.5 * noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            } else {
                                Text label = new Text(i * noteWidth + noteWidth / 2.75,
                                        noteHeight - noteHeight / 20.0, regularKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            note = Notes.A;
                            break;
                        default:
                            // this.mappedKeys.add(new Label("Error")); //FIX ME REMOVE THIS
                            break;
                    }
                }
                // FIX CONDITION //FIXED?
                if (numRegularKeys - i == 0) {
                    switch (bottomNoteMapped) {
                        case A:
                            if (this.bottomNoteMappedOctave != 6) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver,
                                                Notes.Db,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 5.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            break;
                        case B:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label = new Text(i * noteWidth - noteWidth / 5.0, noteHeight / 2.0 - noteHeight / 20.0,
                                    sharpKeyLabelMap.get(j));
                            label.setFont(new Font(noteWidth / 3.0));
                            label.setFill(Color.RED);

                            this.mappingLabels.add(label);
                            break;
                        case D:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label1 = new Text(i * noteWidth - noteWidth / 5.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label1.setFont(new Font(noteWidth / 3.0));
                            label1.setFill(Color.RED);

                            this.mappingLabels.add(label1);
                            break;
                        case E:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Ab,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label2 = new Text(i * noteWidth - noteWidth / 5.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label2.setFont(new Font(noteWidth / 3.0));
                            label2.setFill(Color.RED);

                            this.mappingLabels.add(label2);
                            break;
                        case F:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label3 = new Text(i * noteWidth - noteWidth / 5.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label3.setFont(new Font(noteWidth / 3.0));
                            label3.setFill(Color.RED);

                            this.mappingLabels.add(label3);
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (bottomNoteMapped) {
                        case A:
                            if (this.bottomNoteDisplayedOctave != 6) {
                                this.mappedKeys.add(
                                        new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                                octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                                Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                        noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                                label.setFont(new Font(noteWidth / 3.0));
                                label.setFill(Color.RED);

                                this.mappingLabels.add(label);
                            }
                            break;
                        case B:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label = new Text(i * noteWidth - noteWidth / 10.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label.setFont(new Font(noteWidth / 3.0));
                            label.setFill(Color.RED);

                            this.mappingLabels.add(label);
                            break;
                        case D:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label1 = new Text(i * noteWidth - noteWidth / 10.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label1.setFont(new Font(noteWidth / 3.0));
                            label1.setFill(Color.RED);

                            this.mappingLabels.add(label1);
                            break;
                        case E:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Ab,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label2 = new Text(i * noteWidth - noteWidth / 10.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label2.setFont(new Font(noteWidth / 3.0));
                            label2.setFill(Color.RED);

                            this.mappingLabels.add(label2);
                            break;
                        case F:
                            this.mappedKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                            octave, noteWidth, noteHeight, sharpKeyMap.get(j)));
                            Text label3 = new Text(i * noteWidth - noteWidth / 10.0,
                                    noteHeight / 2.0 - noteHeight / 20.0, sharpKeyLabelMap.get(j));
                            label3.setFont(new Font(noteWidth / 3.0));
                            label3.setFill(Color.RED);

                            this.mappingLabels.add(label3);
                            break;
                        default:
                            break;
                    }
                }
                i--;
            } else {
                switch (note) {
                    case A:
                        if (octave != 0) {
                            if (compareNotes(note, octave,
                                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {
                                this.normalKeys.add(new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0,
                                        this.receiver, Notes.Ab, octave, noteWidth, noteHeight));
                            }
                            this.normalKeys.add(
                                    new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight));
                        } else if (i == 0) {
                            this.normalKeys.add(new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0,
                                    this.receiver, Notes.A, octave, noteWidth, noteHeight));
                        }
                        note = Notes.B;
                        break;
                    case B:
                        if (compareNotes(note, octave,
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                            octave, noteWidth, noteHeight));
                        }
                        this.normalKeys.add(
                                new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight));
                        note = Notes.C;
                        octave++;
                        break;
                    case C:
                        if (compareNotes(note, octave, Notes.C, 8) == 0) {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.C_FINAL, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight));
                            note = Notes.D;
                        } else {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                            octave, noteWidth, noteHeight));
                            note = Notes.D;
                        }
                        break;
                    case D:
                        if (compareNotes(note, octave,
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                            octave, noteWidth, noteHeight));
                        }
                        this.normalKeys.add(
                                new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight));
                        note = Notes.E;
                        break;
                    case E:
                        if (compareNotes(note, octave,
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                            octave, noteWidth, noteHeight));
                        }
                        this.normalKeys.add(
                                new PianoKey(KeyType.B_E_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight));
                        note = Notes.F;
                        break;
                    case F:
                        this.normalKeys.add(
                                new PianoKey(KeyType.C_F_KEY, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight));
                        note = Notes.G;
                        break;
                    case G:
                        if (compareNotes(note, octave,
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {
                            this.normalKeys.add(
                                    new PianoKey(KeyType.SHARP_FLAT, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                            octave, noteWidth, noteHeight));
                        }
                        this.normalKeys.add(
                                new PianoKey(KeyType.STANDARD, i * noteWidth, 0.0, this.receiver, note,
                                        octave, noteWidth, noteHeight));
                        note = Notes.A;
                        break;
                    default:
                        // this.mappedKeys.add(new Label("Error"));
                        break;
                }
            }

        }
        if (compareNotes(note, octave, addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getKey(),
                addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 10).getValue()) != 0) {

            switch (note) {
                case D:
                    if (compareNotes(note, octave, Notes.C, 8) < 0) {
                        this.normalKeys.add(
                                new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Db,
                                        octave, noteWidth, noteHeight));
                    }
                    break;
                case E:
                    this.normalKeys.add(
                            new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Eb,
                                    octave, noteWidth, noteHeight));
                    break;
                case G:
                    this.normalKeys.add(
                            new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Gb,
                                    octave, noteWidth, noteHeight));
                    break;
                case A:
                    this.normalKeys.add(
                            new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Ab,
                                    octave, noteWidth, noteHeight));
                    break;
                case B:
                    this.normalKeys.add(
                            new PianoKey(KeyType.SHARP_FLAT_FINAL, i * noteWidth, 0.0, this.receiver, Notes.Bb,
                                    octave, noteWidth, noteHeight));
                    break;
                default:
                    break;
            }
        }

        this.getChildren().addAll(mappedKeys);
        this.getChildren().addAll(normalKeys);

        if (displayMappingLabels == true) {
            for (Text label : mappingLabels) {
                label.setMouseTransparent(true);
            }

            this.getChildren().addAll(mappingLabels);
        }

        if (displayKeyLabels == true) {
            note = bottomNoteDisplayed;
            octave = bottomNoteDisplayedOctave;
            Text label;
            for (i = 0; i < numRegularKeys; i++) {
                switch (note) {
                    case A:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "A" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.B;
                        break;
                    case B:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "B" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.C;
                        octave++;
                        break;
                    case C:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "C" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.D;
                        break;
                    case D:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "D" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.E;
                        break;
                    case E:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "E" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.F;
                        break;
                    case F:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "F" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.G;
                        break;
                    case G:
                        label = new Text(i * noteWidth + noteWidth / 3.5,
                                noteHeight - noteHeight / 20.0, "G" + octave);
                        label.setFont(new Font(noteWidth / 3.0));
                        label.setFill(Color.BLUE);
                        label.setMouseTransparent(true);
                        this.keyLabels.add(label);
                        note = Notes.A;
                        break;
                    default:
                        break;

                }

            }
            this.getChildren().addAll(keyLabels);
        }

    }

    private static Pair<Notes, Integer> subtractNoteSteps(Notes note, int octave, int stepsDown) {

        for (int j = 0; j < stepsDown; j++) {
            switch (note) {
                case A:
                    note = Notes.G;
                    break;
                case B:
                    note = Notes.A;
                    break;
                case C:
                    note = Notes.B;
                    octave--;
                    break;
                case D:
                    note = Notes.C;
                    break;
                case E:
                    note = Notes.D;
                    break;
                case F:
                    note = Notes.E;
                    break;
                case G:
                    note = Notes.F;
                    break;
                default:
                    break;
            }
        }

        Pair<Notes, Integer> newNote = new Pair<Notes, Integer>(note, octave);

        return newNote;
    }

    private static Pair<Notes, Integer> addNoteSteps(Notes note, int octave, int stepsUp) {

        for (int j = 0; j < stepsUp; j++) {
            switch (note) {
                case A:
                    note = Notes.B;
                    break;
                case B:
                    note = Notes.C;
                    octave++;
                    break;
                case C:
                    note = Notes.D;
                    break;
                case D:
                    note = Notes.E;
                    break;
                case E:
                    note = Notes.F;
                    break;
                case F:
                    note = Notes.G;
                    break;
                case G:
                    note = Notes.A;
                    break;
                default:
                    break;
            }
        }

        Pair<Notes, Integer> newNote = new Pair<Notes, Integer>(note, octave);

        return newNote;
    }

    private void stopAllNotes() {
        for (PianoKey key : mappedKeys) {

            key.stopNote();

        }
    }

    public void stepDownNoteMapping() {

        if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 0) > 0) {
            stepDownNoteMapped();

            if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, bottomNoteDisplayed,
                    bottomNoteDisplayedOctave) < 0) {
                bottomNoteDisplayed = bottomNoteMapped;
                bottomNoteDisplayedOctave = bottomNoteMappedOctave;

            }
            paintPiano();
        }

    }

    public void stepDownOctaveMapping() {

        if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 0) > 0) {
            stepDownOctaveMapped();
            if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 0) < 0) {
                bottomNoteMapped = Notes.A;
                bottomNoteMappedOctave = 0;

            }
            if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, bottomNoteDisplayed,
                    bottomNoteDisplayedOctave) < 0) {
                bottomNoteDisplayed = bottomNoteMapped;
                bottomNoteDisplayedOctave = bottomNoteMappedOctave;

            }
            paintPiano();
        }

    }

    public void stepUpNoteMapping() {
        if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 6) < 0) {
            stepUpNoteMapped();

            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getKey(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getValue()) < 0) {
                stepUpNoteDisplayed();
            }
            paintPiano();
        }

    }

    public void stepUpOctaveMapping() {

        if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 6) < 0) {
            stepUpOctaveMapped();
            if (compareNotes(bottomNoteMapped, bottomNoteMappedOctave, Notes.A, 6) > 0) {
                bottomNoteMapped = Notes.A;
                bottomNoteMappedOctave = 6;

            }
            if (compareNotes(
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getKey(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getValue()) < 0) {
                Pair<Notes, Integer> tempNote = subtractNoteSteps(bottomNoteMapped, bottomNoteMappedOctave,
                        numRegularKeys - 10);
                bottomNoteDisplayed = tempNote.getKey();
                bottomNoteDisplayedOctave = tempNote.getValue();
            }
            paintPiano();
        }

    }

    public void addNote() {
        // 52 regular keys on a piano (not sharp/flat keys)
        if (this.numRegularKeys < 52) {
            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    Notes.C, 8) == 0) {
                Pair<Notes, Integer> tempNote = subtractNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, 1);
                bottomNoteDisplayed = tempNote.getKey();
                bottomNoteDisplayedOctave = tempNote.getValue();
            }

            this.numRegularKeys++;
            paintPiano();
        }

    }

    public void removeNote() {
        // 10 is minimum keys to be displayed
        if (this.numRegularKeys > 10) {

            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getKey(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getValue()) == 0) {

                Pair<Notes, Integer> tempNote = subtractNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 1);
                bottomNoteMapped = tempNote.getKey();
                bottomNoteMappedOctave = tempNote.getValue();

            }

            this.numRegularKeys--;
            paintPiano();
        }

    }

    public void stepUpOctaveDisplay() {
        if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(), Notes.C,
                8) < 0) {
            this.stepUpOctaveDisplayed();
            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    Notes.C, 8) > 0) {
                Pair<Notes, Integer> tempNote = subtractNoteSteps(Notes.C, 8, numRegularKeys - 1);
                bottomNoteDisplayed = tempNote.getKey();
                bottomNoteDisplayedOctave = tempNote.getValue();
            }
            if (compareNotes(bottomNoteDisplayed, bottomNoteDisplayedOctave, bottomNoteMapped,
                    bottomNoteMappedOctave) > 0) {
                bottomNoteMapped = bottomNoteDisplayed;
                bottomNoteMappedOctave = bottomNoteDisplayedOctave;
            }
            paintPiano();
        }

    }

    public void stepUpNoteDisplay() {
        if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(), Notes.C,
                8) < 0) {
            this.stepUpNoteDisplayed();
            if (compareNotes(bottomNoteDisplayed, bottomNoteDisplayedOctave, bottomNoteMapped,
                    bottomNoteMappedOctave) > 0) {
                bottomNoteMapped = bottomNoteDisplayed;
                bottomNoteMappedOctave = bottomNoteDisplayedOctave;
            }
            paintPiano();
        }
    }

    public void stepDownNoteDisplay() {
        if (compareNotes(bottomNoteDisplayed, bottomNoteDisplayedOctave, Notes.A, 0) > 0) {
            this.stepDownNoteDisplayed();
            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getKey(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getValue()) < 0) {

                Pair<Notes, Integer> tempNote1 = addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave,
                        numRegularKeys - 1);
                Pair<Notes, Integer> tempNote2 = subtractNoteSteps(tempNote1.getKey(), tempNote1.getValue(), 9);
                bottomNoteMapped = tempNote2.getKey();
                bottomNoteMappedOctave = tempNote2.getValue();

            }
            paintPiano();
        }
    }

    public void stepDownOctaveDisplay() {
        if (compareNotes(bottomNoteDisplayed, bottomNoteDisplayedOctave, Notes.A, 0) > 0) {
            this.stepDownOctaveDisplayed();
            if (compareNotes(bottomNoteDisplayed, bottomNoteDisplayedOctave, Notes.A, 0) < 0) {
                bottomNoteDisplayed = Notes.A;
                bottomNoteDisplayedOctave = 0;
            }
            if (compareNotes(addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getKey(),
                    addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave, numRegularKeys - 1).getValue(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getKey(),
                    addNoteSteps(bottomNoteMapped, bottomNoteMappedOctave, 9).getValue()) < 0) {

                Pair<Notes, Integer> tempNote1 = addNoteSteps(bottomNoteDisplayed, bottomNoteDisplayedOctave,
                        numRegularKeys - 1);
                Pair<Notes, Integer> tempNote2 = subtractNoteSteps(tempNote1.getKey(), tempNote1.getValue(), 9);
                bottomNoteMapped = tempNote2.getKey();
                bottomNoteMappedOctave = tempNote2.getValue();

            }
            paintPiano();
        }

    }

    public KeyboardPane(Receiver receiver) {
        super();
        this.receiver = receiver;
        paintPiano();

    }

    public KeyboardPane(Notes note, int octave, Receiver receiver) {
        super();
        this.receiver = receiver;

        if (compareNotes(note, octave, Notes.A, 0) < 0) {
            this.bottomNoteDisplayed = Notes.A;
            this.bottomNoteDisplayedOctave = 0;
            this.bottomNoteMapped = Notes.A;
            this.bottomNoteMappedOctave = 0;
        } else if (compareNotes(note, octave, Notes.A, 6) > 0) {
            this.bottomNoteMapped = Notes.A;
            this.bottomNoteMappedOctave = 6;

        } else {
            Notes newNote;
            switch (note) {
                case Ab:
                    newNote = Notes.A;
                    break;
                case Bb:
                    newNote = Notes.B;
                    break;
                case Db:
                    newNote = Notes.D;
                    break;
                case Eb:
                    newNote = Notes.E;
                    break;
                case Gb:
                    newNote = Notes.G;
                    break;
                default:
                    newNote = note;
                    break;
            }
            this.bottomNoteMapped = newNote;
            this.bottomNoteMappedOctave = octave;
        }

        paintPiano();

    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintPiano();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintPiano();
    }

    public ArrayList<PianoKey> getMappedKeys() {
        return this.mappedKeys;
    }

}
