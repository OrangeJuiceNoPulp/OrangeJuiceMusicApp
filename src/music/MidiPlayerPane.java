package music;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MidiPlayerPane extends BorderPane {
    private Button btnExit;
    private Button btnPlayMidi;
    private Button btnStopMidi;
    private Button btnLoadMidi;
    private Sequencer sequencer;
    private Sequence sequence;
    private Text txtFileName;
    private ImageView logo;

    public Button getExitButton() {
        return btnExit;
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    private void loadMidi() {
        try {
            sequencer.stop();
            sequencer.close();
        } catch (Exception ex) {
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Midi File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Midi File (*.mid)", "*.mid"));
        File midiFile = fileChooser.showOpenDialog(null);

        if (midiFile != null) {
            try {
                sequencer = MidiSystem.getSequencer();
                sequence = MidiSystem.getSequence(midiFile);
                txtFileName.setFill(Color.BLACK);
                txtFileName.setText(midiFile.getName());
                btnPlayMidi.setVisible(true);
                btnStopMidi.setVisible(true);

            } catch (Exception ex) {
                txtFileName.setFill(Color.RED);
                txtFileName.setText("Failed to load .mid file");
            }
        }

    }

    private void playMidi() {
        try {

            if (!sequencer.isOpen()) {
                sequencer.open();
            }
            sequencer.setSequence(sequence);
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

    public MidiPlayerPane() {
        super();

        HBox buttonBox = new HBox();

        btnExit = new Button("Exit");
        btnExit.setMinWidth(150);
        btnExit.setMinHeight(50);

        btnLoadMidi = new Button("Load Midi");
        btnLoadMidi.setMinWidth(150);
        btnLoadMidi.setMinHeight(50);

        btnPlayMidi = new Button("Play");
        btnPlayMidi.setMinWidth(150);
        btnPlayMidi.setMinHeight(50);

        btnStopMidi = new Button("Stop");
        btnStopMidi.setMinWidth(150);
        btnStopMidi.setMinHeight(50);

        btnLoadMidi.setOnAction(e -> {
            btnPlayMidi.setText("Play");
            loadMidi();
        });

        HBox songNameBox = new HBox();
        Text txtCurrentFile = new Text("Current File: ");
        txtCurrentFile.setFont(new Font(20));
        txtFileName = new Text("");
        txtFileName.setFont(new Font(20));
        songNameBox.getChildren().addAll(txtCurrentFile, txtFileName);

        buttonBox.getChildren().addAll(btnLoadMidi, btnPlayMidi, btnStopMidi, btnExit);
        this.setBottom(buttonBox);

        btnPlayMidi.setOnAction(e -> {
            if (btnPlayMidi.getText().compareTo("Play") == 0) {
                btnPlayMidi.setText("Pause");
                playMidi();
            } else {
                btnPlayMidi.setText("Play");
                pauseMidi();
            }
        });

        btnStopMidi.setOnAction(e -> {
            btnPlayMidi.setText("Play");
            stopMidi();
        });

        btnPlayMidi.setVisible(false);
        btnStopMidi.setVisible(false);

        this.setTop(songNameBox);

        try {
            logo = new ImageView(new Image(this.getClass().getResourceAsStream("/music/resources/LogoV3.png")));
            this.setCenter(logo);
        } catch (Exception ex) {
        }

    }
}
