package cal.info;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import cal.info.Handlers.InventaireHandler;
import cal.info.Handlers.VenteHandler;
import java.io.IOException;
import cal.info.ServiceInventaire;
import cal.info.ServiceVente;


public class App 
{
    public static void main( String[] args )throws Exception
    {
        ServiceInventaire serviceInventaire = new ServiceInventaire();
        ServiceVente serviceVente = new ServiceVente();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/inventaire", new InventaireHandler(serviceInventaire));
        server.createContext("/vente", new VenteHandler(serviceVente, serviceInventaire));
        
        server.setExecutor(null); 
        server.start();
        System.out.println("Server started on port 8080");
    }
}
