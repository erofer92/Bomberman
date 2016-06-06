package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion implements Runnable{
	
	private Rectangle rectangle;
	private JLabel jlabel;
	private boolean fadedOut;
	private int fireTime; // in milliseconds

	public Explosion(int x, int y){
		this.fadedOut = false;
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.jlabel = new JLabel();
		this.jlabel.setBounds(this.rectangle);
		this.fireTime = 2000;
	}
	
	public void setImageIcon(ImageIcon img){
		this.jlabel.setIcon(img);
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	public int getFireTime(){
		return this.fireTime;
	}

	public boolean isFaded() {
		return fadedOut;
	}
	
	@Override
	public void run(){
		System.out.println("explosion is happening");
		try
		{Thread.sleep(this.fireTime);}
		catch(InterruptedException e)
		{e.printStackTrace();}
		this.fadedOut = true;
		System.out.println("explosion faded out");
	}

}
