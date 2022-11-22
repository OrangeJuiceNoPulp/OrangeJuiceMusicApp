package music;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;

public class TimeSignature {
    private int topNumber;
    private int bottomNumber;

    public int getTopNumber() {
        return topNumber;
    }

    public int getBottomNumber() {
        return bottomNumber;
    }

    public MetaMessage getTimeSignatureMidiMessage() throws InvalidMidiDataException {
        byte[] midiByteMessage = { 0, 0x02, 0x18, 0x08 };
        // First byte determines the numerator of the time signature (determined in
        // switch statement below).
        // Second byte is x for the value 2^x which is the denominator of the time
        // signature (2^2 = 4).
        // Third byte is 24 in decimal, it is the number of midi clocks per metronome
        // click (there are 24 midi clocks per quarter note, so one metronome click
        // every quarter note).
        // Fourth byte is number of 32nd notes per beat (quarter note gets the beat,
        // eight 32nd notes fit in a quarter note).
        switch (topNumber) {
            case 2:
                midiByteMessage[0] = 0x02;
                break;
            case 3:
                midiByteMessage[0] = 0x03;
                break;
            case 4:
                midiByteMessage[0] = 0x04;
                break;
            case 5:
                midiByteMessage[0] = 0x05;
                break;
            default:
                midiByteMessage[0] = 0x04;
                break;
        }
        MetaMessage message = new MetaMessage();
        message.setMessage(0x58, midiByteMessage, 4);
        return message;
    }

    public TimeSignature(int topNumber, int bottomNumber) {
        this.topNumber = topNumber;
        this.bottomNumber = bottomNumber;
    }
}
