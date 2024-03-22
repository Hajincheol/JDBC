package GOOGLE;

public class GoogleMember {
    //필드
    private String gId;
    private String gPw;
    private String gEmail;
    private String gName;
    private String gBirth;
    private String gGender;
    private String gPhone;

    //생성자
    //[Alt] + [Ins]
    public GoogleMember() {
    }

    //메소드 : getter and setter, toString
    public String getgId() { return gId; }
    public void setgId(String gId) { this.gId = gId; }

    public String getgPw() { return gPw; }
    public void setgPw(String gPw) { this.gPw = gPw; }

    public String getgEmail() { return gEmail; }
    public void setgEmail(String gEmail) { this.gEmail = gEmail; }

    public String getgName() { return gName; }
    public void setgName(String gName) { this.gName = gName; }

    public String getgBirth() { return gBirth; }
    public void setgBirth(String gBirth) { this.gBirth = gBirth; }

    public String getgGender() { return gGender; }
    public void setgGender(String gGender) { this.gGender = gGender; }

    public String getgPhone() { return gPhone; }
    public void setgPhone(String gPhone) { this.gPhone = gPhone; }

    @Override
    public String toString() {
        return "GoogleMember{" +
                "gId='" + gId + '\'' +
                ", gPw='" + gPw + '\'' +
                ", gEmail='" + gEmail + '\'' +
                ", gName='" + gName + '\'' +
                ", gBirth='" + gBirth + '\'' +
                ", gGender='" + gGender + '\'' +
                ", gPhone='" + gPhone + '\'' +
                '}';
    }
}