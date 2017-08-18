package sample.model.DAO;

import java.sql.*;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class Sybase_DB {

    protected String db_ip;
    protected String db_port;
    protected String db_username;
    protected String db_password;

    protected Connection con;
    protected Statement stmt;


    public Sybase_DB(String db_ip, String db_port, String db_username, String db_password) {
        this.db_ip = db_ip;
        this.db_port = db_port;
        this.db_username = db_username;
        this.db_password = db_password;
    }

    public Sybase_DB() {

    }

    public static void main(String[] args) {

        Sybase_DB u2000_sls_db = new Sybase_DB();
        u2000_sls_db.connect();


    }


    public void connect() {

        stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:10.76.2.55:4100", "sa", "emsems");

            System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( "select count(*) as test from logdb..tbl_SysLog");
            while ( rs.next( ) ) {
                System.out.println( rs.getString("test") );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public void printVersion() {

        Statement stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            System.out.println("Sybase_DB.printVersion ... successfully connect" );
            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( "select @@version");
            while ( rs.next( ) ) {
                System.out.println( rs.getString("") );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                } // ignore

                stmt = null;
            }
            try {
                con.close();
                System.out.println("Sybase_DB.printVersion ... successfully disconnect" );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public String getDb_ip() {
        return db_ip;
    }

    public void setDb_ip(String db_ip) {
        this.db_ip = db_ip;
    }

    public String getDb_port() {
        return db_port;
    }

    public void setDb_port(String db_port) {
        this.db_port = db_port;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }
}
