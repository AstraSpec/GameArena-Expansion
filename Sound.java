import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Models a simple sound effect. 
 * This class represents a Sound object.
 * Instances of the Sound class can be played to produce sound effects.
 */
public class Sound
{
	// The following instance variables define the
	// information needed to represent a sound.
	// Feel free to more instance variables if you think it will 
	// support your work... 
	
	private String soundFile;			// The name of the sound file to play.
	private boolean loop;				// Whether the sound should loop or not.
	private Clip clip;					// The sound clip to play.
	
	/**
	 * Constructor. Creates a new sound effect.
	 * @param sFile The name of the sound file to play.
	 * @param loop Whether the sound should loop or not.
	 */
	public Sound(String sFile, boolean loop)
	{
		this.soundFile = sFile;
		this.loop = loop;
	}	

	/**
	 * Play the sound effect.
	 */
	public void play()
	{
		try {
			// Gets audio stream from file
			File soundFile = new File(this.soundFile);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
			// Gets sound clip resource
			clip = AudioSystem.getClip();
			// Opens audio input stream
			clip.open(audioInput);
			
			if (loop) 
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else 
				clip.start();
    	
		} 
		catch (Exception e) {}
	}

	/**
	 * Stop the sound effect.
	 */
	public void stop()
	{
		if (clip != null) {
            clip.stop();
            clip.close();
        }
	}
}
