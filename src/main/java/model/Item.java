package model;

import java.sql.Date;

public class Item {
    private int id;
    private String description;
    private String type;
    private int seller;
    private int buyer;
    private double lastBidPrice;
    private double minPrice;
    private Date closingTime;
    private Date lastBidTime;

    public Date getLastBidTime() {
        return lastBidTime;
    }

    public void setLastBidTime(Date lastBidTime) {
        this.lastBidTime = lastBidTime;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public double getLastBidPrice() {
        return lastBidPrice;
    }

    public void setLastBidPrice(double lastBidPrice) {
        this.lastBidPrice = lastBidPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }
}
