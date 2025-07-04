package model;

public enum GeneroMusical {
    ROCK, POP, JAZZ, CLASICA, ELECTRONICA, REGGAETON;

    public static GeneroMusical parse(String valor) {
        try {
            return GeneroMusical.valueOf(valor.trim().toUpperCase());
        } catch (Exception e) {
            System.err.println("Género inválido: " + valor + " – se asigna CLASICA por defecto.");
            return CLASICA;
        }
    }
}

