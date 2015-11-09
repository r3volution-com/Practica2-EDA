import java.util.Vector;

public class Palabra2 {
	private char[] lenguas;
	private String origen;
	private Vector<String> trad;
	public Palabra2 (String p, char[] lenguas){
		origen = p;
		if (lenguas == null) this.lenguas = new char[]{'E', 'F', 'P'};
		else {
			this.lenguas = new char[lenguas.length];
			for (int i = 0; i < lenguas.length; i++) {
				this.lenguas[i] = lenguas[i];
			}
		}
		trad = new Vector<String>(this.lenguas.length);
		for (int i = 0; i< this.lenguas.length; i++){
			trad.addElement(null);
		}
	}
	public int setTrad(String t, char l){
		if (t != null && lenguas != null){
			for (int i = 0; i<lenguas.length;i++) {
				if (l == lenguas[i]) {
					if (trad.elementAt(i) != null) {
						if (!trad.elementAt(i).equalsIgnoreCase(t)){
							trad.setElementAt(t, i);
							return i;
						} else return -1;
					} else {
						trad.setElementAt(t, i);
						return i;
					}
				}
			}
		}
		return -1;
	}
	public String getOrigen(){
		return origen;
	}
	public char[] getLenguas() {
		return lenguas;
	}
	public String getTraduccion(char l){
		for (int i = 0; i<lenguas.length;i++){
			if (l == lenguas[i]) return trad.elementAt(i);
		}
		return null;
	}
	public void escribeInfo(){
		String total = origen+":";
		String palabra;
		for (int i = 0; i<lenguas.length; i++){
			palabra = trad.elementAt(i);
			if (palabra != null) total += palabra;
			if (i != lenguas.length - 1) total+=":";
		}
		System.out.println(total);
	}
	public void escribeInfo(char l){
		String total = origen+":";
		String palabra;
		for (int i = 0; i<lenguas.length; i++){
			if (lenguas[i] == l) {
				palabra = trad.elementAt(i);
				if (palabra != null) total += palabra;
			}
		}
		System.out.println(total);
	}
}
