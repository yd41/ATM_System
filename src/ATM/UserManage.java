package ATM;



public class UserManage {



    private String count;
    private String password;
    private Double balance=0.0;
    private  String username;
    private String phoneNum;
    private String gender;


    public UserManage(String count, String password, Double balance, String username, String phoneNum, String gender) {
        this.count = count;
        this.password = password;
        this.balance = balance;
        this.username = username;
        this.phoneNum = phoneNum;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserManage() {
    }

    public String getAll(){

        return getUsername()+"  \t"+getCount()+"  \t"+getBalance()+"  \t"+getGender()+"  \t"+getPhoneNum()+"  \t"+getPassword();
    }
}