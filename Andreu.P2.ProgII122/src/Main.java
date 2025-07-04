import model.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            CatalogoMusical<Cancion> catalogo = new CatalogoMusical<>();

            catalogo.agregar(new Cancion(1, "Bohemian Rhapsody", "Queen", GeneroMusical.ROCK));
            catalogo.agregar(new Cancion(2, "Billie Jean", "Michael Jackson", GeneroMusical.POP));
            catalogo.agregar(new Cancion(3, "Shape of You", "Ed Sheeran", GeneroMusical.POP));
            catalogo.agregar(new Cancion(4, "Take Five", "Dave Brubeck", GeneroMusical.JAZZ));
            catalogo.agregar(new Cancion(5, "Canon in D", "Pachelbel", GeneroMusical.CLASICA));

            System.out.println("Catálogo de canciones:");
            catalogo.paraCadaElemento(c -> System.out.println(c));

            System.out.println("\nCanciones de género JAZZ:");
            catalogo.filtrar(c -> c.getGenero() == GeneroMusical.JAZZ)
                    .forEach(System.out::println);

            System.out.println("\nCanciones cuyo título contiene 'five':");
            catalogo.filtrar(c -> c.getTitulo().toLowerCase().contains("five"))
                    .forEach(System.out::println);

            System.out.println("\nCanciones ordenadas por ID:");
            catalogo.ordenar();
            catalogo.paraCadaElemento(System.out::println);

            System.out.println("\nCanciones ordenadas por artista:");
            catalogo.ordenar(Comparator.comparing(Cancion::getArtista));
            catalogo.paraCadaElemento(System.out::println);

            catalogo.guardarEnArchivo("src/data/canciones.dat");

            CatalogoMusical<Cancion> cargado = new CatalogoMusical<>();
            cargado.cargarDesdeArchivo("src/data/canciones.dat");
            System.out.println("\nCanciones cargadas desde binario:");
            cargado.paraCadaElemento(System.out::println);

            catalogo.guardarEnCSV("src/data/canciones.csv");

            cargado.cargarDesdeCSV("src/data/canciones.csv", Cancion::fromCSV);
            System.out.println("\nCanciones cargadas desde CSV:");
            cargado.paraCadaElemento(System.out::println);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

