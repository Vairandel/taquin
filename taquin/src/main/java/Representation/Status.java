package Representation;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Status {
    private Integer[][] table;
    //Cette variable represente le tableau à 2 dimensions qui montre la disposition des chiffres
    private Integer[][] objectif; //Cette variable represente l'objectif à atteindre
    private Integer nbreDeCoups;
    //Cette variable represente le nombre de coups minimum pour être à cet Etat
    private Status derivedStatus; //Cette variable est pour savoir à quel stade on était au tour précédent

    //Getters et Setters pour l'accès aux variables privées
    public Status getDerivedStatus() {
        return derivedStatus;
    }

    public void setDerivedStatus(Status derivedStatus) {
        this.derivedStatus = derivedStatus;
    }

    public Integer getNbreDeCoups() {
        return nbreDeCoups;
    }

    public void setNbreDeCoups(Integer nbreDeCoups) {
        this.nbreDeCoups = nbreDeCoups;
    }

    public Integer[][] getTable() {
        return table;
    }

    public Integer[][] getObjectif() {
        return objectif;
    }

    public void setObjectif(Integer[][] objectif) {
        this.objectif = objectif;
    }

    public void setTable(Integer[][] table) {
        this.table = table;
    }

    //Generation du constructeur
    public Status(){
        table = new Integer[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        this.objectif = new Integer[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        this.derivedStatus = null;
        this.setNbreDeCoups(0);
    }
    public Status(Integer[][] table){
        this.table = table;
        this.objectif = new Integer[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        this.derivedStatus = null;
        this.setNbreDeCoups(0);
    }

    //Cette méthode retourne le nombre de chiffre déjà bien placé à notre stade
    public Integer wellPlaced(){
        Integer wellPlaced = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Integer a = getTable()[i][j];
                Integer b = getObjectif()[i][j];
                if(a==b) wellPlaced++;
            }
        }
        return wellPlaced;
    }

    /*
    *  Cette méthode renvoi un entier qui permettra de dire si un Etat est meilleur qu'un autre
    *  Elle me permet d'evaluer la distance entre chaque chiffre et sa position fixé dans l'objectif
    *  Elle fait somme des distance d'eloignement pour chaque chiffre
    *  Plus la destination d'un Etat est grand plus on s'eloigne de l'objectif
     */
    public int getDestination(){
        int Destination = 0;
        for(int i = 0; i < 3; i++) {
            int number;
            for (int j = 0; j < 3; j++) {
                number = getTable()[i][j];
                //System.out.println("Nombre "+number+" choisi");
                int distance = 0;
                for (int ii = 0; ii < 3; ii++) {
                    for (int jj = 0; jj < 3; jj++) {
                        int obj = getObjectif()[ii][jj];
                        //System.out.println("Objectif "+obj+" choisie");
                        if (obj == number) {
                            distance += abs(i - ii) + abs(j - jj);
                            //System.out.println("Nombre "+number+" trouvé "+" avec distance "+distance);
                        }
                    }
                }
                Destination += distance;
            }
        }
        return Destination;
    }

    /*  Cette methode me permettra de comparer le tableau des voisins d'un chiffre avec ceux d'un autre
     *  Cette comparaison se fera avec le tableau des voisins du même chiffre mais pris dans l'objectif
     */
    public static Integer NberOfCommonTab (ArrayList<Integer[]> list1,ArrayList<Integer[]> list2){
        Integer common = 0;
        for(Integer[] tablist1:list1){
            for(Integer[] tablist2:list2){
                if(tablist1[0] == tablist2[0]) common++;
            }
        }
        return common;
    }

    /*  Cette méthode me permet de connaitre le nombre de bon voisinage présent dans un etat
     *  Elle sera aussi utile pour determiner si un état est meilleure qqu'un autre
     */
    public Integer getGoodNeighbors(){
        Integer goodNeighbors = 0;
        Status Objectif = new Status(getObjectif());
        for(Integer[] line:getTable()){
            for(Integer number:line) {
                goodNeighbors += NberOfCommonTab(this.getNeighbors(number),Objectif.getNeighbors(number));
            }
        }
        return goodNeighbors;
    }

    /*
     *Cette méthode récupère dans un tableau la position d'un chiffre donné
     */
    public Integer[] getPosition(Integer number){
        Integer[] position = new Integer[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getTable()[i][j] == number){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    /*
     * Cette méthode renvoi la liste de voisins d'un chiffre donné
     * Chaque voisin est representé par un tableau qui contient le chiffre de ce voisin et sa position
     */
    public ArrayList<Integer[]> getNeighbors(Integer number) {
        ArrayList<Integer[]> NeighborsList = new ArrayList<>();
        Integer[] position = this.getPosition(number);
        //System.out.println("Position de "+number+" : "+position[0]+" et "+position[1]);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == position[0] && j == position[1] + 1)||(i == position[0] + 1 && j == position[1])||(i == position[0] - 1 && j == position[1])||(i == position[0] && j == position[1] - 1)){
                    Integer[] location = {getTable()[i][j], i, j};
                    NeighborsList.add(location);
                }
            }
        }
        return NeighborsList;
    }

    //Cette méthode me permet d'afficher mon état sous forme de tableau dans la console
    @Override
    public String toString() {
        return getTable()[0][0]+"|"+getTable()[0][1]+"|"+getTable()[0][2]+"\n"+"-------"+"\n"+getTable()[1][0]+"|"+getTable()[1][1]+"|"+getTable()[1][2]+"\n"+"-------"+"\n"+getTable()[2][0]+"|"+getTable()[2][1]+"|"+getTable()[2][2]+"\n";
    }

    /*
     * cette méthode renvoi les différents états vers lesquels on peut se diriger lorsqu'on fait un mouvemet
     */
    public ArrayList<Status> move(){
        ArrayList<Status> list = new ArrayList<>();
        Integer[] zeroPosition = this.getPosition(0);
        ArrayList<Integer[]> zeroNeighbors = this.getNeighbors(0);
        for(Integer[] integers:zeroNeighbors){
            Integer[][] tab = new Integer[3][3];
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tab[i][j] = getTable()[i][j];
            tab[zeroPosition[0]][zeroPosition[1]] = tab[integers[1]][integers[2]];
            tab[integers[1]][integers[2]] = 0;
            Status statusDerived = new Status(tab);
            statusDerived.setNbreDeCoups(this.getNbreDeCoups()+1);
            statusDerived.setDerivedStatus(this);
            statusDerived.setObjectif(this.getObjectif());
            list.add(statusDerived);
        }
        return list;
    }

    //Cette méthode fait la même chose que la méthode précédente mais elle est moins efficace
    public ArrayList<Status> step(){
            ArrayList<Status> list = new ArrayList<>();
            if (this.getTable()[0][0] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                    }
                tab1[0][0] = tab1[0][1];
                tab2[0][0] = tab2[1][0];
                tab1[0][1] = 0;
                tab2[1][0] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                list.add(s1);
                list.add(s2);

            }
            if (this.getTable()[0][2] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                    }
                tab1[0][2] = tab1[0][1];
                tab2[0][2] = tab2[1][2];
                tab1[0][1] = 0;
                tab2[1][2] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                list.add(s1);
                list.add(s2);
            }
            if (this.getTable()[2][0] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                    }
                tab1[2][0] = tab1[1][0];
                tab2[2][0] = tab2[2][1];
                tab1[1][0] = 0;
                tab2[2][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                list.add(s1);
                list.add(s2);

            }
            if (this.getTable()[2][2] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                    }
                tab1[2][2] = tab1[1][2];
                tab2[2][2] = tab2[2][1];
                tab1[1][2] = 0;
                tab2[2][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                list.add(s1);
                list.add(s2);

            }
            if (this.getTable()[0][1] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                Integer[][] tab3 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                        tab3[i][j] = getTable()[i][j];
                    }
                tab1[0][1] = tab1[0][0];
                tab1[0][0] = 0;
                tab2[0][1] = tab2[0][2];
                tab2[0][2] = 0;
                tab3[0][1] = tab3[1][1];
                tab3[1][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                Status s3 = new Status(tab3);
                list.add(s1);
                list.add(s2);
                list.add(s3);

            }
            if (this.getTable()[1][0] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                Integer[][] tab3 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                        tab3[i][j] = getTable()[i][j];
                    }
                tab1[1][0] = tab1[0][0];
                tab1[0][0] = 0;
                tab2[1][0] = tab2[2][0];
                tab2[2][0] = 0;
                tab3[1][0] = tab3[1][1];
                tab3[1][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                Status s3 = new Status(tab3);
                list.add(s1);
                list.add(s2);
                list.add(s3);

            }
            if (this.getTable()[1][2] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                Integer[][] tab3 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                        tab3[i][j] = getTable()[i][j];
                    }

                tab1[1][2] = tab1[0][2];
                tab1[0][2] = 0;
                tab2[1][2] = tab2[2][2];
                tab2[2][2] = 0;
                tab3[1][2] = tab3[1][1];
                tab3[1][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                Status s3 = new Status(tab3);
                list.add(s1);
                list.add(s2);
                list.add(s3);
            }
            if (this.getTable()[2][1] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                Integer[][] tab3 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                        tab3[i][j] = getTable()[i][j];
                    }
                tab1[2][1] = tab1[2][0];
                tab1[2][0] = 0;
                tab2[2][1] = tab2[2][2];
                tab2[2][2] = 0;
                tab3[2][1] = tab3[1][1];
                tab3[1][1] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                Status s3 = new Status(tab3);
                list.add(s1);
                list.add(s2);
                list.add(s3);

            }
            if (this.getTable()[1][1] == 0) {
                Integer[][] tab1 = new Integer[3][3];
                Integer[][] tab2 = new Integer[3][3];
                Integer[][] tab3 = new Integer[3][3];
                Integer[][] tab4 = new Integer[3][3];
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        tab1[i][j] = getTable()[i][j];
                        tab2[i][j] = getTable()[i][j];
                        tab3[i][j] = getTable()[i][j];
                        tab4[i][j] = getTable()[i][j];
                    }
                tab1[1][1] = tab1[0][1];
                tab1[0][1] = 0;
                tab2[1][1] = tab2[1][0];
                tab2[1][0] = 0;
                tab3[1][1] = tab3[2][1];
                tab3[2][1] = 0;
                tab4[1][1] = tab3[1][2];
                tab4[1][2] = 0;
                Status s1 = new Status(tab1);
                Status s2 = new Status(tab2);
                Status s3 = new Status(tab3);
                Status s4 = new Status(tab4);
                list.add(s1);
                list.add(s2);
                list.add(s3);
                list.add(s4);

            }
            for (Status status:list){
                status.setNbreDeCoups(this.getNbreDeCoups()+1);
                status.setDerivedStatus(this);
            }
            return list;
        }


    /*
     * Cette méthode me permet de redéfinir la notion d'égalité entre 2 états
     * Ainsi 2 états sont egaux si les chiffres présents dans leurs tables sont rangées dans le même ordre
     */
    public boolean equals(Status status) {
        Integer wellPlaced = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(getTable()[i][j] == status.getTable()[i][j])
                    wellPlaced ++;
            }
        }
        if(wellPlaced == 9) return true;
        else return false;
    }
}

