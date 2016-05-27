package codes;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Principal extends JFrame{
	
	private Cenario cenario;
	private Player player;
	private Moving moving;
	private int wls, i, j;
	
	Principal(){
		player = new Player(50, 50);
		moving = new Moving(player);
		cenario = new Cenario();
		
		//this.setBounds(this.cenario.getRectangle());
		this.setBounds(0, 0,
					  (int)cenario.getRectangle().getWidth() + 16,
					  (int)cenario.getRectangle().getHeight() + 39);
		this.setTitle("MyGame");
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(player.getJLabel());
		this.addKeyListener(moving);
		//inserindo Walls no mapa
		for(j = 0; j < this.cenario.getWalls().size(); j++){
			this.add(this.cenario.getWalls().get(j).getJLabel());
		}
		//inserindo Grounds no mapa
		for(i = 0; i < this.cenario.getGrounds().size(); i++){
			this.add(this.cenario.getGrounds().get(i).getJLabel());
		}
	}
	
	public static void main(String[] args){
		Principal game = new Principal();
	}
}
