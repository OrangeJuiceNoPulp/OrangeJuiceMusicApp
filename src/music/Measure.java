package music;

import java.util.ArrayList;
import java.util.Random;

public class Measure {
    private static int resolution = 64;

    private TimeSignature timeSig;
    private Chord chord;
    private ArrayList<Note> notes;

    private Note getNonRestNote(int index) {
        // if the note is not a rest
        if (!(notes.get(index).getIsRest())) {
            return notes.get(index);
        }
        // if all the notes in the measures are rests, return null
        if (index == 0) {
            return null;
        }
        // recursively gets the previous note until a nonrest note is found
        return getNonRestNote(index - 1);
    }

    public Note getLastNonRestNote() {
        return getNonRestNote(notes.size() - 1);
    }

    public ArrayList<Note> getNoteList() {
        return this.notes;
    }

    public Chord getChord() {
        return this.chord;
    }

    private void fillMeasure(Note prevNote, int minValue, int maxValue, double restBias, double chordBias,
            double harmonicMelodicBias, ArrayList<ArrayList<ArrayList<Double>>> ascendingWeights,
            ArrayList<ArrayList<ArrayList<Double>>> descendingWeights, ArrayList<Double> rhythmWeights, Random rand) {
        int remainingTicks = timeSig.getTopNumber() * resolution; // This line will have to be adjusted if the program
                                                                  // supports time signatures like 6/8 in the future
        Note lastNonRestNote = prevNote;

        while (remainingTicks > 0) {
            boolean nextNoteIsRest;
            double restRandomValue = rand.nextDouble();
            if (restRandomValue <= (restBias / 100.0)) {
                nextNoteIsRest = true;
            } else {
                nextNoteIsRest = false;
            }
            int nextNoteValue = Note.getNextNoteValue(lastNonRestNote, minValue, maxValue, chordBias,
                    harmonicMelodicBias, chord, ascendingWeights, descendingWeights, rand);

            NoteLengthType nextNoteLengthType = Note.getNextNoteLengthType(remainingTicks, rhythmWeights, rand);
            if (nextNoteLengthType == null) {
                break;
            }

            Note nextNote = new Note(nextNoteValue, nextNoteLengthType, nextNoteIsRest);
            this.notes.add(nextNote);

            remainingTicks -= nextNote.getNoteLengthValue();
            if (!(nextNote.getIsRest())) {
                lastNonRestNote = nextNote;
            }
        }
    }

    public Measure(Note prevNote, TimeSignature timeSig, Chord chord, int minValue, int maxValue, double restBias,
            double chordBias,
            double harmonicMelodicBias, ArrayList<ArrayList<ArrayList<Double>>> ascendingWeights,
            ArrayList<ArrayList<ArrayList<Double>>> descendingWeights, ArrayList<Double> rhythmWeights, Random rand) {
        this.chord = chord;
        this.timeSig = timeSig;
        this.notes = new ArrayList<Note>();
        fillMeasure(prevNote, minValue, maxValue, restBias, chordBias, harmonicMelodicBias, ascendingWeights,
                descendingWeights, rhythmWeights, rand);
    }

}
