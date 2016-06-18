package codes;

@SuppressWarnings("serial")
public class MinRangeReachedException extends Exception {
	
	private Bomb bomb;
	
	public MinRangeReachedException(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public Bomb getTriedBomb(){
		return this.bomb;
	}

}
