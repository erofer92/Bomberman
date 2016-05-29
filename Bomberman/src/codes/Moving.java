package codes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Moving implements KeyListener{
	private Player player;
	private Cenario cenario;
	private JFrame jframe;
	private int rightWB = 0;
	private int leftWB = 0;
	private int upWB = 0;
	private int downWB = 0;
	
	Moving(Player player, Cenario cenario, JFrame jframe){
		this.player = player;
		this.cenario = cenario;
		this.jframe = jframe;
	}
	
	private void verifyWallsAndBombs(){
		int bCount, wCount;

		// Verifica onde há Paredes
		for(wCount = 0; wCount < this.cenario.getWalls().size(); wCount++){
			if( (int)(this.player.getRectangle().getMaxX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.rightWB++;
				System.out.println("wall/right");
				continue;
				}
			// Verifica se há paredes na esquerda
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getMaxX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.leftWB++;
				System.out.println("wall/left");
				continue;
			}
			// Verifica se há paredes em cima
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getMaxY())){
				this.upWB++;
				System.out.println("wall/up");
				continue;
			}
			// Verifica se há paredes em baixo
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getMaxY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.downWB++;
				System.out.println("wall/down");
				continue;
			}
		}
		
		// Verifica onde há bombas
		for(bCount = 0; bCount < this.cenario.getBombs().size(); bCount++){
			System.out.println("bomb verify");
			// Verifica se há bomba na direita
			if( (int)(this.player.getRectangle().getMaxX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.rightWB++;
				System.out.println("bomb/right");
				continue;
			}
			// Verifica se há bomba na esquerda
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getMaxX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.leftWB++;
				System.out.println("bomb/left");
				continue;
			}
			// Verifica se há bomba em cima
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getMaxY())){
				this.upWB++;
				System.out.println("bomb/up");
				continue;
			}
			// Verifica se há bomba em baixo
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getMaxY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.downWB++;
				System.out.println("bomb/down");
				continue;
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		this.rightWB = 0;
		this.leftWB = 0;
		this.upWB = 0;
		this.downWB = 0;
		
		//verifica onde há bombas e paredes
		verifyWallsAndBombs();
		
		switch(e.getKeyCode()){
			case 39: // Move Right when no wall and bomb are detected
				if(this.rightWB == 0)
					this.player.goRight();
				break;
				
			case 37: // Move Left when no wall and bomb are detected
				if(this.leftWB == 0)
					this.player.goLeft();
				break;
				
			case 38: // Move Up when no wall and bomb are detected
				if(this.upWB == 0)
					this.player.goUp();
				break;
				
			case 40: // Move Down when no wall and bomb are detected
				if(this.downWB == 0)
					this.player.goDown();
				break;
			case 32: //Space to Plant a Bomb
				try {
					Bomb b = player.plantBomb();
					b.whereToExplode(this.cenario);
					cenario.addBomb(b);
					jframe.add(b.getJLabel(), 1);
				}
				catch (MaxBombPlantedException e2) {
					System.out.println("MaxBombPlanted!");
					e2.printStackTrace();
				}
				break;
			default:
		}
	}
	
	public void keyReleased(KeyEvent e){
		this.player.stopWalking();
	}
	public void keyTyped(KeyEvent e){
		
	}
}
