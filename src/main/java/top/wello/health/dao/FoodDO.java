package top.wello.health.dao;

public class FoodDO extends BaseDO {


    /**
     * heat : 395.0
     * isRecommend : 0
     * recommend :
     * type : 1001007
     * protein :
     * fat :
     * picname :
     * id : 150821213400077
     * urlAddress : http://wikidiabetes.izhangkong.com/ingredients.html?id=150821213400077
     * carbohydrates :
     * gi :
     * author :
     * foodlevel :
     * recommendWeight :
     * weight : 100
     * benefit :
     * picurl : http://comveetmp.oss-cn-hangzhou.aliyuncs.com/201508/2121/zhurou.jpg
     * collectType : 4
     * nutrition :
     * foodleveltext : 适宜吃
     * name : 猪肉（肥瘦）
     */

    private float heat;
    private int isRecommend;
    private String recommend;
    private long type;
    private String protein;
    private String fat;
    private String picname;
    private long id;
    private String urlAddress;
    private String carbohydrates;
    private String gi;
    private String author;
    private String foodlevel;
    private String recommendWeight;
    private int weight;
    private String benefit;
    private String picurl;
    private int collectType;
    private String nutrition;
    private String foodleveltext;
    private String name;

    public float getHeat() {
        return heat;
    }

    public void setHeat(float heat) {
        this.heat = heat;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFoodlevel() {
        return foodlevel;
    }

    public void setFoodlevel(String foodlevel) {
        this.foodlevel = foodlevel;
    }

    public String getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(String recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public int getCollectType() {
        return collectType;
    }

    public void setCollectType(int collectType) {
        this.collectType = collectType;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getFoodleveltext() {
        return foodleveltext;
    }

    public void setFoodleveltext(String foodleveltext) {
        this.foodleveltext = foodleveltext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
