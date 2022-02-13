/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author patel
 */

@Repository
public interface GameDao {
   
    
    // C- Create
    public Game insertGame(Game game);
    
    //R - READONE
    public Game getGameByID(int gameID);
    
    //R - READALL
    public List<Game> getAllGames();
    
    //U - Update
    public void updateGame(Game game);
    
    //D - Delete
    
}
