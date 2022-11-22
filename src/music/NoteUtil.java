package music;

import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

import java.util.Arrays;

public final class NoteUtil {
    public static enum NotePosition {
        I, ii, II, iii, III, IV, v, V, vi, VI, vii, VII, VIII
    }

    public static enum AscendDescend {
        ASCEND, DESCEND
    }

    public static AscendDescend getAscendDescend(int prevNoteValue, int minValue, int maxValue, Random rand) {
        double midValue = (maxValue + minValue) / 2.0; // uses the middle to approximate a mean value
        double standardDeviation = (maxValue - midValue) / 3.0; // approximates that the max value is 3 standard
                                                                // deviations away from the middle value

        double randomNumber = rand.nextGaussian(midValue, standardDeviation); // gets a normally distributed random
                                                                              // number based upon the mean

        // the previous note value is likely beneath the mean, thus the next note should
        // be higher
        if (prevNoteValue < randomNumber) {
            return AscendDescend.ASCEND;
        }
        // the previous note value is likely above the mean, thus the next note should
        // be lower
        else {
            return AscendDescend.DESCEND;
        }

    }

    public static void normalizeWeights(double[] weights, int weightsSize) {
        double sum = 0;
        for (int i = 0; i < weightsSize; i++) {
            sum += weights[i];
        }
        if (sum == 0) {
            weights[0] = 1; // ensures that the sum is not zero so there is not a divide by zero error
            return;
        }
        for (int j = 0; j < weightsSize; j++) {
            weights[j] = weights[j] / sum;
        }

    }

    public static int getWeightedRandom(double[] weights, int weightsSize, Random rand) {
        double value = rand.nextDouble(); // gets a random double between 0 and 1

        double[] normalWeights = Arrays.copyOf(weights, weightsSize);
        normalizeWeights(normalWeights, weightsSize); // normalizes the weights so that they sum to 1

        double sum1 = 0;
        double sum2 = normalWeights[0];

        for (int i = 0; i < (weightsSize - 1); i++) {
            if ((sum1 <= value) && (value <= sum2)) {
                return i;
            }
            sum1 = sum2;
            sum2 += normalWeights[i + 1];
        }
        // if there hasn't been a value returned yet, then the random value must fall
        // under the last value's weight
        return (weightsSize - 1);
    }

    private static Map<NotePosition, Integer> notesAscendValueMap = Map.ofEntries(
            entry(NotePosition.I, 0),
            entry(NotePosition.ii, 1),
            entry(NotePosition.II, 2),
            entry(NotePosition.iii, 3),
            entry(NotePosition.III, 4),
            entry(NotePosition.IV, 5),
            entry(NotePosition.v, 6),
            entry(NotePosition.V, 7),
            entry(NotePosition.vi, 8),
            entry(NotePosition.VI, 9),
            entry(NotePosition.vii, 10),
            entry(NotePosition.VII, 11),
            entry(NotePosition.VIII, 12));

    public static Map<NotePosition, Integer> getNotesAscendValueMap() {
        return notesAscendValueMap;
    }

    private static Map<NotePosition, Integer> notesDescendValueMap = Map.ofEntries(
            entry(NotePosition.VIII, 0),
            entry(NotePosition.VII, -1),
            entry(NotePosition.vii, -2),
            entry(NotePosition.VI, -3),
            entry(NotePosition.vi, -4),
            entry(NotePosition.V, -5),
            entry(NotePosition.v, -6),
            entry(NotePosition.IV, -7),
            entry(NotePosition.III, -8),
            entry(NotePosition.iii, -9),
            entry(NotePosition.II, -10),
            entry(NotePosition.ii, -11),
            entry(NotePosition.I, -12));

    public static Map<NotePosition, Integer> getNotesDescendValueMap() {
        return notesDescendValueMap;
    }

    private static Map<Notes, Integer> noteMap = Map.ofEntries(
            entry(Notes.C, 0),
            entry(Notes.Db, 1),
            entry(Notes.D, 2),
            entry(Notes.Eb, 3),
            entry(Notes.E, 4),
            entry(Notes.F, 5),
            entry(Notes.Gb, 6),
            entry(Notes.G, 7),
            entry(Notes.Ab, 8),
            entry(Notes.A, 9),
            entry(Notes.Bb, 10),
            entry(Notes.B, 11));

    public static Map<Notes, Integer> getNoteMap() {
        return noteMap;
    }

    private static Map<Notes, Integer> scaleOffsetMap = Map.ofEntries(
            entry(Notes.C, 0),
            entry(Notes.Db, 1),
            entry(Notes.D, 2),
            entry(Notes.Eb, 3),
            entry(Notes.E, 4),
            entry(Notes.F, 5),
            entry(Notes.Gb, 6),
            entry(Notes.G, 7),
            entry(Notes.Ab, 8),
            entry(Notes.A, -3),
            entry(Notes.Bb, -2),
            entry(Notes.B, -1));

    public static Map<Notes, Integer> getScaleOffsetMap() {
        return scaleOffsetMap;
    }

    private static Map<NoteLengthType, Integer> noteTickLength64ResolutionMap = Map.ofEntries(
            entry(NoteLengthType.SIXTEENTH, 16),
            entry(NoteLengthType.EIGHTH, 32),
            entry(NoteLengthType.DOTTED_EIGHTH, 48),
            entry(NoteLengthType.QUARTER, 64),
            entry(NoteLengthType.DOTTED_QUARTER, 96),
            entry(NoteLengthType.HALF, 128),
            entry(NoteLengthType.DOTTED_HALF, 192),
            entry(NoteLengthType.WHOLE, 256));

    public static Map<NoteLengthType, Integer> getNoteTickLength64ResolutionMap() {
        return noteTickLength64ResolutionMap;
    }

    private static Map<NotePosition, Notes> notesStandardPositionToNameMap = Map.ofEntries(
            entry(NotePosition.I, Notes.C),
            entry(NotePosition.ii, Notes.Db),
            entry(NotePosition.II, Notes.D),
            entry(NotePosition.iii, Notes.Eb),
            entry(NotePosition.III, Notes.E),
            entry(NotePosition.IV, Notes.F),
            entry(NotePosition.v, Notes.Gb),
            entry(NotePosition.V, Notes.G),
            entry(NotePosition.vi, Notes.Ab),
            entry(NotePosition.VI, Notes.A),
            entry(NotePosition.vii, Notes.Bb),
            entry(NotePosition.VII, Notes.B),
            entry(NotePosition.VIII, Notes.C));

    public static Map<NotePosition, Notes> getNotesStandardPoisitionToNameMap() {
        return notesStandardPositionToNameMap;
    }

    private static Map<Notes, NotePosition> notesNameToStandardPositionMap = Map.ofEntries(
            entry(Notes.C, NotePosition.I),
            entry(Notes.Db, NotePosition.ii),
            entry(Notes.D, NotePosition.II),
            entry(Notes.Eb, NotePosition.iii),
            entry(Notes.E, NotePosition.III),
            entry(Notes.F, NotePosition.IV),
            entry(Notes.Gb, NotePosition.v),
            entry(Notes.G, NotePosition.V),
            entry(Notes.Ab, NotePosition.vi),
            entry(Notes.A, NotePosition.VI),
            entry(Notes.Bb, NotePosition.vii),
            entry(Notes.B, NotePosition.VII));

    public static Map<Notes, NotePosition> getNotesNameToStandardPositionMap() {
        return notesNameToStandardPositionMap;
    }

    // converts note name and octave to midi note value
    public static int getNoteValue(Notes note, int octave) {
        int value;
        value = noteMap.get(note); // note offset
        value += (octave * 12); // octave offset
        value += 12; // offset from midi value 0
        return value;
    }

    public static Notes calcNoteName(int noteValue) {
        int standardNoteValue = noteValue - 21;
        standardNoteValue %= 12;
        switch (standardNoteValue) {
            case 0:
                return Notes.A;
            case 1:
                return Notes.Bb;
            case 2:
                return Notes.B;
            case 3:
                return Notes.C;
            case 4:
                return Notes.Db;
            case 5:
                return Notes.D;
            case 6:
                return Notes.Eb;
            case 7:
                return Notes.E;
            case 8:
                return Notes.F;
            case 9:
                return Notes.Gb;
            case 10:
                return Notes.G;
            case 11:
                return Notes.Ab;
            default:
                return null;
        }
    }

}
