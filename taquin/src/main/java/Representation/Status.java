package Representation;

import java.util.ArrayList;

public class Status {
    private Integer[][] table;
    private Integer[][] objectif;
    private Integer nbreDeCoups;

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

    public Status(){
        table = new Integer[3][3];
    }
    public Status(Integer[][] table){
        this.table = table;
        this.objectif = new Integer[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
    }
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

    @Override
    public String toString() {
        return "\n"+getTable()[0][0]+"|"+getTable()[0][1]+"|"+getTable()[0][2]+"\n"+"-------"+"\n"+getTable()[1][0]+"|"+getTable()[1][1]+"|"+getTable()[1][2]+"\n"+"-------"+"\n"+getTable()[2][0]+"|"+getTable()[2][1]+"|"+getTable()[2][2]+"\n";
    }
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
                return list;
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
                return list;
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
                return list;
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
                return list;
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
                return list;
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
                return list;
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
                return list;
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
                return list;
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
                return list;
            }
            return list;
        }



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

