package codes;


public class Enemy extends Character{

	Enemy(int x, int y){
		super(x, y);
		this.setMoveImages("stop.png", "goRight.png", "goLeft.png", "goUp.png", "goDown.png");
	}
	// NEED IA
}