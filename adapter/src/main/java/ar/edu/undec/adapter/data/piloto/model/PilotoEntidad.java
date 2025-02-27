package ar.edu.undec.adapter.data.piloto.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "pilotos")
public class PilotoEntidad {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private String fullName;
    private String shortName;
    private String pictureUrl;

    public PilotoEntidad() {}

    public PilotoEntidad(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
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
