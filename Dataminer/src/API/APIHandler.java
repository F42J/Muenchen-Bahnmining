package API;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIHandler {

    private final String host;
    private final String id;
    private final String secret;
    private HttpClient client;

    public APIHandler(String host, String id, String secret) {
        this.host = host;
        this.id = id;
        this.secret = secret;
        this.client = HttpClient.newHttpClient();
    }

    public Timetable fetchPlan(int station, int date, int hour) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://"+host+"/db-api-marketplace/apis/timetables/v1/plan/"+station+"/"+date+"/"+hour)).header("DB-Client-Id",id).header("DB-Api-Key",secret).header("accept","application/json").build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }






}
