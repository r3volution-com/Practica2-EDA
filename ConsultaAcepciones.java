import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Mario on 10/11/2015.
 */
public class ConsultaAcepciones {
    public static void main (String[] args){
        if (args.length == 2) {
            FileReader fr = null;
            BufferedReader br = null;
            String linea = null;
            String[] palabras = null;
            ListaBilingue bilingue = null;
            try {
                fr = new FileReader(args[0]);
                br = new BufferedReader(fr);
                bilingue = new ListaBilingue();
                linea = br.readLine();
                while (linea != null){
                    palabras = linea.split("[ ]*\\*[ ]*");
                    if (palabras.length == 2 && (palabras[0] != null && !palabras[0].equals("") && !palabras[0].equals(" ")) && (palabras[1] != null && !palabras[1].equals("") && !palabras[1].equals(" "))) {
                        bilingue.insertaRepetido(palabras[0], palabras[1]);
                    }
                    linea = br.readLine();
                }
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
