package at.htl.LibraryJPAST;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//because of database inserts
public class LibraryDBTests {

    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    private static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db;create=true";
    private static final String USER = "app";
    private static final String PASSWORD = "app";
    private static Connection conn;

    @BeforeClass
    public static void initJdbc() {
        // Verbindung zur DB herstellen
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n"
                    + e.getMessage() + "\n");
            System.exit(1);
        }

    }
    @AfterClass
    public static void teardownJdbc() {
        // Connection schließen
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void t01_readExemplars(){
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT id, weariness,item_id \"item\" FROM exemplar");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("id"),is(1));
            assertThat(rs.getString("weariness"),is("undamaged"));
            assertThat(rs.getInt("item"),is(1));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t02_createExemplars(){
        int countInserts = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO EXEMPLAR (id, WEARINESS, ITEM_ID) VALUES (2,'unusable',1)";
            countInserts += stmt.executeUpdate(sql);
            sql = "INSERT INTO EXEMPLAR (id, WEARINESS, ITEM_ID) VALUES (3,'used',1)";
            countInserts += stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(countInserts, is(2));

        // Daten abfragen
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT id, WEARINESS,item_id item FROM EXEMPLAR");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("id"),is(1));
            assertThat(rs.getString("weariness"),is("undamaged"));
            assertThat(rs.getInt("item"),is(1));
            rs.next();
            assertThat(rs.getInt("id"),is(2));
            assertThat(rs.getString("weariness"),is("unusable"));
            assertThat(rs.getInt("item"),is(1));
            rs.next();
            assertThat(rs.getInt("id"),is(3));
            assertThat(rs.getString("weariness"),is("used"));
            assertThat(rs.getInt("item"),is(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void t03_updateExemplar(){
        int countUpdates = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE EXEMPLAR SET WEARINESS='unusable' where id=3";
            countUpdates += stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(countUpdates,is(1));
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT id, WEARINESS,item_id item FROM EXEMPLAR where id=3");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("id"),is(3));
            assertThat(rs.getString("weariness"),is("unusable"));
            assertThat(rs.getInt("item"),is(1));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void t04_deleteCreatedExemplars(){
        int deletedExemplars =0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM EXEMPLAR where id>1";
            deletedExemplars= stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertThat(deletedExemplars,is(2));
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) counted FROM EXEMPLAR");
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            assertThat(rs.getInt("counted"),is(1));
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
