package ar.edu.undec.adapter.service.piloto.controller;

import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;
import utils.NombreParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


@RestController
@RequestMapping("apiPilotos")
public class CrearPilotoApiController {

    CrearPilotoController crearPilotoController;

    @Autowired
    public CrearPilotoApiController(CrearPilotoController crearPilotoController) {
        this.crearPilotoController = crearPilotoController;
    }

    @PostMapping
    public ResponseEntity<?> crearPorApi(){
        String json = getJson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray){
            String fullName = jsonElement.getAsJsonObject().get("full_name").getAsString();
            fullName = NombreParser.parse(fullName);
            String name = jsonElement.getAsJsonObject().get("first_name").isJsonNull() ? fullName.split(" ")[0] : jsonElement.getAsJsonObject().get("first_name").getAsString();
            String surname = jsonElement.getAsJsonObject().get("last_name").isJsonNull() ? fullName.split(" ")[1] : jsonElement.getAsJsonObject().get("last_name").getAsString();
            String shortName = jsonElement.getAsJsonObject().get("name_acronym").getAsString();
            String pictureUrl = jsonElement.getAsJsonObject().get("headshot_url").isJsonNull() ? "Sin imagen" : jsonElement.getAsJsonObject().get("headshot_url").getAsString();
            PilotoDto piloto = PilotoDto.factory(null, name, surname, fullName, shortName, pictureUrl);
            crearPilotoController.crearPiloto(piloto);
        }
        return ResponseEntity.created(null).build();
    }

    public String getJson(){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL("https://api.openf1.org/v1/drivers");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
