package taquin;

import Representation.Status;

import java.util.PriorityQueue;
import java.util.Comparator;

public class ListePrioritaire {
    private PriorityQueue<Status> liste;

    public ListePrioritaire() {
        Comparator<Status> comparateur = new Comparator<Status>() {

            /*  Dans cette methode on defini comment les valeurs seront ajoutés dans notre liste prioritaire
             *  En fonction d'un élèment de comparaison choisi on classe par ordre croissant ou décroissant
             * Par exemple pour la destination; plus elle est pétite plus l'état est bon
             * Et dans le cas des chiffres bien placés plus il est grand plus l'état est bon
             * Dans notre exemple on a 3 éléments de comparaison possible
             */
            public int compare(Status o1, Status o2) {
                //return Integer.compare(o2.wellPlaced(), o1.wellPlaced());
                return Integer.compare(o1.getDestination(), o2.getDestination());
                //return Integer.compare(o2.getGoodNeighbors(), o1.getGoodNeighbors());
            }
        };
        this.liste = new PriorityQueue<Status>(comparateur);
    }

    //Recupérer la liste prioritaire
    public PriorityQueue<Status> getListe() {
        return liste;
    }

    //Ajouter un élément dans la liste prioritaire
    public void ajouterElement(Status element) {
        liste.offer(element);
    }

    //Prendre l'élément prioritaire de la liste
    public Status prendre(){
        return liste.poll();
    }

}