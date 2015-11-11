import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ListaBilingue {
    private NodoLD diccO;
    private NodoLD diccD;
    public ListaBilingue(){
        diccO = null;
        diccD = null;
    }
    public void leeDiccionario(String f){
        if (f != null) {
            FileReader fr;
            BufferedReader br;
            String linea;
            String[] aux;
            try {
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                linea = br.readLine();
                while (linea != null) {
                    aux = linea.split("[ ]*\\*[ ]*");
                    if ((aux[0] != null && !aux[0].equals("") && !aux[0].equals(" ")) && (aux[1] != null && !aux[1].equals("") && !aux[1].equals(" "))){
                        inserta(aux[0], aux[1]);
                    }
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
    public boolean inserta(String o, String d) {
        if (o != null && d != null && !o.equals("") && !d.equals("")) {
            boolean oexists = false, dexists = false, oinsertado = false, dinsertado = false;
            NodoLD set = new NodoLD();
            set.setPalabraO(o);
            set.setPalabraD(d);
            if (diccO == null || diccD == null) {
                diccO = set;
                diccD = set;
                return true;
            }
            NodoLD aux = diccO;
            while (aux != null && (!oexists && !dexists)) {
                if (aux.getPalabraO() != null && aux.getPalabraO().compareToIgnoreCase(o) == 0) oexists = true;
                if (aux.getPalabraD() != null && aux.getPalabraD().compareToIgnoreCase(d) == 0) dexists = true;
                aux = aux.getNextO();
            }
            if (!oexists && !dexists){
                NodoLD aux2 = diccO;
                if (diccO.getPalabraO() != null && diccO.getPalabraO().compareToIgnoreCase(o) > 0){
                    set.cambiaNextO(diccO);
                    diccO = set;
                    oinsertado = true;
                }
                while (aux2 != null && !oinsertado) {
                    if (aux2.getNextO() == null || (aux2.getNextO() != null && aux2.getNextO().getPalabraO() != null && aux2.getNextO().getPalabraO().compareToIgnoreCase(o) > 0)) {
                        oinsertado = true;
                        set.cambiaNextO(aux2.getNextO());
                        aux2.cambiaNextO(set);
                    }
                    aux2 = aux2.getNextO();
                }
                NodoLD aux3 = diccD;
                if (diccO.getPalabraD() != null && diccD.getPalabraD().compareToIgnoreCase(d) > 0){
                    set.cambiaNextD(diccD);
                    diccD = set;
                    dinsertado = true;
                }
                while (aux3 != null && !dinsertado) {
                    if (aux3.getNextD() == null || (aux3.getNextD() != null && aux3.getNextD().getPalabraD() != null && aux3.getNextD().getPalabraD().compareToIgnoreCase(d) > 0)) {
                        dinsertado = true;
                        set.cambiaNextD(aux3.getNextD());
                        aux3.cambiaNextD(set);
                    }
                    aux3 = aux3.getNextD();
                }
                if (oinsertado && dinsertado) return true;
            }
        }
        return false;
    }
    public boolean insertaRepetido(String o, String d) {
        if (o != null && d != null && !o.equals("") && !d.equals("")) {
            boolean exists = false, oinsertado = false, dinsertado = false;
            NodoLD set = new NodoLD();
            set.setPalabraO(o);
            set.setPalabraD(d);
            if (diccO == null || diccD == null) {
                diccO = set;
                diccD = set;
                return true;
            }
            NodoLD aux = diccO;
            while (aux != null && !exists) {
                if ((aux.getPalabraO() != null && aux.getPalabraO().compareToIgnoreCase(o) == 0) && (aux.getPalabraD() != null && aux.getPalabraD().compareToIgnoreCase(d) == 0)) exists = true;
                aux = aux.getNextO();
            }
            if (!exists){
                if (diccO.getPalabraO() != null && diccO.getPalabraO().compareToIgnoreCase(o) > 0){
                    set.cambiaNextO(diccO);
                    diccO = set;
                    oinsertado = true;
                }
                NodoLD aux2 = diccO;
                while (aux2 != null && !oinsertado) {
                    if (aux2.getNextO() == null || (aux2.getNextO() != null && aux2.getNextO().getPalabraO() != null && aux2.getNextO().getPalabraO().compareToIgnoreCase(o) > 0)) {
                        oinsertado = true;
                        set.cambiaNextO(aux2.getNextO());
                        aux2.cambiaNextO(set);
                    }
                    aux2 = aux2.getNextO();
                }
                if (diccO.getPalabraD() != null && diccD.getPalabraD().compareToIgnoreCase(d) > 0){
                    set.cambiaNextD(diccD);
                    diccD = set;
                    dinsertado = true;
                }
                NodoLD aux3 = diccD;
                while (aux3 != null && !dinsertado) {
                    if (aux3.getNextD() == null || (aux3.getNextD() != null && aux3.getNextD().getPalabraD() != null && aux3.getNextD().getPalabraD().compareToIgnoreCase(d) > 0)) {
                        dinsertado = true;
                        set.cambiaNextD(aux3.getNextD());
                        aux3.cambiaNextD(set);
                    }
                    aux3 = aux3.getNextD();
                }
                if (oinsertado && dinsertado) return true;
            }
        }
        return false;
    }
    public boolean borraO(String s){
        if (s != null) {
            if (diccO != null && diccO.getPalabraO() != null && diccO.getPalabraO().compareToIgnoreCase(s) == 0) {
                if (diccO == diccD) {
                    NodoLD aux, aux2;
                    aux = diccO.getNextO();
                    aux2 = diccD.getNextD();
                    diccO = aux;
                    diccD = aux2;
                    return true;
                } else {
                    NodoLD aux, aux2, aux3;
                    aux = diccO.getNextO();
                    aux2 = diccD;
                    while (aux2 != null) {
                        if (diccO == aux2.getNextD()) {
                            aux3 = aux2.getNextD().getNextD();
                            diccO = aux;
                            aux2.cambiaNextD(aux3);
                            return true;
                        }
                        aux2 = aux2.getNextD();
                    }
                }
            } else {
                NodoLD aux = diccO;
                while (aux != null) {
                    if (aux.getNextO() != null && aux.getNextO().getPalabraO() != null && aux.getNextO().getPalabraO().compareToIgnoreCase(s) == 0) {
                        if (aux.getNextO() == diccD) {
                            NodoLD aux2, aux3;
                            aux2 = aux.getNextO().getNextO();
                            aux3 = diccD.getNextD();
                            aux.cambiaNextO(aux2);
                            diccD = aux3;
                            return true;
                        } else {
                            NodoLD aux2 = diccD;
                            while (aux2 != null) {
                                if (aux2.getNextD() == aux.getNextO()) {
                                    NodoLD aux3, aux4;
                                    aux3 = aux.getNextO().getNextO();
                                    aux4 = aux2.getNextD().getNextD();
                                    aux.cambiaNextO(aux3);
                                    aux2.cambiaNextD(aux4);
                                    return true;
                                }
                                aux2 = aux2.getNextD();
                            }
                        }
                    }
                    aux = aux.getNextO();
                }
            }
        }
        return false;
    }
    public boolean borraD(String s){
        if (s != null) {
            if (diccD != null && diccD.getPalabraD() != null && diccD.getPalabraD().compareToIgnoreCase(s) == 0){
                if (diccO == diccD){
                    NodoLD aux, aux2;
                    aux = diccO.getNextO();
                    aux2 = diccD.getNextD();
                    diccO = aux;
                    diccD = aux2;
                    return true;
                } else {
                    NodoLD aux, aux2, aux3;
                    aux = diccD.getNextD();
                    aux2 = diccO;
                    while (aux2 != null) {
                        if (diccD == aux2.getNextO()){
                            aux3 = aux2.getNextO().getNextO();
                            diccD = aux;
                            aux2.cambiaNextO(aux3);
                            return true;
                        }
                       aux2 = aux2.getNextO();
                    }
                }
            } else {
                NodoLD aux = diccD;
                while (aux != null) {
                    if (aux.getNextD() != null && aux.getNextD().getPalabraD() != null && aux.getNextD().getPalabraD().compareToIgnoreCase(s) == 0) {
                        if (aux.getNextD() == diccO){
                            NodoLD aux2, aux3;
                            aux2 = aux.getNextD().getNextD();
                            aux3 = diccO.getNextO();
                            aux.cambiaNextD(aux2);
                            diccO = aux3;
                            return true;
                        } else {
                            NodoLD aux2 = diccO;
                            while (aux2 != null) {
                                if (aux2.getNextO() == aux.getNextD()) {
                                    NodoLD aux3, aux4;
                                    aux3 = aux.getNextD().getNextD();
                                    aux4 = aux2.getNextO().getNextO();
                                    aux.cambiaNextD(aux3);
                                    aux2.cambiaNextO(aux4);
                                    return true;
                                }
                                aux2 = aux2.getNextO();
                            }
                        }
                    }
                    aux = aux.getNextD();
                }
            }
        }
        return false;
    }
    public String buscaO(String s){
        NodoLD aux = diccO;
        if (s != null) {
            while (aux != null) {
                if (aux.getPalabraO() != null && aux.getPalabraO().compareToIgnoreCase(s) == 0) return aux.getPalabraD();
                aux = aux.getNextO();
            }
        }
        return null;
    }
    public String buscaD(String s){
        NodoLD aux = diccD;
        if (s != null) {
            while (aux != null) {
                if (aux.getPalabraD() != null && aux.getPalabraD().compareToIgnoreCase(s) == 0) return aux.getPalabraO();
                aux = aux.getNextD();
            }
        }
        return null;
    }
    public int indiceO(String s){
        NodoLD aux = diccO;
        int it=0;
        if (s != null) {
            while (aux != null) {
                it++;
                if (aux.getPalabraO() != null){
                    if (aux.getPalabraO().compareToIgnoreCase(s) == 0) return it;
                    else if (aux.getPalabraO().compareToIgnoreCase(s) > 0) return -1;
                }
                aux = aux.getNextO();
            }
        }
        return -1;
    }
    public int indiceD(String s){
        NodoLD aux = diccD;
        int it=0;
        if (s != null) {
            while (aux != null) {
                it++;
                if (aux.getPalabraD() != null){
                    if (aux.getPalabraD().compareToIgnoreCase(s) == 0) return it;
                    else if (aux.getPalabraD().compareToIgnoreCase(s) > 0) return -1;
                }
                aux = aux.getNextO();
            }
        }
        return -1;
    }
    public void visualizaO(){
        NodoLD aux = diccO;
        while (aux != null && aux.getPalabraO() != null) {
            System.out.println(aux.getPalabraO()+":"+aux.getPalabraD());
            aux = aux.getNextO();
        }
    }
    public void visualizaD(){
        NodoLD aux = diccD;
        while (aux != null && aux.getPalabraD() != null) {
            System.out.println(aux.getPalabraD()+":"+aux.getPalabraO());
            aux = aux.getNextD();
        }
    }
    public void visualizaRepetidosO(){
        NodoLD aux = diccO;
        String origenactual = "";
        String cadenaactual = "";
        int oa = 0;
        String cadenatotal = "";
        while (aux != null && aux.getPalabraO() != null) {
            if (!aux.getPalabraO().equalsIgnoreCase(origenactual)) {
                if (oa > 1){
                    cadenatotal += cadenaactual+"\n";
                }
                origenactual = aux.getPalabraO();
                cadenaactual = "";
                oa = 0;
            }
            if (oa == 0) cadenaactual = origenactual+":";
            else cadenaactual += ",";
            cadenaactual += aux.getPalabraD();
            oa++;
            aux = aux.getNextO();
        }
        if (oa > 1){
            cadenatotal += cadenaactual+"\n";
        }
        System.out.println("SALIDA");
        if (!cadenatotal.equals("")) System.out.print(cadenatotal);
        else System.out.println("No existe");
    }
    public void visualizaRepetidosD(){
        NodoLD aux = diccD;
        String origenactual = "";
        String cadenaactual = "";
        int oa = 0;
        String cadenatotal = "";
        while (aux != null && aux.getPalabraD() != null) {
            if (!aux.getPalabraD().equalsIgnoreCase(origenactual)) {
                if (oa > 1){
                    cadenatotal += origenactual+":"+cadenaactual+"\n";
                }
                origenactual = aux.getPalabraD();
                cadenaactual = "";
                oa = 0;
            }
            if (oa != 0) cadenaactual += ",";
            cadenaactual += aux.getPalabraO();
            oa++;
            aux = aux.getNextD();
        }
        System.out.println("SALIDA");
        if (!cadenatotal.equals("")) System.out.print(cadenatotal);
        else System.out.println("No existe");
    }
    public Vector<String> getO(int i){
        NodoLD aux = diccO;
        Vector<String> ret = new Vector<String>();
        int cont = 0;
        while (aux != null && aux.getPalabraO() != null && cont <= i) {
            if (cont == i) {
                ret.add(aux.getPalabraO());
                ret.add(aux.getPalabraD());
                return ret;
            }
            cont++;
            aux = aux.getNextO();
        }
        return null;
    }
    public Vector<String> getD(int i) {
        NodoLD aux = diccD;
        Vector<String> ret = new Vector<String>();
        int cont = 0;
        while (aux != null && aux.getPalabraD() != null && cont <= i) {
            if (cont == i) {
                ret.add(aux.getPalabraO());
                ret.add(aux.getPalabraD());
                return ret;
            }
            cont++;
            aux = aux.getNextD();
        }
        return null;
    }
    private class NodoLD{
        private String palO;
        private String palD;
        private NodoLD nextO;
        private NodoLD nextD;

        public NodoLD(){
            palO = null;
            palD = null;
            nextO = null;
            nextD = null;
        }
        public void cambiaNextO(NodoLD n){
            nextO = n;
        }
        public void cambiaNextD(NodoLD n){
            nextD = n;
        }
        public void setPalabraO(String p){
            palO = p;
        }
        public void setPalabraD(String p){
            palD = p;
        }
        public NodoLD getNextO(){
            return nextO;
        }
        public NodoLD getNextD(){
            return nextD;
        }
        public String getPalabraO(){
            return palO;
        }
        public String getPalabraD(){
            return palD;
        }
    }
}