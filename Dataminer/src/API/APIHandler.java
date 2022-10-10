package API;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.io.StringReader;

public class APIHandler {
    private static final Logger logger = LogManager.getLogger("Muenchen Bahnmining");
    private final String host;
    private final String id;
    private final String secret;
    private OkHttpClient client;

    public APIHandler(String host, String id, String secret) {
        this.host = host;
        this.id = id;
        this.secret = secret;
        client=new OkHttpClient();
    }

    public Document fetchPlan(int station, int date, int hour) {
            String uri = new StringBuilder().append("https://").append(host)
                    .append("/db-api-marketplace/apis/timetables/v1/plan/")
                    .append(station).append("/").append(date).append("/").append(String.format("%02d",hour)).toString();
            Request request = new Request.Builder()
                    .url(uri)
                    .get()
                    .addHeader("DB-Client-Id", id)
                    .addHeader("DB-Api-Key", secret)
                    .addHeader("accept", "application/xml")
                    .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return new SAXBuilder().build(new StringReader(response.body().string()));
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
            logger.error("Failed to fetch data");
        }
        return null;
    }
}
