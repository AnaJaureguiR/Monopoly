
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Tablero implements Serializable {

    private Propiedad[] casillas = new Propiedad[40];
    private Random dado = new Random();
    
    public Tablero() {
        casillas[0] = new Propiedad("Armengual de la Mota", 100, 30);
        casillas[1] = new Propiedad("Arango", 80, 20);
        casillas[2] = new Propiedad("Capitan Huelin", 60,15);
        casillas[3] = new Propiedad("Estacion de servicios", 100,30);
        casillas[4] = new Propiedad("Suministros de luz", 70,18);
        casillas[5] = new Propiedad("Velazquez", 80,20);
        casillas[6] = new Propiedad("Moliere", 90,25);
        casillas[7] = new Propiedad("Paci­fico", 120,40);
        casillas[8] = new Propiedad("Estacion de trenes", 100,30);
        casillas[9] = new Propiedad("Suministros de agua", 70,18);
        casillas[10] = new Propiedad("Larios", 250,100);
        casillas[11] = new Propiedad("Granada", 170,60);
        casillas[12] = new Propiedad("Uncibay", 150,50);
        casillas[13] = new Propiedad("Alameda", 220,80);
        casillas[14] = new Propiedad("Estacion de autobuses", 100,30);
        casillas[15] = new Propiedad("Suministros de gas", 70,18);
        casillas[16] = new Propiedad("Jose Calderon", 80,20);
        casillas[17] = new Propiedad("Camino Martinez", 70,18);
        casillas[18] = new Propiedad("Praga", 100,30);
        casillas[19] = new Propiedad("Kiev", 100,30);
        casillas[20] = new Propiedad("Aeropuerto", 100,30);
        casillas[21] = new Propiedad("Carcel", 0,0);
        casillas[22] = new Propiedad("Nueva", 170,60);
        casillas[23] = new Propiedad("Paseo del Parque", 200,75);
        casillas[24] = new Propiedad("Paseo Reding", 220,80);
        casillas[25] = new Propiedad("Malagueta", 220,80);
        casillas[26] = new Propiedad("Alquiler de coche", 100,30);
        casillas[27] = new Propiedad("Recogida de Basuras", 70,18);
        casillas[28] = new Propiedad("Carlos de Haya", 100,30);
        casillas[29] = new Propiedad("Eugenio Gross", 90,25);
        casillas[30] = new Propiedad("Rafaela", 60,15);
        casillas[31] = new Propiedad("Pelayo", 70,18);
        casillas[32] = new Propiedad("Estación espacial", 100,30);
        casillas[33] = new Propiedad("Servicios a domicilio", 70,18);
        casillas[34] = new Propiedad("Ayala", 120,40);
        casillas[35] = new Propiedad("Cuarteles", 100,30);
        casillas[36] = new Propiedad("Salitre", 90,25);
        casillas[37] = new Propiedad("Heroe de Sostoa", 110,35);
        casillas[38] = new Propiedad("La Hoz", 120,40);
        casillas[39] = new Propiedad("Servicios publicos", 70,18);
    }

    public void realizarTurno(Jugador jugador) {
        
        Scanner scanner = new Scanner(System.in);
        
        if (jugador.estaEnCarcel()) {
            System.out.println(jugador.getNombre() + " esta en la carcel. Turnos restantes: " + (jugador.getTurnosEnCarcel() - 1));
            jugador.reducirTurnosEnCarcel();
            return; //esto hace terminar el turno
        }
        
        int pasos = tirarDado();
        System.out.println(jugador.getNombre() + " ha sacado un " + pasos);
        jugador.mover(pasos);

        if (casillas[jugador.getPosicion()] != null) {
            Propiedad propiedad = casillas[jugador.getPosicion()];

            if (propiedad.getNombre().equals("Carcel")) {
                jugador.irACarcel();
                System.out.println("Has caido en la carcel");
                return; //esto hace terminar el turno
            } else {
                System.out.println("Has caido en " + casillas[jugador.getPosicion()].getNombre());
            }
            
            if (propiedad.estaDisponible()) {
                System.out.println("Esta propiedad esta disponible por €" + propiedad.getPrecio());
                System.out.println("¿Te gustaría comprarla?");
                String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("si")) {
                        propiedad.comprar(jugador);
                    } else if (respuesta.equalsIgnoreCase("no")){
                        System.out.println(jugador.getNombre() + " ha decidido no comprar " + propiedad.getNombre());
                    } else{
                        System.out.println("Respuesta no válida. Por favor responde si o no.");
                    }
                    scanner.close();
                
            } else {
                System.out.println("Esta propiedad pertenece a " + propiedad.getPropietario());
                System.out.println("Debes pagar un alquiler de: " + propiedad.getPrecioAlquiler());
                propiedad.pagarAlquiler(jugador);

                // en este punto se podri­a agregar el tema alquileres
            }
        }
    }

    private int tirarDado() {
        return dado.nextInt(6) + 1;
    }

}
