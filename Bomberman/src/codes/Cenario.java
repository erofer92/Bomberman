package codes;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cenario implements Serializable{
	
	private static final long serialVersionUID = -7476993394280368779L;
	
	private Rectangle rectangle;
	private ArrayList<Wall> walls;
	private ArrayList<Ground> grounds;
	private ArrayList<Bomb> bombs;
	
	Cenario(){
		this.walls = new ArrayList<Wall>();
		this.grounds = new ArrayList<Ground>();
		this.rectangle = new Rectangle(0, 0, 750, 650);
		this.bombs = new ArrayList<Bomb>();
		putScenarioWalls();
	}
	
	public ArrayList<Bomb> getBombs(){
		return this.bombs;
	}
	
	public void addBomb(Bomb b){
		bombs.add(b);
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
	
	private void putScenarioWalls(){
		int px, py, lx, ly;
		//px e py são as posições x e y para colocar a parede.
		//lx e ly são os números das linhas de x e y
		
		for(py = 0, ly = 0; py < this.getRectangle().getHeight(); py+=50, ly++){
			for(px = 0, lx = 0; px < this.getRectangle().getWidth(); px+=50, lx++){
				if( (px == 50 && py == 50) || (px == 50 && py == 100) || (px == 100 && py == 50) )
					this.grounds.add(new Ground(px, py));
					//não coloca parede, pois o Bomberman precisa de 3 espaços iniciais
				else{
					if(px == 0 || px == (this.getRectangle().getWidth() - 50) ||
					   py == 0 || py == (this.getRectangle().getHeight() - 50) )
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
}

