package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb implements Runnable{
	
	private Rectangle rectangle;
	private ImageIcon imgBomb;
	private JLabel jlabel;
	private Explosion explosion;
	private int explosionRange;
	private int timeToExplode; // in milliseconds
	private int explosionFireTime; // time to fire stay burning after explosion happens
	private boolean exploded;
	private Cenario cenario;
	
	Bomb(int x, int y){
		this.exploded = false;
		this.imgBomb = new ImageIcon(getClass().getResource("/images/bomb.png"));
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.explosion = new Explosion(x, y, this.explosionRange);
		this.jlabel = new JLabel(imgBomb);
		this.jlabel.setBounds(rectangle);
		this.explosionRange = 50;
		this.timeToExplode = 3000;
		Thread t = new Thread(this);
		t.start();
	}
	
	// Configura o local onde a bomba será explodida
	// o Cenario recebido como parâmetro é utilizado para coordenar a explosão.
	public void whereToExplode(Cenario cenario){
		this.cenario = cenario;
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
	
	// Explode a bomba, criando uma nova explosão e preenche o seu alcance com fogo
	public void explode(){
		this.explosion = new Explosion( (int)this.getRectangle().getX(),
										(int) this.getRectangle().getY(),
										this.explosionRange);
		this.explosion.adjustExplosion(this.cenario);
		this.exploded = true;
	}
	
	// Retorna True se a bomba estiver explodido
	public boolean exploded(){
		return this.exploded;
	}
	
	// Inicia a contagem para a explosão
	@Override 
	public void run() {
		try
		{Thread.sleep(this.timeToExplode);}
		catch (InterruptedException e)
		{e.printStackTrace();}
		
		explode();
	}
	
	
	
	
	
	
}
