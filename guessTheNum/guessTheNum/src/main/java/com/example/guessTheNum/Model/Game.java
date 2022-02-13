/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author patel
 */
@Getter @Setter
public class Game {
    
    private int id;
    private String answer;
    private boolean inProgress;

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ",\n "
                       + "answer=" + answer + ",\n "
                       + "inProgress=" + inProgress + 
                   '}';
    }
   
}
