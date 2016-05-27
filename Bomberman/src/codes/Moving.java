package codes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Moving implements KeyListener{
	private Player player;
	
	Moving(Player player){
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode()){
		case 39: //Right
			this.player.goRight();
			break;
		case 37: //Left
			this.player.goLeft();
			break;
		case 38: //Up
			this.player.goUp();
			break;
		case 40: //Down
			this.player.goDown();
			break;
		case 32: //Space
			//player.plantBomb();
			// Plant Bomb
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
