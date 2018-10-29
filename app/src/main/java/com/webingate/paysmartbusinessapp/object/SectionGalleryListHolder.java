package com.webingate.paysmartbusinessapp.object;

/**
 * Created by Panacea-Soft on 6/30/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class SectionGalleryListHolder {
    public String id;
    public String name;
    public boolean isSectionTitle;
    public SectionGallery.Image image;
    public boolean isLastPhoto =  false;

    public SectionGalleryListHolder(String id, String name, boolean isSectionTitle, SectionGallery.Image image) {
        this.id = id;
        this.name = name;
        this.isSectionTitle = isSectionTitle;
        this.image = image;
    }

    public SectionGalleryListHolder(String id, String name, boolean isSectionTitle, SectionGallery.Image image, boolean isLastPhoto) {
        this.id = id;
        this.name = name;
        this.isSectionTitle = isSectionTitle;
        this.image = image;
        this.isLastPhoto = isLastPhoto;
    }
}
