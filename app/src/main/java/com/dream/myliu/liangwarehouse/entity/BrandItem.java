package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/11/19/29.
 */
public class BrandItem {
    private String description;
    private String name;
    private int id;
    private BrandLogo logo;
    private BrandCategory category;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BrandLogo getLogo() {
        return logo;
    }

    public void setLogo(BrandLogo logo) {
        this.logo = logo;
    }

    public BrandCategory getCategory() {
        return category;
    }

    public void setCategory(BrandCategory category) {
        this.category = category;
    }

    public class BrandLogo {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class BrandCategory {
        private int id;
        private String name;
    }
}
