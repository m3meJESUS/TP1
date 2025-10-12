package cal.info;
import java.util.ArrayList;
import java.util.List;

public class ServiceInventaire {
    private List<Chaussette> Inventaire = new ArrayList<>();


    public void ajouterChaussette(Chaussette c) {
        Inventaire.add(c);
    }

    public void supprimerChaussette(int id) {
        for (Chaussette c : Inventaire) {
            if (c.getIdentifiant() == id) {
                Inventaire.remove(c);
                break;
            }
        }
    }

    public List<Chaussette> listerChaussettes() {
        return Inventaire;
    }

    public List<Chaussette> rechercherChaussette(String couleur, String taille) {
        List<Chaussette> result = new ArrayList<>();
        for (Chaussette c : Inventaire) {
            if (c.getCouleur().equals(couleur) && c.getTaille().equals(taille)) {
                result.add(c);
            }
        }
        return result;
    }

    public boolean existeDeja(Chaussette c) {
        for (Chaussette chaussette : Inventaire) {
            if (chaussette.getIdentifiant() == c.getIdentifiant()) {
                return true;
            }
        }
        return false;
    }
    public List<Chaussette>  rechercherChaussettes(String couleur, String taille) {
        List<Chaussette> result = new ArrayList<>();
        for (Chaussette c : Inventaire) {
            if (c.getCouleur().equals(couleur) && c.getTaille().equals(taille)) {
                result.add(c);
            }
        }
        return result;
    }
}