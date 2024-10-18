
import java.io.Serializable;

public class Propiedad implements Serializable {

    private String nombre;
    private int precio;
    private Jugador propietario;
    private int precioAlquiler;

    public Propiedad (String nombre, int precio, int precioAlquiler){
        this.nombre = nombre;
        this.precio = precio;
        this.propietario = null;
        this.precioAlquiler = precioAlquiler;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPrecio(){
        return precio;
    }

    public boolean estaDisponible(){
        return propietario == null;
    }

    public int getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(int precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }
        
    public void comprar(Jugador jugador) {
        if (jugador.puedePagar(precio)) {
            propietario = jugador;
            jugador.modificarDinero(-precio);
            jugador.agregarPropiedad(this);  // Agrega al arraylist la propiedad al jugador
            System.out.println(jugador.getNombre() + " ha comprado " + nombre);
        } else {
            System.out.println("No tienes suficiente dinero para pagar");
        }
    }
    
    public void pagarAlquiler(Jugador jugador){
        jugador.modificarDinero(-precioAlquiler);
       
    }

    public String getPropietario(){
        if(propietario!=null){
            return propietario.getNombre();
        }
        
        return "Ninguno";
    }
}