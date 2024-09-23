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
public class Solution {
    public int solution(int[] A){
        int smallPositive = 1;
        for(int i = 0; i < A.length; i++){
            if (smallPositive == A[i]){
                smallPositive++;
                //continue;
            }
            else if(smallPositive < A[i]){
                smallPositive = (A[i] - smallPositive) + 1;
                //continue;
            }
        }
        return smallPositive;        
    }
}
