package ar.edu.undec.adapter.service.piloto.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;;
import java.net.URL;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("apiPilotos")
public class CrearPilotoApiController {
    CrearPilotoController crearPilotoInput;

    @Autowired
    public CrearPilotoApiController(CrearPilotoController crearPilotoInput) {
        this.crearPilotoInput = crearPilotoInput;
    }

    @PostMapping
    public ResponseEntity<?> crearPorApi(){
        String json = getJson();

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray){
            String fullName = jsonElement.getAsJsonObject().get("full_name").getAsString();
            String name = fullName.split(" ")[0];
            String surname = fullName.split(" ")[1];
            String shortName = jsonElement.getAsJsonObject().get("name_acronym").getAsString();
            String pictureUrl = jsonElement.getAsJsonObject().get("headshot_url").isJsonNull() ? "Sin imagen" : jsonElement.getAsJsonObject().get("headshot_url").getAsString();
            CrearPilotoRequestModel input = CrearPilotoRequestModel.factory(null,name,surname,fullName,shortName,pictureUrl);

            crearPilotoInput.crearPiloto(input);
        }
        return ResponseEntity.created(null).build();
    }

    private String getJson(){
        StringBuilder sb = new StringBuilder();
        try {
            URL u = new URL("https://api.openf1.org/v1/drivers");
            HttpsURLConnection con = (HttpsURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
