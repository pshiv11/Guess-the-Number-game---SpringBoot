/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Service;

import com.example.guessTheNum.Model.Game;
import com.example.guessTheNum.Model.Round;
import java.util.List;

/**
 *
 * @author patel
 */

public interface ServiceLayer {
    
    
    
    public Game createGame(); // -- Service later generates the answer for the game   
    
    public Round createRound(int gameID, String guess); // -- if the guess is correct then update the game and create a round, and if its not then still create a round and generate the anser
    
    public List<Game> getAllGames(); // -- List all games and only display the answers for the games that are completed
    
    public Game getGame(int gameID); // -- only display the answer for the game its completed
    
    public List<Round> getRoundsForGame(int gameID);
    
    
    
    
    
}
