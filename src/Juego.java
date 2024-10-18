
import java.io.*;
import java.util.Scanner;

public class Juego {

    private Tablero tablero;
    private Jugador[] jugadores;
    private int jugadorActual;

    public Juego() {
        tablero = new Tablero();
        jugadores = new Jugador[2];
        jugadores[0] = new Jugador("Jugador 1");
        jugadores[1] = new Jugador("Jugador 2");
        jugadorActual = 0;
    }

    // Método para iniciar el juego
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        
        Cabecera.imprimirCabecera();
        
        // Verificar si existe un archivo de guardado
        File archivoGuardado = new File("juegoGuardado.ser");
        if (archivoGuardado.exists()) {
            System.out.println("Se encontró un juego guardado. ¿Quieres cargarlo?: ");
            String cargarJuego = scanner.nextLine();

            if (cargarJuego.equalsIgnoreCase("si")) {
                if (cargar()) {
                    System.out.println("Juego cargado correctamente.");
                } else {
                    System.out.println("No se pudo cargar el juego. Empezando uno nuevo.");
                    iniciarNuevoJuego();
                }
            } else {
                iniciarNuevoJuego();
            }
        } else {
            iniciarNuevoJuego();
        }

        OUTER:
        while (true) {

            System.out.println("\nTurno de " + jugadores[jugadorActual].getNombre());
            System.out.println("1. Tirar dado");
            System.out.println("2. Ver mis propiedades y dinero");
            System.out.println("3. Guardar juego y salir");
            System.out.println("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    tablero.realizarTurno(jugadores[jugadorActual]);
                    jugadorActual = (jugadorActual + 1) % jugadores.length; // Cambiar turno
                    break;
                case 2:
                    jugadores[jugadorActual].mostrarPropiedades();
                    jugadores[jugadorActual].mostrarDinero();
                    break;
                case 3:
                    guardar();
                    System.out.println("Juego guardado. Saliendo del juego.");
                    break OUTER; 
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } scanner.close();
    }


    //inicializar un nuevo juego
    public void iniciarNuevoJuego() {
        tablero = new Tablero();
        jugadores = new Jugador[2];
        jugadores[0] = new Jugador("Jugador 1");
        jugadores[1] = new Jugador("Jugador 2");
        jugadorActual = 0;
        System.out.println("Iniciando un nuevo juego.");
    }

    //guardar el juego
    public void guardar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("juegoGuardado.ser"))) {
            out.writeObject(tablero);
            out.writeObject(jugadores);
            out.writeInt(jugadorActual);
            System.out.println("Juego guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el juego: " + e.getMessage());
        }
    }

    //cargar el juego
    public boolean cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("juegoGuardado.ser"))) {
            tablero = (Tablero) in.readObject();
            jugadores = (Jugador[]) in.readObject();
            jugadorActual = in.readInt();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el juego: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        
        juego.iniciar();
    }
}
