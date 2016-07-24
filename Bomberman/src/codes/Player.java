package codes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player /*implements Runnable */{

	private Rectangle rectangle;
	private ImageIcon goRight;
	private ImageIcon goLeft;
	private ImageIcon goUp;
	private ImageIcon goDown;
	private ImageIcon death;
	private JLabel jlabel;
	private int life;
	private int maxBombCount;
	private String name;
	private ArrayList<Bomb> bombs;
	
	public Player(Point p) {
		this.setDefaultProperties(p);
	}
	
	private void setDefaultProperties(Point p){
		this.goRight = new ImageIcon(getClass().getResource("/images/goRight.png"));
		this.goLeft = new ImageIcon(getClass().getResource("/images/goLeft.png"));
		this.goUp = new ImageIcon(getClass().getResource("/images/goUp.png"));
		this.goDown = new ImageIcon(getClass().getResource("/images/goDown.png"));
		this.death = new ImageIcon(getClass().getResource("/images/death.png"));
		this.rectangle = new Rectangle(p.x, p.y, goDown.getIconWidth(), goDown.getIconHeight());
		this.jlabel = new JLabel(this.goDown);
		this.jlabel.setBounds(this.rectangle);
		this.bombs = new ArrayList<Bomb>();
		this.bombs.add(new Bomb());
		this.life = 1;
		this.maxBombCount = 1;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Bomb> getBombArray(){
		return this.bombs;
	}
	
	public int getmaxBombCount(){
		return this.maxBombCount;
	}
	
	public JLabel getJLabel(){
		return jlabel;
	}
	
	public void goRight(){
		if(this.isAlive()){
			this.jlabel.setIcon(goRight);
			this.rectangle.x += this.rectangle.width;
			this.jlabel.setBounds(rectangle);
		}
	}
	
	public void goLeft(){
		if(this.isAlive()){
			this.jlabel.setIcon(goLeft);
			this.rectangle.x -= this.rectangle.width;
			this.jlabel.setBounds(rectangle);
		}
	}
	
	public void goUp(){
		if(this.isAlive()){
			this.jlabel.setIcon(goUp);
			this.rectangle.y -= this.rectangle.height;
			this.jlabel.setBounds(rectangle);
		}
	}
	
	public void goDown(){
		if(this.isAlive()){
			this.jlabel.setIcon(goDown);
			this.rectangle.y += this.rectangle.height;
			this.jlabel.setBounds(rectangle);
		}
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public void kill(){
		life--;
	}
	
	public boolean isAlive(){
		if(this.life > 0)
			return true;
		this.jlabel.setIcon(this.death);
		return false;
	}
	
	public void addBomb(){
		this.bombs.add(new Bomb());
	}
	
	public Bomb plantBomb() throws MaxBombPlantedException{
		if(this.bombs.size() > 0){
			Bomb b = this.bombs.get(0);
			this.bombs.remove(0);
			b.setLocation(this.rectangle.getLocation());
			return b;
		}
		throw new MaxBombPlantedException();
	}
}
