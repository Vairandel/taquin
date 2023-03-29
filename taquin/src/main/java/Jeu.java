import Representation.Status;
import taquin.ListePrioritaire;

import java.util.ArrayList;
import java.util.LinkedList;

public class Jeu {

    /*  Cette classe prendra un Satus en reverra la procédure à suivre pour atteindre l'objectif
     *  Elle renvoi aussi le nombre de coups minimal pour atteindre l'objectif
     *  Elle nous affiche aussi le nombre d'entré dans la boucle (ceci me permet de manière personnel de voir quelle methode de comparaison est plus optimale)
     */
    public static void Jeu(Status status) {
        ListePrioritaire lp = new ListePrioritaire();
        ArrayList<Status> used = new ArrayList<>();

        lp.ajouterElement(status);
        Status s;
        Boolean exist = false;
        Integer nb = 0;

        //for (int i = 0; i < 5; i++) {
        while (!exist){
            nb++;
            s = lp.prendre();
            System.out.println("Etat pris\n" + s.toString() + " Nbre de coups : "+s.getNbreDeCoups());
            used.add(s);
            //ArrayList<Status> list = s.step();
            ArrayList<Status> list = s.move();
            for (Status l : list) {
                //l.setNbreDeCoups(s.getNbreDeCoups()+1);
                Boolean test = false;
                for (Status stat : used) {
                    if (l.equals(stat)) {
                        test = true;
                    }
                }
                for (Status stat : lp.getListe()) {
                    if (l.equals(stat)) {
                        test = true;
                    }
                }
                if (test == false) {
                    lp.ajouterElement(l);
                   // System.out.println("Element " + l.toString() + " ajouté");
                }
            }
            //System.out.println("Etat connus");
            for (Status l : lp.getListe()) {
                //System.out.println(l.toString()+" et nbre"+l.wellPlaced());
                if (l.wellPlaced() == 9) {
                    System.out.println(l.toString());
                    exist = true;
                    System.out.println("PROCEDURE A SUIVRE");
                    LinkedList<Status> evolution = new LinkedList<>();
                    Status derivedStatus = l;
                    //On stocke la procedure dans une liste
                    while(derivedStatus != null){
                        evolution.addFirst(derivedStatus);
                        derivedStatus = derivedStatus.getDerivedStatus();
                    }
                    //Et on l'affiche en console
                    for(Status status1:evolution){
                        if(status1.wellPlaced() == 9) System.out.println(status1.toString());
                        else System.out.println(status1.toString() + "  || \n  \\/");
                    }
                    //Affichage du nombre de coups minimal et du nombre d'entrée dans la boucle while
                    System.out.println("Reussi Nombre de coups : " + l.getNbreDeCoups());
                    System.out.println("Nombre entrée dans la boucle : "+ nb);
                }

            }
        }

    }

        public static void main (String[]args){
            Integer[][] initial = {{6, 7, 4}, {2, 1, 8}, {5, 3, 0}};
            Integer[][] objectif = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Status status = new Status(initial);
            //status.setObjectif(objectif);

            Jeu(status);
        }
}
