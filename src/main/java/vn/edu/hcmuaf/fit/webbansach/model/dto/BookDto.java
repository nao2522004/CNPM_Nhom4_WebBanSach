package vn.edu.hcmuaf.fit.webbansach.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// BookDto.java
public class BookDto {
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
    private Integer stockQty;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

    private String imageUrl;
    private List<Integer> categoryIds;

    public BookDto() {
    }

    public BookDto(String title, String author, String description, BigDecimal price, Integer stockQty, LocalDate publishedDate, String imageUrl, List<Integer> categoryIds) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.stockQty = stockQty;
        this.publishedDate = publishedDate;
        this.imageUrl = imageUrl;
        this.categoryIds = categoryIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}


