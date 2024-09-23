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
public class TestTask3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Task3 task3 = new Task3();
        int [] A = {1,4,-1,3,2};
        System.out.println(task3.solution(A));
        
        int [] B = {1,4,1,3,2};
        System.out.println(task3.solution(B));
        
        int [] C = {1,4,5,3,-1,3};
        System.out.println(task3.solution(C));
    }
    
}
