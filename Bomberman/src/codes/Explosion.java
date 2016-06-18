package codes;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion implements Runnable {
	
	private Rectangle rectangle;
	private JLabel jlabel;
	private boolean fadedOut;
	private static int fireTime; // in milliseconds
	
	public Explosion(Point p, ImageIcon img){
		this.setDefaultConfiguration(p, img);
	}
	
	public void setDefaultConfiguration(Point p, ImageIcon img){
		this.rectangle = new Rectangle(p.x, p.x, img.getIconWidth(), img.getIconHeight());
		this.jlabel = new JLabel(img);
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
		if(time > 0)
			Explosion.fireTime = time;
		else
			Explosion.fireTime = 0;
	}
	
	public int getFireTime(){
		return Explosion.fireTime;
	}

	public boolean isFaded() {
		return fadedOut;
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
	}

}
