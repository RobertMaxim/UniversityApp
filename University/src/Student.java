import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student extends JFrame {
    public Student(){}

    private int id;

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }


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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
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

    private String grupa;
    private String firstName, lastName, cnp, email;
    private Boolean status;

    Student( String firstName, String lastName, String grupa,String cnp, String email, Boolean status){
        this.lastName=lastName;
        this.firstName=firstName;
        this.grupa=grupa;
        this.cnp=cnp;
        this.email=email;
        this.status=status;
    }

    public int StudentInsert() throws SQLException, ClassNotFoundException {

        int valid=0;
        if(valid==0) {
            String jdbcURL = "jdbc:sqlserver://localhost:1433;database=University;integratedSecurity=true";
            String username = "admin";
            String password = "1q2w3e";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String sql = "INSERT INTO dbo.Student (FirstName, LastName, cnp, email, status, grupa) VALUES(?,?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, this.getFirstName());
            stmt.setString(2, this.getLastName());
            stmt.setString(3, this.getCnp());
            stmt.setString(4, this.getEmail());
            stmt.setBoolean(5, this.getStatus());
            stmt.setString(6, this.getGrupa());
            stmt.executeUpdate();
            valid=1;
        }
        if(valid==1)
            return 1;
        else
            return 0;
    }

}