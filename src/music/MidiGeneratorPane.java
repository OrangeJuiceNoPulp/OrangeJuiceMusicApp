package music;

import java.io.File;
import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MidiGeneratorPane extends BorderPane {
    private static ObservableList<String> scalesList = FXCollections.observableArrayList("Major", "Natural Minor",
            "Harmonic Minor", "Melodic Minor");
    private static ObservableList<String> noteList = FXCollections.observableArrayList("A", "A#/Bb", "B", "C", "C#/Db",
            "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab");
    private ComboBox<String> cboScaleType;
    private ComboBox<String> cboRootNote;
    private Button btnExit;
    private Sequencer sequencer;
    private ScaleWeightsPane majorWeights;
    private ScaleWeightsPane naturalWeights;
    private ScaleWeightsPane melodicWeights;
    private ScaleWeightsPane harmonicWeights;
    private TimeWeightsPane timeWeights;

    private RangePane melodyRange;
    private RangePane bassRange;

    private TextField txtRandomSeed;

    private Button btnSave;
    private Button btnGen;
    private Button btnPlay;
    private Button btnStop;

    private HBox buttonBox;
    private HBox seedSaveBox;
    private VBox bottomPanel;
    private VBox harmonicMelodicBiasBox;
    private VBox scaleChordBiasBox;
    private VBox scaleBox;

    private Slider sldHarmonicMelodicBias;
    private Slider sldScaleChordBias;

    private CheckBox chkGenMelody;
    private CheckBox chkGenBass;

    private VBox leftOptionsBox;

    private Song currentSong;
    private Sequence currentSequence;

    private void playMidi() {
        try {

            if (!sequencer.isOpen()) {
                sequencer.open();
            }
            sequencer.setSequence(currentSequence);
            sequencer.start();
        } catch (Exception ex) {

        }
    }

    private void pauseMidi() {
        try {
            sequencer.stop();
        } catch (Exception ex) {

        }
    }

    private void stopMidi() {
        try {
            sequencer.stop();
            sequencer.setTickPosition(0);
        } catch (Exception ex) {

        }
    }

    private ArrayList<ArrayList<Double>> getAscendScaleWeights(ScaleType scale) {
        ArrayList<ArrayList<Double>> ascendWeights = new ArrayList<ArrayList<Double>>();
        switch (scale) {
            case MAJOR:
                for (NoteWeightsPane weights : majorWeights.getAscendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    ascendWeights.add(noteWeights);
                }
                break;
            case NATURAL_MINOR:
                for (NoteWeightsPane weights : naturalWeights.getAscendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    ascendWeights.add(noteWeights);
                }
                break;
            case HARMONIC_MINOR:
                for (NoteWeightsPane weights : harmonicWeights.getAscendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    ascendWeights.add(noteWeights);
                }
                break;
            case MELODIC_MINOR:
                for (NoteWeightsPane weights : melodicWeights.getAscendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    ascendWeights.add(noteWeights);
                }
                break;
            default:
                break;
        }
        return ascendWeights;
    }

    private ArrayList<ArrayList<Double>> getDescendScaleWeights(ScaleType scale) {
        ArrayList<ArrayList<Double>> descendWeights = new ArrayList<ArrayList<Double>>();
        switch (scale) {
            case MAJOR:
                for (NoteWeightsPane weights : majorWeights.getDescendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    descendWeights.add(noteWeights);
                }
                break;
            case NATURAL_MINOR:
                for (NoteWeightsPane weights : naturalWeights.getDescendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    descendWeights.add(noteWeights);
                }
                break;
            case HARMONIC_MINOR:
                for (NoteWeightsPane weights : harmonicWeights.getDescendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    descendWeights.add(noteWeights);
                }
                break;
            case MELODIC_MINOR:
                for (NoteWeightsPane weights : melodicWeights.getDescendingWeights()) {
                    ArrayList<Double> noteWeights = new ArrayList<Double>();
                    for (Spinner<Double> spinner : weights.getSpinnerWeights()) {
                        try {
                            noteWeights.add(spinner.getValue());
                        } catch (Exception ex) {
                            noteWeights.add(0.0);
                        }
                    }
                    descendWeights.add(noteWeights);
                }
                break;
            default:
                break;
        }
        return descendWeights;
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public Button getExitButton() {
        return btnExit;
    }

    public MidiGeneratorPane() {
        super();

        btnExit = new Button("Exit");
        btnExit.setMinWidth(219);
        btnExit.setMinHeight(50);

        btnPlay = new Button("Play");
        btnPlay.setMinWidth(219);
        btnPlay.setMinHeight(50);

        btnStop = new Button("Stop");
        btnStop.setMinWidth(219);
        btnStop.setMinHeight(50);

        btnGen = new Button("Generate Midi");
        btnGen.setMinWidth(219);
        btnGen.setMinHeight(50);

        btnSave = new Button("Save Midi");
        btnSave.setMinWidth(219);

        txtRandomSeed = new TextField();
        txtRandomSeed.setMinWidth(567.5);

        majorWeights = new ScaleWeightsPane(ScaleType.MAJOR);
        naturalWeights = new ScaleWeightsPane(ScaleType.NATURAL_MINOR);
        harmonicWeights = new ScaleWeightsPane(ScaleType.HARMONIC_MINOR);
        melodicWeights = new ScaleWeightsPane(ScaleType.MELODIC_MINOR);

        timeWeights = new TimeWeightsPane();

        cboScaleType = new ComboBox<String>(scalesList);
        cboScaleType.setMinWidth(153);
        cboScaleType.setValue(scalesList.get(0));
        cboScaleType.setOnAction(e -> {
            switch (cboScaleType.getValue()) {
                case "Major":
                    this.setCenter(majorWeights);
                    break;
                case "Natural Minor":
                    this.setCenter(naturalWeights);
                    break;
                case "Harmonic Minor":
                    this.setCenter(harmonicWeights);
                    break;
                case "Melodic Minor":
                    this.setCenter(melodicWeights);
                    break;
                default:
                    break;
            }
        });

        cboRootNote = new ComboBox<String>();
        cboRootNote.setMinWidth(153);
        cboRootNote.getItems().addAll(noteList);
        cboRootNote.setValue(noteList.get(3));

        HBox scaleTypeBox = new HBox(3);
        scaleTypeBox.setAlignment(Pos.CENTER_LEFT);
        scaleTypeBox.getChildren().addAll(new Label("Scale Type:"), cboScaleType);

        HBox scaleRootBox = new HBox(5);
        scaleRootBox.setAlignment(Pos.CENTER_LEFT);
        scaleRootBox.getChildren().addAll(new Label("Root Note:"), cboRootNote);

        chkGenMelody = new CheckBox("Generate Melody Part");
        chkGenMelody.setIndeterminate(false);
        chkGenMelody.setSelected(true);

        chkGenBass = new CheckBox("Generate Bass Part");
        chkGenBass.setIndeterminate(false);
        chkGenBass.setSelected(true);

        chkGenMelody.setOnAction(e -> {
            if (!(chkGenBass.isSelected() || chkGenMelody.isSelected())) {
                chkGenMelody.setSelected(true);
            }
        });
        chkGenBass.setOnAction(e -> {
            if (!(chkGenBass.isSelected() || chkGenMelody.isSelected())) {
                chkGenMelody.setSelected(true);
            }
        });

        scaleBox = new VBox(5);
        scaleBox.setPadding(new Insets(5));
        scaleBox.setStyle("-fx-border-color: black;");
        scaleBox.getChildren().addAll(scaleTypeBox, scaleRootBox, chkGenMelody, chkGenBass);

        sldHarmonicMelodicBias = new Slider(0.0, 100.0, 50.0);
        HBox harmonicMelodicLabelsBox = new HBox(135);
        harmonicMelodicLabelsBox.getChildren().addAll(new Label("Original"), new Label("Natural"));

        harmonicMelodicBiasBox = new VBox(5);
        harmonicMelodicBiasBox.setPadding(new Insets(5));
        harmonicMelodicBiasBox.setStyle("-fx-border-color: black;");
        harmonicMelodicBiasBox.getChildren().addAll(new Label("Harmonic/Melodic Bias:"), sldHarmonicMelodicBias,
                harmonicMelodicLabelsBox);

        sldScaleChordBias = new Slider(0.0, 100.0, 25.0);
        HBox scaleChordLabelsBox = new HBox(155);
        scaleChordLabelsBox.getChildren().addAll(new Label("Scale"), new Label("Chord"));

        scaleChordBiasBox = new VBox(5);
        scaleChordBiasBox.setPadding(new Insets(5));
        scaleChordBiasBox.setStyle("-fx-border-color: black;");
        scaleChordBiasBox.getChildren().addAll(new Label("Note Bias:"), sldScaleChordBias, scaleChordLabelsBox);

        melodyRange = new RangePane("Melody");
        bassRange = new RangePane("Bass");

        leftOptionsBox = new VBox();
        leftOptionsBox.getChildren().addAll(scaleBox, melodyRange, bassRange, harmonicMelodicBiasBox,
                scaleChordBiasBox);

        btnPlay.setVisible(false);
        btnStop.setVisible(false);
        btnSave.setVisible(false);
        btnGen.setOnAction(e -> {
            try {
                try {
                    sequencer.stop();
                    sequencer.close();
                } catch (Exception ex) {
                }
                sequencer = MidiSystem.getSequencer();

                Notes melodyRangeTopNote = melodyRange.getTopNote();
                Notes melodyRangeBottomNote = melodyRange.getBottomNote();
                int melodyRangeTopOctave = melodyRange.getTopNoteOctave();
                int melodyRangeBottomOctave = melodyRange.getBottomNoteOctave();

                Notes bassRangeTopNote = bassRange.getTopNote();
                Notes bassRangeBottomNote = bassRange.getBottomNote();
                int bassRangeTopOctave = bassRange.getTopNoteOctave();
                int bassRangeBottomOctave = bassRange.getBottomNoteOctave();

                Notes scaleRoot;
                String note = cboRootNote.getValue();
                switch (note) {
                    case "A":
                        scaleRoot = Notes.A;
                        break;
                    case "A#/Bb":
                        scaleRoot = Notes.Bb;
                        break;
                    case "B":
                        scaleRoot = Notes.B;
                        break;
                    case "C":
                        scaleRoot = Notes.C;
                        break;
                    case "C#/Db":
                        scaleRoot = Notes.Db;
                        break;
                    case "D":
                        scaleRoot = Notes.D;
                        break;
                    case "D#/Eb":
                        scaleRoot = Notes.Eb;
                        break;
                    case "E":
                        scaleRoot = Notes.E;
                        break;
                    case "F":
                        scaleRoot = Notes.F;
                        break;
                    case "F#/Gb":
                        scaleRoot = Notes.Gb;
                        break;
                    case "G":
                        scaleRoot = Notes.G;
                        break;
                    case "G#/Ab":
                        scaleRoot = Notes.Ab;
                        break;
                    default:
                        scaleRoot = Notes.C;
                        break;
                }

                int melodyRangeMaxValue = (NoteUtil.getNoteValue(melodyRangeTopNote, melodyRangeTopOctave)
                        - NoteUtil.getScaleOffsetMap().get(scaleRoot));
                int melodyRangeMinValue = (NoteUtil.getNoteValue(melodyRangeBottomNote, melodyRangeBottomOctave)
                        - NoteUtil.getScaleOffsetMap().get(scaleRoot));

                // ensures that the max range value is greater than the min range value
                if (melodyRangeMaxValue < melodyRangeMinValue) {
                    int temp = melodyRangeMaxValue;
                    melodyRangeMaxValue = melodyRangeMinValue;
                    melodyRangeMinValue = temp;
                }

                int bassRangeMaxValue = (NoteUtil.getNoteValue(bassRangeTopNote, bassRangeTopOctave)
                        - NoteUtil.getScaleOffsetMap().get(scaleRoot));
                int bassRangeMinValue = (NoteUtil.getNoteValue(bassRangeBottomNote, bassRangeBottomOctave)
                        - NoteUtil.getScaleOffsetMap().get(scaleRoot));

                // ensures that the max range value is greater than the min range value
                if (bassRangeMaxValue < bassRangeMinValue) {
                    int temp = bassRangeMaxValue;
                    bassRangeMaxValue = bassRangeMinValue;
                    bassRangeMinValue = temp;
                }

                ArrayList<ArrayList<ArrayList<Double>>> ascendingNoteWeights = new ArrayList<ArrayList<ArrayList<Double>>>();
                ArrayList<ArrayList<ArrayList<Double>>> descendingNoteWeights = new ArrayList<ArrayList<ArrayList<Double>>>();
                ScaleType scale;

                switch (cboScaleType.getValue()) {
                    case "Major":
                        scale = ScaleType.MAJOR;
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.MAJOR));
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.MAJOR));

                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.MAJOR));
                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.MAJOR));
                        break;
                    case "Natural Minor":
                        scale = ScaleType.NATURAL_MINOR;
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.NATURAL_MINOR));
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.NATURAL_MINOR));

                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.NATURAL_MINOR));
                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.NATURAL_MINOR));
                        break;
                    case "Harmonic Minor":
                        scale = ScaleType.HARMONIC_MINOR;
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.NATURAL_MINOR));
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.HARMONIC_MINOR));

                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.NATURAL_MINOR));
                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.HARMONIC_MINOR));
                        break;
                    case "Melodic Minor":
                        scale = ScaleType.MELODIC_MINOR;
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.NATURAL_MINOR));
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.MELODIC_MINOR));

                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.NATURAL_MINOR));
                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.MELODIC_MINOR));
                        break;
                    default:
                        scale = ScaleType.MAJOR;
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.MAJOR));
                        ascendingNoteWeights.add(getAscendScaleWeights(ScaleType.MAJOR));

                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.MAJOR));
                        descendingNoteWeights.add(getDescendScaleWeights(ScaleType.MAJOR));
                        break;
                }

                ArrayList<Double> rhythmWeights = timeWeights.getRhythmWeights();
                TimeSignature timeSig = timeWeights.getTimeSignature();

                double BPM = timeWeights.getTempo();
                int numMeasures = timeWeights.getNumMeasures();

                double restBias = timeWeights.getRestBias();
                double chordBias = sldScaleChordBias.getValue();
                double harmonicMelodicBias = sldHarmonicMelodicBias.getValue();

                boolean hasMelodyPart = chkGenMelody.isSelected();
                boolean hasBassPart = chkGenBass.isSelected();

                currentSong = new Song(BPM, numMeasures, ascendingNoteWeights, descendingNoteWeights, rhythmWeights,
                        melodyRangeMaxValue, melodyRangeMinValue, bassRangeMaxValue, bassRangeMinValue, scale,
                        scaleRoot, timeSig, hasMelodyPart, hasBassPart, restBias, chordBias, harmonicMelodicBias);
                try {
                    long seed = Long.parseLong(txtRandomSeed.getText());
                    currentSong.setRandomSeed(seed);
                } catch (NumberFormatException ex) {
                }
                currentSong.generateSong();

                currentSequence = currentSong.convertToSequence();

                btnPlay.setText("Play");
                btnPlay.setVisible(true);
                btnStop.setVisible(true);
                btnSave.setVisible(true);
            } catch (Exception ex) {
            }
        });

        btnPlay.setOnAction(e -> {
            if (btnPlay.getText().compareTo("Play") == 0) {
                btnPlay.setText("Pause");
                playMidi();
            } else {
                btnPlay.setText("Play");
                pauseMidi();
            }
        });

        btnStop.setOnAction(e -> {
            btnPlay.setText("Play");
            stopMidi();
        });

        btnSave.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Midi File");
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Midi File (*.mid)", "*.mid"));
            File midiFile = fileChooser.showSaveDialog(null);
            try {
                MidiSystem.write(currentSong.convertToSequence(), 1, midiFile);
            } catch (Exception ex) {
            }
        });

        buttonBox = new HBox();
        buttonBox.setStyle("-fx-border-color: black;");
        buttonBox.getChildren().addAll(btnGen, btnPlay, btnStop, btnExit);

        seedSaveBox = new HBox(0);
        seedSaveBox.setPadding(new Insets(5, 0, 5, 0));
        seedSaveBox.setAlignment(Pos.CENTER_LEFT);
        seedSaveBox.setStyle("-fx-border-color: black;");
        seedSaveBox.getChildren().addAll(new Label("  Random Seed:  "), txtRandomSeed, btnSave);

        bottomPanel = new VBox();
        bottomPanel.getChildren().addAll(seedSaveBox, buttonBox);

        this.setLeft(leftOptionsBox);
        this.setCenter(majorWeights);
        this.setRight(timeWeights);
        this.setBottom(bottomPanel);
    }

}
