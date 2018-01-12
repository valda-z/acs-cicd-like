package com.microsoft.azuresample.acscicdlike.model;

import org.springframework.stereotype.Component;
import com.microsoft.azuresample.acscicdlike.Utils.PostgreSqlHelper;
import java.sql.Connection;
import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vazvadsk on 2016-12-05.
 */
@Component
public class LikeDAO {
    static final Logger LOG = LoggerFactory.getLogger(LikeDAO.class);

    @PostConstruct
    public void init(){
        LOG.info("### INIT of LikeDAO called.");

        try {
            Connection conn = PostgreSqlHelper.GetConnection();
            try (PreparedStatement stmt = conn.prepareStatement(
                            "CREATE TABLE IF NOT EXISTS LikeItem(\n" +
                            " Id varchar(50) NOT NULL,\n" +
                            " LikeCount int NULL,\n" +
                            " Updated timestamp NOT NULL\n" +
                            ");"))
            {
                stmt.executeUpdate();
            }finally {
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("ERROR: cannot connect to PostgreSQL Server.");
        }
    }

    public Like query(String id){
        Like ret = new Like(
            id,
            0,
            null
            );
        try {
            Connection conn = PostgreSqlHelper.GetConnection();
            try (PreparedStatement selectStatement = conn.prepareStatement(
                    "SELECT Id, LikeCount, Updated FROM LikeItem WHERE id=?"))
            {
                selectStatement.setString(1, id);
                ResultSet rs = selectStatement.executeQuery();
                if(rs.next()) {
                    ret = (new Like(
                            rs.getString("Id"),
                            rs.getInt("LikeCount"),
                            rs.getDate("Updated")
                            ));
                }
                rs.close();
            }finally {
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("ERROR: cannot connect to PostgreSQL Server.");
        }
        return ret;
    }

    public String addLike(String id){
                try {
                    Connection conn = PostgreSqlHelper.GetConnection();
                    try (PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE LikeItem SET LikeCount=LikeCount+1, Updated=? WHERE id=?"))
                    {
                        stmt.setString(2, id);
                        stmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                        System.out.println("UPDATE: before update call.");
                        if(stmt.executeUpdate() == 0){
                            PreparedStatement stmtIns = conn.prepareStatement(
                                "INSERT INTO LikeItem(Id, LikeCount, Updated) VALUES(?,?,?)");
                                stmtIns.setString(1, id);
                                stmtIns.setInt(2, 1);
                                stmtIns.setDate(3, new java.sql.Date(new java.util.Date().getTime())); 
                                stmtIns.executeUpdate();                                   
                        }
                    }finally {
                        conn.close();
                    }
                } catch (SQLException e) {
                    LOG.error("ERROR: cannot connect to PostgreSQL Server.");
                }
        
                return id;
            }
        }
