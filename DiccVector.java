import java.util.Vector;

public class DiccVector implements Diccionario {
	private int nlenguas;
	private Vector<Character> lenguas;
	private Vector<Palabra2> dicc;
	public DiccVector(){
		nlenguas = -1;
		lenguas = new Vector<Character> (null);
		dicc = new Vector<Palabra2> (null);
	}
	
	public void leeDiccionario(String f) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean inserta(Palabra2 p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean borra(String s) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int busca(String s) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String traduce(String s, char l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void visualiza() {
		// TODO Auto-generated method stub
		
	}
	
	public void visualiza(int j) {
		// TODO Auto-generated method stub
		
	}
	
	public void visualiza(int j, char l) {
		// TODO Auto-generated method stub
		
	}
}
