import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Models a simple sprite. 
 * This class represents a Sprite object. When combined with the GameArena class,
 * instances of the Sprite class can be displayed on the screen.
 */
public class Sprite
{
	// The following instance variables define the
	// information needed to represent a Sprite
	// Feel free to more instance variables if you think it will 
	// support your work... 
	
	private String source;		        // The filepath or URL of the image displayed by this Sprite.
	private BufferedImage img;			// The image displayed by this Sprite.
	private double xPosition;			// The X coordinate of this Sprite.
	private double yPosition;			// The Y coordinate of this Sprite.
	private double width;				// The width of this Sprite.
	private double height;				// The height of this Sprite.
	private int layer;				    // The layer this Sprite is on.
	
	/**
	 * Constructor. Creates a Sprite with the given parameters.
	 * @param s The source of the Sprite.
	 * @param x The x co-ordinate position of top left corner of the Sprite (in pixels).
	 * @param y The y co-ordinate position of top left corner of the Sprite (in pixels).
	 * @param w The width of the Sprite (in pixels).
	 * @param h The height of the Sprite (in pixels).
	 */
	public Sprite(String s, double x, double y, double w, double h)
	{
		this.source = s;
		this.xPosition = x;
		this.yPosition = y;
		this.width = w;
		this.height = h;
		this.layer = 0;

		setSource(s);
	}
									
	/**
	 * Constructor. Creates a Sprite with the given parameters.
	 * @param s The source of the Sprite.
	 * @param x The x co-ordinate position of top left corner of the Sprite (in pixels).
	 * @param y The y co-ordinate position of top left corner of the Sprite (in pixels).
	 * @param w The width of the Sprite (in pixels).
	 * @param h The height of the Sprite (in pixels).
	 * @param layer The layer this Sprite is to be drawn on. Objects with a higher layer number are always drawn on top of those with lower layer numbers.
	 */
	public Sprite(String s, double x, double y, double w, double h, int layer)
	{
		this.xPosition = x;
		this.yPosition = y;
		this.width = w;
		this.height = h;
		this.layer = layer;

		setSource(s);
	}

	/**
	 * Obtains the source of this Sprite.
	 * @return the source of this Sprite.
	 */
	public String getSource()
	{
		return source;
	}

	/**
	 * Sets the source of this Sprite.
	 * @param s the new source of this Sprite.
	 */
	public void setSource(String s)
	{
		source = s;
		try {
			if (source.startsWith("http://") || source.startsWith("https://")) {
				URL url = URI.create(source).toURL();
				img = ImageIO.read(url);
			} 
			else {
				img = ImageIO.read(new File(source));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtains the image of this Sprite.
	 * @return the image of this Sprite.
	 */
	public BufferedImage getImage()
	{
		return img;
	}

	/**
	 * Sets the image of this Sprite.
	 * @param i the new image of this Sprite.
	 */
	public void setImage(BufferedImage i)
	{
		img = i;
	}
			
	/**
	 * Obtains the current position of this Sprite.
	 * @return the X coordinate of this Sprite within the GameArena.
	 */
	public double getXPosition()
	{
		return xPosition;
	}

	/**
	 * Obtains the current position of this Sprite.
	 * @return the Y coordinate of this Sprite within the GameArena.
	 */
	public double getYPosition()
	{
		return yPosition;
	}

	/**
	 * Moves the current position of this Sprite to the given X co-ordinate.
	 * @param x the new x co-ordinate of this Sprite.
	 */
	public void setXPosition(double x)
	{
		this.xPosition = x;
	}

	/**
	 * Moves the current position of this Sprite to the given Y co-ordinate.
	 * @param y the new y co-ordinate of this Sprite.
	 */
	public void setYPosition(double y)
	{
		this.yPosition = y;
	}

	/**
	 * Obtains the width of this Sprite.
	 * @return the width of this Sprite,in pixels.
	 */
	public double getWidth()
	{
		return width;
	}

	/**
	 * Sets the width of this Sprite to the given value.
	 * @param w the new width of this Sprite, in pixels.
	 */
	public void setWidth(double w)
	{
		width = w;
	}

	/**
	 * Obtains the height of this Sprite.
	 * @return the height of this Sprite,in pixels.
	 */
	public double getHeight()
	{
		return height;
	}

	/**
	 * Sets the height of this Sprite to the given value.
	 * @param h the new height of this Sprite, in pixels.
	 */
	public void setHeight(double h)
	{
		height = h;
	}

	/**
	 * Obtains the layer of this Sprite.
	 * @return the layer of this Sprite.
	 */
	public int getLayer()
	{
		return layer;
	}

	/**
	 * Moves this Sprite by the given amount.
	 * @param dx the distance to move on the x axis (in pixels).
	 * @param dy the distance to move on the y axis (in pixels).
	 */
	public void move(double dx, double dy)
	{
		xPosition += dx;
		yPosition += dy;
	}

	/**
	 * Determines if this Sprite is overlapping the given sprite.
	 * @param s the sprite to test for collision.
	 * @return true of this sprite is overlapping the sprite s, false otherwise.
	 */
	public boolean collides(Sprite s)
	{
		return (xPosition + width > s.xPosition && xPosition < s.xPosition + s.width) && (yPosition + height > s.yPosition && yPosition < s.yPosition + s.height);
	}
}
