package cal.info.Handlers;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import cal.info.Vente;
import cal.info.Chaussette;
import cal.info.ServiceVente;
import java.io.*;
import cal.info.ServiceInventaire;




public class VenteHandler implements HttpHandler{
    public final ServiceVente serviceVente;
    public final ServiceInventaire serviceInventaire;

    // Super facon d'instancier les classes de type service
    public VenteHandler(ServiceVente serviceVente, ServiceInventaire serviceInventaire) {
        this.serviceVente = serviceVente;
        this.serviceInventaire = serviceInventaire;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String methodEchange = exchange.getRequestMethod();
        String response = serviceVente.listerVentes().toString();
        switch (methodEchange) {
            case "GET":
                if (exchange.getRequestURI().getPath().contains("/vente")) {
                   serviceVente.listerVentes();
                   // et ?
                }
                break;
            
            case "POST":
                if (exchange.getRequestURI().getPath().contains("/vente")) {
                    Gson gson = new Gson();
                    Vente vente = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Vente.class);
                    for(Chaussette c : vente.getChaussettes()){
                        if(!serviceInventaire.existeDeja(c)){
                            response = "Une des chaussettes n'existe pas dans l'inventaire";
                            System.out.println(response);
                            break;
                        }
                    }
                    if(!response.equals("Une des chaussettes n'existe pas dans l'inventaire")){

                        // Excellant tout ca

                        for(Chaussette c : vente.getChaussettes()){
                            serviceInventaire.supprimerChaussette(c.getIdentifiant());
                        }   
                        serviceVente.ajouterVente(vente);
                        response = "Vente ajoutee";
                    }
                    
                }
                break;
            //dans le url il faut mettre ?id=1
            case "DELETE":
                if (exchange.getRequestURI().getPath().contains("/vente")) {
                    String query = exchange.getRequestURI().getQuery();
                    int id = Integer.parseInt(query.split("=")[1]);
                    if(serviceVente.venteExiste(id)){
                        for(Chaussette c : serviceVente.rechercherVente(id).getChaussettes()){
                            serviceInventaire.ajouterChaussette(c);
                        }
                         serviceVente.annulerVente(id);
                        response = "Vente annulee";
                    }
                    else
                        response = "Vente n'existe pas";
                }
                break;
        }






        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
    }

    
}
