package codes;

public class Player extends Character
{
	
	Player(int x, int y){
		super(x, y);
		this.setMoveImages("/images/goDown.png",
							"/images/goRight.png",
							"/images/goLeft.png",
							"/images/goUp.png",
							"/images/goDown.png");
	}
	
	public Bomb plantBomb(){
		return new Bomb((int)this.getRectangle().getMinX(), (int)this.getRectangle().getMinY());
	}
	
}