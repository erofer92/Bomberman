package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Principal extends JFrame implements Serializable{
	
	private Cenario cenario;
	private Player player;
	private Moving moving;
	private int WaG;
	
	Principal(){
		player = new Player(50, 50);
		cenario = new Cenario();
		moving = new Moving(player, cenario, this);
		
		//this.setBounds(this.cenario.getRectangle());
		this.setBounds(0, 0,
					  (int)cenario.getRectangle().getWidth() + 6,
					  (int)cenario.getRectangle().getHeight() + 29);
		this.setTitle("MyGame");
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(player.getJLabel());
		this.addKeyListener(moving);
		// add Walls on map
		for(WaG = 0; WaG < this.cenario.getWalls().size(); WaG++){
			this.add(this.cenario.getWalls().get(WaG).getJLabel());
		}
		// add Grounds on map
		for(WaG = 0; WaG < this.cenario.getGrounds().size(); WaG++){
			this.add(this.cenario.getGrounds().get(WaG).getJLabel());
		}
	}
	
	public static void main(String[] args){
		Principal game = new Principal();
		String mainDesktop = new String("C:\\Users\\Eron\\Google Drive\\workspace Java\\Bomberman\\src\\files\\Statistics.txt");
		//String ffbDesktop = new String("C:\\Users\\1510522\\Desktop\\Bomberman\\src\\files\\Statistics.txt");
		
		File f = new File(mainDesktop);
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.close();
			fos.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
