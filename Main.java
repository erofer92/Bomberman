package codes;

public class Main {

	public static void main(String[] args) {
		
		Classic game = new Classic(13, 13);
		Thread thread = new Thread(game);
		thread.start();
		
	}
}
