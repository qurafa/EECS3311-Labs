package project;

public class ManagerAccount extends Account{

    private Store store;
    private boolean adminPrivilege;

    public ManagerAccount(){
        super();
    }

    public void setAdminPrivilege(Boolean adminPrivilege){
        this.adminPrivilege = adminPrivilege;
    }

    public Boolean hasAdminPrivilege(){
        return adminPrivilege;
    }
}
