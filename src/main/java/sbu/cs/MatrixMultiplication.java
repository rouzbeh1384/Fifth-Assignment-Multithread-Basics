package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixMultiplication {

    private static java.util.Arrays Arrays;

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {

        int startRow  ,endRow,startCol, endCol;

        List<List<Integer>> tempMatrixProduct;
        List<List<Integer>> MatrixA;
        List<List<Integer>> MatrixB;

        public  BlockMultiplier(int startRow, int endRow, int startCol, int endCol, List<List<Integer>> MatrixA, List<List<Integer>> MatrixB, List<List<Integer>> MatrixC) {
            // TODO
            this.startCol=startCol;
            this.startRow=startRow;
            this.endRow=endRow;
            this.endCol=endCol;
            this.MatrixA=MatrixA;
            this.MatrixB=MatrixB;
            tempMatrixProduct=MatrixC;
        }

        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
//            int index=startRow;
//            for (int i=startCol;i<endCol;i++){
//                int value=0;
//                    for (int j=startRow;j<endRow;j++){
//                    value=MatrixA.get(i).get(j)*MatrixB.get(j).get(i);
//                    }
//                    System.out.println(value);
//                tempMatrixProduct.get(i).set(index, value);
//                    index++;

            for (int i=startRow;i<endRow;i++){
                System.out.println(endRow);
                for (int j=startCol;j<endCol;j++){
                    int value=0;
                    for(int k=0;k<MatrixA.get(0).size();k++){
                    value+=MatrixA.get(i).get(k)*MatrixB.get(k).get(j);
                    }
//                    System.out.println(value);
                    tempMatrixProduct.get(i).set(j,value);
                }
//                System.out.println("_________");
            }




        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        int p=matrix_A.size();
        int q=matrix_A.get(0).size();
        int r=matrix_B.get(0).size();
        List<List<Integer >>result=new ArrayList<>();
        for (int i=0;i<p;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for (int j=0;j<r;j++){
                row.add(0);
            }
            result.add(row);
        }



        Thread thread1=new Thread(new BlockMultiplier(0,q/2,0,p/2,matrix_A,matrix_B,result));
        Thread thread2=new Thread(new BlockMultiplier(q/2,q,p/2,p,matrix_A,matrix_B,result));
        Thread thread3=new Thread(new BlockMultiplier(0,q/2,p/2,p,matrix_A,matrix_B,result));
        Thread thread4=new Thread(new BlockMultiplier(0,q,0,p,matrix_A,matrix_B,result));






        thread1.start();
       thread2.start();
       thread3.start();
        thread4.start();

        try {
            thread1.join();
           thread2.join();
            thread3.join();
            thread4.join();
        }catch (Exception e){

        }
//        for (int k=0;k<result.size();k++){
//            for (int h=0;h<result.get(0).size();h++)
//                System.out.print(result.get(k).get(h)+" ");
//            System.out.println("");
//        }


        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> a = new ArrayList<>();

        a.add(Arrays.asList(1, 2, 3,4,5));
        a.add(Arrays.asList(1, 2, 3,4,5));
        a.add(Arrays.asList(1, 2, 3,4,5));
        a.add(Arrays.asList(1, 2, 3,4,5));
        a.add(Arrays.asList(1, 2, 3,4,5));

        ParallelizeMatMul(a,a);
    }
}
