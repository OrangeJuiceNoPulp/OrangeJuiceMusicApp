1.	Project Name: Midi Piano
Team Name: Team Orange Juice
Team Members: Jason Kauppila

2.	In this project, I am trying to build a music program using midi. I want to be able to generate a random “song” (collection of random notes which may or may not sound musically pleasing due to the factor of randomness) which can be generated and played in the application, and also saved as a .mid file. (If time permits, I would like to add the ability to open and play midi files from an input file path.)
I wish to build such a program because the randomly generated music may be able to serve as inspiration in song writing, and storing it as a midi file allows it to be easily openable in existing notation software.
It will be useful for providing musicians with a place to seek inspiration when writing songs by both randomly generating music, and allowing them to use a keyboard for when they want to experiment playing a melody themselves (rather than them having to go somewhere else to find a musical keyboard when the spark of inspiration hits them).
To use the song generator, there will be the necessary buttons and text fields necessary to get appropriate information when generating and saving the randomly generated music. To use the musical keyboard, the user may click the keys with their mouse or enter the appropriate key on their computer’s keyboard. The user will also be able to use their mouse to press buttons to change certain settings of the musical keyboard.

3.	Initial UML Diagram: 
    See PNG File
    Mermaid Code for Initial UML Diagram:

    classDiagram
    Pane <|-- PianoPane
    Polygon <|-- PianoKey
    AppLauncher <|-- Application

    PianoPane o-- PianoKey
    PianoKey -- KeyType
    PianoKey -- Notes
    
    Note -- Notes
    Scale -- Notes
    Scale -- ScaleType
    Song *-- Measure
    Song *-- Scale
    Song *-- TimeSignature
    Measure *-- TimeSignature
    Measure *-- Note
    Measure *-- Chord
    Note -- NoteLength
    Chord -- ChordType
    Chord -- Notes
    RhythmFiller -- Chord
    RhythmFiller -- Scale
    RhythmFiller -- TimeSignature
    RhythmFiller -- Note

    AppLauncher *-- PianoPane
    AppLauncher *-- Song

    class PianoKey {
        -KeyCode keyboardKey
        -KeyType type
        -int octave
        -Notes note
        -boolean isPressed
        -Receiver receiver
        +startNote(Receiver receiver)
        +stopNote(Receiver receiver)
        -setChangeColor()
        +getKeyboardKey() KeyCode
        +getKeyType() KeyType
        +getIsPressed() boolean
        +setIsPressed(boolean pressed)
    }

    class PianoPane {
        +Map<Integer, KeyCode> regularKeyMap$
        +Map<Integer, KeyCode> sharpKeyMap$
        -int bottomNoteOctave
        -Notes bottomNote
        -Receiver receiver
        -paintPiano()
        +setWidth(double width)
        +setHeight(double height)
    }

    class KeyType {
        <<Enumeration>>
        STANDARD
        B_E_KEY
        C_F_KEY
        SHARP_FLAT
    }

    class RhythmFiller {
        +noteLengthInTicks(int noteLength)$ int
        +fill(Chord chord, Scale scale, TimeSignature time, Note previousNote)$
        +fill(Chord chord, Scale scale, TimeSignature time)$
        -fillNextNote(ArrayList<Note> notes, int remainingTicks) int
    }
    class Song {
        -LinkedList<Measure>
        -Scale scale
        -TimeSignature timeSignature
        -int BPM
        +generateSong(int numMeasures)
        +clearSong()
        +playSong(Sequencer sequencer)
        +saveSong(String fileName)
    }

    class Note {
        -int OFFSET$
        +Map<Notes> noteMap$
        -int length
        -boolean isDotted
        -Notes noteName
        -NoteLength noteType
        -int octave
        -int noteValue
        -boolean isRest
        -int upperBound
        -int lowerBound

        +drawNote(double centerX, double centerY)
        +playNote(intStartTick) int
        +generateNote(Chord chord, Scale scale, Note previousNote, int length) Note$
        +generateNote(Chord chord, Scale scale, int upperBound, int lowerBound, int length) Note$
        +generateNote(Chord chord, Scale scale, Note previousNote, NoteLength noteType, boolean isDotted) Note$
        +generateNote(Chord chord, Scale scale, int upperBound, int lowerBound, NoteLength noteType, boolean isDotted) Note$

    }

    class Chord {
        -Notes rootNote
        -ChordType type
        -ArrayList<Notes> notesInChord
        -generateNotes()
    }

    class Measure {
        -TimeSignature timeSignature
        -Scale scale
        -ArrayList<Note> notes
        -fillMeasure()
        +getFirstNote() Note
        +getLastNote() Note
        +getChord() Chord
        -generateChord(Chord previousChord) Chord
    }

    class TimeSignature {
        -int topNumber
        -int bottomNumber
        +getTopNumber() int
        +getBottomNumber() int
    }
    class Scale {
        -ScaleType scaleType
        -Notes rootNote
        -Map<Notes> notesList
        -generateNotesList()
        +getNotesList() Map<Notes>
        +getRootNote() Notes
        +getScaleType() ScaleType
    }
    class ChordType {
        <<Enumeration>>
        MAJOR
        MINOR
        MAJOR_M7
        MAJOR_7
        MINOR_M7
        MINOR_7
        DIMINISHED
    }
    class ScaleType {
        <<Enumeration>>
        MAJOR
        NATURAL_MINOR
        MELODIC_MINOR
        HARMONIC_MINOR
    }
    class Notes {
        <<Enumeration>>
        A
        Bb
        B
        C
        Db
        D
        Eb
        E
        F
        Gb
        G
        Ab
    }
    class NoteLength {
        <<Enumeration>>
       WHOLE
       HALF
       QUARTER
       EIGHTH
       SIXTEENTH
    }
    
   class AppLauncher {
    -displayPiano()
    -displaySongGenerator()
    +start(Stage primaryStage)
    +main(String[] args)$
   }
   
4.	Plan and estimate of effort:

Week 1 (November 1-7): 
Plan: Implement the keys of the keyboard
Effort Estimate: Tons of effort

Week 2 (November 8-14): 
Plan: Implement buttons to adjust keyboard settings and finalize the keyboard GUI
	Effort Estimate: Medium amount of effort (in best case scenario)

Week 3 (November 15-21): 
Plan: Implement the random “song” generator
Effort Estimate: Lots of effort

Week 4 (November 22-28): 
Plan: Implement GUI for random “song” generator, adjust weights for random “song” generator, debug project
	Effort Estimate: Tons of effort
