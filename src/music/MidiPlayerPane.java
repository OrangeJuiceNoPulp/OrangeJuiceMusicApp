package music;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Timer timer;
    private TimerTask timerTask;
    private Text txtTimer;
    private long timerTimeMilliseconds = 0;
    private final int TIMER_MILLISECOND_UPDATE = 10;
    private long timerUpdateCounter = 0;
    private boolean timerIsPaused = true;
    private Button btnExit;
    private Button btnPlayMidi;
    private Button btnStopMidi;
    private Button btnLoadMidi;
    private Sequencer sequencer;
    private Sequence sequence;
    private Label lblFileName;
    private ImageView logo;

    public void closeTimer() {
        timer.cancel();
    }

    public Button getExitButton() {
        return btnExit;
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    private void loadMidi() {
        try {
            stopMidi();
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
                lblFileName.setTextFill(Color.BLACK);
                lblFileName.setText(midiFile.getName());
                btnPlayMidi.setVisible(true);
                btnStopMidi.setVisible(true);

            } catch (Exception ex) {
                lblFileName.setTextFill(Color.RED);
                lblFileName.setText("Failed to load .mid file");
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
            timerIsPaused = false;
        } catch (Exception ex) {

        }
    }

    private void pauseMidi() {
        try {
            sequencer.stop();
            timerIsPaused = true;
        } catch (Exception ex) {

        }
    }

    private void stopMidi() {
        try {
            sequencer.stop();
            timerIsPaused = true;
            timerTimeMilliseconds = 0;
            txtTimer.setText("00:00:00");
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

        BorderPane topPane = new BorderPane();
        HBox songNameBox = new HBox();
        Text txtCurrentFile = new Text("Current File: ");
        txtCurrentFile.setFont(new Font(20));
        lblFileName = new Label("");
        lblFileName.setFont(new Font(20));
        lblFileName.setMaxWidth(400);
        songNameBox.getChildren().addAll(txtCurrentFile, lblFileName);

        txtTimer = new Text("00:00:00");
        txtTimer.setFont(new Font(20));

        topPane.setLeft(songNameBox);
        topPane.setRight(txtTimer);


        timerTask = new TimerTask() {
            public void run() {
                if (!timerIsPaused) {
                    //keeps track of how many milliseconds have passed since the timer was paused according to the update resolution
                    timerTimeMilliseconds += TIMER_MILLISECOND_UPDATE;
                    timerUpdateCounter++;
                    //The update resolution could have been one second, but that would lead to less precision in keeping track of the song time in the event of the song being paused

                    //Updates the timer display every 1 second
                    if ((timerUpdateCounter % (1000 / TIMER_MILLISECOND_UPDATE)) == 0) {
                        int seconds = (int) ((timerTimeMilliseconds / 1000) % 60);
                        int minutes = (int) (((timerTimeMilliseconds / 1000) / 60) % 60);
                        int hours = (int) ((((timerTimeMilliseconds / 1000) / 60) / 60) % 24); //The timer will go back to 00:00:00 once a day has passed
                        //Ideally, the song won't be running for over a day
                        String timerString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                        txtTimer.setText(timerString);
                    }
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0,  TIMER_MILLISECOND_UPDATE);

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

        this.setTop(topPane);

        try {
            logo = new ImageView(new Image(this.getClass().getResourceAsStream("/music/resources/LogoV3.png")));
            this.setCenter(logo);
        } catch (Exception ex) {
        }

    }
}
