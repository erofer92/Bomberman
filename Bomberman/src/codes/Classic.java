package codes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Classic extends JFrame implements Runnable{

	private Player player;
	private Object map[][];
	private ImageIcon breakableWall;
	private ImageIcon solidWall;
	private Rectangle mapDimension;
	private ArrayList<Bomb> bombs;
	private Moving moving;
	private ArrayList<ArrayList<Explosion>> explosions;
	
	public Classic(int width, int height){
		player = new Player(new Point(50, 50));
		setDefaultProperties(width, height);
		adjustMap();
		addJLabels();
		//Thread thread = new Thread(this.player);
		//thread.start();
	}
	
	private void setDefaultProperties(int width, int height){
		this.mapDimension = new Rectangle(width, height);
		this.setTitle("Bomberman");
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(50, 100, 50));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 10,
						(int)(this.mapDimension.width * player.getRectangle().getWidth() + 6),
						(int)(this.mapDimension.height * player.getRectangle().getHeight()) + 29);
		this.map = new Object[width][height];
		this.breakableWall = new ImageIcon(getClass().getResource("/images/breakableWall.png"));
		this.solidWall = new ImageIcon(getClass().getResource("/images/solidWall.png"));
		this.bombs = new ArrayList<Bomb>();
		this.moving = new Moving(this);
		this.addKeyListener(this.moving);
		this.explosions = new ArrayList<ArrayList<Explosion>>();
	}
	
	private void adjustMap(){
		//Both width and height must be pairs and greater then 3!
		int x, y;
		for(y = 0; y < this.mapDimension.height; y++){
			for(x = 0; x < this.mapDimension.width; x++){
				if( (y == 1 && x > 0 && x < 3) ||
					(y == 2 && x == 1)){
					this.map[x][y] = null;
					continue;
				}
				if((y == 0 || y == this.mapDimension.height - 1) ||
					(x == 0 || x == this.mapDimension.width - 1) ||
					(y % 2 == 0 && x % 2 == 0)){
					this.map[x][y] = new SolidWall(x * this.solidWall.getIconWidth(),
													y * this.solidWall.getIconHeight(),
													this.solidWall);
					continue;
				}
				
				this.map[x][y] = new BreakableWall(x * this.breakableWall.getIconWidth(),
													y * this.breakableWall.getIconHeight(),
													this.breakableWall);
			}
		}
	}

	private void addJLabels(){
		int x, y;
		
		this.add(player.getJLabel());
		
		for(y = 0; y < this.mapDimension.height; y++){
			for(x = 0; x < this.mapDimension.width; x++){
				if( this.map[x][y] != null)
					this.add( ((Wall)map[x][y]).getJLabel() );
			}
		}
	}

	public Player getPlayer(){
		return this.player;
	}
	
	public ArrayList<Bomb> getBombs(){
		return this.bombs;
	}
	
	public Object[][] getMap(){
		return this.map;
	}
	
	private void spread(Bomb b){
		int range, auxX, auxY;
		ArrayList<Explosion> exs = new ArrayList<Explosion>();
		Explosion ex;
		Point p = new Point(b.getRectangle().getLocation());
		p.x = (int)(p.x / b.getRectangle().getWidth());
		p.y = (int)(p.y / b.getRectangle().getHeight());
		auxX = p.x;
		auxY = p.y;
		Point p2 = new Point(b.getRectangle().getLocation());
		
		range = b.getRange();
		
		// Adiciona a Explosão Central
		try{
			ex = new Explosion(p2, "Central");
			this.map[p.x][p.y] = ex;
			this.add(ex.getJLabel());
			exs.add(ex);
		}
		catch(InvalidExplosionTypeException e){
			//e.printStackTrace();
			System.out.println("InvalidType: " + e.getExplosionType());
		}
		
		// Adicionando Explosões à Direita.
		for(p.x++, p2.x += b.getRectangle().getWidth();
			!(map[p.x][p.y] instanceof Wall) &&
			(p2.x < range);
			p.x++, p2.x += b.getRectangle().getWidth()){
			try{
				ex = new Explosion(p2, "Horizontal");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		if(!(map[p.x][p.y] instanceof SolidWall)){
			
			if(this.map[p.x][p.y] instanceof BreakableWall)
				this.remove( ((BreakableWall)(this.map[p.x][p.y])).getJLabel() );
			
			try{
				ex = new Explosion(p2, "Right");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		
		// Adicionando Explosões à Esquerda.
		p.x = auxX;
		p2.setLocation(b.getRectangle().getLocation());
		for(p.x--, p2.x -= b.getRectangle().getWidth();
			!(map[p.x][p.y] instanceof Wall) &&
			(p2.x > range);
			p.x--, p2.x -= b.getRectangle().getWidth()){
			try{
				ex = new Explosion(p2, "Horizontal");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		if(!(map[p.x][p.y] instanceof SolidWall)){

			if(this.map[p.x][p.y] instanceof BreakableWall)
				this.remove( ((BreakableWall)(this.map[p.x][p.y])).getJLabel() );
			
			try{
				ex = new Explosion(p2, "Left");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}

		// Adicionando Explosões em Cima.
		p.x = auxX;
		p2.setLocation(b.getRectangle().getLocation());
		for(p.y--, p2.y -= b.getRectangle().getHeight();
			!(map[p.x][p.y] instanceof Wall) &&
			(p2.y > range);
			p.y--, p2.y -= b.getRectangle().getHeight()){
			try{
				ex = new Explosion(p2, "Vertical");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		if(!(map[p.x][p.y] instanceof SolidWall)){

			if(this.map[p.x][p.y] instanceof BreakableWall)
				this.remove( ((BreakableWall)(this.map[p.x][p.y])).getJLabel() );
			
			try{
				ex = new Explosion(p2, "Up");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		
		// Adicionando Explosões em Baixo.
		p.y = auxY;
		p2.setLocation(b.getRectangle().getLocation());
		for(p.y++, p2.y += b.getRectangle().getHeight();
			!(map[p.x][p.y] instanceof Wall) &&
			(p2.y < range);
			p.y++, p2.y += b.getRectangle().getHeight()){
			try{
				ex = new Explosion(p2, "Vertical");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		if(!(map[p.x][p.y] instanceof SolidWall)){

			if(this.map[p.x][p.y] instanceof BreakableWall)
				this.remove( ((BreakableWall)(this.map[p.x][p.y])).getJLabel() );
			
			try{
				ex = new Explosion(p2, "Down");
				this.map[p.x][p.y] = ex;
				this.add(ex.getJLabel());
				exs.add(ex);
			}
			catch(InvalidExplosionTypeException e){
				//e.printStackTrace();
				System.out.println("InvalidType: " + e.getExplosionType());
			}
		}
		
		this.explosions.add(exs);
	}
	
	private void checkExplosions(){
		Point p;
		if(this.bombs.size() > 0){
			if(this.bombs.get(0).isExploded()){
				p = new Point(this.bombs.get(0).getRectangle().getLocation());
				p.x = (int) (p.x / this.bombs.get(0).getRectangle().getWidth()); 
				p.y = (int) (p.y / this.bombs.get(0).getRectangle().getHeight());
				this.remove(this.bombs.get(0).getJLabel());
				this.spread(this.bombs.get(0));
				this.bombs.remove(0);
				this.map[p.x][p.y] = null;
				this.player.addBomb();
			}
		}
		if(this.explosions.size() > 0){
			if(this.explosions.get(0).get(0).isFadedOut()){
				this.removeExplosions(this.explosions.get(0));
				this.explosions.remove(0);
			}
		}
	}
	
	private void removeExplosions(ArrayList<Explosion> ex){
		int i;
		Point p = new Point();
		
		for(i = 0; i < ex.size(); i++){
			p.x = (int)(ex.get(i).getRectangle().x / ex.get(i).getRectangle().getWidth());
			p.y = (int)(ex.get(i).getRectangle().y / ex.get(i).getRectangle().getHeight());
			
			this.remove(ex.get(i).getJLabel());
			this.map[p.x][p.y] = null;
		}
	}
		
	@Override
	public void run(){
		
		Point p = new Point();
		
		while(this.player.isAlive()){
			checkExplosions();
			
			p.setLocation((int)(this.player.getRectangle().x / this.player.getRectangle().width),
						  (int)(this.player.getRectangle().y / this.player.getRectangle().height));
			
			if(this.map[p.x][p.y] instanceof Explosion)
				this.player.kill();
			
			this.repaint();
		}
		this.removeKeyListener(this.moving);
	}
	
}

