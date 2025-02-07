package ar.edu.undec.adapter.service.piloto.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

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
        ArrayList<CrearPilotoRequestModel> arrayPilotos = new ArrayList<>();
        boolean repetido = false;

        for(JsonElement jsonElement : jsonArray){
            String fullName = jsonElement.getAsJsonObject().get("full_name").getAsString();
            String name = fullName.split(" ")[0];
            String surname = fullName.split(" ")[1];
            String shortName = jsonElement.getAsJsonObject().get("name_acronym").getAsString();
            String pictureUrl = jsonElement.getAsJsonObject().get("headshot_url").isJsonNull() ? "Sin imagen" : jsonElement.getAsJsonObject().get("headshot_url").getAsString();
            CrearPilotoRequestModel piloto = CrearPilotoRequestModel.factory(null, name, surname, fullName, shortName, pictureUrl);
            for(CrearPilotoRequestModel p : arrayPilotos){
                if(Objects.equals(piloto.getFullName(), p.getFullName())){
                    repetido = true;
                }
            }
            if(!repetido){
                arrayPilotos.add(piloto);
                crearPilotoController.crearPiloto(piloto);
            }
            repetido = false;
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
