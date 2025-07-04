package model;

import interfaces.CSVSerializable;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

public class CatalogoMusical<T extends CSVSerializable & Serializable & Comparable<T>> implements Serializable {
    private List<T> elementos = new ArrayList<>();

    public void agregar(T item) {
        if (!elementos.contains(item)) {
            elementos.add(item);
        }
    }

    public T obtener(int indice) {
        return elementos.get(indice);
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < elementos.size()) {
            elementos.remove(indice);
        }
    }

    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T item : elementos) {
            if (criterio.test(item)) resultado.add(item);
        }
        return resultado;
    }

    public void ordenar() {
        Collections.sort(elementos);
    }

    public void ordenar(Comparator<? super T> comparador) {
        elementos.sort(comparador);
    }

    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : elementos) {
            accion.accept(item);
        }
    }

    public void guardarEnArchivo(String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(elementos);
        }
    }

    
    public void cargarDesdeArchivo(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            this.elementos = (List<T>) ois.readObject();
        }
    }

    public void guardarEnCSV(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for (T item : elementos){
                bw.write(item.toCSV());
                bw.newLine();
            }
        }
    }

    public void cargarDesdeCSV(String path, Function<String, T> generador) throws IOException {
        elementos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                T item = generador.apply(linea);
                if (item != null) {
                    elementos.add(item);
                }
            }
        }
    }
}

