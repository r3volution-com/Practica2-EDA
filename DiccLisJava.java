//DNI 77842527Q GONZALEZ ALVARADO, MARIO
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* Uso ArrayList ya que una vez hecho DiccVector, gracias a su similitud de funciones, me era mas facil de implementar.
	De la forma que lo hago, los costes disminuyen usando ArrayList, por ejemplo en buscar y borrar. */

public class DiccLisJava implements Diccionario {
	private int nlenguas;
	private ArrayList<Character> lenguas;
	private ArrayList<Palabra2> dicc;
	/*Se inicializa la clase DiccLisJava*/
	public DiccLisJava(){
		nlenguas = -1;
		lenguas = new ArrayList<Character> ();
		dicc = new ArrayList<Palabra2> ();
	}

	/*Carga un archivo de diccionario e inserta las traducciones.
	Leemos linea por linea y pasamos por parametro a inserta la palabra leida con su origen y traduccion.*/
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
					lenguas.add(aux[i].charAt(0));
					leng[i] = aux[i].charAt(0);
				}
				linea = br.readLine();
				while (linea != null) {
					aux2 = linea.split("[ ]*\\*[ ]*");
					palabra = new Palabra2(aux2[0],leng);
					for (int i = 1; i < aux2.length; i++){
						if (aux2[i] != null && !aux2[i].equals("") && !aux2[i].equals(" ")) {
							palabra.setTrad(aux2[i], leng[i - 1]);
						}
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

	/*Inserta una palabra en el Vector de String ordenada alfabeticamente*/
	public boolean inserta(Palabra2 p) {
		if (p != null && p.getOrigen() != null && !p.getOrigen().equalsIgnoreCase("") && p.getLenguas().length == lenguas.size()) {
			char[] len = p.getLenguas();
			for (int i = 0; i < len.length && i < lenguas.size(); i++){ /*Comprobamos que todos los idiomas sean los mismo, sino acabamos ejecucion*/
				if (len[i] != lenguas.get(i)){
					return false;
				}
			}
			int position = getPosicionBinario(p.getOrigen());
			if (position < 0) {
				position = getPosicionLineal(p.getOrigen());
				dicc.add(position, p);
				return true;
			} else {
				Palabra2 pal = dicc.get(position);
				boolean insertado = false;
				char[] lenguas = p.getLenguas();
				for (int i = 0; pal != null && i < lenguas.length; i++) { /* Insertamos la nueva traduccion que hay para ese origen */
					if (p.getTraduccion(lenguas[i]) != null && pal.setTrad(p.getTraduccion(lenguas[i]), lenguas[i]) != -1) insertado = true;
				}
				if (insertado) return true;
			}
		}
		return false;
	}

	/*Borra una palabra del Vector.
	Llamamos a un metodo adicional que busca la palabra que nos pasan por parametro, de forma lineal hasta encontrarla.
	Nos devuelve una posicion y accedemos a ela directamente con el remove.	*/
	public boolean borra(String s) {
		if (s != null) {
			int position = getPosicionBinario(s);
			if (position >= 0) {
				dicc.remove(position);
				return true;
			}
		}
		return false;
	}

	/*Busca una palabra en el Vector (usamos el algoritmo binario)*/
	public int busca(String s) {
		int iteraciones = 0, centro, min = 0, max = dicc.size()-1;
		if (s != null) {
			while (min <= max){
				iteraciones++;
				centro = (max + min) / 2;
				if (dicc.get(centro).getOrigen().compareToIgnoreCase(s) == 0) return iteraciones;
				else if (dicc.get(centro).getOrigen().compareToIgnoreCase(s) < 0) min = centro + 1;
				else max = centro - 1;
			}
		}
		return (iteraciones * -1);
	}

	/*Obtiene la traduccion de la cadena especificada para la lengua especificada*/
	public String traduce(String s, char l) {
		if (s != null) {
			int position = getPosicionBinario(s);
			if (position >= 0) return dicc.get(position).getTraduccion(l);
		}
		return null;
	}

	/*Visualiza el contenido del vector ordenada con el formato especificado*/
	public void visualiza() {
		for (int i = 0; i<dicc.size(); i++){
			dicc.get(i).escribeInfo();
		}
	}

	/*Visualiza el contenido ordenado hasta el indice j*/
	public void visualiza(int j) {
		for (int i = 0; i < j && i < dicc.size(); i++){
			dicc.get(i).escribeInfo();
		}
	}
	/*Visualiza el contenido ordenado hasta el indice j para la lengua l*/
	public void visualiza(int j, char l) {
		for (int i = 0; i < j && i < dicc.size(); i++) {
			dicc.get(i).escribeInfo(l);
		}
	}

	/*Obtiene la posicion de una cadena en el diccionario con un algoritmo lineal*/
	public int getPosicionLineal(String s){
		for (int i = 0; i < dicc.size(); i++){
			if (dicc.get(i).getOrigen().compareToIgnoreCase(s) > 0) return i;
		}
		return dicc.size();
	}

	/*Obtiene la posicion de una cadena por el algoritmo binario*/
	public int getPosicionBinario(String s){
		int centro = 0, min = 0, max = dicc.size()-1;
		while(min<=max){
			centro=(max+min)/2;
			if(dicc.get(centro).getOrigen().compareToIgnoreCase(s) == 0) return centro;
			else if(dicc.get(centro).getOrigen().compareToIgnoreCase(s) < 0 ) min=centro+1;
			else max=centro-1;
		}
		return -1;
	}
}
