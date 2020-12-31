package com.xiongfk.springBooting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EbizRiskamntDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sid;

    private String name;

    private String gender;

    private Date birthday;

    private String cardtype;

    private String cardno;

    private BigDecimal riskAmnt;

    private String amntType;

    private String contNo;

    private String applyNo;

    private BigDecimal remark1;

    private String remark2;

    private Date createDate;

    private Integer creatorId;

    private Date modifyDate;

    private Integer modifierId;

    private String isDelete;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public BigDecimal getRiskAmnt() {
        return riskAmnt;
    }

    public void setRiskAmnt(BigDecimal riskAmnt) {
        this.riskAmnt = riskAmnt;
    }

    public String getAmntType() {
        return amntType;
    }

    public void setAmntType(String amntType) {
        this.amntType = amntType == null ? null : amntType.trim();
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo == null ? null : contNo.trim();
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public BigDecimal getRemark1() {
        return remark1;
    }

    public void setRemark1(BigDecimal remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}