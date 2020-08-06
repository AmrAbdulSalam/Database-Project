package sample;

public class TabelClass {
    String start , end ;
    int lic , id ;

    public TabelClass (String start , String end , int lic , int id){
        this.end = end;
        this.start = start;
        this.id = id;
        this.lic = lic;
    }

    public String getStart() {
        return start;
    }

    public int getId() {
        return id;
    }

    public int getLic() {
        return lic;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLic(int lic) {
        this.lic = lic;
    }

}
