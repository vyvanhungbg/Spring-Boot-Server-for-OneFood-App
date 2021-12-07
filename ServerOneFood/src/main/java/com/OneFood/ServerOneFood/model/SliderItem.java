package com.OneFood.ServerOneFood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class SliderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSliderItem;
    @NotNull(message = "This field can not be null")
    private String imageUrl;
    @NotNull(message = "This field can not be null")
    private String description;


    public SliderItem() {
    }

    public SliderItem(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public long getIdSliderItem() {
        return idSliderItem;
    }

    public void setIdSliderItem(long idSliderItem) {
        this.idSliderItem = idSliderItem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
