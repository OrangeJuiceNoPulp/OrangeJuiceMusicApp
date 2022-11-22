package music;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Song {
    private static int resolution = 64;

    private double BPM;
    private int numMeasures;

    private int melodyRangeMinValue;
    private int melodyRangeMaxValue;

    private int bassRangeMinValue;
    private int bassRangeMaxValue;

    private ArrayList<ArrayList<ArrayList<Double>>> ascendingNoteWeights;
    private ArrayList<ArrayList<ArrayList<Double>>> descendingNoteWeights;
    private ArrayList<Double> rhythmWeights;
    private ScaleType scale;

    private Random rand;

    private LinkedList<Measure> melodyMeasures;
    private LinkedList<Measure> bassMeasures;

    private Notes scaleRootNote;
    private TimeSignature timeSig;

    private boolean hasMelodyPart;
    private boolean hasBassPart;

    private double restBias;
    private double chordBias;
    private double harmonicMelodicBias;

    public void generateSong() {
        Chord prevChord = Chord.getRandomChord(scale, rand);
        Note prevMelodyNote = new Note((int) ((melodyRangeMaxValue + melodyRangeMinValue) / 2.0));
        Note prevBassNote = new Note((int) ((bassRangeMaxValue + bassRangeMinValue) / 2.0));

        for (int i = 0; i < numMeasures; i++) {
            Chord chord = Chord.getRandomChord(scale, rand);
            if (chord.getRootNote() == prevChord.getRootNote()) {
                chord = Chord.getRandomChord(scale, rand); // decreases the chances of the same chord appearing twice in
                                                           // a row
            }

            Measure nextMelodyMeasure = new Measure(prevMelodyNote, timeSig, chord, melodyRangeMinValue,
                    melodyRangeMaxValue, restBias, chordBias, harmonicMelodicBias, ascendingNoteWeights,
                    descendingNoteWeights, rhythmWeights, rand);
            Measure nextBassMeasure = new Measure(prevBassNote, timeSig, chord, bassRangeMinValue, bassRangeMaxValue,
                    restBias, chordBias, harmonicMelodicBias, ascendingNoteWeights, descendingNoteWeights,
                    rhythmWeights, rand);
            melodyMeasures.addLast(nextMelodyMeasure);
            bassMeasures.addLast(nextBassMeasure);

            prevChord = chord;
            if (nextMelodyMeasure.getLastNonRestNote() != null) {
                prevMelodyNote = nextMelodyMeasure.getLastNonRestNote();
            }
            if (nextBassMeasure.getLastNonRestNote() != null) {
                prevBassNote = nextBassMeasure.getLastNonRestNote();
            }
        }
    }

    public Sequence convertToSequence() throws InvalidMidiDataException {

        Sequence sequence = new Sequence(Sequence.PPQ, resolution);

        Track track = sequence.createTrack();

        // set BPM for midi sequence
        HexFormat formatter = HexFormat.of();
        byte[] bpmBytes = formatter.parseHex(formatter.toHexDigits((int) (60000000 / this.BPM)).substring(2));
        // The tempo value should be 3 bytes, so the substring method removes the first
        // byte (after converting the integer into a hexidecimal string, before the
        // string is converted to bytes) since an integer is 4 bytes.
        MetaMessage bpmMessage = new MetaMessage();
        bpmMessage.setMessage(81, bpmBytes, 3);
        // Tempo message (type 81) takes 3 bytes to specify the tempo in microseconds
        // per quarter note (60,000,000 microseconds per minute divided by beats per
        // minute where quarter note gets the beat).
        MidiEvent bpmEvent = new MidiEvent(bpmMessage, 0);
        track.add(bpmEvent);

        MidiEvent timeSigEvent = new MidiEvent(timeSig.getTimeSignatureMidiMessage(), 0);
        track.add(timeSigEvent);

        if (hasMelodyPart) {
            long delay = 0;
            for (Measure measure : this.melodyMeasures) {
                for (Note note : measure.getNoteList()) {
                    if (!(note.getIsRest())) {
                        ShortMessage startNote = new ShortMessage();
                        startNote.setMessage(144, 1,
                                (note.getNoteValue() + NoteUtil.getScaleOffsetMap().get(scaleRootNote)) % 128, 100);
                        // adds note to channel 1 with a note value adjusted to fit the entered scale,
                        // (% 128 in case the value falls outside the midi range), 100 for velocity
                        MidiEvent startNoteEvent = new MidiEvent(startNote, delay);

                        ShortMessage stopNote = new ShortMessage();
                        stopNote.setMessage(128, 1,
                                (note.getNoteValue() + NoteUtil.getScaleOffsetMap().get(scaleRootNote)) % 128, 100);
                        MidiEvent stopNoteEvent = new MidiEvent(stopNote, delay + (note.getNoteLengthValue() - 1));

                        track.add(startNoteEvent);
                        track.add(stopNoteEvent);
                    }
                    delay += note.getNoteLengthValue();
                }
            }

        }

        if (hasBassPart) {
            long delay = 0;
            for (Measure measure : this.bassMeasures) {
                for (Note note : measure.getNoteList()) {
                    if (!(note.getIsRest())) {
                        ShortMessage startNote = new ShortMessage();
                        startNote.setMessage(144, 2,
                                (note.getNoteValue() + NoteUtil.getScaleOffsetMap().get(scaleRootNote)) % 128, 100);
                        // adds note to channel 2 with a note value adjusted to fit the entered scale,
                        // (% 128 in case the value falls outside the midi range), 100 for velocity
                        MidiEvent startNoteEvent = new MidiEvent(startNote, delay);

                        ShortMessage stopNote = new ShortMessage();
                        stopNote.setMessage(128, 2,
                                (note.getNoteValue() + NoteUtil.getScaleOffsetMap().get(scaleRootNote)) % 128, 100);
                        MidiEvent stopNoteEvent = new MidiEvent(stopNote, delay + (note.getNoteLengthValue() - 1));

                        track.add(startNoteEvent);
                        track.add(stopNoteEvent);
                    }
                    delay += note.getNoteLengthValue();
                }
            }
        }

        return sequence;
    }

    public void setRandomSeed(long randomSeed) {
        rand.setSeed(randomSeed);
    }

    public Song(double BPM, int numMeasures, ArrayList<ArrayList<ArrayList<Double>>> ascendingNoteWeights,
            ArrayList<ArrayList<ArrayList<Double>>> descendingNoteWeights, ArrayList<Double> rhythmWeights,
            int melodyRangeMaxValue, int melodyRangeMinValue, int bassRangeMaxValue, int bassRangeMinValue,
            ScaleType scale, Notes scaleRootNote, TimeSignature timeSig, boolean hasMelodyPart, boolean hasBassPart,
            double restBias, double chordBias, double harmonicMelodicBias) {
        this.BPM = BPM;
        this.numMeasures = numMeasures;
        this.rand = new Random();

        this.ascendingNoteWeights = ascendingNoteWeights;
        this.descendingNoteWeights = descendingNoteWeights;
        this.rhythmWeights = rhythmWeights;

        this.melodyRangeMaxValue = melodyRangeMaxValue;
        this.melodyRangeMinValue = melodyRangeMinValue;

        this.bassRangeMaxValue = bassRangeMaxValue;
        this.bassRangeMinValue = bassRangeMinValue;

        this.scale = scale;
        this.scaleRootNote = scaleRootNote;

        this.timeSig = timeSig;

        this.hasMelodyPart = hasMelodyPart;
        this.hasBassPart = hasBassPart;

        this.restBias = restBias;
        this.chordBias = chordBias;
        this.harmonicMelodicBias = harmonicMelodicBias;

        this.melodyMeasures = new LinkedList<Measure>();
        this.bassMeasures = new LinkedList<Measure>();

    }

}
