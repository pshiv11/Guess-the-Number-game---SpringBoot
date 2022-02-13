/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.Model;

import com.example.guessTheNum.Model.GameDaoDB.GameMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class RoundDaoDB implements RoundDao {
    
    @Autowired
    private JdbcTemplate jdbc;

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
           Round round = new Round();
           round.setId(rs.getInt("roundID"));
           round.setGuess(rs.getString("guess"));
           round.setTime(rs.getTimestamp("guessTime").toLocalDateTime());
           round.setResult(rs.getString("result"));
           return round;
        }
    }    

    @Override
    public Round insertRound(Round round) {
        final String INSERT_ROUND = "INSERT into Round(guess, guessTime, result, gameID) values(?, ?, ?, ?)";
        jdbc.update(INSERT_ROUND, round.getGuess(), round.getTime(), round.getResult(), round.getGame().getId());
        
        int roundID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(roundID);
        return round;
    }
    
    @Override
    public Round getRoundByID(int roundID) {
        
        try{
            final String SELECT_ROUND_BY_ID = "SELECT * from Round WHERE roundID = ?";
            Round retrievedRound = jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundID);
            retrievedRound.setGame(getGameForRound(retrievedRound));

            return retrievedRound;
        }catch(DataAccessException ex){
            return null;
        }

    }
    
    @Override
    public List<Round> getAllRoundsForGame(int gameID) {
        List<Round> result = new ArrayList<Round>();
        final String SELECT_ALL_ROUNDS_FOR_GAME = "SELECT * from Round WHERE gameID = ? ";
        result = jdbc.query(SELECT_ALL_ROUNDS_FOR_GAME, new RoundMapper(), gameID);
        
        for(Round r: result){
            r.setGame(getGameForRound(r));
        }
        return result;
    }
   
    //this method will retrieve the game object for a particular round
    private Game getGameForRound(Round round){
        final String SELECT_GAME_FOR_ROUND = "SELECT g.* FROM Game g "
                    + "JOIN Round r ON r.gameID = g.gameID WHERE r.roundID = ?";
        
        return jdbc.queryForObject(SELECT_GAME_FOR_ROUND, new GameMapper(), round.getId());
    }
    
    
}

