package codes;

public class InvalidTimeToExplodeException extends Exception {

	private int time;
	private Bomb bomb;
	
	public InvalidTimeToExplodeException(Bomb bomb, int timeToExplode) {
		this.bomb = bomb;
		this.time = timeToExplode;
	}
	
	public int getTriedTime(){
		return this.time;
	}
	
	public Bomb getTriedBomb(){
		return bomb;
	}
	
}
