/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcodility;

/**
 *
 * @author Alex
 */
public class Task3 {
    public int badSolution(int[] A) {
        int count = 0;
        for(int node : A){
            if (node == -1) {
                count++;
            }
        }
        return A.length - count;
    }
    
    public int solution(int[] A) {
        int count = 1;
        int i = 0;
        System.out.println("i: " + i);
        while(A[i] != -1 && A[i] < A.length && count < A.length){
            i = A[i];
            System.out.println("i: " + i);
            count++;
        }
        return count;
    }
}

//https://codility.com/c/feedback/9GRUZV-AJV