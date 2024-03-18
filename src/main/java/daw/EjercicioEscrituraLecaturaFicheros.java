/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class EjercicioEscrituraLecaturaFicheros {

    public static void main(String[] args) {
        boolean exit = true;
        Scanner sr = new Scanner(System.in);
        String menu = """
                      1- Escribir en fichero
                      2- Leer fichero
                      3- Salir
                      """;
        int resMenu = 0;
        do {
            //controlamos excepciones por si mete en el int un string
            try {
                System.out.println(menu);
                System.out.println("Introduce la opción en números");
                resMenu = sr.nextInt();
                sr.nextLine();

                //condicionales para que dependiendo de la opción elegida haga
                //una cosa u otra
                switch (resMenu) {
                    case 1 -> {
                        System.out.println("Introduce el nombre del fichero a escribir sin el .txt");
                        String idFichero = sr.nextLine() + ".txt";
                        escribir(idFichero);
                    }
                    case 2 -> {
                        System.out.println("Introduce el nombre del fichero a leer sin el .txt");
                        String idFichero = sr.nextLine() + ".txt";
                        leer(idFichero);
                    }
                    case 3 -> {
                        System.out.println("Saliendo...");
                        exit = false;
                    }
                }
            } catch (InputMismatchException fg) {
                System.out.println("Error, Introduce un número del 1 al 3");
            }
        } while (exit);

    }

    public static void escribir(String idFichero) {
        Scanner sc = new Scanner(System.in);
        String pregunta = "";
        //este bucle se va a repetir hasta que llegue a la letra fin
        do {
            //le preguntamos al usuario que introduzca algo que añadir al texto
            System.out.println("Introduce la línea que quieres añadir");
            pregunta = sc.nextLine();

            if (!pregunta.equalsIgnoreCase("fin")) {
                try {
                    Files.write(Paths.get(idFichero), (pregunta + "\n").getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    System.out.println("Error creando el fichero");
                }
            }

        } while (!pregunta.equalsIgnoreCase("fin"));
        
    }

    public static void leer(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split("\n");
                for (String string : tokens) {
                    System.out.print(string);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
