package codes;

import java.awt.Point;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame implements Runnable{
	
	private Cenario cenario;
	private Player player;
	private Moving moving;
	private int WaG;
	
	public MyJFrame(){
		Point p = new Point(0, 0);
		player = new Player(p, null, "Player 1");
		cenario = new Cenario();
		moving = new Moving(player, cenario, this);
		cenario.start();
		this.setTitle("Bomberman");
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10,
					  cenario.getRectangle().width + 6,
					  cenario.getRectangle().height + 29);
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
		this.setVisible(true);
	}
	
	public void stop(){
		this.removeKeyListener(moving);
	}
	
	@Override
	public void run() {
		while(true)
			this.repaint();
	}
	
}
