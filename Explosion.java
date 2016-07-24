package codes;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion implements Runnable {
	
	private Rectangle rectangle;
	private ImageIcon img;
	private JLabel jlabel;
	private boolean fadedOut;
	private static int fireTime; // in milliseconds
	
	public Explosion(Point p, String explosionType) throws InvalidExplosionTypeException{
		this.setDefaultProperties(p, explosionType);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	private void setDefaultProperties(Point p, String explosionType) throws InvalidExplosionTypeException {
		
		switch(explosionType){
			case "Central":
				this.img = new ImageIcon(getClass().getResource("/images/centerExplosion.png"));
				break;
			case "Right" :
				this.img = new ImageIcon(getClass().getResource("/images/rightExplosion.png"));
				break;
			case "Left" :
				this.img = new ImageIcon(getClass().getResource("/images/leftExplosion.png")); 
				break;
			case "Up" :
				this.img = new ImageIcon(getClass().getResource("/images/upperExplosion.png"));
				break;
			case "Down" :
				this.img = new ImageIcon(getClass().getResource("/images/bottomExplosion.png"));
				break;
			case "Horizontal" :
				this.img = new ImageIcon(getClass().getResource("/images/horizontalExplosion.png"));
				break;
			case "Vertical" :
				this.img = new ImageIcon(getClass().getResource("/images/verticalExplosion.png"));
				break;
			default:
				throw new InvalidExplosionTypeException(explosionType);
		}
		this.rectangle = new Rectangle(p.x, p.y, this.img.getIconWidth(), this.img.getIconHeight());
		this.jlabel = new JLabel(this.img);
		this.jlabel.setBounds(this.rectangle);
		this.fadedOut = false;
		Explosion.fireTime = 1000;
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	// caso seja passado um valor negativo, o valor se transforma em zero
	public static void setFireTime(int time){
		if(time > 0){
			Explosion.fireTime = time;
			return;
		}
		Explosion.fireTime = 0;
	}
	
	public int getFireTime(){
		return Explosion.fireTime;
	}

	public boolean isFadedOut() {
		return this.fadedOut;
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(Explosion.fireTime);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		this.fadedOut = true;
		System.out.println("Explosion Faded Out!");
	}

}
