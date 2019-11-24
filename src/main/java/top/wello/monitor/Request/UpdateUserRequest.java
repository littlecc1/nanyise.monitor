package top.wello.monitor.Request;

public class UpdateUserRequest extends BaseRequest {

    private int sport;
    private int height;
    private int weight;
    private int heatDay;
    private String birth;
    private int sex;

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
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

    public int getHeatDay() {
        return heatDay;
    }

    public void setHeatDay(int heatDay) {
        this.heatDay = heatDay;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
