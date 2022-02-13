/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import java.util.List;

/**
 *
 * @author patel
 */
public interface RoundDao {
    
    // C- Create
    public Round insertRound(Round round);
    
    //R - READONE  --might not needed
    public Round getRoundByID(int roundID);
    
    //R - READALL
    
    public List<Round> getAllRoundsForGame(int gameID);
    
    //U - Update   -- might not needed
    
    //D - Delete   -- might not needed
    
}
