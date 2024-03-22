package NAVER;

public class NaverMember {
    //필드
    private String nId;
    private String nPw;
    private String nEmail;
    private String nName;
    private String nBirth;
    private String nGender;
    private String nPhone;

    //생성자
    //[Alt] + [Ins]
    public NaverMember() { }

    //메소드 : getter and setter, toString
    public String getnId() { return nId; }
    public void setnId(String nId) { this.nId = nId; }

    public String getnPw() { return nPw; }
    public void setnPw(String nPw) { this.nPw = nPw; }

    public String getnEmail() { return nEmail; }
    public void setnEmail(String nEmail) { this.nEmail = nEmail; }

    public String getnName() { return nName; }
    public void setnName(String nName) { this.nName = nName; }

    public String getnBirth() { return nBirth; }
    public void setnBirth(String nBirth) { this.nBirth = nBirth; }

    public String getnGender() { return nGender; }
    public void setnGender(String nGender) { this.nGender = nGender; }

    public String getnPhone() { return nPhone; }
    public void setnPhone(String nPhone) { this.nPhone = nPhone; }

    @Override
    public String toString() {
        return "NaverMember{" +
                "nId='" + nId + '\'' +
                ", nPw='" + nPw + '\'' +
                ", nEmail='" + nEmail + '\'' +
                ", nName='" + nName + '\'' +
                ", nBirth=" + nBirth +
                ", nGender='" + nGender + '\'' +
                ", nPhone='" + nPhone + '\'' +
                '}';
    }
}