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
public class Guess {
    private int gameID;
    private String guess;

    public Guess(int gameID, String guess) {
        this.gameID = gameID;
        this.guess = guess;
    }
}
