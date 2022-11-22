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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PianoPane extends BorderPane {
    private KeyboardPane piano;
    private Slider sldVolume;
    private Receiver receiver;
    private Button btnExit;
    private VBox buttonsPanelTop;
    private VBox buttonsPanelBottom;
    private CheckBox chkDisplayNoteNames;
    private CheckBox chkDisplayKeyMapping;

    private Button btnUpOctaveMapped;
    private Button btnUpStepMapped;
    private Button btnDownStepMapped;
    private Button btnDownOctaveMapped;

    private Button btnUpOctaveDisplay;
    private Button btnUpStepDisplay;
    private Button btnDownStepDisplay;
    private Button btnDownOctaveDisplay;

    private Button btnAddNote;
    private Button btnRemoveNote;

    private ComboBox<String> cboInstruments;

    private static ObservableList<String> instruments = FXCollections.observableArrayList("Piano", "Harpsichord",
            "Music Box", "Church Organ", "Guitar", "Electric Bass", "Violin", "Trumpet", "Square", "Sawtooth");
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
            entry("Sawtooth", 81));

    public Slider getVolumeSlider() {
        return this.sldVolume;
    }

    public KeyboardPane getKeyboardPane() {
        return this.piano;
    }

    public Button getExitButton() {
        return btnExit;
    }

    public VBox getbuttonsPanelTop() {
        return buttonsPanelTop;
    }

    public VBox getbuttonsPanelBottom() {
        return buttonsPanelBottom;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public PianoPane(Receiver receiver) {
        super();
        this.receiver = receiver;
        piano = new KeyboardPane(receiver);

        Background buttonBackground = new Background(new BackgroundFill(Color.GRAY.brighter().brighter(),
                new CornerRadii(0.0), new Insets(0.0, 0.0, 0.0, 0.0)));
        Border buttonBorder = new Border(new BorderStroke(Color.GRAY.darker().darker(), BorderStrokeStyle.SOLID,
                new CornerRadii(0.0), new BorderWidths(1)));

        chkDisplayNoteNames = new CheckBox("Display Note Names");
        chkDisplayNoteNames.setTextFill(Color.WHITE);
        chkDisplayNoteNames.setAllowIndeterminate(false);
        chkDisplayNoteNames.setSelected(false);
        chkDisplayNoteNames.setOnMouseClicked(e -> {
            piano.setDisplayKeyLabels(chkDisplayNoteNames.isSelected());
        });

        chkDisplayKeyMapping = new CheckBox("Display Key Mapping");
        chkDisplayKeyMapping.setTextFill(Color.WHITE);
        chkDisplayKeyMapping.setAllowIndeterminate(false);
        chkDisplayKeyMapping.setSelected(false);
        chkDisplayKeyMapping.setOnMouseClicked(e -> {
            piano.setDisplayMappingLabels(chkDisplayKeyMapping.isSelected());
        });

        HBox hBoxVolume = new HBox(0);
        sldVolume = new Slider(0, 127, 100);
        sldVolume.setOrientation(Orientation.HORIZONTAL);
        sldVolume.setMaxWidth(110.0);
        PianoKey.getVolumeProperty().bind(sldVolume.valueProperty());

        Label lblVolume = new Label("Volume:");
        lblVolume.setTextFill(Color.WHITE);

        hBoxVolume.getChildren().addAll(lblVolume, sldVolume);

        VBox checkBoxPanel = new VBox(10);
        checkBoxPanel.getChildren().addAll(chkDisplayKeyMapping, chkDisplayNoteNames, hBoxVolume);

        VBox optionsPanel = new VBox(10);

        HBox hBoxUpOctave = new HBox(0);
        HBox hBoxUpStep = new HBox(0);
        HBox hBoxDownStep = new HBox(0);
        HBox hBoxDownOctave = new HBox(0);

        btnUpOctaveMapped = new Button("^^^");
        btnUpOctaveMapped.setBackground(buttonBackground);
        btnUpOctaveMapped.setBorder(buttonBorder);
        btnUpOctaveMapped.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnUpOctaveMapped.setMinWidth(75.0);
        btnUpOctaveMapped.setTextFill(Color.RED);
        btnUpOctaveMapped.setOnAction(e -> {
            piano.stepUpOctaveMapping();
        });

        btnUpStepMapped = new Button("^");
        btnUpStepMapped.setBackground(buttonBackground);
        btnUpStepMapped.setBorder(buttonBorder);
        btnUpStepMapped.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnUpStepMapped.setMinWidth(75.0);
        btnUpStepMapped.setTextFill(Color.RED);
        btnUpStepMapped.setOnAction(e -> {
            piano.stepUpNoteMapping();
        });

        btnDownStepMapped = new Button("v");
        btnDownStepMapped.setBackground(buttonBackground);
        btnDownStepMapped.setBorder(buttonBorder);
        btnDownStepMapped.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnDownStepMapped.setMinWidth(75.0);
        btnDownStepMapped.setTextFill(Color.RED);
        btnDownStepMapped.setOnAction(e -> {
            piano.stepDownNoteMapping();
        });

        btnDownOctaveMapped = new Button("v v v");
        btnDownOctaveMapped.setBackground(buttonBackground);
        btnDownOctaveMapped.setBorder(buttonBorder);
        btnDownOctaveMapped.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnDownOctaveMapped.setMinWidth(75.0);
        btnDownOctaveMapped.setTextFill(Color.RED);
        btnDownOctaveMapped.setOnAction(e -> {
            piano.stepDownOctaveMapping();
        });

        btnUpOctaveDisplay = new Button("^^^");
        btnUpOctaveDisplay.setBackground(buttonBackground);
        btnUpOctaveDisplay.setBorder(buttonBorder);
        btnUpOctaveDisplay.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnUpOctaveDisplay.setMinWidth(75.0);
        btnUpOctaveDisplay.setTextFill(Color.BLUE);
        btnUpOctaveDisplay.setOnAction(e -> {
            piano.stepUpOctaveDisplay();
        });

        btnUpStepDisplay = new Button("^");
        btnUpStepDisplay.setBackground(buttonBackground);
        btnUpStepDisplay.setBorder(buttonBorder);
        btnUpStepDisplay.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnUpStepDisplay.setMinWidth(75.0);
        btnUpStepDisplay.setTextFill(Color.BLUE);
        btnUpStepDisplay.setOnAction(e -> {
            piano.stepUpNoteDisplay();
        });

        btnDownStepDisplay = new Button("v");
        btnDownStepDisplay.setBackground(buttonBackground);
        btnDownStepDisplay.setBorder(buttonBorder);
        btnDownStepDisplay.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnDownStepDisplay.setMinWidth(75.0);
        btnDownStepDisplay.setTextFill(Color.BLUE);
        btnDownStepDisplay.setOnAction(e -> {
            piano.stepDownNoteDisplay();
        });

        btnDownOctaveDisplay = new Button("v v v");
        btnDownOctaveDisplay.setBackground(buttonBackground);
        btnDownOctaveDisplay.setBorder(buttonBorder);
        btnDownOctaveDisplay.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnDownOctaveDisplay.setMinWidth(75.0);
        btnDownOctaveDisplay.setTextFill(Color.BLUE);
        btnDownOctaveDisplay.setOnAction(e -> {
            piano.stepDownOctaveDisplay();
        });

        hBoxUpOctave.getChildren().addAll(btnUpOctaveMapped, btnUpOctaveDisplay);
        hBoxUpStep.getChildren().addAll(btnUpStepMapped, btnUpStepDisplay);
        hBoxDownStep.getChildren().addAll(btnDownStepMapped, btnDownStepDisplay);
        hBoxDownOctave.getChildren().addAll(btnDownOctaveMapped, btnDownOctaveDisplay);

        btnAddNote = new Button("Add Note");
        btnAddNote.setBackground(buttonBackground);
        btnAddNote.setBorder(buttonBorder);
        btnAddNote.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnAddNote.setMinWidth(150.0);
        btnAddNote.setOnAction(e -> {
            piano.addNote();
        });

        btnRemoveNote = new Button("Remove Note");
        btnRemoveNote.setBackground(buttonBackground);
        btnRemoveNote.setBorder(buttonBorder);
        btnRemoveNote.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnRemoveNote.setMinWidth(150.0);
        btnRemoveNote.setOnAction(e -> {
            piano.removeNote();
        });

        cboInstruments = new ComboBox<String>();
        cboInstruments.setBackground(buttonBackground);
        cboInstruments.setBorder(buttonBorder);
        cboInstruments.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
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

        btnExit = new Button("Exit");
        btnExit.setBackground(buttonBackground);
        btnExit.setBorder(buttonBorder);
        btnExit.minHeightProperty()
                .bind(this.heightProperty().subtract(20).subtract(checkBoxPanel.getHeight()).divide(10));
        btnExit.setMinWidth(150.0);

        buttonsPanelTop = new VBox(0);
        buttonsPanelTop.getChildren().addAll(hBoxUpOctave, hBoxUpStep, btnAddNote, btnRemoveNote, hBoxDownStep,
                hBoxDownOctave);
        buttonsPanelBottom = new VBox(0);
        buttonsPanelBottom.getChildren().addAll(cboInstruments, btnExit);

        optionsPanel.getChildren().addAll(buttonsPanelTop, checkBoxPanel, buttonsPanelBottom);
        optionsPanel.setAlignment(Pos.CENTER_LEFT);
        optionsPanel.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        optionsPanel.setBackground(new Background(
                new BackgroundFill(Color.GRAY.darker(), new CornerRadii(0.0), new Insets(0.0, 0.0, 0.0, 0.0))));
        this.setCenter(piano);
        this.setRight(optionsPanel);

    }

}
