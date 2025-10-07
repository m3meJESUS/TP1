package cal.info;

public class Chaussette {
    private int identifiant;
    private String couleur;
    private String taille;
    private String typeTissu;
    private double prix;

    public Chaussette(int identifiant, String couleur, String taille, String typeTissu, double prix) {
        this.identifiant = identifiant;
        this.couleur = couleur;
        this.taille = taille;
        this.typeTissu = typeTissu;
        this.prix = prix;
    }
    public int getIdentifiant() {
        return identifiant;
    }
    public String getCouleur() {
        return couleur;
    }
    public String getTaille() {
        return taille;
    }
    public String getTypeTissu() {
        return typeTissu;
    }
    public double getPrix() {
        return prix;
    }
    @Override
    public String toString() {
        return "Chaussette [identifiant=" + identifiant + ", couleur=" + couleur + ", taille=" + taille + ", typeTissu=" + typeTissu
                + ", prix=" + prix + "]";
    }
}
