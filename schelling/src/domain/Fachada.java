package domain;

import java.io.File;

public class Fachada {
    
    // Método para abrir un archivo
    public void open(File file) throws CityException {
        throw new CityException("Opción open en construcción. Archivo " + file.getName());
    }

    // Método para guardar un archivo
    public void save(File file) throws CityException {
        throw new CityException("Opción save en construcción. Archivo " + file.getName());
    }

    // Método para importar un archivo
    public void importFile(File file) throws CityException {
        throw new CityException("Opción import en construcción. Archivo " + file.getName());
    }

    // Método para exportar un archivo
    public void export(File file) throws CityException {
        throw new CityException("Opción export en construcción. Archivo " + file.getName());
    }
}
