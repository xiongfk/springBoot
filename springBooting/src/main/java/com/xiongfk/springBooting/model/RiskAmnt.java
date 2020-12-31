package com.xiongfk.springBooting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 庞尔生 on 2018/7/12.
 */
public class RiskAmnt implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 普通医疗
     */
    private BigDecimal commonMedical;
    /**
     * 意外医疗
     */
    private BigDecimal accidentMedical;
    /**
     * 中高端医疗
     */
    private BigDecimal mediumAndHighMedical;
    /**
     * 04 重疾(所有)
     */
    private BigDecimal seriousIllness;
    /**
     * 旅游意外伤害（责任）
     */
    private BigDecimal accidentHurt;
    /**
     * 航空意外（责任）
     */
    private BigDecimal rescue;
    /**
     * 重大疾病(重疾)
     */
    private BigDecimal greatIllness;
    /**
     * 严重特定病(重疾)
     */
    private BigDecimal specificIllness;
    /**
     * 轻症(重疾)
     */
    private BigDecimal mildCase;
    /**
     * 借款人意外险(责任)
     */
    private BigDecimal borrower;
    /**
     * 投保单号
     */
    private String appContNo;
    /**
     * 保单号
     */
    private String contNo;
    /**
     * 创建时间
     */
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getCommonMedical() {
        return commonMedical;
    }

    public void setCommonMedical(BigDecimal commonMedical) {
        this.commonMedical = commonMedical;
    }

    public BigDecimal getAccidentMedical() {
        return accidentMedical;
    }

    public void setAccidentMedical(BigDecimal accidentMedical) {
        this.accidentMedical = accidentMedical;
    }

    public BigDecimal getMediumAndHighMedical() {
        return mediumAndHighMedical;
    }

    public void setMediumAndHighMedical(BigDecimal mediumAndHighMedical) {
        this.mediumAndHighMedical = mediumAndHighMedical;
    }

    public BigDecimal getSeriousIllness() {
        return seriousIllness;
    }

    public void setSeriousIllness(BigDecimal seriousIllness) {
        this.seriousIllness = seriousIllness;
    }

    public BigDecimal getAccidentHurt() {
        return accidentHurt;
    }

    public void setAccidentHurt(BigDecimal accidentHurt) {
        this.accidentHurt = accidentHurt;
    }

    public BigDecimal getRescue() {
        return rescue;
    }

    public void setRescue(BigDecimal rescue) {
        this.rescue = rescue;
    }

    public BigDecimal getGreatIllness() {
        return greatIllness;
    }

    public void setGreatIllness(BigDecimal greatIllness) {
        this.greatIllness = greatIllness;
    }

    public BigDecimal getSpecificIllness() {
        return specificIllness;
    }

    public void setSpecificIllness(BigDecimal specificIllness) {
        this.specificIllness = specificIllness;
    }

    public BigDecimal getMildCase() {
        return mildCase;
    }

    public void setMildCase(BigDecimal mildCase) {
        this.mildCase = mildCase;
    }

    public BigDecimal getBorrower() {
        return borrower;
    }

    public void setBorrower(BigDecimal borrower) {
        this.borrower = borrower;
    }

    public String getAppContNo() {
        return appContNo;
    }

    public void setAppContNo(String appContNo) {
        this.appContNo = appContNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }
}
