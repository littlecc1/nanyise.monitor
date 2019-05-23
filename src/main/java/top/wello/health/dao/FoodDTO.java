package top.wello.health.dao;

public class FoodDTO {

    private long id;
    private String heat;
    private int isRecommend;
    private long type;
    private String protein;
    private String fat;
    private String urlAddress;
    private int weight;
    private String name;
    private String picurl;
    private String foodleveltext;


    public FoodDTO() {
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getFoodleveltext() {
        return foodleveltext;
    }

    public void setFoodleveltext(String foodleveltext) {
        this.foodleveltext = foodleveltext;
    }

    public FoodDTO(long id, String heat, int isRecommend, long type, String protein, String fat, String urlAddress, int weight, String name, String picurl, String foodleveltext) {
        this.id = id;
        this.heat = heat;
        this.isRecommend = isRecommend;
        this.type = type;
        this.protein = protein;
        this.fat = fat;
        this.urlAddress = urlAddress;
        this.weight = weight;
        this.name = name;
        this.picurl = picurl;
        this.foodleveltext = foodleveltext;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodDTO{" +
                "id=" + id +
                ", heat='" + heat + '\'' +
                ", isRecommend=" + isRecommend +
                ", type=" + type +
                ", protein='" + protein + '\'' +
                ", fat='" + fat + '\'' +
                ", urlAddress='" + urlAddress + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", picurl='" + picurl + '\'' +
                ", foodleveltext='" + foodleveltext + '\'' +
                '}';
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
