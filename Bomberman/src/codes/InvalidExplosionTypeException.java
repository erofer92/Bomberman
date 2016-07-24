package codes;

@SuppressWarnings("serial")
public class InvalidExplosionTypeException extends Exception {
	
	private String explosiontype;
	
	public InvalidExplosionTypeException(String explosiontype){
		this.explosiontype = explosiontype;
	}
	
	public String getExplosionType(){
		return this.explosiontype;
	}
	
}
