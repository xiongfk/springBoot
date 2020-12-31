package com.xiongfk.springBooting.model;

import java.util.Date;

public class EbizRedisLog {
    private Integer sid;

    private String tableName;

    private Integer redisCount;

    private Integer dbCount;

    private Integer updateCount;

    private Date createDate;

    private Date modifyDate;

    private String isDelete;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getRedisCount() {
        return redisCount;
    }

    public void setRedisCount(Integer redisCount) {
        this.redisCount = redisCount;
    }

    public Integer getDbCount() {
        return dbCount;
    }

    public void setDbCount(Integer dbCount) {
        this.dbCount = dbCount;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}