package app;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Connection connect() {
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "cars.db";
        String url = "jdbc:sqlite:" + path;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<Car> selectAll(){
        String sql = "SELECT make, color, licensenumber FROM cars";
        List<Car> cars = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                Car car = new Car(rs.getString("licensenumber"),
                        rs.getString("make"),
                        rs.getString("color"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("-->" + e.getMessage());
        }
        return null;
    }

    public void insert(String make, String color, String licensenumber) {
        String sql = "INSERT INTO cars(make, color, licensenumber) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, make);
            pstmt.setString(2, color);
            pstmt.setString(3, licensenumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(String make, String color, String licensenumber, String new_make, String new_color, String new_licensenumber) {
        String sql = "UPDATE cars set make=?, color=?, licensenumber=? WHERE make=? AND color=? AND licensenumber=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, new_make);
            pstmt.setString(2, new_color);
            pstmt.setString(3, new_licensenumber);
            pstmt.setString(4, make);
            pstmt.setString(5, color);
            pstmt.setString(6, licensenumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String licensenumber){
        String sql = "DELETE FROM cars WHERE licensenumber=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, licensenumber);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
