package codes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class Moving implements KeyListener{
	private Player player;
	private Cenario cenario;
	
	Moving(Player player, Cenario cenario){
		this.player = player;
		this.cenario = cenario;
	}
	
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		Iterator<Wall> iterator = cenario.getWalls().iterator();
		int i;
		
		switch(e.getKeyCode()){
		case 39: // Move Right when no wall is detected
			i = 0;
			while(iterator.hasNext()){
				if( (int)(this.player.getRectangle().getMaxX()) ==
					(int)(iterator.next().getRectangle().getX()) &&
					(int)(this.player.getRectangle().getY()) ==
					(int)(iterator.next().getRectangle().getY()))
					i++;
			}
			if(i == 0)
				this.player.goRight();
			break;
			
		case 37: // Move Left when no wall is detected
			i = 0;
			while(iterator.hasNext()){
				if( (int)(this.player.getRectangle().getX()) ==
					(int)(iterator.next().getRectangle().getMaxX()) &&
					(int)(this.player.getRectangle().getY()) ==
					(int)(iterator.next().getRectangle().getY()))
					i++;
			}
			if(i == 0)
				this.player.goLeft();
			break;
			
		case 38: // Move Up when no wall is detected
			i = 0;
			while(iterator.hasNext()){
				if( (int)(this.player.getRectangle().getX()) ==
					(int)(iterator.next().getRectangle().getX()) &&
					(int)(this.player.getRectangle().getY()) ==
					(int)(iterator.next().getRectangle().getMaxY()))
					i++;
			}
			if(i == 0)
				this.player.goUp();
			break;
			
		case 40: // Move Down when no wall is detected
			i = 0;
			while(iterator.hasNext()){
				if( (int)(this.player.getRectangle().getX()) ==
					(int)(iterator.next().getRectangle().getX()) &&
					(int)(this.player.getRectangle().getMaxY()) ==
					(int)(iterator.next().getRectangle().getY()))
					i++;
			}
			if(i == 0)
				this.player.goDown();
			break;
		case 32: //Space Plant a Bomb when no Bomb is detected in that place
			try {
				player.plantBomb();
			} catch (MaxBombPlantedException e1) {
				e1.printStackTrace();
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
