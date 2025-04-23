package practice.projects.repository;

public class ProductEntity {
    private String name;
    private String description;
    private String entryTime;
    private String expiryTime;
    private int minBiddingAmount;

    public ProductEntity() {
    }

    public ProductEntity(String name) {
        this.name = name;
    }

    public ProductEntity(String name, int minBiddingAmount) {
        this.name = name;
        this.minBiddingAmount = minBiddingAmount;
    }

    public ProductEntity(String name, String description,
                         String entryTime, String expiryTime,
                         int minBiddingAmount) {
        this.name = name;
        this.description = description;
        this.entryTime = entryTime;
        this.expiryTime = expiryTime;
        this.minBiddingAmount = minBiddingAmount;
    }

    public int compareTo(ProductEntity other){
        return this.entryTime.compareTo(other.entryTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public int getMinBiddingAmount() {
        return minBiddingAmount;
    }

    public void setMinBiddingAmount(int minBiddingAmount) {
        this.minBiddingAmount = minBiddingAmount;
    }


    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", entryTime=" + entryTime +
                ", expiryTime=" + expiryTime +
                ", minBiddingAmount=" + minBiddingAmount +
                '}';
    }
}
