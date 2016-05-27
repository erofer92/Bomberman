package codes;

import javax.swing.ImageIcon;

public class Player extends Character{
	
	Player(int x, int y){
		super(x, y);
		this.setMoveImages("/images/goDown.png",
				"/images/goRight.png",
				"/images/goLeft.png",
				"/images/goUp.png",
				"/images/goDown.png");
		this.setJLabel(new ImageIcon(getClass().getResource("/images/goDown.png")));
	}
	
}