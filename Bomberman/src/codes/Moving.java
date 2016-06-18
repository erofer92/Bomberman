package codes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Moving implements KeyListener{
	private Player player;
	private Cenario cenario;
	private JFrame jframe;
	private boolean rightWB = false;
	private boolean leftWB = false;
	private boolean upWB = false;
	private boolean downWB = false;
	
	Moving(Player player, Cenario cenario, JFrame jframe){
		this.player = player;
		this.cenario = cenario;
		this.jframe = jframe;
	}
	
	private void verifyWallsAndBombs(){
		/*
		 * Esse método precisa ser otimizado.
		 * Há muitas comparações, muitos IFs e FORs.
		 */
		int wCount, bCount;

		// Verifica onde há Paredes
		for(wCount = 0; wCount < this.cenario.getWalls().size(); wCount++){
			if( (int)(this.player.getRectangle().getMaxX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.rightWB = true;
				continue;
				}
			// Verifica se há paredes na esquerda
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getMaxX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.leftWB = true;
				continue;
			}
			// Verifica se há paredes em cima
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getMaxY())){
				this.upWB = true;
				continue;
			}
			// Verifica se há paredes em baixo
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getMaxY()) ==
				(int)(this.cenario.getWalls().get(wCount).getRectangle().getY())){
				this.downWB = true;
				continue;
			}
		}
		
		// Verifica onde há bombas
		for(bCount = 0; bCount < this.cenario.getBombs().size(); bCount++){
			// Verifica se há bomba na direita
			if( (int)(this.player.getRectangle().getMaxX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.rightWB = true;
				continue;
			}
			// Verifica se há bomba na esquerda
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getMaxX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.leftWB = true;
				continue;
			}
			// Verifica se há bomba em cima
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getMaxY())){
				this.upWB = true;
				continue;
			}
			// Verifica se há bomba em baixo
			if( (int)(this.player.getRectangle().getX()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getX()) &&
				(int)(this.player.getRectangle().getMaxY()) ==
				(int)(this.cenario.getBombs().get(bCount).getRectangle().getY())){
				this.downWB = true;
				continue;
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		this.rightWB = false;
		this.leftWB = false;
		this.upWB = false;
		this.downWB = false;
		
		//verifica onde há bombas e paredes
		verifyWallsAndBombs();
		
		switch(e.getKeyCode()){
			case 39: // Move Right when no wall and bomb are detected
				if(!this.rightWB)
					this.player.goRight();
				break;
				
			case 37: // Move Left when no wall and bomb are detected
				if(!this.leftWB)
					this.player.goLeft();
				break;
				
			case 38: // Move Up when no wall and bomb are detected
				if(!this.upWB)
					this.player.goUp();
				break;
				
			case 40: // Move Down when no wall and bomb are detected
				if(!this.downWB)
					this.player.goDown();
				break;
			case 32: //Space to Plant a Bomb
				try {
					Bomb b = player.plantBomb();
					cenario.getBombs().add(b);
					jframe.add(b.getJLabel(), 2);
					//b.explode();
				}
				catch (MaxBombPlantedException e2) {
					System.out.println("MaxBombPlanted!");
					e2.printStackTrace();
				}
				break;
			default:
				System.out.println("default");
		}
	}
	
	public void keyReleased(KeyEvent e){
		this.player.stopWalking();
	}
	public void keyTyped(KeyEvent e){
		
	}
}
