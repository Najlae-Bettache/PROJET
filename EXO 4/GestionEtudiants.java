import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GestionEtudiants {
    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void ajouterEtudiant(String nom, String prenom, String classe) {
        listeEtudiants.add(new Etudiant(nom, prenom, classe));
        System.out.println("Étudiant ajouté avec succès.");
    }

    public void afficherEtudiants() {
        if (listeEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
        } else {
            System.out.println("\nListe des étudiants:");
            for (Etudiant etudiant : listeEtudiants) {
                System.out.println(etudiant);
            }
        }
    }

    public void supprimerEtudiant(String nom) {
        Iterator<Etudiant> iterator = listeEtudiants.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().nom.equalsIgnoreCase(nom)) {
                iterator.remove();
                System.out.println("Étudiant supprimé avec succès.");
                return;
            }
        }
        System.out.println("Aucun étudiant trouvé avec ce nom.");
    }
}
