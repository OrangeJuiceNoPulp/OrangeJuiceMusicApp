package piano;

import java.util.Map;
import static java.util.Map.entry;

import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PianoPane extends BorderPane {
    public KeyboardPane piano;
    private Receiver receiver;
    private static ObservableList<String> instruments = FXCollections.observableArrayList("Piano", "Harpsichord", "Music Box", "Church Organ", "Guitar", "Electric Bass", "Violin", "Trumpet", "Square", "Sawtooth");
    private static Map<String, Integer> instrumentMap = Map.ofEntries(
        entry("Piano", 1),
        entry("Harpsichord", 6),
        entry("Music Box", 10),
        entry("Church Organ", 19),
        entry("Guitar", 25),
        entry("Electric Bass", 33),
        entry("Violin", 41),
        entry("Trumpet", 56),
        entry("Square", 80),
        entry("Sawtooth", 81)
    );
    private ComboBox<String> cboInstruments;

    public PianoPane(Receiver receiver) {
        super();
        this.receiver = receiver;
        piano = new KeyboardPane(receiver);

        VBox buttonsPanel = new VBox(10);

        HBox hBoxUpOctave = new HBox(0);
        HBox hBoxUpStep = new HBox(0);
        HBox hBoxDownStep = new HBox(0);
        HBox hBoxDownOctave = new HBox(0);

        Button btnUpOctaveMapped = new Button("^^^");
        btnUpOctaveMapped.setMinWidth(75.0);
        btnUpOctaveMapped.setTextFill(Color.RED);
        btnUpOctaveMapped.setOnAction(e -> {
            piano.stepUpOctaveMapping();
        });
        Button btnUpStepMapped = new Button("^");
        btnUpStepMapped.setMinWidth(75.0);
        btnUpStepMapped.setTextFill(Color.RED);
        btnUpStepMapped.setOnAction(e -> {
            piano.stepUpNoteMapping();
        });
        Button btnDownStepMapped = new Button("v");
        btnDownStepMapped.setMinWidth(75.0);
        btnDownStepMapped.setTextFill(Color.RED);
        btnDownStepMapped.setOnAction(e -> {
            piano.stepDownNoteMapping();
        });
        Button btnDownOctaveMapped = new Button("v v v");
        btnDownOctaveMapped.setMinWidth(75.0);
        btnDownOctaveMapped.setTextFill(Color.RED);
        btnDownOctaveMapped.setOnAction(e -> {
            piano.stepDownOctaveMapping();
        });

        Button btnUpOctaveDisplay = new Button("^^^");
        btnUpOctaveDisplay.setMinWidth(75.0);
        btnUpOctaveDisplay.setTextFill(Color.BLUE);
        btnUpOctaveDisplay.setOnAction(e -> {
            piano.stepUpOctaveDisplay();
        });
        Button btnUpStepDisplay = new Button("^");
        btnUpStepDisplay.setMinWidth(75.0);
        btnUpStepDisplay.setTextFill(Color.BLUE);
        btnUpStepDisplay.setOnAction(e -> {
            piano.stepUpNoteDisplay();
        });
        Button btnDownStepDisplay = new Button("v");
        btnDownStepDisplay.setMinWidth(75.0);
        btnDownStepDisplay.setTextFill(Color.BLUE);
        btnDownStepDisplay.setOnAction(e -> {
            piano.stepDownNoteDisplay();
        });
        Button btnDownOctaveDisplay = new Button("v v v");
        btnDownOctaveDisplay.setMinWidth(75.0);
        btnDownOctaveDisplay.setTextFill(Color.BLUE);
        btnDownOctaveDisplay.setOnAction(e -> {
            piano.stepDownOctaveDisplay();
        });

        hBoxUpOctave.getChildren().addAll(btnUpOctaveMapped, btnUpOctaveDisplay);
        hBoxUpStep.getChildren().addAll(btnUpStepMapped, btnUpStepDisplay);
        hBoxDownStep.getChildren().addAll(btnDownStepMapped, btnDownStepDisplay);
        hBoxDownOctave.getChildren().addAll(btnDownOctaveMapped, btnDownOctaveDisplay);





        Button btnAddNote = new Button("Add Note");
        btnAddNote.setMinWidth(150.0);
        btnAddNote.setOnAction(e -> {
            piano.addNote();
        });
        Button btnRemoveNote = new Button("Remove Note");
        btnRemoveNote.setMinWidth(150.0);
        btnRemoveNote.setOnAction(e -> {
            piano.removeNote();
        });



        CheckBox chkDisplayNoteNames = new CheckBox("Display Note Names: ");
        chkDisplayNoteNames.setAllowIndeterminate(false);
        chkDisplayNoteNames.setSelected(false);
        chkDisplayNoteNames.setOnMouseClicked(e -> {
            piano.setDisplayKeyLabels(chkDisplayNoteNames.isSelected());
        });

        CheckBox chkDisplayKeyMapping = new CheckBox("Display Key Mapping: ");
        chkDisplayKeyMapping.setAllowIndeterminate(false);
        chkDisplayKeyMapping.setSelected(false);
        chkDisplayKeyMapping.setOnMouseClicked(e -> {
            piano.setDisplayMappingLabels(chkDisplayKeyMapping.isSelected());
        });

        HBox hBoxVolume = new HBox(0);
        Slider sldVolume = new Slider(0, 127, 100);
        sldVolume.setOrientation(Orientation.HORIZONTAL);
        sldVolume.setMaxWidth(110.0);
        sldVolume.valueProperty().addListener(ov -> {
            piano.setVolume( (int) sldVolume.getValue());
        });
        hBoxVolume.getChildren().addAll(new Label("Volume:"), sldVolume);

        cboInstruments = new ComboBox<String>();
        cboInstruments.setMinWidth(150.0);
        cboInstruments.getItems().addAll(instruments);
        cboInstruments.setValue(instruments.get(0));
        cboInstruments.setOnAction(e -> {

            try {
                
                ShortMessage a = new ShortMessage();
                a.setMessage(192, 1, instrumentMap.get(cboInstruments.getValue()), 100);
    
                receiver.send(a, -1); // -1 means that midi gets to the event as soon as it can
    
            } catch (Exception ex) {
            }

        });


        buttonsPanel.getChildren().addAll(hBoxUpOctave, hBoxUpStep, btnAddNote, btnRemoveNote, hBoxDownStep, hBoxDownOctave, chkDisplayKeyMapping, chkDisplayNoteNames, hBoxVolume, cboInstruments);
        buttonsPanel.setAlignment(Pos.CENTER_LEFT);
        buttonsPanel.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));
        buttonsPanel.setBackground( new Background(new BackgroundFill( Color.GRAY, new CornerRadii(0.0), new Insets(0.0, 0.0, 0.0, 0.0))));
        this.setCenter(piano);
        this.setRight(buttonsPanel);


    }

}
