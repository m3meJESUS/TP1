package cal.info;
import java.util.ArrayList;
import java.util.List;

public class ServiceVente {
    private List<Vente> ventes;

    public void creerVente(Vente v) {
        this.ventes = new ArrayList<>();
    }

    public void annulerVente(int id) {
        for (Vente v : ventes) {
            if (v.getIdentifiant() == id) {
                ventes.remove(v);
                break;
            }
        }
    }

    public List<Vente> listerVentes() {
        return ventes;
    }

    public Vente rechercherVente(int id) {
        for (Vente v : ventes) {
            if (v.getIdentifiant() == id) {
                return v;
            }
        }
        return null;
    }
}

