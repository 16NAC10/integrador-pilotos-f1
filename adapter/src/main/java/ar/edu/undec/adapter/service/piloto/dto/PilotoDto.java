package ar.edu.undec.adapter.service.piloto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PilotoDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("short_name")
    private String shortName;

    @JsonProperty("picture_url")
    private String pictureUrl;

    public PilotoDto() {}

    private PilotoDto(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.shortName = shortName;
        this.pictureUrl = pictureUrl;
    }

    public static PilotoDto factory(UUID id, String name, String surname, String fullName, String shortName, String pictureUrl) {
        return new PilotoDto(id, name, surname, fullName, shortName, pictureUrl);
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
