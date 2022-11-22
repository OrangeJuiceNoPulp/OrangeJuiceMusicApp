package music;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import music.NoteUtil.AscendDescend;
import music.NoteUtil.NotePosition;

public class ScaleWeightsPane extends BorderPane {
    private static ObservableList<String> initNoteList = FXCollections.observableArrayList("I", "ii", "II", "iii",
            "III", "IV", "v", "V", "vi", "VI", "vii", "VII");

    private NoteWeightsPane I_WeightsAscend;
    private NoteWeightsPane ii_WeightsAscend;
    private NoteWeightsPane II_WeightsAscend;
    private NoteWeightsPane iii_WeightsAscend;
    private NoteWeightsPane III_WeightsAscend;
    private NoteWeightsPane IV_WeightsAscend;
    private NoteWeightsPane v_WeightsAscend;
    private NoteWeightsPane V_WeightsAscend;
    private NoteWeightsPane vi_WeightsAscend;
    private NoteWeightsPane VI_WeightsAscend;
    private NoteWeightsPane vii_WeightsAscend;
    private NoteWeightsPane VII_WeightsAscend;
    private NoteWeightsPane I_WeightsDescend;
    private NoteWeightsPane ii_WeightsDescend;
    private NoteWeightsPane II_WeightsDescend;
    private NoteWeightsPane iii_WeightsDescend;
    private NoteWeightsPane III_WeightsDescend;
    private NoteWeightsPane IV_WeightsDescend;
    private NoteWeightsPane v_WeightsDescend;
    private NoteWeightsPane V_WeightsDescend;
    private NoteWeightsPane vi_WeightsDescend;
    private NoteWeightsPane VI_WeightsDescend;
    private NoteWeightsPane vii_WeightsDescend;
    private NoteWeightsPane VII_WeightsDescend;

    private ArrayList<NoteWeightsPane> ascendingWeights;
    private ArrayList<NoteWeightsPane> descendingWeights;
    private ComboBox<String> cboInitialNote;

    public ArrayList<NoteWeightsPane> getAscendingWeights() {
        return ascendingWeights;
    }

    public ArrayList<NoteWeightsPane> getDescendingWeights() {
        return descendingWeights;
    }

    public ScaleWeightsPane(ScaleType scale) {
        super();

        setStyle("-fx-border-color: black;");
        setPadding(new Insets(5));

        I_WeightsAscend = new NoteWeightsPane(scale, NotePosition.I, AscendDescend.ASCEND);
        ii_WeightsAscend = new NoteWeightsPane(scale, NotePosition.ii, AscendDescend.ASCEND);
        II_WeightsAscend = new NoteWeightsPane(scale, NotePosition.II, AscendDescend.ASCEND);
        iii_WeightsAscend = new NoteWeightsPane(scale, NotePosition.iii, AscendDescend.ASCEND);
        III_WeightsAscend = new NoteWeightsPane(scale, NotePosition.III, AscendDescend.ASCEND);
        IV_WeightsAscend = new NoteWeightsPane(scale, NotePosition.IV, AscendDescend.ASCEND);
        v_WeightsAscend = new NoteWeightsPane(scale, NotePosition.v, AscendDescend.ASCEND);
        V_WeightsAscend = new NoteWeightsPane(scale, NotePosition.V, AscendDescend.ASCEND);
        vi_WeightsAscend = new NoteWeightsPane(scale, NotePosition.vi, AscendDescend.ASCEND);
        VI_WeightsAscend = new NoteWeightsPane(scale, NotePosition.VI, AscendDescend.ASCEND);
        vii_WeightsAscend = new NoteWeightsPane(scale, NotePosition.vii, AscendDescend.ASCEND);
        VII_WeightsAscend = new NoteWeightsPane(scale, NotePosition.VII, AscendDescend.ASCEND);
        I_WeightsDescend = new NoteWeightsPane(scale, NotePosition.I, AscendDescend.DESCEND);
        ii_WeightsDescend = new NoteWeightsPane(scale, NotePosition.ii, AscendDescend.DESCEND);
        II_WeightsDescend = new NoteWeightsPane(scale, NotePosition.II, AscendDescend.DESCEND);
        iii_WeightsDescend = new NoteWeightsPane(scale, NotePosition.iii, AscendDescend.DESCEND);
        III_WeightsDescend = new NoteWeightsPane(scale, NotePosition.III, AscendDescend.DESCEND);
        IV_WeightsDescend = new NoteWeightsPane(scale, NotePosition.IV, AscendDescend.DESCEND);
        v_WeightsDescend = new NoteWeightsPane(scale, NotePosition.v, AscendDescend.DESCEND);
        V_WeightsDescend = new NoteWeightsPane(scale, NotePosition.V, AscendDescend.DESCEND);
        vi_WeightsDescend = new NoteWeightsPane(scale, NotePosition.vi, AscendDescend.DESCEND);
        VI_WeightsDescend = new NoteWeightsPane(scale, NotePosition.VI, AscendDescend.DESCEND);
        vii_WeightsDescend = new NoteWeightsPane(scale, NotePosition.vii, AscendDescend.DESCEND);
        VII_WeightsDescend = new NoteWeightsPane(scale, NotePosition.VII, AscendDescend.DESCEND);

        ascendingWeights = new ArrayList<>();
        ascendingWeights.add(I_WeightsAscend);
        ascendingWeights.add(ii_WeightsAscend);
        ascendingWeights.add(II_WeightsAscend);
        ascendingWeights.add(iii_WeightsAscend);
        ascendingWeights.add(III_WeightsAscend);
        ascendingWeights.add(IV_WeightsAscend);
        ascendingWeights.add(v_WeightsAscend);
        ascendingWeights.add(V_WeightsAscend);
        ascendingWeights.add(vi_WeightsAscend);
        ascendingWeights.add(VI_WeightsAscend);
        ascendingWeights.add(vii_WeightsAscend);
        ascendingWeights.add(VII_WeightsAscend);

        descendingWeights = new ArrayList<>();
        descendingWeights.add(I_WeightsDescend);
        descendingWeights.add(ii_WeightsDescend);
        descendingWeights.add(II_WeightsDescend);
        descendingWeights.add(iii_WeightsDescend);
        descendingWeights.add(III_WeightsDescend);
        descendingWeights.add(IV_WeightsDescend);
        descendingWeights.add(v_WeightsDescend);
        descendingWeights.add(V_WeightsDescend);
        descendingWeights.add(vi_WeightsDescend);
        descendingWeights.add(VI_WeightsDescend);
        descendingWeights.add(vii_WeightsDescend);
        descendingWeights.add(VII_WeightsDescend);

        cboInitialNote = new ComboBox<String>(initNoteList);
        cboInitialNote.setMinWidth(300.0);
        cboInitialNote.setValue(initNoteList.get(0));
        cboInitialNote.setOnAction(e -> {
            switch (cboInitialNote.getValue()) {
                case "I":
                    this.setLeft(I_WeightsAscend);
                    this.setRight(I_WeightsDescend);
                    break;
                case "ii":
                    this.setLeft(ii_WeightsAscend);
                    this.setRight(ii_WeightsDescend);
                    break;
                case "II":
                    this.setLeft(II_WeightsAscend);
                    this.setRight(II_WeightsDescend);
                    break;
                case "iii":
                    this.setLeft(iii_WeightsAscend);
                    this.setRight(iii_WeightsDescend);
                    break;
                case "III":
                    this.setLeft(III_WeightsAscend);
                    this.setRight(III_WeightsDescend);
                    break;
                case "IV":
                    this.setLeft(IV_WeightsAscend);
                    this.setRight(IV_WeightsDescend);
                    break;
                case "v":
                    this.setLeft(v_WeightsAscend);
                    this.setRight(v_WeightsDescend);
                    break;
                case "V":
                    this.setLeft(V_WeightsAscend);
                    this.setRight(V_WeightsDescend);
                    break;
                case "vi":
                    this.setLeft(vi_WeightsAscend);
                    this.setRight(vi_WeightsDescend);
                    break;
                case "VI":
                    this.setLeft(VI_WeightsAscend);
                    this.setRight(VI_WeightsDescend);
                    break;
                case "vii":
                    this.setLeft(vii_WeightsAscend);
                    this.setRight(vii_WeightsDescend);
                    break;
                case "VII":
                    this.setLeft(VII_WeightsAscend);
                    this.setRight(VII_WeightsDescend);
                    break;
                default:
                    break;
            }

        });

        VBox topPane = new VBox();
        HBox initialNotePane = new HBox(10);
        initialNotePane.setAlignment(Pos.CENTER_LEFT);
        initialNotePane.getChildren().addAll(new Label("Starting Note:"), cboInitialNote);
        topPane.getChildren().addAll(initialNotePane, new Label("Next Note Weights:"));

        this.setTop(topPane);
        setAlignment(cboInitialNote, Pos.CENTER);
        this.setLeft(I_WeightsAscend);
        this.setRight(I_WeightsDescend);
    }

}