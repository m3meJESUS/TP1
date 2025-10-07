package main.java.cal.info;

public class ServiceInventaire {
    private List<Chaussette> chaussettes;

    public ServiceInventaire() {
        this.chaussettes = new ArrayList<>();
    }

    public void ajouterChaussette(Chaussette c) {
        chaussettes.add(c);
    }

    public void supprimerChaussette(int id) {
        for (Chaussette c : chaussettes) {
            if (c.getIdentifiant() == id) {
                chaussettes.remove(c);
                break;
            }
        }
    }

    public List<Chaussette> listerChaussettes() {
        return chaussettes;
    }

    public List<Chaussette> rechercherChaussette(String couleur, String taille) {
        List<Chaussette> result = new ArrayList<>();
        for (Chaussette c : chaussettes) {
            if (c.getCouleur().equals(couleur) && c.getTaille().equals(taille)) {
                result.add(c);
            }
        }
        return result;
    }
}
