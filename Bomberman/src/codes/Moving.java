package codes;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Moving implements KeyListener{
	
	private Player player;
	private Classic jframe;
	
	public Moving(Classic jframe){
		this.jframe = jframe;
		this.player = this.jframe.getPlayer();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		Point p = new Point(this.player.getRectangle().getLocation());
		p.x = (int)(this.player.getRectangle().getX() / this.player.getRectangle().width);
		p.y = (int)(this.player.getRectangle().getY() / this.player.getRectangle().height);
		
		switch(e.getKeyCode()){
			case 39: // Move Right when no wall and bomb are detected
				if(this.jframe.getMap()[p.x + 1][p.y] == null ||
					this.jframe.getMap()[p.x + 1][p.y] instanceof Explosion)
					this.player.goRight();
				break;
				
			case 37: // Move Left when no wall and bomb are detected
				if(this.jframe.getMap()[p.x - 1][p.y] == null ||
					this.jframe.getMap()[p.x - 1][p.y] instanceof Explosion)
					this.player.goLeft();
				break;
				
			case 38: // Move Up when no wall and bomb are detected
				if(this.jframe.getMap()[p.x][p.y - 1] == null ||
					this.jframe.getMap()[p.x][p.y - 1] instanceof Explosion)
					this.player.goUp();
				break;
				
			case 40: // Move Down when no wall and bomb are detected
				if(this.jframe.getMap()[p.x][p.y + 1] == null ||
					this.jframe.getMap()[p.x][p.y + 1] instanceof Explosion)
					this.player.goDown();
				break;
			case 32: //Space to Plant a Bomb
				try {
					Bomb b = player.plantBomb();
					this.jframe.getMap()[p.x][p.y] = b;
					p.setLocation(this.player.getRectangle().getLocation());
					b.setLocation(p);
					this.jframe.getBombs().add(b);
					this.jframe.add(b.getJLabel());
					Thread thread = new Thread(b);
					thread.start();
				}
				catch (MaxBombPlantedException e1) {
					System.out.println("MaxBombPlanted!");
					//e1.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid Key!");
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
