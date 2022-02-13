/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Service;

import com.example.guessTheNum.Model.Game;
import com.example.guessTheNum.Model.GameDao;
import com.example.guessTheNum.Model.Round;
import com.example.guessTheNum.Model.RoundDao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author patel
 */

@Component
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    @Override
    public Game createGame() {
        Random random = new Random();
        String answer  = String.format("%04d", random.nextInt(9999)) ;
        
        Game newGame = new Game();
        newGame.setAnswer(answer);
        newGame.setInProgress(true);
        return gameDao.insertGame(newGame);
        
    }

    @Override
    public Round createRound(int gameID, String guess) {
        
        //creates a new round
        Round round = new Round();
        
        //retrieves the game for @gameID
        Game retrievedGame = gameDao.getGameByID(gameID);
        
        //sets the guess, guessTime and game for the round
        round.setGuess(guess);
        round.setGame(retrievedGame);
        round.setTime(LocalDateTime.now());
        
        // retrieves the answer for @game and sets the result
        String answer = retrievedGame.getAnswer();
        
        if(retrievedGame.getAnswer().equals(guess)){
            retrievedGame.setInProgress(false);
            gameDao.updateGame(retrievedGame);
            round.setResult("e:" + guess.length() + ":p:0");
        }
        else{
            round.setResult(this.getResult(retrievedGame.getAnswer(), guess));
            retrievedGame = this.getGame(gameID);
            round.setGame(retrievedGame);
        }
        
        Round insertedRound = roundDao.insertRound(round);
        return insertedRound;
  
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames = gameDao.getAllGames();
        for(Game g: allGames){
            if(g.isInProgress()){
                g.setAnswer("xxxx");
            }
        }
        return allGames;
    }

    @Override
    public Game getGame(int gameID) {
        Game retrievedGame = gameDao.getGameByID(gameID);
        if(retrievedGame.isInProgress()){
            retrievedGame.setAnswer("xxxx");
            return retrievedGame;
        }else{
            return retrievedGame;
        }
    }
    @Override
    public List<Round> getRoundsForGame(int gameID) {
        return roundDao.getAllRoundsForGame(gameID);
    }
    
    private String getResult(String answer, String guess){
        int e = 0;
        int p = 0;
    
        for(int i = 0; i < answer.length(); i++){
            if(answer.charAt(i) == guess.charAt(i)){
                e++;
            }
            else if(answer.contains(Character.toString(guess.charAt(i)))){
                p++;
            }
            else{
                continue;
            }
        }
        return "e:" + e + ":p:" + p;
    }

}
