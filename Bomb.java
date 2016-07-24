package codes;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb implements Runnable {

	private int range;
	private int timeToExplode; // in milliseconds
	private boolean exploded;
	private static ImageIcon imgBomb;
	private Rectangle rectangle;
	private JLabel jlabel;
	
	public Bomb(){
		setDefaultProperties();
	}
	
	// This method serves only to set default properties and do constructor stay clean.
	public void setDefaultProperties(){
		Bomb.imgBomb = new ImageIcon(getClass().getResource("/images/bomb.png"));
		this.jlabel = new JLabel(imgBomb);
		this.rectangle = new Rectangle(imgBomb.getIconWidth(), imgBomb.getIconHeight());
		this.range = (int) (this.rectangle.getWidth() * 2); // or this.rectangle.height
		this.timeToExplode = 3000; // 3000 by default (3sec)
		this.exploded = false;
	}
	
	// Set the bomb's location
	public void setLocation(Point p){
		this.rectangle.setLocation(p);
		this.jlabel.setBounds(this.rectangle);
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public JLabel getJLabel(){
		return jlabel;
	}

	// Increase the bomb's explosion range
	// The maximum range is 500. (10 blocks of distance relative to explosion center).
	// The maximum range won't be changed if there is an attempt to increase it to more then 500.
	public void increaseExplosionRange(){
		if(this.range < this.rectangle.x * 10)
			this.range += this.rectangle.x;
	}
	
	// Decrease the bomb's explosion range
	// The minimum range is 50. (1 block of distance relative to explosion center).
	// The minimum range won't be changed if there is an attempt to decrease it to less then 50.
	public void decreaseExplosionRange(){
		if(this.range > this.rectangle.x)
			this.range -= this.rectangle.x;
	}
	
	// By default, the range is started with the same bomb's size. 50 in this case.
	public int getRange(){
		return this.range;
	}
	
	// Sets the time that bomb takes to explode (in milliseconds).
	// If the time is negative, it will be set to zero.
	public void setTimeToExplode(int timeToExplode){
		if(timeToExplode > 0){
			this.timeToExplode = timeToExplode;
			return;
		}
		this.timeToExplode = 0;
	}
	
	// Returns the time that bomb takes to explode (in milliseconds).
	public int getTimeToExplode() {
		return timeToExplode;
	}
	
	// Returns true if the bomb has already exploded.
	public boolean isExploded(){
		return this.exploded;
	}
	
	// Starts the count to explosion, then explode.
	public void run() {
		try{
			Thread.sleep(this.timeToExplode);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		this.exploded = true;
		System.out.println("Bomb Exploded!");
	}
}

