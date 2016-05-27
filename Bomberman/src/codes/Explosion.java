package codes;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Explosion implements Runnable{
	
	private Rectangle rectangle;
	private ImageIcon topExplosion;
	private ImageIcon bottomExplosion;
	private ImageIcon leftExplosion;
	private ImageIcon rightExplosion;
	private ImageIcon verticalExplosion;
	private ImageIcon horizontalExplosion;
	private ImageIcon centerExplosion;

	Explosion(Bomb b){
		centerExplosion = new ImageIcon(getClass().getResource("centerExplosion.png"));
	}

	@Override
	public void run(){
		
	}
}
