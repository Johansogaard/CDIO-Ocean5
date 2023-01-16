package spil_Version_2;

public class Konto {


    private int b = 0;
    private int fieldvalue = 0;

    public Konto(int balance) {
        b = balance;
    }

    public int update(int b) {
        this.b += b;
        return this.b;

    }

    public void updateFieldValue(int fv) {
        fieldvalue += fv;
    }

    public int getFieldvalue() {
        return fieldvalue;
    }

    public int getBalance() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
