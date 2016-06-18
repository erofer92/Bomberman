package codes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb implements Runnable {

	private int range;
	private int timeToExplode; // in milliseconds
	private int fireTime; // time to fire stay burning after explosion happens (in milliseconds)
	private boolean exploded;
	private static ImageIcon imgBomb;
	private ImageIcon centralExplosion;
	private ImageIcon rightExplosion;
	private ImageIcon leftExplosion;
	private ImageIcon upperExplosion;
	private ImageIcon bottomExplosion;
	private ImageIcon verticalExplosion;
	private ImageIcon horizontalExplosion;
	private Rectangle rectangle;
	private JLabel jlabel;
	private ArrayList<Explosion> explosions;
	
	public Bomb(Point p){
		setDefaultConfiguration(p);
	}
	
	// Este método serve simplesmente para deixar o Construtor mais limpo.
	private void setDefaultConfiguration(Point p){
		this.centralExplosion = new ImageIcon(getClass().getResource("/images/centerExplosion.png"));
		this.rightExplosion = new ImageIcon(getClass().getResource("/images/rightExplosion.png"));
		this.leftExplosion = new ImageIcon(getClass().getResource("/images/leftExplosion.png"));
		this.upperExplosion = new ImageIcon(getClass().getResource("/images/upperExplosion.png"));
		this.bottomExplosion = new ImageIcon(getClass().getResource("/images/bottomExplosion.png"));
		this.horizontalExplosion = new ImageIcon(getClass().getResource("/images/horizontalExplosion.png"));
		this.verticalExplosion = new ImageIcon(getClass().getResource("/images/verticalExplosion.png"));
		Bomb.imgBomb = new ImageIcon(getClass().getResource("/images/bomb.png"));
		this.rectangle = new Rectangle(p.x, p.y, imgBomb.getIconWidth(), imgBomb.getIconHeight());
		this.jlabel = new JLabel(imgBomb);
		this.jlabel.setBounds(rectangle);
		this.range = this.rectangle.width;
		this.timeToExplode = 3000;
		this.fireTime = 1000;
		this.exploded = false;
		this.explosions = new ArrayList<Explosion>();
	}

	// Configura o local da bomba.
	// o Ponto indica o canto superior esquerdo.
	public void setLocation(Point p){
		this.rectangle.setLocation(p);
	}
	
	/*
	public static void setImageIcon(ImageIcon img){
		Bomb.imgBomb = img;
	}
	*/
	
	// Retorna o Retângulo da Bomba
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	// Retorna a JLabel da Bomba
	public JLabel getJLabel(){
		return jlabel;
	}
	
	// Este método aumenta o alcance da explosão.
	// O alcance máximo é 500 (10 blocos de distância em relação ao centro da explosão).
	// O alcance máximo não será alterado se houver tentativa de aumentá-lo.
	public void increaseExplosionRange(){
		if(this.range < this.rectangle.x * 15)
			this.range += this.rectangle.x;
	}
	
	// Este método diminui o alcance da explosão.
	// O alcance mínimo é 50 (um bloco de distância em relação ao centro da explosão).
	// O alcance mínimo não será alterado se houver tentativa de diminuí-lo.
	public void decreaseExplosionRange(){
		if(this.range > this.rectangle.x)
			this.range -= this.rectangle.x;
	}
	
	// Retorna o alcance da explosão da bomba.
	// Por padrão, o Range é iniciado com o mesmo tamanho da bomba.
	// No caso, 50.
	public int getRange(){
		return this.range;
	}
		
	// Este método configura o tempo que a bomba leva para explodir (milliseconds).
	// Caso o tempo seja negativo, ele será definido como zero.
	public void setTimeToExplode(int timeToExplode){
		if(timeToExplode > 0)
			this.timeToExplode = timeToExplode;
		else
			this.timeToExplode = 0;
	}
	
	// Este método retorna o tempo que a bomba leva para explodir (milliseconds).
	public int getTimeToExplode() {
		return timeToExplode;
	}
	
	// Este método configura o tempo que o fogo da explosão demora para sumir (milliseconds).
	public void setFireTime(int fireTime){
		Explosion.setFireTime(fireTime);
	}
	
	// Este método retorna o tempo que o fogo da explosão demora para sumir (milliseconds).
	public int getFireTime(){
		return this.fireTime;
	}
	
	// Este método retorna true se a bomba já tiver explodido.
	public boolean isExploded(){
		return this.exploded;
	}
	
	// Este método força a bomba a explodir ou não e retorna o próprio boolean passado. 
	public boolean isExploded(boolean b){
		this.exploded = b;
		return this.exploded;
	}
	
	// Este método explode a bomba e configura até onde as explosões irão alcançar.
	private void setExplosionRange(Point pRight, Point pLeft, Point pUp, Point pDown){
		Explosion explosion;
		Point p;
		
		explosion = new Explosion(this.rectangle.getLocation(), this.centralExplosion);
		this.explosions.add(explosion);
		
		for(p = this.rectangle.getLocation(); p.x < pRight.x; p.x += this.rectangle.width){
			explosion = new Explosion(p, horizontalExplosion);
			this.explosions.add(explosion);
		}
		explosion = new Explosion(p, rightExplosion);
		this.explosions.add(explosion);
		
		for(p = this.rectangle.getLocation(); p.x > pLeft.x; p.x -= this.rectangle.width){
			explosion = new Explosion(p, horizontalExplosion);
			this.explosions.add(explosion);
		}
		explosion = new Explosion(p, leftExplosion);
		this.explosions.add(explosion);
		
		for(p = this.rectangle.getLocation(); p.y > pUp.y; p.y -= this.rectangle.height){
			explosion = new Explosion(p, verticalExplosion);
			this.explosions.add(explosion);
		}
		explosion = new Explosion(p, upperExplosion);
		this.explosions.add(explosion);
		
		for(p = this.rectangle.getLocation(); p.y < pDown.x; p.x += this.rectangle.height){
			explosion = new Explosion(p, verticalExplosion);
			this.explosions.add(explosion);
		}
		explosion = new Explosion(p, bottomExplosion);
		this.explosions.add(explosion);
	}
	
	// Este método configura a explosão com o método setExplosionRange() e então
	// coloca a Thread a disposição do escalonador.
	// O método run é executado e a bomba é explodida.
	// Este método retorna o próprio ArrayList de Explosões da bomba.
	// Não deveria retornar o próprio Array, mas por enquanto será assim.
	public ArrayList<Explosion> explode(Point pRight, Point pLeft, Point pUp, Point pDown){
		this.setExplosionRange(pRight, pLeft, pUp, pDown);
		Thread t = new Thread(this);
		t.start();
		return this.explosions;
	}
	
	// Inicia a contagem para a explosão e então explode a bomba
	public void run() {
		try{
			Thread.sleep(this.timeToExplode);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		this.isExploded(true);
	}
}
