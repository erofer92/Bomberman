package codes;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Cenario implements Runnable{
	
	private Rectangle rectangle;
	private ArrayList<Wall> walls;
	private ArrayList<Ground> grounds;
	private ArrayList<Bomb> bombs;
	private ArrayList<Explosion> explosions;
	private JFrame jframe;
	private Player player;
	
	Cenario(){
		this.walls = new ArrayList<Wall>();
		this.grounds = new ArrayList<Ground>();
		this.bombs = new ArrayList<Bomb>();
		this.rectangle = new Rectangle(0, 0, 750, 650);
		putScenarioWalls();
		Thread t = new Thread(this);
		t.start();
	}
	
	public void addPlayer(Player player){
		this.player = player;
	}
	
	public ArrayList<Bomb> getBombs(){
		return this.bombs;
	}

	public ArrayList<Ground> getGrounds(){
		return this.grounds;
	}
	
	public ArrayList<Wall> getWalls(){
		return this.walls;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public void whereAmI(JFrame jframe){
		this.jframe = jframe;
	}
	
	private void putScenarioWalls(){
		int px, py, lx, ly;
		//px e py são as posições x e y para colocar a parede.
		//lx e ly são os números das linhas de x e y
		
		for(py = 0, ly = 0; py < this.getRectangle().height; py+=50, ly++){
			for(px = 0, lx = 0; px < this.getRectangle().width; px+=50, lx++){
				if( (px == 50 && py == 50) || (px == 50 && py == 100) || (px == 100 && py == 50) )
					this.grounds.add(new Ground(px, py));
					//não coloca parede, pois o Bomberman precisa de 3 espaços iniciais
				else{
					if(px == 0 || px == (this.getRectangle().width - 50) ||
					   py == 0 || py == (this.getRectangle().height - 50) )
						this.walls.add(new SolidWall(px, py));
					
					else{
						if(lx%2 == 0 && ly%2 == 0)
							this.walls.add(new SolidWall(px, py));
						else
							this.walls.add(new BreakableWall(px, py));
					}
				}
			}
		}
	}
	
	public void removeExplodedBombs(){
		int i;
		if(this.bombs.size() > 0)
			System.out.println("size if: " + this.bombs.size());
		
		for(i = 0; i < this.bombs.size(); i++){
			System.out.println("size: " + this.bombs.size());
			if(this.bombs.get(i).isExploded()){
				this.jframe.remove(this.bombs.get(i).getJLabel());
				System.out.println("removeu JLabel da bomba");
			}
		}
	}
	/*
	public void removeFadedOutExplosions(){
		int i;
		int i2;
		boolean removed;
		
		for(i = 0 ; i < this.bombs.size(); i++){
			removed = false;
			for(i2 = 0;	i2 < this.bombs.get(i).getExplosions().size(); i2++){
				if(this.bombs.get(i).getExplosions().get(i2).isFaded()){
					this.jframe.remove(this.bombs.get(i).getExplosions().remove(i2).getJLabel());
					removed = true;
				}
			}
			this.bombs.remove(i);
			if(removed)
				this.player.decreaseBombPlanted();
		}
	}*/
	
	@Override
	public void run() {
		while(true){
			//this.removeFadedOutExplosions();
			this.removeExplodedBombs();
		}
	}
}

