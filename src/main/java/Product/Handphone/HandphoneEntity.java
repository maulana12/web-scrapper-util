package Product.Handphone;



import java.math.BigDecimal;

public class HandphoneEntity {

    private int id;
    private String name;
    private String description;
    private String linkImage;
    private BigDecimal price;
    private String rating;
    private String merchant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public HandphoneEntity(String name, String description, String linkImage, BigDecimal price, String rating, String merchant) {
        this.name = name;
        this.description = description;
        this.linkImage = linkImage;
        this.price = price;
        this.rating = rating;
        this.merchant = merchant;
    }
}
