package main.java.cal.info;

public class Vente {
    private int identifiant;
    private Date dateVente;
    private double total;
    private List<Chaussette> chaussettes;

    public Vente(int identifiant, Date dateVente, double total, List<Chaussette> chaussettes) {
        this.identifiant = identifiant;
        this.dateVente = dateVente;
        this.total = total;
        this.chaussettes = chaussettes;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public double getTotal() {
        return total;
    }
    
    public List<Chaussette> getChaussettes() {
        return chaussettes;
    }

    
}
