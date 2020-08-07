package sample;

public final class UserInformation {
    private static int id;
    private static String username , password , birthday , name , lastname ;
    private  static double salary;
    private static int customer_id , bank_id;

    public UserInformation(){

    }

    public static void setBirthday(String birthday) {
        UserInformation.birthday = birthday;
    }

    public static void setId(int id) {
        UserInformation.id = id;
    }

    public static void setUsername(String username) {
        UserInformation.username = username;
    }

    public static void setSalary(double salary) {
        UserInformation.salary = salary;
    }

    public static void setPassword(String password) {
        UserInformation.password = password;
    }

    public static void setName(String name) {
        UserInformation.name = name;
    }

    public static void setLastname(String lastname) {
        UserInformation.lastname = lastname;
    }

    public static String getUsername() {
        return username;
    }

    public static int getId() {
        return id;
    }

    public static String getPassword() {
        return password;
    }

    public static String getName() {
        return name;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static String getLastname() {
        return lastname;
    }

    public static double getSalary() {
        return salary;
    }
//
//    public static void setBank_id(int bank_id) {
//        UserInformation.bank_id = bank_id;
//    }

    public static void setCustomer_id(int customer_id) {
        UserInformation.customer_id = customer_id;
    }

    public static int getCustomer_id() {
        return customer_id;
    }

//    public static int getBank_id() {
//        return bank_id;
//    }
}
