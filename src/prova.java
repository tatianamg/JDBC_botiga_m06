import java.sql.*;

public class prova {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            System.out.println("Error al aconseguir el Driver");
        }
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://dbclassetest.cjb3iunslamu.us-east-2.rds.amazonaws.com/botiga?" +
                            "user=admin&password=adminadmin");

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT nom FROM seccions");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
/*
            if (stmt.execute("SELECT titol,autor FROM llibrer")) {
                rs = stmt.getResultSet();
            }
*/
            while (rs.next()) {
                String titol = rs.getString("titol");
                String autor = rs.getString("autor");
                System.out.println("llibre recuperat:" + "\t" + titol +
                        "\t" + autor);
            }
            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) { } // ignore

                conn = null;
            }

        }
    }
}