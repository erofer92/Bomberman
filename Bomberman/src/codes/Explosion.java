package codes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion implements Runnable{
	
	private Rectangle rectangle;
	private ImageIcon upperExplosion;
	private ImageIcon bottomExplosion;
	private ImageIcon leftExplosion;
	private ImageIcon rightExplosion;
	private ImageIcon verticalExplosion;
	private ImageIcon horizontalExplosion;
	private ImageIcon centerExplosion;
	private JLabel jlabel;
	private int explosionRange;
	private int fireTime; // in milliseconds
	private ArrayList<JLabel> explosionLabels;

	Explosion(int x, int y, int explosionRange){
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.explosionRange = explosionRange;
		this.upperExplosion = new ImageIcon(getClass().getResource("/images/upperExplosion.png"));
		this.bottomExplosion = new ImageIcon(getClass().getResource("/images/bottomExplosion.png"));
		this.leftExplosion = new ImageIcon(getClass().getResource("/images/leftExplosion.png"));
		this.rightExplosion = new ImageIcon(getClass().getResource("/images/rightExplosion.png"));
		this.verticalExplosion = new ImageIcon(getClass().getResource("/images/verticalExplosion.png"));
		this.horizontalExplosion = new ImageIcon(getClass().getResource("/images/horizontalExplosion.png"));
		this.centerExplosion = new ImageIcon(getClass().getResource("/images/centerExplosion.png"));
		this.jlabel = new JLabel(centerExplosion);
		this.explosionLabels = new ArrayList<JLabel>();
		
		Thread t = new Thread(this);
		t.start();
	}
	
	// Ajusta o fogo da explosão para não atravessar paredes
	// Ainda está imcompleto
	public void adjustExplosion(Cenario cenario){
		int xrange, rightWall = 0, leftWall = 0, upWall = 0, downWall = 0;
		Iterator<Wall> iteratorWall = cenario.getWalls().iterator();
		this.jlabel.setBounds(this.rectangle);
		
		// Coordena a explosão
		for(xrange = 0;	xrange < this.explosionRange; xrange+=50){
			while(iteratorWall.hasNext()){
				// Coordena a explosão para a direita
				if( (int)(this.rectangle.getMaxX()) ==
					(int)(iteratorWall.next().getRectangle().getX()) &&
					(int)(this.rectangle.getY()) ==
					(int)(iteratorWall.next().getRectangle().getY())){
					rightWall++;
					continue;
				}
				// Coordena a explosão para a esquerda
				if( (int)(this.rectangle.getX()) ==
					(int)(iteratorWall.next().getRectangle().getMaxX()) &&
					(int)(this.rectangle.getY()) ==
					(int)(iteratorWall.next().getRectangle().getY())){
					leftWall++;
					continue;
				}
				// Coordena a explosão para cima
				if( (int)(this.rectangle.getX()) ==
					(int)(iteratorWall.next().getRectangle().getX()) &&
					(int)(this.rectangle.getY()) ==
					(int)(iteratorWall.next().getRectangle().getMaxY())){
					upWall++;
					continue;
				}
				// Coordena a explosão para baixo
				if( (int)(this.rectangle.getX()) ==
					(int)(iteratorWall.next().getRectangle().getX()) &&
					(int)(this.rectangle.getMaxY()) ==
					(int)(iteratorWall.next().getRectangle().getY())){
					downWall++;
					continue;
				}
			}
			
			if(rightWall == 0){
				if(xrange == explosionRange - 50){
					//colocar rightExplosion
				}
				else{
					//colocar horizontalExplosion ou rightExplosion
				}
			}
			if(leftWall == 0){
				if(xrange == explosionRange - 50){
					//colocar leftExplosion
				}
				else{
					//colocar horizontalExplosion
				}
			}
			if(upWall == 0){
				if(xrange == explosionRange - 50){
					//colocar upperExplosion
				}
				else{
					//colocar verticalExplosion
				}
			}
			if(downWall == 0){
				if(xrange == explosionRange - 50){
					//colocar bottomExplosion
				}
				else{
					//colocar verticalExplosion
				}
			}
		}
	}
	
	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}

	@Override
	public void run() {
		
		try
		{Thread.sleep(this.fireTime);}
		catch(InterruptedException e)
		{e.printStackTrace();}
		// Depois do fireTime, o fogo deve desaparecer do cenário
	}
}
