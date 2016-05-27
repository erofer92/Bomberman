package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb implements Runnable
{
	private Rectangle rectangle;
	private ImageIcon imgBomb;
	private JLabel jlabel;
	private Explosion explosion;
	private int explosionRange = 50;
	
	Bomb(int x, int y){
		imgBomb = new ImageIcon(getClass().getResource("bomb.png"));
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.jlabel = new JLabel();
		this.jlabel.setBounds(rectangle);
		jlabel.setIcon(imgBomb);
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public JLabel getJLabel(){
		return jlabel;
	}
	
	//ainda falta implementar
	public void explode(){
		this.explosion = new Explosion(this.rectangle.x, this.rectangle.y);
	}
	
	public void increaseExplosionRange(){
		this.explosionRange += 50;
	}

	//ainda falta implementar
	public void run(){
		
	}
	
}
