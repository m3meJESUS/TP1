package cal.info;
import java.util.List;

public class Vente {
    private int identifiant;
    private String dateVente;
    private double total;
    private List<Chaussette> chaussettes;

    public Vente(int identifiant, String dateVente, double total, List<Chaussette> chaussettes) {
        this.identifiant = identifiant;
        this.dateVente = dateVente;
        this.total = total;
        this.chaussettes = chaussettes;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public String getDateVente() {
        return dateVente;
    }

    public double getTotal() {
        return total;
    }
    
    public List<Chaussette> getChaussettes() {
        return chaussettes;
    }
    public void setChaussettes(List<Chaussette> chaussettes) {
        this.chaussettes = chaussettes;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    
    @Override
    public String toString() {
        return "Vente [identifiant=" + identifiant + ", dateVente=" + dateVente + ", total=" + total + ", chaussettes=" + chaussettes + "]";
    }
}
