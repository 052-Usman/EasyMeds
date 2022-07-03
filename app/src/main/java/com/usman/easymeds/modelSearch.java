package com.usman.easymeds;

public class modelSearch {
    String brand_name, company_name, generic_name, drug_category, link, packs_price;
    String content, indication_dosage, contra_indications, precautions, interactions, pregnancy;
    String lactation, side_effects, review;
    double rating;

    modelSearch(){}

    public modelSearch(String brand_name, String company_name, String generic_name, String drug_category, String link,
                       String packs_price, String content, String indication_dosage, String contra_indications,
                       String precautions, String interactions, String pregnancy, String lactation,
                       String side_effects, Double rating, String review) {
        this.brand_name = brand_name;
        this.company_name = company_name;
        this.generic_name = generic_name;
        this.drug_category = drug_category;
        this.link = link;
        this.packs_price = packs_price;
        this.content = content;
        this.indication_dosage = indication_dosage;
        this.contra_indications = contra_indications;
        this.precautions = precautions;
        this.interactions = interactions;
        this.pregnancy = pregnancy;
        this.lactation = lactation;
        this.side_effects = side_effects;
        this.rating = rating;
        this.review = review;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getDrug_category() {
        return drug_category;
    }

    public void setDrug_category(String drug_category) {
        this.drug_category = drug_category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPacks_price() {
        return packs_price;
    }

    public void setPacks_price(String packs_price) {
        this.packs_price = packs_price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndication_dosage() {
        return indication_dosage;
    }

    public void setIndication_dosage(String indication_dosage) {
        this.indication_dosage = indication_dosage;
    }

    public String getContra_indications() {
        return contra_indications;
    }

    public void setContra_indications(String contra_indications) {
        this.contra_indications = contra_indications;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getLactation() {
        return lactation;
    }

    public void setLactation(String lactation) {
        this.lactation = lactation;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating; }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
