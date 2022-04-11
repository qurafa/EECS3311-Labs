package project;

public class ManagerAccount extends Account{

    private boolean adminPrivilege;

    public ManagerAccount(){
    }

    public void setAdminPrivilege(Boolean adminPrivilege){
        this.adminPrivilege = adminPrivilege;
    }

    public Boolean hasAdminPrivilege(){
        return adminPrivilege;
    }
}
