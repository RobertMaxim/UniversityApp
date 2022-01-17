import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRolid() {
        return rolid;
    }

    public void setRolid(int rolid) {
        this.rolid = rolid;
    }

    private String password;
    private int rolid;

    User(String user, String pw, int rol) throws SQLException {
        username = user;
        password = pw;
        rolid=rol;
        String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
        String username = "admin";
        String password = "1q2w3e";

        Connection connection = DriverManager.getConnection(jdbcURL, username, password);

        String sql = "INSERT INTO dbo.[User] (rolid,username, password) VALUES(?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, String.valueOf(this.getRolid()));
        stmt.setString(2, this.getUsername());
        stmt.setString(3, this.getPassword());
        stmt.executeUpdate();
    }
}
