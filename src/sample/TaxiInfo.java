package sample;

public class TaxiInfo {
    private static String model , price , no_dirver  , location , name;
    private static int phone , licenc_no;
    private static boolean selectTaxi = false;


    public static void setPrice(String price) {
        TaxiInfo.price = price;
    }

    public static void setModel(String model) {
        TaxiInfo.model = model;
    }

    public static void setName(String name) {
        TaxiInfo.name = name;
    }

    public static void setLocation(String location) {
        TaxiInfo.location = location;
    }

    public static void setNo_dirver(String no_dirver) {
        TaxiInfo.no_dirver = no_dirver;
    }

    public static void setPhone(int phone) {
        TaxiInfo.phone = phone;
    }


    public static String getPrice() {
        return price;
    }

    public static String getModel() {
        return model;
    }

    public static String getName() {
        return name;
    }

    public static int getPhone() {
        return phone;
    }

    public static String getLocation() {
        return location;
    }

    public static String getNo_dirver() {
        return no_dirver;
    }

    public static void setSelectTaxi(boolean selectTaxi) {
        TaxiInfo.selectTaxi = selectTaxi;
    }

    public static boolean isSelectTaxi() {
        return selectTaxi;
    }

    public static void setLicenc_no(int licenc_no) {
        TaxiInfo.licenc_no = licenc_no;
    }

    public static int getLicenc_no() {
        return licenc_no;
    }
}
