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
	ArrayList<Wall> walls;
	ArrayList<Enemy> enemies;
	
	Cenario(){
		rectangle = new Rectangle(0, 0, 750, 650);
		cenario = new ImageIcon(getClass().getResource("/projectImages/cenario.png"));
		jlabel = new JLabel(cenario);
		this.jlabel.setBounds(rectangle);
		putScenarioWalls();
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}

	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	private void putScenarioWalls(){
		Wall wall;
		int px = 0;
		int py = 0;
		//px e py são as posições x e y para colocar a parede.
		
		for(py = 0; py < this.getRectangle().getHeight(); py+=50){
			for(px = 0; px < this.getRectangle().getWidth(); px+=50){
				if( (px == 50 && py == 50) || (px == 50 && py == 100) || (px == 100 && py == 50) );
					//não coloca parede, pois o Bomberman precisa de 3 espaços iniciais
				else{
					if(px == 0 || px == (this.getRectangle().getWidth() - 50) ||
					   py == 0 || py == (this.getRectangle().getHeight() - 50) )
						wall = new SolidWall(px, py);
					
					else{
						if(px%2 == 0 && py%2 == 0)
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
