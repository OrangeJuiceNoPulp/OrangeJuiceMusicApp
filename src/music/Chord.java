package music;

import java.util.Arrays;
import java.util.Random;

import music.NoteUtil.NotePosition;

public class Chord {
    private NotePosition rootNote;
    private Chord7Type seventhType;
    private ChordType chordType;
    private double[] defaultChordWeights;

    private void initializeChordWeights() {
        defaultChordWeights = new double[12];

        defaultChordWeights[0] = 9;

        switch (chordType) {
            case MAJOR:
                defaultChordWeights[4] = 5;
                defaultChordWeights[7] = 7;
                break;
            case MINOR:
                defaultChordWeights[3] = 5;
                defaultChordWeights[7] = 7;
                break;
            case DIMINISHED:
                defaultChordWeights[3] = 5;
                defaultChordWeights[6] = 6;
                break;
            case AUGMENTED:
                defaultChordWeights[4] = 5;
                defaultChordWeights[8] = 6;
                break;
        }

        switch (seventhType) {
            case M7:
                defaultChordWeights[11] = 3;
                break;
            case m7:
                defaultChordWeights[10] = 3;
                break;
            case dim7:
                defaultChordWeights[9] = 3;
                break;
            case NONE:
                break;
        }
    }

    public NotePosition getRootNote() {
        return rootNote;
    }

    public Chord(NotePosition rootNote, ChordType chordType, Chord7Type seventhType) {
        this.rootNote = rootNote;
        this.chordType = chordType;
        this.seventhType = seventhType;
        initializeChordWeights();
    }

    public static Chord getRandomChord(ScaleType scale, Random rand) {
        int nextChord = rand.nextInt(7); // There are 7 root notes for chords for each scale type included in this
                                         // program, each will have equal probability of appearing here
        NotePosition newRoot;
        ChordType newChordType;
        Chord7Type newSeventhType;

        switch (scale) {
            case MAJOR:
                switch (nextChord) {
                    case 0:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 1:
                        newRoot = NotePosition.II;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 2:
                        newRoot = NotePosition.III;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 3:
                        newRoot = NotePosition.IV;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 4:
                        newRoot = NotePosition.V;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 5:
                        newRoot = NotePosition.VI;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 6:
                        newRoot = NotePosition.VII;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.m7;
                        break;
                    default:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                }
                break;
            case NATURAL_MINOR:
                switch (nextChord) {
                    case 0:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 1:
                        newRoot = NotePosition.II;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 2:
                        newRoot = NotePosition.iii;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 3:
                        newRoot = NotePosition.IV;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 4:
                        newRoot = NotePosition.V;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 5:
                        newRoot = NotePosition.vi;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 6:
                        newRoot = NotePosition.vii;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    default:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                }
                break;
            case HARMONIC_MINOR:
                switch (nextChord) {
                    case 0:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 1:
                        newRoot = NotePosition.II;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 2:
                        newRoot = NotePosition.iii;
                        newChordType = ChordType.AUGMENTED;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 3:
                        newRoot = NotePosition.IV;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 4:
                        newRoot = NotePosition.V;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 5:
                        newRoot = NotePosition.vi;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 6:
                        newRoot = NotePosition.VII;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.dim7;
                        break;
                    default:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                }
                break;
            case MELODIC_MINOR:
                switch (nextChord) {
                    case 0:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 1:
                        newRoot = NotePosition.II;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 2:
                        newRoot = NotePosition.iii;
                        newChordType = ChordType.AUGMENTED;
                        newSeventhType = Chord7Type.M7;
                        break;
                    case 3:
                        newRoot = NotePosition.IV;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 4:
                        newRoot = NotePosition.V;
                        newChordType = ChordType.MAJOR;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 5:
                        newRoot = NotePosition.VI;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.m7;
                        break;
                    case 6:
                        newRoot = NotePosition.VII;
                        newChordType = ChordType.DIMINISHED;
                        newSeventhType = Chord7Type.m7;
                        break;
                    default:
                        newRoot = NotePosition.I;
                        newChordType = ChordType.MINOR;
                        newSeventhType = Chord7Type.M7;
                        break;
                }
                break;
            default:
                newRoot = NotePosition.I;
                newChordType = ChordType.MINOR;
                newSeventhType = Chord7Type.M7;
                break;
        }

        // 75% of the time, the chord will not be a 7th chord
        if (rand.nextInt(4) != 0) {
            newSeventhType = Chord7Type.NONE;
        }

        Chord newChord = new Chord(newRoot, newChordType, newSeventhType);
        return newChord;
    }

    public double[] getNextNoteChordWeights(NotePosition prevNote) {
        double[] tempArray = new double[24];

        for (int i = 0; i < 24; i++) {
            tempArray[((i + NoteUtil.getNotesAscendValueMap().get(this.rootNote)) % 24)] = this.defaultChordWeights[(i
                    % 12)];
        }

        double[] chordWeights = new double[13];

        for (int j = 0; j < 13; j++) {
            chordWeights[j] = tempArray[((j + NoteUtil.getNotesAscendValueMap().get(prevNote)) % 24)];
        }
        return Arrays.copyOf(chordWeights, 13);
    }
}
