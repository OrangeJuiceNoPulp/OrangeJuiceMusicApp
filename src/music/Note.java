package music;

import java.util.Map;
import static java.util.Map.entry;

public class Note extends NoteName {
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
        entry(Notes.B, 11)
        );
    private static final int OFFSET = 12;

    private int noteLength;

    private boolean isRest;

    public static Map<Notes, Integer> getNoteMap() {
        return Map.copyOf(noteMap);
    }
    public static int getOffset() {
        return OFFSET;
    }

    public Note(int noteValue, int noteLength) {
        super(noteValue);
        this.noteLength = noteLength;
        this.isRest = false;
    }
    public Note(int noteValue, int noteLength, boolean rest) {
        this(noteValue, noteLength);
        this.isRest = rest;
    }
}
