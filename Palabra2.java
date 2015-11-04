import java.util.Vector;

public class Palabra2 {
	private char[] lenguas;
	private String origen;
	private Vector<String> trad;
	public Palabra2 (String p, char[] lenguas){
		origen = p;
		if (lenguas == null) this.lenguas = new char[]{'E', 'F', 'P'};
		else this.lenguas = lenguas;
		trad = new Vector<String>(this.lenguas.length);
	}
	public int setTrad(String t, char l){

		return -1;
	}
	public String getOrigen(){
		return origen;
	}
	public String getTraduccion(char l){
		for (int i = 0; i<lenguas.length;i++){
			//if (l == lenguas[i]) return l;
		}
		return null;
	}
	public void escribeInfo(){
		String total = "";
		for (int i = 0; i<trad.length; i++){
			if (i != 0) total += ":";
			total += trad[i];
		}
		System.out.println(total);
	}
}
