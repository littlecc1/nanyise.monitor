package top.wello.health.dao;

import java.io.Serializable;

public class UserDTO extends BaseDO implements Serializable {


    private static final long serialVersionUID = 7248741808243342193L;
    private long id;

    private String wechatOpenId;
    private String wechatUnionId;
    private String wechatName;
    private String wechatAvatar;
    private int wechatGender;

    private String session;
    private String wechatSession;

    private String birth;
    private int height;
    private int weight;
    private int sport;
    private int heatDay;

    public boolean newUser;

    @Override
    public String toString() {
        return "UserDTO: " + id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getHeatDay() {
        return heatDay;
    }

    public void setHeatDay(int heatDay) {
        this.heatDay = heatDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long userId) {
        this.id = userId;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getWechatUnionId() {
        return wechatUnionId;
    }

    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatAvatar() {
        return wechatAvatar;
    }

    public void setWechatAvatar(String wechatAvatar) {
        this.wechatAvatar = wechatAvatar;
    }

    public int getWechatGender() {
        return wechatGender;
    }

    public void setWechatGender(int wechatGender) {
        this.wechatGender = wechatGender;
    }

    public String getWechatSession() {
        return wechatSession;
    }

    public void setWechatSession(String wechatSession) {
        this.wechatSession = wechatSession;
    }
}
