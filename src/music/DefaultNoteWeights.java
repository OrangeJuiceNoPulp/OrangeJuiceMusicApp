package music;

import music.NoteUtil.AscendDescend;
import music.NoteUtil.NotePosition;

public class DefaultNoteWeights {
    public static double getDefaultNoteWeight(ScaleType scale, NotePosition initNote, NotePosition newNote,
            AscendDescend direction) {
        double weight = 0.0;

        switch (direction) {
            case ASCEND:
                switch (scale) {
                    case MAJOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case III:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case VI:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }

                        break;
                    case NATURAL_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vi:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if melodic
                            case VI:
                                switch (newNote) {
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vii:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if harmonic/melodic
                            case VII:
                                switch (newNote) {
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                    case HARMONIC_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vi:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case iii:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vii:
                                switch (newNote) {
                                    case ii:
                                        weight = 9;
                                        break;
                                    case II:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                    case MELODIC_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case III:
                                        weight = 6;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vi:
                                switch (newNote) {
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VI:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case II:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vii:
                                switch (newNote) {
                                    case ii:
                                        weight = 9;
                                        break;
                                    case II:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 7;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 2;
                                        break;
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 7;
                                        break;
                                    case ii:
                                        weight = 9;
                                        break;
                                    case iii:
                                        weight = 7;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 2;
                                        break;
                                    case vii:
                                        weight = 2;
                                        break;
                                    case VIII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                }
                break;

            case DESCEND:
                switch (scale) {
                    case MAJOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case III:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case VI:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }

                        break;

                    case NATURAL_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vi:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if melodic
                            case VI:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case vi:
                                        weight = 4;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vii:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if harmonic/melodic
                            case VII:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                    case HARMONIC_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case vi:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vii:
                                switch (newNote) {
                                    case ii:
                                        weight = 2;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 4;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 6;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case VI:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                    case MELODIC_MINOR:
                        switch (initNote) {
                            case I:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case II:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case VI:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case iii:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case IV:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            case V:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 4;
                                        break;
                                    case vi:
                                        weight = 6;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vi:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 1;
                                        break;
                                    case V:
                                        weight = 5;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VI:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case IV:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // if natural
                            case vii:
                                switch (newNote) {
                                    case ii:
                                        weight = 1;
                                        break;
                                    case II:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 4;
                                        break;
                                    case IV:
                                        weight = 4;
                                        break;
                                    case V:
                                        weight = 7;
                                        break;
                                    case VI:
                                        weight = 7;
                                        break;
                                    case VII:
                                        weight = 9;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;

                            // standard
                            case VII:
                                switch (newNote) {
                                    case I:
                                        weight = 1;
                                        break;
                                    case ii:
                                        weight = 2;
                                        break;
                                    case iii:
                                        weight = 2;
                                        break;
                                    case III:
                                        weight = 5;
                                        break;
                                    case v:
                                        weight = 2;
                                        break;
                                    case vi:
                                        weight = 7;
                                        break;
                                    case vii:
                                        weight = 9;
                                        break;
                                    case VIII:
                                        weight = 7;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                            default:
                                switch (newNote) {
                                    case VII:
                                        weight = 1;
                                        break;
                                    default:
                                        weight = 0;
                                        break;
                                }
                                break;
                        }
                        break;

                }
                break;
        }

        return weight;
    }
}