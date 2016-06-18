package codes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Character{

	private Rectangle rectangle;
	private ImageIcon goRight;
	private ImageIcon goLeft;
	private ImageIcon goUp;
	private ImageIcon goDown;
	private ImageIcon death;
	private JLabel jlabel;
	private int life;
	private int bombCount;
	private String name;
	private ArrayList<Bomb> bombs;
	
	public Character(Point p, ImageIcon initialImage, String name){
		this.setDefaultConfiguration(p, initialImage, name);
	}
	
	public Character(Point p, ImageIcon initialImage, String name,
					ImageIcon goRight, ImageIcon goLeft, 
					ImageIcon goUp,	ImageIcon goDown, 
					ImageIcon death){
		
		this.setDefaultConfiguration(p, initialImage, name);
		this.setImages(goRight, goLeft, goUp, goDown, death);
	}
	
	public void setImages(ImageIcon goRight, ImageIcon goLeft, 
							ImageIcon goUp,	ImageIcon goDown, 
							ImageIcon death){
		this.goRight = goRight;
		this.goLeft = goLeft;
		this.goUp = goUp;
		this.goDown = goDown;
		this.death = death;
	}
	
	private void setDefaultConfiguration(Point p, ImageIcon initialImage, String name){
		this.name = name;
		this.life = 1;
		this.bombCount = 1;
		this.rectangle = new Rectangle(p.x, p.y, initialImage.getIconWidth(), initialImage.getIconHeight());
		this.jlabel = new JLabel(initialImage);
		this.jlabel.setBounds(this.rectangle);
	}
	
	public int getBombCount(){
		return this.bombCount;
	}
	
	public JLabel getJLabel(){
		return jlabel;
	}
	
	public void goRight(){
		this.jlabel.setIcon(goRight);
		this.rectangle.x += this.rectangle.width;
		this.jlabel.setBounds(rectangle);
	}
	
	public void goLeft(){
		this.jlabel.setIcon(goLeft);
		this.rectangle.x -= this.rectangle.width;
		this.jlabel.setBounds(rectangle);
	}
	
	public void goUp(){
		this.jlabel.setIcon(goUp);
		this.rectangle.y -= this.rectangle.height;
		this.jlabel.setBounds(rectangle);
	}
	
	public void goDown(){
		this.jlabel.setIcon(goDown);
		this.rectangle.y += this.rectangle.height;
		this.jlabel.setBounds(rectangle);
	}
	
	public void stopWalking(){
		//this.jlabel.setIcon(stop);
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public boolean isAlive(){
		if(this.life > 0)
			return true;
		
		this.jlabel.setIcon(this.death);
		return false;
	}
	
	public Bomb plantBomb() throws MaxBombPlantedException{
		if(this.bombs.size() < this.bombCount){
			Bomb b = new Bomb(this.rectangle.getLocation());
			this.bombs.add(b);
			return b;
		}
		throw new MaxBombPlantedException();
	}
	
	public void run(){
		int i;
		while(this.isAlive()){
			for(i = 0; i < this.bombs.size(); i++){
				if(this.bombs.get(i).isExploded()){
					this.bombs.remove(i);
				}
			}
		}
		System.out.println(this.name + " is DEAD!");
	}
}
