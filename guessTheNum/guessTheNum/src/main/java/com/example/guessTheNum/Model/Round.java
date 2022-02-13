/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author patel
 */

@Getter @Setter
public class Round {
    
    private int id;
    private String guess;
    private LocalDateTime time;
    private String result;
    private Game game;  // this is a foreign key for "Game" in a relational database

    @Override
    public String toString() {
        return "Round{" + "id=" + id + ", guess=" + guess + ", time=" + time + ", result=" + result + ", game=" + game + '}';
    }
       
}
