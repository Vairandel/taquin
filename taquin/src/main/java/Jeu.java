import Representation.Status;
import taquin.ListePrioritaire;

import java.util.ArrayList;

public class Jeu {

    public static void Jeu(Status status) {
        ListePrioritaire lp = new ListePrioritaire();
        ArrayList<Status> used = new ArrayList<>();

        lp.ajouterElement(status);
        Status s;
        Boolean exist = false;
        Integer nb = 0;
        Status obj = new Status(status.getObjectif());
        //System.out.println("Entrez");
        //for (int i = 0; i < 5; i++) {
        while (!exist){
            nb++;
            s = lp.prendre();
            System.out.println("Etat pris" + s.toString() + " Nbre de coups "+s.getNbreDeCoups());
            used.add(s);
            ArrayList<Status> list = s.step();
            for (Status l : list) {
                l.setNbreDeCoups(s.getNbreDeCoups()+1);
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
                    System.out.println("Reussi Nombre de coups : " + l.getNbreDeCoups());
                    System.out.println("Nombre entrée dans la boucle : "+ nb);
                }
            }

        }
        }

        public static void main (String[]args){
            Integer[][] initial = {{6, 3, 1}, {2, 4, 8}, {5, 7, 0}};
            Integer[][] objectif = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
            Status status = new Status(initial);
            status.setNbreDeCoups(0);
            //System.out.println(status.wellPlaced());
        /*System.out.println("Status initial" + status.toString());
        ArrayList<Status> list = status.step();
        for(Status i:list)
            System.out.println(i.toString());
        status.setObjectif(objectif);*/
           Jeu(status);
            /*Integer[][] initial1 = {{6, 3, 1}, {2, 4, 8}, {5, 7, 0}};
            Status status1 = new Status(initial1);
            System.out.println(status.equals(status1));*/
        }
}
