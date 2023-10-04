package Config;

public class EmoloyeeModel {

    private String firstName;

    private String lastName;

    private String empId;

    private String username;

    private String password;

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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

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

    public EmoloyeeModel(){

    }


    public EmoloyeeModel(String firstName, String lastName, String empId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.empId = empId;

    }

    public EmoloyeeModel(String firstName, String lastName, String empId, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empId = empId;
        this.username = username;
        this.password = password;

    }

}
