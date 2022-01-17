import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Profesor extends JFrame {
    public Profesor() {
    }

    private int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private String firstName;
    private String lastName;
    private String email;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;
    private Boolean status;

    Profesor(String firstName, String lastName, String subject, String email, Boolean status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.subject = subject;
        this.email = email;
        this.status = status;
    }

    public int ProfInsert() throws SQLException, ClassNotFoundException {

        int valid = 0;
        if (valid == 0) {
            String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
            String username = "admin";
            String password = "1q2w3e";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String sql = "INSERT INTO dbo.Profesor (FirstName, LastName, Subject, Email, Active) VALUES(?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, this.getFirstName());
            stmt.setString(2, this.getLastName());
            stmt.setString(3, this.getSubject());
            stmt.setString(4, this.getEmail());
            stmt.setBoolean(5, this.getStatus());
            stmt.executeUpdate();
            valid = 1;
        }
        if (valid == 1)
            return 1;
        else
            return 0;
    }

}