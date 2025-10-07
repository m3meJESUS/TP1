package cal.info.Handlers;
import com.sun.net.httpserver.*;
import cal.info.ServiceVente;
import java.io.*;


public class VenteHandler implements HttpHandler{
    public final ServiceVente serviceVente;
    public VenteHandler(ServiceVente serviceVente) {
        this.serviceVente = serviceVente;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Service Vente is running";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
    }

    
}
