package cal.info.Handlers;
import com.sun.net.httpserver.*;
import cal.info.Chaussette;
import cal.info.ServiceInventaire;
import java.io.*;
import com.google.gson.Gson;

public class InventaireHandler implements HttpHandler {
    public final ServiceInventaire serviceInventaire;
    public InventaireHandler(ServiceInventaire serviceInventaire) {
        this.serviceInventaire = serviceInventaire;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    
        String response = serviceInventaire.listerChaussettes().toString();
        String methodEchange = exchange.getRequestMethod();

        switch (methodEchange) {
            case "GET":
                if (exchange.getRequestURI().getPath().contains("/inventaire") && exchange.getRequestURI().getQuery() != null) {
                    String query = exchange.getRequestURI().getQuery();
                    String[] params = query.split("&");
                    String couleur = null;
                    String taille = null;
                    for (String param : params) {
                        String[] keyValue = param.split("=");
                        if (keyValue[0].equals("couleur")) {
                            couleur = keyValue[1];
                        } else if (keyValue[0].equals("taille")) {
                            taille = keyValue[1];
                        }
                    }
                    if (couleur != null && taille != null) {
                        response = serviceInventaire.rechercherChaussettes(couleur, taille).toString();
                    } else {
                        response = "Parametres manquants";
                    }

                }
                else if(exchange.getRequestURI().getPath().contains("/inventaire")) {
                   serviceInventaire.listerChaussettes();
                }
                System.out.println(response);
                break;
            
            case "POST":
                Gson gsonPost = new Gson();
                Chaussette laChaussette = gsonPost.fromJson(new InputStreamReader(exchange.getRequestBody()), Chaussette.class);
                
                if(serviceInventaire.existeDeja(laChaussette)){
                    response = "Chaussette existe deja";
                    break;
                }
                else{
                    serviceInventaire.ajouterChaussette(laChaussette);
                    response = "Chaussette a ete ajoutee";
                    System.out.println(response);
                }
                break;
            //dans le url il faut mettre ?id=1
            case "DELETE":
                 if (exchange.getRequestURI().getPath().contains("/inventaire")) {
                    String query = exchange.getRequestURI().getQuery();
                    int id = Integer.parseInt(query.split("=")[1]);
                    serviceInventaire.supprimerChaussette(id);
                    response = "Chaussette a ete supprimee";
                    System.out.println(response);
                    break;
                }
                break;

            case "PUT":
                Gson gsonPut = new Gson();
                Chaussette chaussetteModifiee = gsonPut.fromJson(new InputStreamReader(exchange.getRequestBody()), Chaussette.class);
                if(exchange.getRequestURI().getPath().contains("/inventaire")){
                    if(serviceInventaire.existeDeja(chaussetteModifiee)){
                        serviceInventaire.supprimerChaussette(chaussetteModifiee.getIdentifiant());
                        serviceInventaire.ajouterChaussette(chaussetteModifiee);
                        response = "Chaussette a ete modifiee";
                        break;
                    }
                    else{
                        response = "Chaussette n'existe pas";
                        break;
                    }
                }
            break;
        }



        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
