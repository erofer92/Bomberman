package codes;

public class InvalidFireTimeException extends Exception {
	
	private int time;
	
	public InvalidFireTimeException(int time) {
		this.time = time;
	}
	
	public int getTriedTime(){
		return this.time;
	}
	
}
