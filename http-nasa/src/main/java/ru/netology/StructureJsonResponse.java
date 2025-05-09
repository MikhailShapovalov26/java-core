package ru.netology;

import javax.xml.crypto.Data;

public class StructureJsonResponse {

    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getUrl() {
        return url;
    }

    public String getMedia_type() {
        return media_type;
    }
}
