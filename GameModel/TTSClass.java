package GameModel;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.signalproc.effects.*;
import marytts.util.data.audio.AudioPlayer;
import javax.sound.sampled.AudioInputStream;

/**
 * Class TTSClass.  Handles all the necessary tasks to run MaryTTS.
 */
public class TTSClass {
    /** MaryTTS instance */
    MaryInterface marytts;
    /**
     * TTSClass Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public TTSClass() {
        try {
            marytts = new LocalMaryInterface();
        } catch (MaryConfigurationException e) {
            System.out.println("MaryTTS error.");
            throw new RuntimeException();
        }
        marytts.setVoice("dfki-spike-hsmm");
    }
    /**
     * say
     * __________________________
     * Speaks out the given string
     * @param text the string to speak
     */
    public void say(String text) {
        try {
            AudioPlayer ap = new AudioPlayer();
            AudioInputStream audio = marytts.generateAudio(text);
            ap.setAudio(audio);
            ap.start();
            ap.join();
        } catch (SynthesisException | InterruptedException e) {
            System.out.println("MaryTTS error.");
        }
    }
}