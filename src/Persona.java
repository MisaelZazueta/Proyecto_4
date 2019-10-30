//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.util.ArrayList;
import java.util.Date;

public class Persona {
    private String nombre;
    private String apellido;
    private char sexo;
    private Date fechaNac;
    private ArrayList<Persona> amigos;

    public Persona(String nombre, String apellido, char sexo, Date fechaNac){
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        amigos = new ArrayList<>();
    }
    public boolean añadirAmigo(Persona persona){
        if(!amigos.contains(persona)) {
            amigos.add(persona);
            return true;
        }
        return false;
    }
    public boolean eliminarAmigo(Persona persona){
        if(amigos.contains(persona)){
            amigos.remove(persona);
            return true;
        }
        return false;
    }

    public ArrayList<Persona> getAmigos() {
        return amigos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + sexo + " " + fechaNac;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public char getSexo() {
        return sexo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Persona){
            Persona persona = (Persona) obj;
            if(nombre.equals(persona.nombre) && apellido.equals(persona.apellido) && sexo == persona.sexo && fechaNac.equals(persona.fechaNac))
                return true;
            return false;
        }
        return true;
    }
}
