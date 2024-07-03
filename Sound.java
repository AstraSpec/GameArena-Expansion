import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.net.URI;
import java.net.URL;

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
	
	private String soundSource;			// The name of the sound file or URL to play.
	private boolean loop;				// Whether the sound should loop or not.
	private Clip clip;					// The sound clip to play.
	private float volume;				// The volume control for the sound.
	
	/**
	 * Constructor. Creates a new sound effect.
	 * @param source The file or URL to play.
	 * @param loop Whether the sound should loop or not.
	 * @param volume The volume of the sound (1.0f for default, 0.0f for mute, 2.0f for double).
	 */
	public Sound(String source, boolean loop, float volume)
	{
		this.soundSource = source;
		this.loop = loop;
		this.volume = volume;
		
		try {
			AudioInputStream audioInput;
			
			if (source.startsWith("http://") || source.startsWith("https://")) {
				// Obtains audio stream from URL
				URL soundURL = URI.create(source).toURL();
				audioInput = AudioSystem.getAudioInputStream(soundURL);
			} else {
				// Obtains audio stream from file
				File soundFile = new File(source);
				audioInput = AudioSystem.getAudioInputStream(soundFile);
			}

			// Gets sound clip resource
			this.clip = AudioSystem.getClip();
			// Opens audio input stream
			this.clip.open(audioInput);
			
			// Sets volume of clip after converting volume to decibels
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
			gainControl.setValue(20f * (float) Math.log10(volume));
		} 
		catch (Exception e) {}
	}	

	/**
	 * Plays the sound effect.
	 */
	public void play()
	{
		if (clip.isRunning())
			// Stops the clip to allow for changes to be made while the sound is playing.
			clip.stop();
		else
			// Resets the clip to the beginning of the sound file.
			clip.setFramePosition(0);

		// Plays the sound effect.
		if (loop) 
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else 
			clip.start();
	}

	/**
	 * Stops the sound effect from playing.
	 */
	public void stop()
	{
        clip.stop();
	}

	/**
	 * Closes the sound effect, meaning it cannot be used again.
	 */
	public void close()
	{
		clip.stop();
		clip.close();
	}

	/**
	 * Checks if sound is playing.
	 * @return True if sound is playing, false otherwise.
	 */
	public boolean isPlaying()
	{
		return clip.isRunning();
	}

	/**
	 * Obtains the name of the sound file.
	 * @return The name of the sound file.
	 */
	public String getSource()
	{
		return this.soundSource;
	}

	/**
	 * Sets the loop status of this sound effect.
	 * @param l The new loop status of this sound effect.
	 */
	public void setLoop(boolean l)
	{
		this.loop = l;

		if (clip.isRunning())
			play();
	}

	/**
	 * Obtains the loop status of this sound effect.
	 * @return The loop status of this sound effect.
	 */
	public boolean getLoop()
	{
		return this.loop;
	}

	/**
	 * Sets the volume of this sound clip.
	 * @param v The new volume of this sound clip (1.0f for default, 0.0f for mute).
	 */
	public void setVolume(float v)
	{
		// Clamps the volume between 0 and 2 (max being double the normal volume)
		v = Math.clamp(v, 0.0f, 2.0f);
		// Sets volume of clip after converting volume to decibels
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		gainControl.setValue(20f * (float) Math.log10(v));
		this.volume = v;
	}

	/**
	 * Obtains the volume of this sound clip.
	 * @return The volume of this sound clip.
	 */
	public float getVolume()
	{
		return this.volume;
	}
}
