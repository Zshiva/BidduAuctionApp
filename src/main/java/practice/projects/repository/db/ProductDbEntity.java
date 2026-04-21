package practice.projects.repository.db;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;

@MappedEntity("products")
public class ProductDbEntity {

    @Id
    @GeneratedValue(Type.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String entryTime;
    private String expiryTime;
    private int minBiddingAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

