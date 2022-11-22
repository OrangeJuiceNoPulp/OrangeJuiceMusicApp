package music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RangePane extends VBox {
    private static ObservableList<String> noteList = FXCollections.observableArrayList("A", "A#/Bb", "B", "C", "C#/Db",
            "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab");
    private static ObservableList<Integer> C_OctaveList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    private static ObservableList<Integer> A_Ab_B_OctaveList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6,
            7);
    private static ObservableList<Integer> defaultOctaveList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);

    private ComboBox<String> cboBottomNote;
    private ComboBox<String> cboTopNote;

    private ComboBox<Integer> cboBottomNoteOctave;
    private ComboBox<Integer> cboTopNoteOctave;

    public Notes getTopNote() {
        String note = cboTopNote.getValue();
        switch (note) {
            case "A":
                return Notes.A;
            case "A#/Bb":
                return Notes.Bb;
            case "B":
                return Notes.B;
            case "C":
                return Notes.C;
            case "C#/Db":
                return Notes.Db;
            case "D":
                return Notes.D;
            case "D#/Eb":
                return Notes.Eb;
            case "E":
                return Notes.E;
            case "F":
                return Notes.F;
            case "F#/Gb":
                return Notes.Gb;
            case "G":
                return Notes.G;
            case "G#/Ab":
                return Notes.Ab;
            default:
                return Notes.C;
        }
    }

    public Notes getBottomNote() {
        String note = cboBottomNote.getValue();
        switch (note) {
            case "A":
                return Notes.A;
            case "A#/Bb":
                return Notes.Bb;
            case "B":
                return Notes.B;
            case "C":
                return Notes.C;
            case "C#/Db":
                return Notes.Db;
            case "D":
                return Notes.D;
            case "D#/Eb":
                return Notes.Eb;
            case "E":
                return Notes.E;
            case "F":
                return Notes.F;
            case "F#/Gb":
                return Notes.Gb;
            case "G":
                return Notes.G;
            case "G#/Ab":
                return Notes.Ab;
            default:
                return Notes.C;
        }
    }

    public int getTopNoteOctave() {
        return cboTopNoteOctave.getValue();
    }

    public int getBottomNoteOctave() {
        return cboBottomNoteOctave.getValue();
    }

    public RangePane(String partName) {
        super(5);
        setPadding(new Insets(5));
        setStyle("-fx-border-color: black;");
        cboTopNote = new ComboBox<String>();
        cboBottomNote = new ComboBox<String>();

        cboTopNote.getItems().addAll(noteList);
        cboTopNote.setValue(noteList.get(3));
        cboBottomNote.getItems().addAll(noteList);
        cboBottomNote.setValue(noteList.get(3));

        cboTopNote.setOnAction(e -> {
            int index = noteList.indexOf(cboTopNote.getValue());
            cboTopNoteOctave.getItems().clear();
            int a = noteList.indexOf("A");
            int b_flat = noteList.indexOf("A#/Bb");
            int b = noteList.indexOf("B");
            int c = noteList.indexOf("C");

            if ((index == a) || (index == b_flat) || (index == b)) {
                cboTopNoteOctave.getItems().addAll(A_Ab_B_OctaveList);
                cboTopNoteOctave.setValue(5);

            } else if (index == c) {
                cboTopNoteOctave.getItems().addAll(C_OctaveList);
                cboTopNoteOctave.setValue(5);
            } else {
                cboTopNoteOctave.getItems().addAll(defaultOctaveList);
                cboTopNoteOctave.setValue(4);
            }

        });

        cboBottomNote.setOnAction(e -> {
            int index = noteList.indexOf(cboBottomNote.getValue());
            cboBottomNoteOctave.getItems().clear();
            int a = noteList.indexOf("A");
            int b_flat = noteList.indexOf("A#/Bb");
            int b = noteList.indexOf("B");
            int c = noteList.indexOf("C");

            if ((index == a) || (index == b_flat) || (index == b)) {
                cboBottomNoteOctave.getItems().addAll(A_Ab_B_OctaveList);
                cboBottomNoteOctave.setValue(3);

            } else if (index == c) {
                cboBottomNoteOctave.getItems().addAll(C_OctaveList);
                cboBottomNoteOctave.setValue(3);
            } else {
                cboBottomNoteOctave.getItems().addAll(defaultOctaveList);
                cboBottomNoteOctave.setValue(2);
            }

        });

        cboTopNoteOctave = new ComboBox<Integer>();
        cboBottomNoteOctave = new ComboBox<Integer>();

        cboTopNoteOctave.getItems().addAll(C_OctaveList);
        cboTopNoteOctave.setValue(5);
        cboBottomNoteOctave.getItems().addAll(C_OctaveList);
        cboBottomNoteOctave.setValue(3);

        HBox topNoteBox = new HBox(5);
        topNoteBox.setAlignment(Pos.CENTER_LEFT);
        HBox bottomNoteBox = new HBox(5.5);
        bottomNoteBox.setAlignment(Pos.CENTER_LEFT);

        HBox topCboHBox = new HBox(5); // The ComboBoxes were off by one pixel, so this is a solution to that
        topCboHBox.setAlignment(Pos.CENTER_LEFT);
        topCboHBox.getChildren().addAll(cboTopNote, cboTopNoteOctave);
        HBox bottomCboHBox = new HBox(5);
        bottomCboHBox.setAlignment(Pos.CENTER_LEFT);
        bottomCboHBox.getChildren().addAll(cboBottomNote, cboBottomNoteOctave);

        topNoteBox.getChildren().addAll(new Label("Top Note:      "), topCboHBox);
        bottomNoteBox.getChildren().addAll(new Label("Bottom Note:"), bottomCboHBox);
        this.getChildren().addAll(new Label(partName + " Range:"), topNoteBox, bottomNoteBox);
    }
}
