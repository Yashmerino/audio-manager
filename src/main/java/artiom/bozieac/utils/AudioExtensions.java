package artiom.bozieac.utils;

import java.util.List;

/**
 * Class that holds constants for audio files extensions.
 */
public class AudioExtensions {

    /**
     * Constant for .mp3 extension.
     */
    private static final String MP3 = ".mp3";

    /**
     * Constant for .wav extension.
     */
    private static final String WAV = ".wav";

    /**
     * Constant for .flac extension.
     */
    private static final String FLAC = ".flac";

    /**
     * List that holds all of the audio files extensions.
     */
    private static final List<String> extensions = List.of(MP3, WAV, FLAC);

    /**
     * Checks if a file is audio file.
     *
     * @param filename - The file's name.
     * @return <code>true</code> or <code>false</code>
     */
    public static boolean isAudioFile(final String filename) {
        for (String extension : extensions) {
            if (filename.endsWith(extension)) {
                return true;
            }
        }

        return false;
    }
}
