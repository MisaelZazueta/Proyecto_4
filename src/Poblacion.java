//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.util.ArrayList;
import java.util.Date;

public class Poblacion {
    ArrayList<Persona> personas = new ArrayList<>();
    public void añadirPersona(Persona persona){
        if(!personas.contains(persona))
            personas.add(persona);
    }
    public boolean añadirAmistad(Persona persona, Persona persona2){
        int x = 0, y = 0;
        for (int i = 0; i < personas.size(); i++) { // Obtener indices para acceder a la posicion de la persona en el arreglo de personas.
            if (personas.get(i).equals(persona))   //Esto sirve para acceder a la persona directamente del arreglo y añadirle a ese objeto la nueva amistad.
                x = i;
            if (personas.get(i).equals(persona2))
                y = i;
        }
        if( x != y) {
            if (personas.get(x).añadirAmigo(personas.get(y)) && personas.get(y).añadirAmigo(personas.get(x)))
                return true;
            return false;
        }
        else
            return false;
    }
    public boolean eliminarAmistad(Persona persona, Persona persona2){
        int x = 0, y = 0;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).equals(persona))
                x = i;
            if (personas.get(i).equals(persona2))
                y = i;
        }
        if( x != y) {
            if(personas.get(x).eliminarAmigo(personas.get(y)) && personas.get(y).eliminarAmigo(personas.get(x)))
                return true;
            return false;
        }
        else
            return false;
    }
    public void buscarAmistad(Persona persona, Persona persona2){
        if(persona.getAmigos().contains(persona2)) {
            System.out.println("Verdadero");
        }
        else {
            System.out.println("Falso");
        }
    }
    public void buscarNivel(Persona persona, int nivel){
        System.out.println("Amigos de nivel " + nivel + " de: " + persona.toString());
            ArrayList<Persona> listaAuxNeg = new ArrayList<>();
            ArrayList<Persona> listaAuxPos = new ArrayList<>();
            ArrayList<Persona> listaAuxIni = new ArrayList<>();
            int x = 0;
            listaAuxIni.add(persona);
            listaAuxNeg.add(persona);
            for (int i = 0; i < nivel; i++) {
                for(Persona pactual: listaAuxIni) {
                    for (Persona p : pactual.getAmigos()) {
                        if(!listaAuxNeg.contains(p) && !listaAuxIni.contains(p) && !listaAuxPos.contains(p)){
                            listaAuxPos.add(p);
                        }
                    }
                }
                if(i < (nivel - 1)) {
                    for (int j = 0; j < listaAuxPos.size(); j++) {
                        listaAuxNeg.add(listaAuxPos.get(j));
                    }
                    listaAuxIni.clear();
                    for (int j = 0; j < listaAuxPos.size(); j++) {
                        listaAuxIni.add(listaAuxPos.get(j));
                    }
                    listaAuxPos.clear();
                }
            }
            if(!listaAuxPos.isEmpty()){
                for (int i = 0; i < listaAuxPos.size(); i++) {
                    System.out.println("    " + listaAuxPos.get(i));
                }
            }
            else{
                System.out.println("No existen amigos en este nivel.");
            }
        }
    public int existePersona(Persona persona){
        for (int i = 0; i < personas.size(); i++)
            if (personas.get(i).equals(persona))
                return i;
            return -1;
    }
}
