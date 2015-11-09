import java.util.Vector;

public class DiccMiLista implements Diccionario {
	private int nlenguas;
	private Vector<Character> lenguas;
	private NodoL dicc;
	
	public DiccMiLista(){
		
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

	public static class NodoL {
        private Palabra2 pal;
        private NodoL next;

        public NodoL(){
            pal = null;
            next = null;
        }
        public NodoL(Palabra2 p){
            pal = p;
            next = null;
        }
        public void cambiaNext(NodoL n){
            next = n;
        }
        public void setPalabra2(Palabra2 p){
            pal = p;
        }
        public NodoL getNext(){
            return next;
        }
        public Palabra2 getPalabra2(){
            return pal;
        }
    }
}
