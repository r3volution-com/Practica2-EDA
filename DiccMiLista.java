import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DiccMiLista implements Diccionario {
	private int nlenguas;
	private Vector<Character> lenguas;
	private NodoL dicc;
	
	public DiccMiLista(){
		nlenguas = -1;
		lenguas = new Vector<Character> ();
		dicc = new NodoL();
	}

	public void leeDiccionario(String f) {
		if (f != null) {
			FileReader fr;
			BufferedReader br;
			String linea;
			String[] aux, aux2;
			char[] leng;
			Palabra2 palabra;
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				linea = br.readLine();
				nlenguas = Integer.parseInt(linea);
				leng = new char[nlenguas];
				linea = br.readLine();
				aux = linea.split(" ");
				for (int i = 0; i < aux.length; i++){
					lenguas.addElement(aux[i].charAt(0));
					leng[i] = aux[i].charAt(0);
				}
				linea = br.readLine();
				while (linea != null) {
					aux2 = linea.split("[ ]*\\*[ ]*");
					palabra = new Palabra2(aux2[0],leng);
					for (int i = 1; i < aux2.length; i++){
						palabra.setTrad(aux2[i], leng[i-1]);
					}
					inserta(palabra);
					linea = br.readLine();
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public boolean inserta(Palabra2 p) {
		if (p != null && !p.getOrigen().equals("") && p.getLenguas().length == lenguas.size()) {
			char[] len = p.getLenguas();
			for (int i = 0; i < len.length && i < lenguas.size(); i++){
				if (len[i] != lenguas.elementAt(i)){
					return false;
				}
			}
			if (dicc == null){
				dicc = new NodoL();
			}
			NodoL aux = dicc;
			if (dicc.getPalabra2() != null && dicc.getPalabra2().getOrigen() != null && dicc.getPalabra2().getOrigen().compareToIgnoreCase(p.getOrigen()) > 0){
				NodoL aux2 = new NodoL();
				aux2.setPalabra2(p);
				aux2.cambiaNext(dicc);
				dicc = aux2;
				return true;
			}
			while (aux != null){
				Palabra2 pal = aux.getPalabra2();
				if (pal == null){
					aux.setPalabra2(p);
					return true;
				} else {
					if (pal.getOrigen() != null && pal.getOrigen().compareToIgnoreCase(p.getOrigen()) == 0) {
						char[] lenguas = p.getLenguas();
						boolean insertado = false;
						for (int i = 0; lenguas != null && i < lenguas.length; i++) {
							if (p.getTraduccion(lenguas[i]) != null && !p.getTraduccion(lenguas[i]).equals("") && pal.setTrad(p.getTraduccion(lenguas[i]), lenguas[i]) != -1) insertado = true;
						}
						if (insertado) return true;
						else return false;
					} else if (aux.getNext() == null){
						NodoL aux2 = new NodoL();
						aux2.setPalabra2(p);
						aux2.cambiaNext(aux.getNext());
						aux.cambiaNext(aux2);
						return true;
					} else if (aux.getNext() != null && aux.getNext().getPalabra2() != null && aux.getNext().getPalabra2().getOrigen() != null && aux.getNext().getPalabra2().getOrigen().compareToIgnoreCase(p.getOrigen()) > 0) {
						NodoL aux2 = new NodoL();
						aux2.setPalabra2(p);
						aux2.cambiaNext(aux.getNext());
						aux.cambiaNext(aux2);
						return true;
					}
				}
				aux = aux.getNext();
			}
		}
		return false;
	}

	
	public boolean borra(String s) {
		if (dicc != null && dicc.getPalabra2() != null && dicc.getPalabra2().getOrigen() != null && dicc.getPalabra2().getOrigen().compareToIgnoreCase(s) == 0){
			if (dicc.getNext() != null){
				dicc = dicc.getNext();
			} else dicc = null;
			return true;
		}
		NodoL aux = dicc;
		while (aux != null) {
			if (aux.getNext() != null && aux.getNext().getPalabra2() != null && aux.getNext().getPalabra2().getOrigen() != null && aux.getNext().getPalabra2().getOrigen().compareToIgnoreCase(s) == 0){
				aux.cambiaNext(aux.getNext().getNext());
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	
	public int busca(String s) {
		NodoL aux = dicc;
		int it=0;
		if (s != null) {
			while (aux != null) {
				it++;
				if (aux.getPalabra2() != null && aux.getPalabra2().getOrigen() != null){
					if (aux.getPalabra2().getOrigen().compareToIgnoreCase(s) == 0) return it;
					else if (aux.getPalabra2().getOrigen().compareToIgnoreCase(s) > 0) return it*-1;
				}
				aux = aux.getNext();
			}
		}
		return it*-1;
	}

	
	public String traduce(String s, char l) {
		NodoL aux = dicc;
		if (s != null){
			while (aux != null) {
				if (aux.getPalabra2() != null && aux.getPalabra2().getOrigen() != null && aux.getPalabra2().getOrigen().compareToIgnoreCase(s) == 0){
					return aux.getPalabra2().getTraduccion(l);
				}
				aux = aux.getNext();
			}

		}
		return null;
	}

	
	public void visualiza() {
		NodoL aux = dicc;
		while (aux != null && aux.getPalabra2() != null) {
			aux.getPalabra2().escribeInfo();
			aux = aux.getNext();
		}
	}

	
	public void visualiza(int j) {
		NodoL aux = dicc;
		int cont = 0;
		while (aux != null && aux.getPalabra2() != null && cont < j) {
			aux.getPalabra2().escribeInfo();
			aux = aux.getNext();
			cont++;
		}
	}

	
	public void visualiza(int j, char l) {
		NodoL aux = dicc;
		int cont = 0;
		while (aux != null && aux.getPalabra2() != null && cont < j) {
			aux.getPalabra2().escribeInfo(l);
			aux = aux.getNext();
			cont++;
		}
	}

	private class NodoL {
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
