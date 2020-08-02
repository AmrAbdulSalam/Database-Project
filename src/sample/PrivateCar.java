package sample;

public class PrivateCar {

    private static String model ,price , engine_pow;
    private static int index , licence_no , payer_id;
    private static boolean selectPrivate;

    public static void setEngine_pow(String engine_pow) {
        PrivateCar.engine_pow = engine_pow;
    }

    public static void setModel(String model) {
        PrivateCar.model = model;
    }

    public static void setPrice(String price) {
        PrivateCar.price = price;
    }

    public static String getEngine_pow() {
        return engine_pow;
    }

    public static String getModel() {
        return model;
    }

    public static String getPrice() {
        return price;
    }

    public static void setIndex(int index) {
        PrivateCar.index = index;
    }

    public static void setLicence_no(int licence_no) {
        PrivateCar.licence_no = licence_no;
    }

    public static int getIndex() {
        return index;
    }

    public static int getLicence_no() {
        return licence_no;
    }

    public static void setPayer_id(int payer_id) {
        PrivateCar.payer_id = payer_id;
    }

    public static int getPayer_id() {
        return payer_id;
    }

    public static void setSelectPrivate(boolean selectPrivate) {
        PrivateCar.selectPrivate = selectPrivate;
    }

    public static boolean isSelectPrivate() {
        return selectPrivate;
    }
}
