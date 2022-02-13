/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Controller;

import com.example.guessTheNum.Model.Game;
import com.example.guessTheNum.Model.Guess;
import com.example.guessTheNum.Model.Round;
import com.example.guessTheNum.Service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author patel
 */
@RestController
public class GuessTheNumController {
    
    @Autowired
    ServiceLayer myService;
    
    @PostMapping("/begin")
    public int createNewGame(){
        Game newGame = myService.createGame();
        return newGame.getId();
        
    }  
    
    @GetMapping("game/{gameID}")
    public String getGame(@PathVariable int gameID){
    
        Game retrievedGame = myService.getGame(gameID);
        if(retrievedGame != null){
            return retrievedGame.toString();
        }else{
            return "Game does not exist";
        }     
    }
    
    @GetMapping("/games")
    public String getAllGames(){
        String result = myService.getAllGames().toString();
        return result;
    }
    
    
    @PostMapping("/guess")
    public String createNewRound(@RequestBody Guess guess){
        
        int gameID = guess.getGameID();
        String userGuess = guess.getGuess();
        Round round = myService.createRound(gameID, userGuess);
        
        return round.toString();

    }
    
    @GetMapping("/rounds/{gameID}")
    public String getRoundsForGame(@PathVariable int gameID){
        List<Round> result = myService.getRoundsForGame(gameID);
        if(result == null){
            return "Game with ID:" + gameID + " does not exist";
        }
        return result.toString();
    }
}
