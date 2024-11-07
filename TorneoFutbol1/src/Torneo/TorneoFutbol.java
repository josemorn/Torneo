package Torneo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TorneoFutbol {
    
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println("Ingrese la etapa del torneo (octavos, cuartos, semifinales, final):");
        String etapa = scanner.nextLine().toLowerCase();
        
        int numEquipos = obtenerNumEquipos(etapa);
        if (numEquipos == -1) {
            System.out.println("Etapa inválida.");
            return;
        }
        
        List<String> equipos = new ArrayList<>();
        System.out.println("Ingrese los nombres de los " + numEquipos + " equipos:");
        for (int i = 0; i < numEquipos; i++) {
            System.out.print("Equipo " + (i + 1) + ": ");
            equipos.add(scanner.nextLine());
        }
        
        System.out.println("\nSorteo y resultados de partidos para la etapa de " + etapa + ":");
        sortearPartidos(equipos);
    }
    
    // Método que determina el número de equipos para cada etapa
    private static int obtenerNumEquipos(String etapa) {
        switch (etapa) {
            case "octavos":
                return 16;
            case "cuartos":
                return 8;
            case "semifinales":
                return 4;
            case "final":
                return 2;
            default:
                return -1;
        }
    }
    
    // Método recursivo para sortear los partidos
    private static void sortearPartidos(List<String> equipos) {
        Random random = new Random();

        // Caso base: si solo quedan dos equipos, muestra el partido final y termina
        if (equipos.size() == 2) {
            System.out.println("\nFinal:");
            String equipo1 = equipos.get(0);
            String equipo2 = equipos.get(1);
            int goles1 = random.nextInt(5); // Goles aleatorios para equipo 1
            int goles2 = random.nextInt(5); // Goles aleatorios para equipo 2
            System.out.println(equipo1 + " " + goles1 + " - " + goles2 + " " + equipo2);

            String ganadorFinal = goles1 > goles2 ? equipo1 : equipo2;
            System.out.println("\nEl campeón del torneo es: " + ganadorFinal);
            return;
        }
        
        // Barajar aleatoriamente los equipos para emparejar al azar
        Collections.shuffle(equipos);
        
        // Imprimir los partidos de la etapa actual
        System.out.println("\nResultados de los partidos:");
        List<String> ganadores = new ArrayList<>();
        for (int i = 0; i < equipos.size(); i += 2) {
            String equipo1 = equipos.get(i);
            String equipo2 = equipos.get(i + 1);
            int goles1 = random.nextInt(5); // Goles aleatorios para equipo 1
            int goles2 = random.nextInt(5); // Goles aleatorios para equipo 2
            
            System.out.println(equipo1 + " " + goles1 + " - " + goles2 + " " + equipo2);
            
            // Determinar el ganador y agregarlo a la lista de ganadores
            if (goles1 > goles2) {
                ganadores.add(equipo1);
            } else if (goles2 > goles1) {
                ganadores.add(equipo2);
            } else {
                // En caso de empate, agregar uno aleatorio para avanzar
                ganadores.add(random.nextBoolean() ? equipo1 : equipo2);
            }
        }
        
        // Llamada recursiva para la siguiente etapa con los equipos ganadores
        sortearPartidos(ganadores);
    }
}
