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
public class Task1 {
    public void solution(int N) {
        String word = "";
        for(int i = 1; i <= N; i++){
            if (i % 3 == 0){
                word = "Fizz";  
            }
            if (i % 5 == 0){
                word = word + "Buzz";  
            }
            if (i % 7 == 0){
                word = word + "Woof";  
            }
            if (word.equals("")){
                System.out.println(i);
            }else{
                System.out.println(word);
            }
            word = "";           
        }
    }
}
