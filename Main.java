package com.example.mypackage;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner=new Scanner(System.in);
        System.out.println("ENTER DIMENSIONS N and M\n");
        int N=scanner.nextInt();
        int M=scanner.nextInt();
        if(checkInput(N,M)==-1){
            System.out.println("WRONG INPUT ERROR CODE -1");
        }else{
            Layer layer1=new Layer(N,M);
            Layer layer2=new Layer(N,M);
            int nBricks=(N*M)/2;
            ArrayList<Integer> freeBricks=setFreeBricks(nBricks);

            layer1.initiliazeLayer();
            layer2.emptyLayer();
            if(layer1.checkLayer()==-1){
                System.out.println("WRONG INPUT! ERROR CODE -1 CAN'T BUILD NEXT LAYER");
            }else{
                solve(layer1,layer2,freeBricks);
            }
        }
        scanner.close();
    }


    //GETS A RANDOM BRICK BETWEEN 1 AND THE TOTAL NUMBER OF BRICKS
    public static int getBrick(ArrayList<Integer> freeBricks){
        while(true){
            int nBrick=(int) ((Math.random() * (freeBricks.size())) +1);
            for(int i=0;i<freeBricks.size();i++){
                if(nBrick==freeBricks.get(i)){
                    freeBricks.set(i,0);
                    return nBrick;
                }
            }
            if(isEmpty(freeBricks)){
                return -1;
            }
        }
    }

    //BUILDS THE NEXT LAYER
    public static void solve(Layer prevLayer,Layer nextLayer,ArrayList<Integer> freeBricks){
        int N=prevLayer.getN();
        int M=prevLayer.getM();
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                if(nextLayer.getLayer().get(i*M+j)==0){
                    if(j+1<M&&prevLayer.getLayer().get(i*M+j)!=prevLayer.getLayer().get(i*M+j+1)){
                        int nBrick=getBrick(freeBricks);
                        nextLayer.getLayer().set(i*M+j,nBrick);
                        nextLayer.getLayer().set(i*M+j+1,nBrick);
                    }else if(i+1<N&&prevLayer.getLayer().get(i*M+j)!=prevLayer.getLayer().get((i+1)*M+j)){
                        int nBrick=getBrick(freeBricks);
                        nextLayer.getLayer().set(i*M+j,nBrick);
                        nextLayer.getLayer().set((i+1)*M+j,nBrick);
                    }
                }
            }

        System.out.println("\nTHE NEXT LAYER IS :\n");
        nextLayer.printLayer();
    }


    //CHECKS IF A GIVEN ARRAY IS EMPTY
    public static boolean isEmpty(ArrayList<Integer> arrayList){
        int counter=0;
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i)==0){
                counter++;
            }
        }
        if(counter==arrayList.size()){
            return true;
        }
        return false;
    }

    //CHECKS USER's INPUT
    public static int checkInput(int n,int m){
        if(n>0&&m>0){
            if(n<100&&m<100)
                if(n%2==0&&m%2==0)
                    if(n<m)
                        return 1;
        }
        return -1;
    }


    //INITIALIZES AN ARRAYLIST OF FREE BRICKS
    public static ArrayList<Integer> setFreeBricks(int Bricks){
        ArrayList<Integer> freeBricks=new ArrayList<>();
        for(int i=0;i<Bricks;i++){
            freeBricks.add(i+1);
        }
        return freeBricks;
    }
}
