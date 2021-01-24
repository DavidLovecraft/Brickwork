package com.example.mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Layer {
    private int N;
    private int M;
    private ArrayList<Integer> layer;

    public Layer(int n, int m) {
        this.N = n;
        this.M = m;
        this.layer=new ArrayList<>();
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public ArrayList<Integer> getLayer() {
        return layer;
    }

    //INITIaLIZES  THE LAYER
    public void initiliazeLayer(){
        System.out.println("Set layer\n");
        Scanner scanner=new Scanner(System.in);
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                layer.add(scanner.nextInt());
            }
        scanner.close();
    }

    //PRINTS THE LAYER
    public void printLayer(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(layer.get(i*M+j)+" ");
            }
            System.out.println();
        }
    }

    //FILLS A THE LAYER WITH 0
    public void emptyLayer(){
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                layer.add(0);
            }
    }

    //CHECKS IF THE USER INPUT IS CORRECT NEEDS A FIX
    public int checkLayer(){
        int nBricks=(N*M)/2;
        ArrayList<Integer> checkedBricks=new ArrayList<>();

        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                if(!checkedBricks.contains(i*M+j)){
                    if(j+1<M&&layer.get(i*M+j)==layer.get(i*M+j+1)&&j+1<M){
                        checkedBricks.add(i*M+j);
                        checkedBricks.add(i*M+j+1);
                    }else if(i+1<N&&layer.get(i*M+j)==layer.get((i+1)*M+j)){
                        checkedBricks.add(i*M+j);
                        checkedBricks.add((i+1)*M+j);
                    }
                }
            }

        for(int i=0;i<N*M;i++){
            if(!checkedBricks.contains(i)){
                return -1;
            }
        }

        if(checkedBricks.size()/2==nBricks){
            return 1;
        }
        return -1;
    }
}
