package codes;

public class Player extends Character
{
	
	Player(int x, int y){
		super(x, y);
		this.setMoveImages("/projectImages/goDown.png",
							"/projectImages/goRight.png",
							"/projectImages/goLeft.png",
							"/projectImages/goUp.png",
							"/projectImages/goDown.png");
	}
	
	public Bomb plantBomb(){
		return new Bomb((int)this.getRectangle().getMinX(), (int)this.getRectangle().getMinY());
	}
	
}