package cal.info.Handlers;
import com.sun.net.httpserver.*;
import cal.info.ServiceInventaire;
import java.io.*;

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
                
                
                break;
            
            case "POST":


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
