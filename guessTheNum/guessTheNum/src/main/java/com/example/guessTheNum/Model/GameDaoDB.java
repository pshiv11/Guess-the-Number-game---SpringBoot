/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author patel
 */
@Repository
public class GameDaoDB implements GameDao {
    
    @Autowired
    private JdbcTemplate jdbc; 
    
    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setInProgress(rs.getBoolean("inProgress"));
            return game;
        }
        
    }

    @Override
    public Game insertGame(Game game) {
        final String INSERT_GAME = "INSERT INTO Game(answer, inProgress) VALUES(?,?)";
        jdbc.update(INSERT_GAME,
                    game.getAnswer(),
                    game.isInProgress()
                );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);
        return game;
    }
    
    @Override
    public Game getGameByID(int gameID) {
        try{
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameID = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameID);
        }catch(DataAccessException ex){
            return null;
        }
    }
    
    @Override
    public List<Game> getAllGames() {
        
        try{
            final String SELECT_ALL_GAMES = "SELECT * FROM Game";
            return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
        }catch(DataAccessException ex){
            return null;
        }
    }
    
    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE Game SET inProgress = ? WHERE gameID = ? ";
        jdbc.update(UPDATE_GAME, game.isInProgress(), game.getId());
         
    }
    
}
