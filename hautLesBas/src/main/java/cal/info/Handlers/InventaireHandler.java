package cal.info.Handlers;
import com.sun.net.httpserver.*;
import cal.info.Chaussette;
import cal.info.ServiceInventaire;
import java.io.*;
import java.util.List;
import com.google.gson.Gson;

public class InventaireHandler implements HttpHandler {
    public final ServiceInventaire serviceInventaire;
    public InventaireHandler(ServiceInventaire serviceInventaire) {
        this.serviceInventaire = serviceInventaire;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    
        String response = "Service Inventaire is running";
        String methodEchange = exchange.getRequestMethod();

        switch (methodEchange) {
            case "GET":
                List <Chaussette> inventaire = serviceInventaire.listerChaussettes();
                Gson gsonGet = new Gson();
                response = gsonGet.toJson(inventaire);
                System.out.println(response);


                break;
            
            case "POST":
                Gson gsonPost = new Gson();
                Chaussette laChaussette = gsonPost.fromJson(new InputStreamReader(exchange.getRequestBody()), Chaussette.class);
                serviceInventaire.ajouterChaussette(laChaussette);
                response = "Chaussette added";
                System.out.println(response);

                //serviceInventaire.ajouterChaussette(exchange);

                break;

            case "DELETE":


                break;

            case "PUT":
            
                break;
        }



        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
