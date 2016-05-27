package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb implements Runnable{
	
	private Rectangle rectangle;
	private ImageIcon imgBomb;
	private JLabel jlabel;
	private Explosion explosion;
	private int explosionRange;
	private int timeToExplode; // in milliseconds
	
	Bomb(int x, int y){
		this.imgBomb = new ImageIcon(getClass().getResource("/images/bomb.png"));
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.explosion = new Explosion(x, y);
		this.jlabel = new JLabel(imgBomb);
		this.jlabel.setBounds(rectangle);
		this.explosionRange = 50;
		this.timeToExplode = 3000;
		Thread thread = new Thread(this);
	}
	public Explosion getExplosion(){
		return this.explosion;
	}
	
	public int getExplosionRange(){
		return this.explosionRange;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public JLabel getJLabel(){
		return jlabel;
	}
	
	//ainda falta implementar
	private void explode(){
		this.explosion = new Explosion(this.rectangle.x, this.rectangle.y);
	}
	
	public void increaseExplosionRange(){
		this.explosionRange += 50;
	}

	//ainda falta implementar
	public void run(){
		try {
			Thread.sleep(this.timeToExplode);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.explode();
	}
	
}
