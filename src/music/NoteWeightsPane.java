package music;

import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import music.NoteUtil.AscendDescend;
import music.NoteUtil.NotePosition;

public class NoteWeightsPane extends GridPane {
    private Spinner<Double> I_Weight;
    private Spinner<Double> ii_Weight;
    private Spinner<Double> II_Weight;
    private Spinner<Double> iii_Weight;
    private Spinner<Double> III_Weight;
    private Spinner<Double> IV_Weight;
    private Spinner<Double> v_Weight;
    private Spinner<Double> V_Weight;
    private Spinner<Double> vi_Weight;
    private Spinner<Double> VI_Weight;
    private Spinner<Double> vii_Weight;
    private Spinner<Double> VII_Weight;
    private Spinner<Double> VIII_Weight;

    private ArrayList<Spinner<Double>> spinnerWeights;

    private Label lblAscendDescend;

    public ArrayList<Spinner<Double>> getSpinnerWeights() {
        return spinnerWeights;
    }

    public NoteWeightsPane(ScaleType scale, NotePosition initNote, AscendDescend direction) {
        super();
        I_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.I, direction), 1.0);
        I_Weight.setEditable(true);
        ii_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.ii, direction), 1.0);
        ii_Weight.setEditable(true);
        II_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.II, direction), 1.0);
        II_Weight.setEditable(true);
        iii_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.iii, direction), 1.0);
        iii_Weight.setEditable(true);
        III_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.III, direction), 1.0);
        III_Weight.setEditable(true);
        IV_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.IV, direction), 1.0);
        IV_Weight.setEditable(true);
        v_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.v, direction), 1.0);
        v_Weight.setEditable(true);
        V_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.V, direction), 1.0);
        V_Weight.setEditable(true);
        vi_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.vi, direction), 1.0);
        vi_Weight.setEditable(true);
        VI_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.VI, direction), 1.0);
        VI_Weight.setEditable(true);
        vii_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.vii, direction), 1.0);
        vii_Weight.setEditable(true);
        VII_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.VII, direction), 1.0);
        VII_Weight.setEditable(true);
        VIII_Weight = new Spinner<Double>(0.0, 100.0,
                DefaultNoteWeights.getDefaultNoteWeight(scale, initNote, NotePosition.VIII, direction), 1.0);
        VIII_Weight.setEditable(true);

        spinnerWeights = new ArrayList<Spinner<Double>>();
        spinnerWeights.add(I_Weight);
        spinnerWeights.add(ii_Weight);
        spinnerWeights.add(II_Weight);
        spinnerWeights.add(iii_Weight);
        spinnerWeights.add(III_Weight);
        spinnerWeights.add(IV_Weight);
        spinnerWeights.add(v_Weight);
        spinnerWeights.add(V_Weight);
        spinnerWeights.add(vi_Weight);
        spinnerWeights.add(VI_Weight);
        spinnerWeights.add(vii_Weight);
        spinnerWeights.add(VII_Weight);
        spinnerWeights.add(VIII_Weight);

        switch (direction) {
            case ASCEND:
                lblAscendDescend = new Label("Ascend");

                this.add(lblAscendDescend, 0, 0);

                this.add(new Label("I*"), 0, 1);
                this.add(new Label("ii"), 0, 2);
                this.add(new Label("II"), 0, 3);
                this.add(new Label("iii"), 0, 4);
                this.add(new Label("III"), 0, 5);
                this.add(new Label("IV"), 0, 6);
                this.add(new Label("v"), 0, 7);
                this.add(new Label("V"), 0, 8);
                this.add(new Label("vi"), 0, 9);
                this.add(new Label("VI"), 0, 10);
                this.add(new Label("vii"), 0, 11);
                this.add(new Label("VII"), 0, 12);
                this.add(new Label("VIII"), 0, 13);

                this.add(I_Weight, 1, 1);
                this.add(ii_Weight, 1, 2);
                this.add(II_Weight, 1, 3);
                this.add(iii_Weight, 1, 4);
                this.add(III_Weight, 1, 5);
                this.add(IV_Weight, 1, 6);
                this.add(v_Weight, 1, 7);
                this.add(V_Weight, 1, 8);
                this.add(vi_Weight, 1, 9);
                this.add(VI_Weight, 1, 10);
                this.add(vii_Weight, 1, 11);
                this.add(VII_Weight, 1, 12);
                this.add(VIII_Weight, 1, 13);
                break;
            case DESCEND:
                lblAscendDescend = new Label("Descend");

                this.add(lblAscendDescend, 0, 0);

                this.add(new Label("I"), 0, 13);
                this.add(new Label("ii"), 0, 12);
                this.add(new Label("II"), 0, 11);
                this.add(new Label("iii"), 0, 10);
                this.add(new Label("III"), 0, 9);
                this.add(new Label("IV"), 0, 8);
                this.add(new Label("v"), 0, 7);
                this.add(new Label("V"), 0, 6);
                this.add(new Label("vi"), 0, 5);
                this.add(new Label("VI"), 0, 4);
                this.add(new Label("vii"), 0, 3);
                this.add(new Label("VII"), 0, 2);
                this.add(new Label("VIII*"), 0, 1);

                this.add(I_Weight, 1, 13);
                this.add(ii_Weight, 1, 12);
                this.add(II_Weight, 1, 11);
                this.add(iii_Weight, 1, 10);
                this.add(III_Weight, 1, 9);
                this.add(IV_Weight, 1, 8);
                this.add(v_Weight, 1, 7);
                this.add(V_Weight, 1, 6);
                this.add(vi_Weight, 1, 5);
                this.add(VI_Weight, 1, 4);
                this.add(vii_Weight, 1, 3);
                this.add(VII_Weight, 1, 2);
                this.add(VIII_Weight, 1, 1);
        }

        for (Node node : this.getChildren()) {
            setHalignment(node, HPos.CENTER);
        }

    }
}