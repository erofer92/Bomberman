package codes;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
@SuppressWarnings("unused")
public class Cenario {
	
	private Rectangle rectangle;
	private ImageIcon cenario;
	private JLabel jlabel;
	private ArrayList<Wall> walls;
	private ArrayList<Enemy> enemies;
	private ArrayList<Ground> grounds;
	
	Cenario(){
		this.grounds = new ArrayList<Ground>();
		this.walls = new ArrayList<Wall>();
		this.enemies = new ArrayList<Enemy>();
		this.rectangle = new Rectangle(0, 0, 750, 650);
		this.cenario = new ImageIcon(getClass().getResource("/images/scenario.png"));
		this.jlabel = new JLabel(cenario);
		this.jlabel.setBounds(rectangle);
		putScenarioWalls();
	}
	public ArrayList<Enemy> getEnemies(){
		return this.enemies;
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

	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	private void putScenarioWalls(){
		Wall wall;
		Ground ground;
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
						wall = new SolidWall(px, py);
					
					else{
						if(lx%2 == 0 && ly%2 == 0)
							wall = new SolidWall(px, py);
						else
							wall = new BreakableWall(px, py);
					}
					this.walls.add(wall);
				}
			}
		}
	}
}

