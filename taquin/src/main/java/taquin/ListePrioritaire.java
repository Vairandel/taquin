package taquin;

import Representation.Status;

import java.util.PriorityQueue;
import java.util.Comparator;

public class ListePrioritaire {
    private PriorityQueue<Status> liste;

    public ListePrioritaire() {
        Comparator<Status> comparateur = new Comparator<Status>() {
            public int compare(Status o1, Status o2) {
            return Integer.compare(o2.wellPlaced(), o1.wellPlaced()); // ordre décroissant

            }
        };
        this.liste = new PriorityQueue<Status>(comparateur);
    }

    public PriorityQueue<Status> getListe() {
        return liste;
    }

    public void ajouterElement(Status element) {
        liste.offer(element);
    }
    public Status prendre(){
        return liste.poll();
    }

}