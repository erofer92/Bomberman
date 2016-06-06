package codes;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bomb implements Runnable{
	
	private Rectangle rectangle;
	private ImageIcon imgBomb;
	private JLabel jlabel;
	private int explosionRange;
	private int timeToExplode; // in milliseconds
	private int fireTime; // time to fire stay burning after explosion happens
	private boolean exploded;
	private Cenario cenario;
	private JFrame jframe;
	private ArrayList<Explosion> explosions;
	private ImageIcon upperExplosion;
	private ImageIcon bottomExplosion;
	private ImageIcon leftExplosion;
	private ImageIcon rightExplosion;
	private ImageIcon verticalExplosion;
	private ImageIcon horizontalExplosion;
	private ImageIcon centerExplosion;
	
	public Bomb(int x, int y){
		this.upperExplosion = new ImageIcon(getClass().getResource("/images/upperExplosion.png"));
		this.bottomExplosion = new ImageIcon(getClass().getResource("/images/bottomExplosion.png"));
		this.leftExplosion = new ImageIcon(getClass().getResource("/images/leftExplosion.png"));
		this.rightExplosion = new ImageIcon(getClass().getResource("/images/rightExplosion.png"));
		this.verticalExplosion = new ImageIcon(getClass().getResource("/images/verticalExplosion.png"));
		this.horizontalExplosion = new ImageIcon(getClass().getResource("/images/horizontalExplosion.png"));
		this.centerExplosion = new ImageIcon(getClass().getResource("/images/centerExplosion.png"));
		this.imgBomb = new ImageIcon(getClass().getResource("/images/bomb.png"));
		this.explosions = new ArrayList<Explosion>();
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.jlabel = new JLabel(imgBomb);
		this.jlabel.setBounds(rectangle);
		this.timeToExplode = 3000;
		this.explosionRange = 150;
		this.exploded = false;
	}
	
	// Configura o local onde a bomba será explodida
	// o Cenario e o JFrame recebidos como parâmetros são utilizados para coordenar a explosão.
	public void whereAmI(Cenario cenario, JFrame jframe){
		this.cenario = cenario;
		this.jframe = jframe;
	}
	
	public ArrayList<Explosion> getExplosions(){
		return this.explosions;
	}
	
	// Retorna o alcance da explosão da bomba
	public int getExplosionRange(){
		return this.explosionRange;
	}
	
	// Retorna o Retângulo da Bomba
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	// Retorna a JLabel da Bomba
	public JLabel getJLabel(){
		return jlabel;
	}

	// Diminui o alcance da explosão
	// O alcance máximo é 200 (quatro blocos de distância em relação ao centro da explosão)
	public void increaseExplosionRange() throws MaxRangeReachedException{
		if(this.explosionRange < 200)
			this.explosionRange += 50;
		else
			throw new MaxRangeReachedException();
	}
	
	// Diminui o alcance da explosão
	// O alcance mínimo é 50 (um bloco de distância em relação ao centro da explosão)
	public void decreaseExplosionRange() throws MinRangeReachedException{
		if(this.explosionRange > 50)
			this.explosionRange -= 50;
		else
			throw new MinRangeReachedException();
	}
	
	// Retorna o tempo que a bomba leva para explodir
	public int getTimeToExplode() {
		return timeToExplode;
	}
	
	// Configura o tempo até que a explosão ocorra
	public void setTimeToExplode(int timeToExplode) {
		this.timeToExplode = timeToExplode;
	}
	
	public void setFireTime(int fireTime){
		this.fireTime = fireTime;
	}
	
	public int getFireTime(){
		return this.fireTime;
	}
	
	// Retorna True se a bomba explodiu
	public boolean isExploded(){
		return this.exploded;
	}
	
	private void explode(){
		Explosion explosion;
		Thread thread;
		int i, range, size;
		size = this.cenario.getWalls().size();
		boolean temParedeNaDireita, temParedeNaEsquerda, temParedeEmCima, temParedeEmBaixo;
		boolean acabouDireita, acabouEsquerda, acabouCima, acabouBaixo;

		acabouDireita = false;
		acabouEsquerda = false;
		acabouCima = false;
		acabouBaixo = false;
		
		explosion = new Explosion(this.rectangle.x, this.rectangle.y);
		explosion.setImageIcon(centerExplosion);
		this.explosions.add(explosion);
		this.jframe.add(explosion.getJLabel(), 4);
		thread = new Thread(explosion);
		thread.start();

		
		for(range = 50; range <= this.explosionRange; range += 50){
			
			temParedeNaDireita = false;
			temParedeNaEsquerda = false;
			temParedeEmCima = false;
			temParedeEmBaixo = false;
			
			for(i = 0; i < size; i++){
				
				if(this.rectangle.x + range == this.cenario.getWalls().get(i).getRectangle().x &&
					this.rectangle.y == this.cenario.getWalls().get(i).getRectangle().y &&
					!acabouDireita){
					
					if(this.cenario.getWalls().get(i) instanceof BreakableWall){
						this.jframe.remove(this.cenario.getWalls().get(i).getJLabel());
						this.cenario.getWalls().remove(i);
						size--;
						Ground g = new Ground(this.rectangle.x + range, this.rectangle.y);
						this.cenario.getGrounds().add(g);
						this.jframe.add(g.getJLabel(),1);
						
						explosion = new Explosion(this.rectangle.x + range, this.rectangle.y);
						
						if(range == this.explosionRange)
							explosion.setImageIcon(rightExplosion);
						else
							explosion.setImageIcon(horizontalExplosion);
						
						this.explosions.add(explosion);
						this.jframe.add(explosion.getJLabel(), 4);
						thread = new Thread(explosion);
						thread.start();
					}
					acabouDireita = true;
					temParedeNaDireita = true;
					continue;
				}
				if(this.rectangle.x - range == this.cenario.getWalls().get(i).getRectangle().x &&
					this.rectangle.y == this.cenario.getWalls().get(i).getRectangle().y &&
					!acabouEsquerda){
					
						if(this.cenario.getWalls().get(i) instanceof BreakableWall){
						this.jframe.remove(this.cenario.getWalls().get(i).getJLabel());
						this.cenario.getWalls().remove(i);
						size--;
						Ground g = new Ground(this.rectangle.x - range, this.rectangle.y);
						this.cenario.getGrounds().add(g);
						this.jframe.add(g.getJLabel(), 1);
						
						explosion = new Explosion(this.rectangle.x - range, this.rectangle.y);
						
						if(range == this.explosionRange)
							explosion.setImageIcon(leftExplosion);
						else
							explosion.setImageIcon(horizontalExplosion);
						
						this.explosions.add(explosion);
						this.jframe.add(explosion.getJLabel(), 4);
						thread = new Thread(explosion);
						thread.start();
					}
					acabouEsquerda = true;
					temParedeNaEsquerda = true;
					continue;
				}
				if(this.rectangle.x == this.cenario.getWalls().get(i).getRectangle().x &&
					this.rectangle.y - range == this.cenario.getWalls().get(i).getRectangle().y &&
					!acabouCima){
					
						if(this.cenario.getWalls().get(i) instanceof BreakableWall){
						this.jframe.remove(this.cenario.getWalls().get(i).getJLabel());
						this.cenario.getWalls().remove(i);
						size--;
						Ground g = new Ground(this.rectangle.x, this.rectangle.y - range);
						this.cenario.getGrounds().add(g);
						this.jframe.add(g.getJLabel(), 1);
						
						explosion = new Explosion(this.rectangle.x, this.rectangle.y - range);
						
						if(range == this.explosionRange)
							explosion.setImageIcon(upperExplosion);
						else
							explosion.setImageIcon(verticalExplosion);
						
						this.explosions.add(explosion);
						this.jframe.add(explosion.getJLabel(), 4);
						thread = new Thread(explosion);
						thread.start();
					}
					acabouCima = true;
					temParedeEmCima = true;
					continue;
				}
				if(this.rectangle.x == this.cenario.getWalls().get(i).getRectangle().x &&
					this.rectangle.y + range == this.cenario.getWalls().get(i).getRectangle().y &&
					!acabouBaixo){
					
					if(this.cenario.getWalls().get(i) instanceof BreakableWall){
						this.jframe.remove(this.cenario.getWalls().get(i).getJLabel());
						this.cenario.getWalls().remove(i);
						size--;
						Ground g = new Ground(this.rectangle.x, this.rectangle.y + range);
						this.cenario.getGrounds().add(g);
						this.jframe.add(g.getJLabel(), 1);
						
						explosion = new Explosion(this.rectangle.x, this.rectangle.y + range);
						
						if(range == this.explosionRange)
							explosion.setImageIcon(bottomExplosion);
						else
							explosion.setImageIcon(verticalExplosion);
						
						this.explosions.add(explosion);
						this.jframe.add(explosion.getJLabel(), 4);
						thread = new Thread(explosion);
						thread.start();
					}
					acabouBaixo = true;
					temParedeEmBaixo = true;
					continue;
				}
			}

			if(!temParedeNaDireita && !acabouDireita){
					explosion = new Explosion(this.rectangle.x + range, this.rectangle.y);
						
					if(range == this.explosionRange)
						explosion.setImageIcon(rightExplosion);
					else
						explosion.setImageIcon(horizontalExplosion);

					this.explosions.add(explosion);
					this.jframe.add(explosion.getJLabel(), 4);
					thread = new Thread(explosion);
					thread.start();
			}
			if(!temParedeNaEsquerda && !acabouEsquerda){
					explosion = new Explosion(this.rectangle.x - range, this.rectangle.y);
						
					if(range == this.explosionRange)
						explosion.setImageIcon(leftExplosion);
					else
						explosion.setImageIcon(horizontalExplosion);
	
					this.explosions.add(explosion);
					this.jframe.add(explosion.getJLabel(), 4);
					thread = new Thread(explosion);
					thread.start();
			}
			if(!temParedeEmCima && !acabouCima){
					explosion = new Explosion(this.rectangle.x, this.rectangle.y - range);
						
					if(range == this.explosionRange)
						explosion.setImageIcon(upperExplosion);
					else
						explosion.setImageIcon(verticalExplosion);
	
					this.explosions.add(explosion);
					this.jframe.add(explosion.getJLabel(), 4);
					thread = new Thread(explosion);
					thread.start();
			}
			if(!temParedeEmBaixo && !acabouBaixo){
					explosion = new Explosion(this.rectangle.x, this.rectangle.y + range);
						
					if(range == this.explosionRange)
						explosion.setImageIcon(bottomExplosion);
					else
						explosion.setImageIcon(verticalExplosion);
	
					this.explosions.add(explosion);
					this.jframe.add(explosion.getJLabel(), 4);
					thread = new Thread(explosion);
					thread.start();
			}
			if(acabouDireita && acabouEsquerda && acabouCima && acabouBaixo)
				break;
		}
		
		this.exploded = true;
	}
	
	// Inicia a contagem para a explosão
	@Override 
	public void run() {
		
		try
		{Thread.sleep(this.timeToExplode);}
		catch (InterruptedException e)
		{e.printStackTrace();}
		
		explode();
		System.out.println("bomba explodiu");
		
	}
}
