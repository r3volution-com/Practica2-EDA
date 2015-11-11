//DNI 77842527Q GONZALEZ ALVARADO, MARIO
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConsultaAcepciones {
    public static void main (String[] args){
        /*Si se cumple que haya 2 parametros*/
        if (args.length == 2) {
            FileReader fr = null;
            BufferedReader br = null;
            String linea = null;
            String[] palabras = null;
            ListaBilingue bilingue = null;
            try {
                /*Cargamos el archivo y creamos el diccionario*/
                fr = new FileReader(args[0]);
                br = new BufferedReader(fr);
                bilingue = new ListaBilingue();
                linea = br.readLine();
                /*Leemos el archivo linea por linea*/
                while (linea != null){
                    palabras = linea.split("[ ]*\\*[ ]*");
                    if (palabras.length == 2 && (palabras[0] != null && !palabras[0].equals("") && !palabras[0].equals(" ")) && (palabras[1] != null && !palabras[1].equals("") && !palabras[1].equals(" "))) {
                        /*Inserta utilizando la funcion de insertaRepetido para permitir las acepciones*/
                        bilingue.insertaRepetido(palabras[0], palabras[1]);
                    }
                    linea = br.readLine();
                }
                /*Visualizamos segun la opcion*/
                if (args[1].equalsIgnoreCase("O")) bilingue.visualizaRepetidosO();
                else if (args[1].equalsIgnoreCase("D")) bilingue.visualizaRepetidosD();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
