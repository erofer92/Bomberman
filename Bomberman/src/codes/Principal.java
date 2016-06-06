package codes;

public class Principal {
	
	public static void main(String[] args){
		
		MyJFrame game = new MyJFrame();
		Thread t = new Thread(game);
		t.start();
		
	}
}
