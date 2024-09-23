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
public class TestCodility {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] A = {1,3,6,4,1,2};
        System.out.println(solution.solution(A));
        int [] B = {1,2,3};
        System.out.println(solution.solution(B));
        int [] C = {-1,-3};
        System.out.println(solution.solution(C));
        int [] D = {-1,-3,0};
        System.out.println(solution.solution(D));
    }
    
}
