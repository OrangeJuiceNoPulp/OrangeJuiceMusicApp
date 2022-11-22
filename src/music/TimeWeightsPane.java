package music;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TimeWeightsPane extends BorderPane {
    private static ObservableList<String> timeSignatureTopList = FXCollections.observableArrayList("2", "3", "4", "5");

    private ComboBox<String> cboTimeSignatureTop;
    private HBox timeSignatureBox;
    private HBox tempoBox;
    private VBox restBox;
    private HBox measuresBox;

    private VBox topBox;
    private VBox centerBox;

    private GridPane spinnersPane;

    private Spinner<Integer> spnNumMeasures;

    private Spinner<Double> wholeWeight;
    private Spinner<Double> dotHalfWeight;
    private Spinner<Double> halfWeight;
    private Spinner<Double> dotQuarterWeight;
    private Spinner<Double> quarterWeight;
    private Spinner<Double> dotEighthWeight;
    private Spinner<Double> eighthWeight;
    private Spinner<Double> sixteenthWeight;

    private Spinner<Double> spnTempo;

    private Slider sldRest;

    private ArrayList<Spinner<Double>> spinnerWeights;

    public ArrayList<Double> getRhythmWeights() {
        ArrayList<Double> rhythmWeights = new ArrayList<Double>();
        for (Spinner<Double> spinner : spinnerWeights) {
            double value = 0;
            try {
                value = spinner.getValue();
            } catch (Exception ex) {
            }
            rhythmWeights.add(value);
        }
        return rhythmWeights;
    }

    public double getTempo() {
        try {
            return spnTempo.getValue();
        } catch (Exception ex) {
            return 120.0;
        }
    }

    public int getNumMeasures() {
        try {
            return spnNumMeasures.getValue();
        } catch (Exception ex) {
            return 64;
        }
    }

    public double getRestBias() {
        return sldRest.getValue();
    }

    public TimeSignature getTimeSignature() {
        int topNumber;
        int bottomNumber = 4;
        switch (cboTimeSignatureTop.getValue()) {
            case "2":
                topNumber = 2;
                break;
            case "3":
                topNumber = 3;
                break;
            case "4":
                topNumber = 4;
                break;
            case "5":
                topNumber = 5;
                break;
            default:
                topNumber = 4;
                break;
        }
        TimeSignature newTimeSig = new TimeSignature(topNumber, bottomNumber);
        return newTimeSig;
    }

    public TimeWeightsPane() {
        super();

        spnNumMeasures = new Spinner<Integer>(1, 1024, 64, 1);
        spnNumMeasures.setEditable(true);

        wholeWeight = new Spinner<Double>(0.0, 100.0, 1.0, 1.0);
        wholeWeight.setEditable(true);
        dotHalfWeight = new Spinner<Double>(0.0, 100.0, 2.0, 1.0);
        dotHalfWeight.setEditable(true);
        halfWeight = new Spinner<Double>(0.0, 100.0, 5.0, 1.0);
        halfWeight.setEditable(true);
        dotQuarterWeight = new Spinner<Double>(0.0, 100.0, 10.0, 1.0);
        dotQuarterWeight.setEditable(true);
        quarterWeight = new Spinner<Double>(0.0, 100.0, 30.0, 1.0);
        quarterWeight.setEditable(true);
        dotEighthWeight = new Spinner<Double>(0.0, 100.0, 5.0, 1.0);
        dotEighthWeight.setEditable(true);
        eighthWeight = new Spinner<Double>(0.0, 100.0, 32.0, 1.0);
        eighthWeight.setEditable(true);
        sixteenthWeight = new Spinner<Double>(0.0, 100.0, 15.0, 1.0);
        sixteenthWeight.setEditable(true);

        spinnerWeights = new ArrayList<Spinner<Double>>();
        spinnerWeights.add(wholeWeight);
        spinnerWeights.add(dotHalfWeight);
        spinnerWeights.add(halfWeight);
        spinnerWeights.add(dotQuarterWeight);
        spinnerWeights.add(quarterWeight);
        spinnerWeights.add(dotEighthWeight);
        spinnerWeights.add(eighthWeight);
        spinnerWeights.add(sixteenthWeight);

        spinnersPane = new GridPane();

        spinnersPane.add(new Label("Whole  "), 0, 0);
        spinnersPane.add(new Label("Dotted Half  "), 0, 1);
        spinnersPane.add(new Label("Half  "), 0, 2);
        spinnersPane.add(new Label("Dotted Quarter  "), 0, 3);
        spinnersPane.add(new Label("Quarter  "), 0, 4);
        spinnersPane.add(new Label("Dotted Eighth  "), 0, 5);
        spinnersPane.add(new Label("Eighth  "), 0, 6);
        spinnersPane.add(new Label("Sixteenth  "), 0, 7);

        spinnersPane.add(wholeWeight, 1, 0);
        spinnersPane.add(dotHalfWeight, 1, 1);
        spinnersPane.add(halfWeight, 1, 2);
        spinnersPane.add(dotQuarterWeight, 1, 3);
        spinnersPane.add(quarterWeight, 1, 4);
        spinnersPane.add(dotEighthWeight, 1, 5);
        spinnersPane.add(eighthWeight, 1, 6);
        spinnersPane.add(sixteenthWeight, 1, 7);

        for (Node node : spinnersPane.getChildren()) {
            GridPane.setHalignment(node, HPos.RIGHT);
        }

        cboTimeSignatureTop = new ComboBox<String>(timeSignatureTopList);
        cboTimeSignatureTop.setValue(timeSignatureTopList.get(2));

        spnTempo = new Spinner<Double>(60.0, 240.0, 120.0, 1.0);
        spnTempo.setEditable(true);

        sldRest = new Slider(0.0, 100.0, 2.5);

        tempoBox = new HBox(5);
        tempoBox.setAlignment(Pos.CENTER_RIGHT);
        tempoBox.getChildren().addAll(new Label("Tempo in BPM:"), spnTempo);

        restBox = new VBox();
        HBox restLabels = new HBox(178);
        restLabels.getChildren().addAll(new Label(" Note"), new Label("Rest"));
        restBox.getChildren().addAll(new Label(" Rest Weight:"), sldRest, restLabels);

        timeSignatureBox = new HBox(5);
        timeSignatureBox.setAlignment(Pos.CENTER_LEFT);
        timeSignatureBox.getChildren().addAll(new Label("Time Signature:"), cboTimeSignatureTop, new Label("/ 4"));

        measuresBox = new HBox(5);
        measuresBox.setAlignment(Pos.CENTER_RIGHT);
        measuresBox.getChildren().addAll(new Label("Measures:"), spnNumMeasures);

        topBox = new VBox();
        topBox.getChildren().addAll(timeSignatureBox, new Label("Note Length Weights:"), new Label("        Note Type"),
                spinnersPane);

        centerBox = new VBox(10);
        centerBox.getChildren().addAll(restBox, tempoBox, measuresBox);

        topBox.setStyle("-fx-border-color: black;");
        topBox.setPadding(new Insets(5));

        centerBox.setStyle("-fx-border-color: black;");
        centerBox.setPadding(new Insets(5));

        this.setTop(topBox);

        this.setCenter(centerBox);

    }
}
