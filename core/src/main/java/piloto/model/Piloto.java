package piloto.model;

import piloto.exception.PilotoInvalidoException;

import java.util.UUID;

public class Piloto {

    private UUID id;
    private String name;
    private String surname;
    private String shortName;
    private String pictureUrl;

    public Piloto() {}

    private Piloto(UUID id, String name, String surname, String shortName, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }

    public static Piloto factory(UUID id, String name, String surname, String shortName, String pictureUrl) throws PilotoInvalidoException {
        if(name == null || name.isBlank()){
            throw new PilotoInvalidoException("Nombre no válido");
        }
        if(surname == null || surname.isBlank()){
            throw new PilotoInvalidoException("Apellido no válido");
        }
        if(shortName == null || shortName.isBlank()){
            throw new PilotoInvalidoException("Nombre abreviado no válido");
        }
        if(pictureUrl == null || pictureUrl.isBlank()){
            throw new PilotoInvalidoException("URL de imagen no válida");
        }
        return new Piloto(id, name, surname, shortName, pictureUrl);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
