package model;

import interfaces.CSVSerializable;
import java.io.Serializable;
import java.util.Objects;

public class Cancion implements CSVSerializable, Comparable<Cancion>, Serializable {
    private int id;
    private String titulo;
    private String artista;
    private GeneroMusical genero;

    public Cancion(int id, String titulo, String artista, GeneroMusical genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public GeneroMusical getGenero() { return genero; }

    @Override
    public String toCSV() {
        return id + "," + titulo + "," + artista + "," + genero;
    }

    public static Cancion fromCSV(String linea) {
        String[] partes = linea.split(",");
        int id = Integer.parseInt(partes[0].trim());
        String titulo = partes[1].trim();
        String artista = partes[2].trim();
        GeneroMusical genero = GeneroMusical.parse(partes[3].trim());
        return new Cancion(id, titulo, artista, genero);
    }

    @Override
    public int compareTo(Cancion o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "Cancion{" + "id=" + id + ", titulo='" + titulo + "', artista='" + artista + "', genero=" + genero + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cancion)) return false;
        Cancion c = (Cancion) o;
        return id == c.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
