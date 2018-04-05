package cn.ltaoj.mythguard.bean;

/**
 * Created by ltaoj on 2018/3/16 21:06.
 */

public class RegistData {

    private String uname;
    private String idNumber;
    private String phone;
    private String facePath;
    // type取值为户主、常驻住户、访客
    private String type;
    private String houseNumber;
    private String binderId;

    public RegistData() {
    }

    public RegistData(String uname, String idNumber, String phone, String facePath, String type, String houseNumber, String binderId) {
        this.uname = uname;
        this.idNumber = idNumber;
        this.phone = phone;
        this.facePath = facePath;
        this.type = type;
        this.houseNumber = houseNumber;
        this.binderId = binderId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBinderId() {
        return binderId;
    }

    public void setBinderId(String binderId) {
        this.binderId = binderId;
    }
}
