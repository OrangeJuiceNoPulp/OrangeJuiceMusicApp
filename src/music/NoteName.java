package music;

public class NoteName {
    
    private int noteValue;
    private Notes noteName;

    


    private Notes calcNoteName() {
        int standardNoteValue = this.noteValue - 21;
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
    public Notes getNoteName() {
        return noteName;
    }
    public int getNoteValue() {
        return noteValue;
    }

    public NoteName(int noteValue) {
        this.noteValue = noteValue;
        this.noteName = calcNoteName();
    }
}
