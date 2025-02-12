package piloto.usecase.crearpilotousecase;

import java.util.UUID;

public class CrearPilotoRequestModel {
    private UUID id;
    private String name;
    private String surname;
    private String fullName;
    private String shortName;
    private String pictureUrl;

    public CrearPilotoRequestModel() {}

    private CrearPilotoRequestModel(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }

    public static CrearPilotoRequestModel factory(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        try{
            return new CrearPilotoRequestModel(id, name, surname, fullName, shortName, pictureUrl);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear instancia de CrearPilotoRequestModel");
        }
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
