
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

    private String nombre;
    private int posicion;
    private int dinero;
    private boolean enCarcel;
    private ArrayList<Propiedad> propiedades;
    private int turnosEnCarcel;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.posicion = 0;
        this.dinero = 1500;
        this.enCarcel = false;
        this.propiedades = new ArrayList<>();
        this.turnosEnCarcel = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getDinero() {
        return dinero;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public void agregarPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
    }

    public void mover(int pasos) {
        posicion = (posicion + pasos) % 40; //para que llegue solo a 40 casillas
        System.out.println(nombre + " se ha movido a la posicion " + posicion);
    }

    public void modificarDinero(int cantidad) {
        dinero += cantidad;
        System.out.println(nombre + " ahora tiene €" + dinero);
    }

    public boolean puedePagar(int cantidad) {
        return dinero >= cantidad;
    }

    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
    }

    public boolean estaEnCarcel() {
        return enCarcel;
    }

    public void irACarcel() {
        enCarcel = true;
        turnosEnCarcel = 3;
    }

    public void reducirTurnosEnCarcel() {
        if (enCarcel) {
            turnosEnCarcel--;
            if (turnosEnCarcel <= 0) {
                enCarcel = false;
            }
        }
    }

    public int getTurnosEnCarcel() {
        return turnosEnCarcel;
    }

    public void setTurnosEnCarcel(int turnosEnCarcel) {
        this.turnosEnCarcel = turnosEnCarcel;
    }

    public void mostrarPropiedades() {
        if (propiedades.isEmpty()) {
            System.out.println(nombre + " no tiene propiedades.");
        } else {
            System.out.println("Propiedades de " + nombre + ":");
            for (Propiedad propiedad : propiedades) {
                System.out.println("- " + propiedad.getNombre());
            }
        }
    }
    
    public void mostrarDinero(){
        System.out.println(nombre + " tiene "+ this.getDinero()+"€");
    }
}
