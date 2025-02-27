package piloto.model;

import piloto.exception.PilotoInvalidoException;

import java.util.UUID;

public class Piloto {

    private UUID id;
    private String name;
    private String surname;
    private String fullName;
    private String shortName;
    private String pictureUrl;

    public Piloto() {}

    private Piloto(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }

    public static Piloto factory(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) throws PilotoInvalidoException {
        if(name == null || name.isBlank()){
            throw new PilotoInvalidoException("Nombre no válido");
        }
        if(surname == null || surname.isBlank()){
            throw new PilotoInvalidoException("Apellido no válido");
        }
        if(fullName == null || fullName.isBlank()){
            throw new PilotoInvalidoException("Nombre completo no válido");
        }
        if(shortName == null || shortName.isBlank()){
            throw new PilotoInvalidoException("Nombre abreviado no válido");
        }
        if(pictureUrl == null || pictureUrl.isBlank()){
            throw new PilotoInvalidoException("URL de imagen no válida");
        }
        return new Piloto(id, name, surname, fullName, shortName, pictureUrl);
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

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
