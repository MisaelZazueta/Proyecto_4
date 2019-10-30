//Desarrollado por Ing. Jose Misael Burruel Zazueta
//Version 1.0
//MC Computacionales Instituto Tecnologico de Culiacan
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {
    public static void main(String[] args) throws IOException {
        File fichero = new File("C:\\Users\\Usuari0\\Desktop\\Maestria en ciencias\\POO\\Proyecto_4\\src\\Catalogo.txt");
        Scanner s = null;
        Poblacion poblacion = new Poblacion();
        String ruta = "C:\\Users\\Usuari0\\Desktop\\Maestria en ciencias\\POO\\Proyecto_4\\src\\Ignorado.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        try {
            s = new Scanner(fichero);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Pattern pat = Pattern.compile("[{]([\\s]*[[aA-zZ]+[\\s]*]+)[\\s]*[,][\\s]*([[aA-zZ]+[\\s]*]+)[\\s]*[,][\\s]*(h|H|m|M)[\\s]*[,][\\s]*([0-9]{2}/[0-9]{2}/[0-9]{4})[\\s]*[}](.*)");
                Matcher mat = pat.matcher(linea);
                if (mat.find() && mat.matches()) {
                    String nombre = mat.group(1).toUpperCase();
                    String apellido = mat.group(2).toUpperCase();
                    char sexo = mat.group(3).toUpperCase().charAt(0);
                    Date fechaNac = form.parse(mat.group(4));
                    Persona persona = new Persona(nombre, apellido, sexo, fechaNac);
                    poblacion.añadirPersona(persona);
                }
            }
        } catch (Exception ex) {
            System.out.println("Linea incorrecta");
        } finally {
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Linea incorrecta");
            }
        }
        File fichero2 = new File("C:\\Users\\Usuari0\\Desktop\\Maestria en ciencias\\POO\\Proyecto_4\\src\\Amistades.txt");
        Scanner s2 = null;
        try {
            s2 = new Scanner(fichero2);
            while (s2.hasNextLine()) {
                String linea = s2.nextLine();
                Pattern pat = Pattern.compile("[{]([[aA-zZ]+[\\s]*]+)[,]([[aA-zZ]+[\\s]*]+)[,](h|H|m|M)[,]([0-9]{2}/[0-9]{2}/[0-9]{4})[}][\\s]*(amigo|eliminar|amigos)[\\s]*[{]([[aA-zZ]+[\\s]*]+)[,]([[aA-zZ]+[\\s]*]+)[,](h|H|m|M)[,]([0-9]{2}/[0-9]{2}/[0-9]{4})[}][\\s]*(.*)");
                Pattern pat2 = Pattern.compile("[{]([[aA-zZ]+[\\s]*]+)[,]([[aA-zZ]+[\\s]*]+)[,](h|H|m|M)[,]([0-9]{2}/[0-9]{2}/[0-9]{4})[}][\\s]*(amigo|eliminar|amigos)[\\s]*([0-9]+)[\\s]*(.*)");
                Pattern pat3 = Pattern.compile("([0-9]+)[\\s]*(amigo|eliminar|amigos)[\\s]*[{]([[aA-zZ]+[\\s]*]+)[,]([[aA-zZ]+[\\s]*]+)[,](h|H|m|M)[,]([0-9]{2}/[0-9]{2}/[0-9]{4})[}][\\s]*(.*)");
                Pattern pat4 = Pattern.compile("([0-9]+)[\\s]*(amigo|eliminar|amigos)[\\s]*([0-9]+)[\\s]*(.*)");
                Pattern pat5 = Pattern.compile("amigos[\\s]*[{]([[aA-zZ]+[\\s]*]+)[,]([[aA-zZ]+[\\s]*]+)[,](h|H|m|M)[,]([0-9]{2}/[0-9]{2}/[0-9]{4})[}][\\s]*([0-9]+)[\\s]*(.*)");
                Pattern pat6 = Pattern.compile("amigos[\\s]*([0-9]+)[\\s]*([0-9]+)[\\s]*(.*)");
                Matcher mat = pat.matcher(linea);
                Matcher mat2 = pat2.matcher(linea);
                Matcher mat3 = pat3.matcher(linea);
                Matcher mat4 = pat4.matcher(linea);
                Matcher mat5 = pat5.matcher(linea);
                Matcher mat6 = pat6.matcher(linea);
                if (mat.find() && mat.matches()) {
                    String nombre = mat.group(1).toUpperCase();
                    String apellido = mat.group(2).toUpperCase();
                    char sexo = mat.group(3).toUpperCase().charAt(0);
                    Date fechaNac = form.parse(mat.group(4));
                    String operador = mat.group(5).toLowerCase();
                    String nombre2 = mat.group(6).toUpperCase();
                    String apellido2 = mat.group(7).toUpperCase();
                    char sexo2 = mat.group(8).toUpperCase().charAt(0);
                    Date fechaNac2 = form.parse(mat.group(9));
                    Persona persona = new Persona(nombre, apellido, sexo, fechaNac);
                    Persona persona2 = new Persona(nombre2, apellido2, sexo2, fechaNac2);
                    if(poblacion.existePersona(persona) >= 0 && poblacion.existePersona(persona2) >= 0)
                        switch (operador){
                            case "amigo":
                                if (!poblacion.añadirAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(poblacion.existePersona(persona2)))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                            case "amigos":
                                poblacion.buscarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(poblacion.existePersona(persona2)));
                                break;
                            case "eliminar":
                                if(!poblacion.eliminarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(poblacion.existePersona(persona2)))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                        }
                    else{
                        bw.write(linea);
                        bw.newLine();
                    }
                }
                else if (mat2.find() && mat2.matches()) {
                    String nombre = mat2.group(1).toUpperCase();
                    String apellido = mat2.group(2).toUpperCase();
                    char sexo = mat2.group(3).toUpperCase().charAt(0);
                    Date fechaNac = form.parse(mat2.group(4));
                    String operador = mat2.group(5).toLowerCase();
                    String posicion2 = mat2.group(6).toUpperCase();
                    int posicion = Integer.parseInt(posicion2);
                    Persona persona = new Persona(nombre, apellido, sexo, fechaNac);
                    if(poblacion.existePersona(persona) >= 0 && poblacion.personas.size() >= posicion && posicion>0)
                        switch (operador){
                            case "amigo":
                                if (!poblacion.añadirAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicion-1))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                            case "amigos":
                                poblacion.buscarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicion-1));
                                break;
                            case "eliminar":
                                if(!poblacion.eliminarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicion-1))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                        }
                    else{
                        bw.write(linea);
                        bw.newLine();
                    }
                }
                else if (mat3.find() && mat3.matches()) {
                    String posicion = mat3.group(1);
                    String operador = mat3.group(2);
                    String nombre = mat3.group(3).toUpperCase();
                    String apellido = mat3.group(4).toUpperCase();
                    char sexo = mat3.group(5).toUpperCase().charAt(0);
                    Date fechaNac = form.parse(mat3.group(6));
                    int posicionA = Integer.parseInt(posicion);
                    Persona persona = new Persona(nombre, apellido, sexo, fechaNac);
                    if(poblacion.existePersona(persona) >= 0 && poblacion.personas.size() >= posicionA && posicionA>0)
                        switch (operador){
                            case "amigo":
                                if (!poblacion.añadirAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicionA-1))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                            case "amigos":
                                poblacion.buscarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicionA-1));
                                break;
                            case "eliminar":
                                if(!poblacion.eliminarAmistad(poblacion.personas.get(poblacion.existePersona(persona)), poblacion.personas.get(posicionA-1))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                        }
                    else{
                        bw.write(linea);
                        bw.newLine();
                    }
                }
                else if (mat4.find() && mat4.matches()) {
                    String posicion = mat4.group(1);
                    String operador = mat4.group(2).toLowerCase();
                    String posicion2 = mat4.group(3);
                    int posicionA = Integer.parseInt(posicion);
                    int posicionB = Integer.parseInt(posicion2);
                    if(posicionA != posicionB && posicionA <= poblacion.personas.size() && posicionB <= poblacion.personas.size() && posicionA>0 && posicionB>0)
                        switch (operador){
                            case "amigo":
                                if (!(poblacion.añadirAmistad(poblacion.personas.get(posicionA-1), poblacion.personas.get(posicionB-1)))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                            case "amigos":
                                poblacion.buscarAmistad(poblacion.personas.get(posicionA-1), poblacion.personas.get(posicionB-1));
                                break;
                            case "eliminar":
                                if(!poblacion.eliminarAmistad(poblacion.personas.get(posicionA-1), poblacion.personas.get(posicionB-1))){
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                break;
                        }
                }
                else if (mat5.find() && mat5.matches()) {
                    String nombre = mat5.group(1).toUpperCase();
                    String apellido = mat5.group(2).toUpperCase();
                    char sexo = mat5.group(3).toUpperCase().charAt(0);
                    Date fechaNac = form.parse(mat5.group(4));
                    String nivelA = mat5.group(5);
                    Persona persona = new Persona(nombre, apellido, sexo, fechaNac);
                    int nivel = Integer.parseInt(nivelA);
                    if(poblacion.existePersona(persona) >= 0)
                        poblacion.buscarNivel(poblacion.personas.get(poblacion.existePersona(persona)), nivel);
                    else{
                            bw.write(linea);
                            bw.newLine();
                        }
                }
                else if (mat6.find() && mat6.matches()) {
                    String posicionA = mat6.group(1);
                    String nivelA = mat6.group(2);
                    int posicion = Integer.parseInt(posicionA);
                    int nivel = Integer.parseInt(nivelA);
                    if(posicion <= poblacion.personas.size())
                        poblacion.buscarNivel(poblacion.personas.get(posicion - 1), nivel);
                    else {
                            bw.write(linea);
                            bw.newLine();
                        }
                }
                else{
                    bw.write(linea);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception ex) {
            System.out.println("Linea incorrecta");
        } finally {
            try {
                if (s2 != null)
                    s2.close();
            } catch (Exception ex2) {
                System.out.println("Linea incorrecta");
            }
        }
    }
}
