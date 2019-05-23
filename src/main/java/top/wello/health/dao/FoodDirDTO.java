package top.wello.health.dao;

public class FoodDirDTO {

    private long id;
    private String imgUrl;
    private String text;

    public FoodDirDTO(long id, String imgUrl, String text) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.text = text;
    }

    public FoodDirDTO() {
    }

    @Override
    public String toString() {
        return "FoodDirDTO{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
