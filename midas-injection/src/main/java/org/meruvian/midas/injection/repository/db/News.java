package org.meruvian.midas.injection.repository.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "NEWS".
 */
public class News implements java.io.Serializable, LogInformationAware {

    private Long dbId;
    private java.util.Date dbCreateDate;
    private java.util.Date dbUpdateDate;
    private String dbCreateBy;
    private String dbUpdateBy;
    private Integer dbActiveFlag;
    private String id;
    private java.util.Date refCreateDate;
    private String refCreateBy;
    private String title;
    private String content;
    private Integer status;

    public News() {
    }

    public News(Long dbId) {
        this.dbId = dbId;
    }

    public News(Long dbId, java.util.Date dbCreateDate, java.util.Date dbUpdateDate, String dbCreateBy, String dbUpdateBy, Integer dbActiveFlag, String id, java.util.Date refCreateDate, String refCreateBy, String title, String content, Integer status) {
        this.dbId = dbId;
        this.dbCreateDate = dbCreateDate;
        this.dbUpdateDate = dbUpdateDate;
        this.dbCreateBy = dbCreateBy;
        this.dbUpdateBy = dbUpdateBy;
        this.dbActiveFlag = dbActiveFlag;
        this.id = id;
        this.refCreateDate = refCreateDate;
        this.refCreateBy = refCreateBy;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public java.util.Date getDbCreateDate() {
        return dbCreateDate;
    }

    public void setDbCreateDate(java.util.Date dbCreateDate) {
        this.dbCreateDate = dbCreateDate;
    }

    public java.util.Date getDbUpdateDate() {
        return dbUpdateDate;
    }

    public void setDbUpdateDate(java.util.Date dbUpdateDate) {
        this.dbUpdateDate = dbUpdateDate;
    }

    public String getDbCreateBy() {
        return dbCreateBy;
    }

    public void setDbCreateBy(String dbCreateBy) {
        this.dbCreateBy = dbCreateBy;
    }

    public String getDbUpdateBy() {
        return dbUpdateBy;
    }

    public void setDbUpdateBy(String dbUpdateBy) {
        this.dbUpdateBy = dbUpdateBy;
    }

    public Integer getDbActiveFlag() {
        return dbActiveFlag;
    }

    public void setDbActiveFlag(Integer dbActiveFlag) {
        this.dbActiveFlag = dbActiveFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.Date getRefCreateDate() {
        return refCreateDate;
    }

    public void setRefCreateDate(java.util.Date refCreateDate) {
        this.refCreateDate = refCreateDate;
    }

    public String getRefCreateBy() {
        return refCreateBy;
    }

    public void setRefCreateBy(String refCreateBy) {
        this.refCreateBy = refCreateBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public void setLogInformation(LogInformation logInformation) {
        setRefCreateDate(logInformation.getCreateDate());
        setRefCreateBy(logInformation.getCreateBy());
    }

}
