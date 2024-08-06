package src.database;

public enum DBPW {
    MOK("");

    private String pw;
    DBPW(String pw ) {
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }
}
