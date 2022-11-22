package music;

import java.util.Random;

import music.NoteUtil.AscendDescend;
import music.NoteUtil.NotePosition;

import java.util.ArrayList;

public class Note {

    private static final int OFFSET = 12;

    private Notes standardNoteName; // the note name of this note if the scale root note was C

    private int noteValue;
    private NotePosition notePosition;

    private int noteLengthValue;
    private NoteLengthType noteLengthType;

    private boolean isRest;

    public boolean getIsRest() {
        return isRest;
    }

    public static int getOffset() {
        return OFFSET;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public int getNoteLengthValue() {
        return noteLengthValue;
    }

    public NotePosition getNotePosition() {
        return notePosition;
    }

    public NoteLengthType getNoteLengthType() {
        return noteLengthType;
    }

    public static NoteLengthType getNextNoteLengthType(int remainingTicks, ArrayList<Double> rhythmWeights,
            Random rand) {
        NoteLengthType nextNote;
        double p1 = rhythmWeights.get(0); // whole note probability
        double p2 = rhythmWeights.get(1); // dotted half note probability
        double p3 = rhythmWeights.get(2); // half note probability
        double p4 = rhythmWeights.get(3); // dotted quarter note probability
        double p5 = rhythmWeights.get(4); // quarter note probability
        double p6 = rhythmWeights.get(5); // eighth note probability
        double p7 = rhythmWeights.get(6); // dotted eighth note probability
        double p8 = rhythmWeights.get(7); // sixteenth note probability

        if (remainingTicks == 0) {
            return nextNote = null;
        }
        if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.WHOLE)) {
            double[] weights = { p1, p2, p3, p4, p5, p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 8, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.WHOLE;
                    break;
                case 1:
                    nextNote = NoteLengthType.DOTTED_HALF;
                    break;
                case 2:
                    nextNote = NoteLengthType.HALF;
                    break;
                case 3:
                    nextNote = NoteLengthType.DOTTED_QUARTER;
                    break;
                case 4:
                    nextNote = NoteLengthType.QUARTER;
                    break;
                case 5:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 6:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 7:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.DOTTED_HALF)) {
            double[] weights = { p2, p3, p4, p5, p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 7, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.DOTTED_HALF;
                    break;
                case 1:
                    nextNote = NoteLengthType.HALF;
                    break;
                case 2:
                    nextNote = NoteLengthType.DOTTED_QUARTER;
                    break;
                case 3:
                    nextNote = NoteLengthType.QUARTER;
                    break;
                case 4:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 5:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 6:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.HALF)) {
            double[] weights = { p3, p4, p5, p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 6, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.HALF;
                    break;
                case 1:
                    nextNote = NoteLengthType.DOTTED_QUARTER;
                    break;
                case 2:
                    nextNote = NoteLengthType.QUARTER;
                    break;
                case 3:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 4:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 5:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.DOTTED_QUARTER)) {
            double[] weights = { p4, p5, p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 5, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.DOTTED_QUARTER;
                    break;
                case 1:
                    nextNote = NoteLengthType.QUARTER;
                    break;
                case 2:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 3:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 4:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.QUARTER)) {
            double[] weights = { p5, p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 4, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.QUARTER;
                    break;
                case 1:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 2:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 3:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.DOTTED_EIGHTH)) {
            double[] weights = { p6, p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 3, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.DOTTED_EIGHTH;
                    break;
                case 1:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 2:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else if (remainingTicks >= NoteUtil.getNoteTickLength64ResolutionMap().get(NoteLengthType.EIGHTH)) {
            double[] weights = { p7, p8 };
            int value = NoteUtil.getWeightedRandom(weights, 2, rand);
            switch (value) {
                case 0:
                    nextNote = NoteLengthType.EIGHTH;
                    break;
                case 1:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
                default:
                    nextNote = NoteLengthType.SIXTEENTH;
                    break;
            }
        } else {
            nextNote = NoteLengthType.SIXTEENTH;
        }

        return nextNote;
    }

    public static int getNextNoteValue(Note prevNote, int minValue, int maxValue, double chordBias,
            double harmonicMelodicBias, Chord chord, ArrayList<ArrayList<ArrayList<Double>>> ascendingWeights,
            ArrayList<ArrayList<ArrayList<Double>>> descendingWeights, Random rand) {
        int nextNoteValue;

        double[] noteWeights = new double[13];

        AscendDescend ascendDescend = NoteUtil.getAscendDescend(prevNote.getNoteValue(), minValue, maxValue, rand);
        double noteWeightsTypeRandomValue = rand.nextDouble();
        if (ascendDescend == AscendDescend.ASCEND) {
            if (noteWeightsTypeRandomValue <= (harmonicMelodicBias / 100.0)) {
                ArrayList<Double> ascendingNoteWeights = ascendingWeights.get(0)
                        .get(NoteUtil.getNotesAscendValueMap().get(prevNote.getNotePosition()));
                for (int i = 0; i < 13; i++) {
                    noteWeights[i] = ascendingNoteWeights.get(i);
                }
            } else {
                ArrayList<Double> ascendingNoteWeights = ascendingWeights.get(1)
                        .get(NoteUtil.getNotesAscendValueMap().get(prevNote.getNotePosition()));
                for (int i = 0; i < 13; i++) {
                    noteWeights[i] = ascendingNoteWeights.get(i);
                }
            }
        } else {
            if (noteWeightsTypeRandomValue <= (harmonicMelodicBias / 100.0)) {
                ArrayList<Double> descendingNoteWeights = descendingWeights.get(0)
                        .get(NoteUtil.getNotesAscendValueMap().get(prevNote.getNotePosition()));
                for (int i = 0; i < 13; i++) {
                    noteWeights[i] = descendingNoteWeights.get(i);
                }
            } else {
                ArrayList<Double> descendingNoteWeights = descendingWeights.get(1)
                        .get(NoteUtil.getNotesAscendValueMap().get(prevNote.getNotePosition()));
                for (int i = 0; i < 13; i++) {
                    noteWeights[i] = descendingNoteWeights.get(i);
                }
            }
        }

        double[] chordWeights = chord.getNextNoteChordWeights(prevNote.getNotePosition());

        NoteUtil.normalizeWeights(noteWeights, 13);
        NoteUtil.normalizeWeights(chordWeights, 13);

        double[] finalWeights = new double[13];
        for (int j = 0; j < 13; j++) {
            finalWeights[j] = ((chordBias / 100.0) * chordWeights[j]) + ((1.0 - (chordBias / 100.0)) * noteWeights[j]);
        }

        int index = NoteUtil.getWeightedRandom(finalWeights, 13, rand);

        if (ascendDescend == AscendDescend.ASCEND) {
            nextNoteValue = prevNote.getNoteValue() + index; // The next note ascends based upon the position of the
                                                             // previous note
        } else {
            nextNoteValue = prevNote.getNoteValue() + (index - 12); // The next note descends based upon the position of
                                                                    // the previous note
        }

        if (nextNoteValue < minValue) {
            nextNoteValue += 12; // raises the value by an octave if the next note falls out of the range
        }
        if (nextNoteValue > maxValue) {
            nextNoteValue -= 12; // lowers the value by an octave if the next note falls out of the range
        }

        return nextNoteValue;
    }

    protected Note(int noteValue) {

        this.noteValue = noteValue;

        this.standardNoteName = NoteUtil.calcNoteName(noteValue);
        this.notePosition = NoteUtil.getNotesNameToStandardPositionMap().get(standardNoteName);
        this.isRest = false;
        this.noteLengthValue = 0;
        this.noteLengthType = null;
    }

    public Note(int noteValue, NoteLengthType noteLength, boolean rest) {

        this.isRest = rest;
        this.noteValue = noteValue;
        this.noteLengthType = noteLength;
        this.noteLengthValue = NoteUtil.getNoteTickLength64ResolutionMap().get(noteLength);
        this.standardNoteName = NoteUtil.calcNoteName(noteValue);
        this.notePosition = NoteUtil.getNotesNameToStandardPositionMap().get(standardNoteName);
    }
}
