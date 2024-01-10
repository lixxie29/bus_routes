package repository;

import domain.Route;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Route> getAll(){

        ArrayList<Route> Routes= new ArrayList<>();;
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM routedatabase");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Route w = new Route(
                        rs.getString("sourceCity"),
                        rs.getString("destCity"),
                        rs.getInt("departureTime"),
                        rs.getInt("arrivalTime"),
                        rs.getInt("nrSeats"),
                        rs.getInt("ticketPrice")
                        );
                Routes.add(w);
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Routes;
    }
    public void UpdateSchema(Integer updatedSeats,String sourceCity,String destCity,Integer departure){
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE routedatabase  set nrSeats=? WHERE sourceCity=? AND destCity=? and departureTime=?");

            statement.setInt(1,updatedSeats);
            statement.setString(2, sourceCity);
            statement.setString(3,destCity);
            statement.setInt(4,departure);


            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
