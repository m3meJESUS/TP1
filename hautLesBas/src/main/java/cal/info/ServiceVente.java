package cal.info;
import java.util.ArrayList;
import java.util.List;

public class ServiceVente {
    private List<Vente> ventes = new ArrayList<>();

    public ServiceVente() {
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
    public void ajouterVente(Vente v) {
        ventes.add(v);
    }
    public String toString() {
        return "ServiceVente [ventes=" + ventes + "]";
    }
}

