package com.gioov.nimrod.system.entity;

import com.gioov.nimrod.common.exportbyexcel.IsOrNotExportByExcelHandler;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcel;
import com.gioov.nimrod.common.exportbyexcel.SimpleDateFormatExportByExcelHandler;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class DictionaryEntity implements Serializable {

    private static final long serialVersionUID = -4000696333938261490L;

    /**
     * id
     */
    @ExportByExcel(name = "ID", importIgnore = true, exportIgnore = true)
    private Long id;

    /**
     * 字典键名
     */
    @ExportByExcel("字典键名")
    private String keyName;

    /**
     * 字典键
     */
    @ExportByExcel(name = "字典键")
    private String key;

    /**
     * 字典值名
     */
    @ExportByExcel("字典值名")
    private String valueName;

    /**
     * 字典值别名
     */
    @ExportByExcel("字典值别名")
    private String valueSlug;

    /**
     * 字典值
     */
    @ExportByExcel("字典值")
    private String value;

    /**
     * 是否有效（0=否，1=是，默认=0）
     */
    @ExportByExcel(value = "是否有效", handler = IsOrNotExportByExcelHandler.class)
    private Integer enable;

    /**
     * 字典分类 id
     */
    private Long dictionaryCategoryId;

    /**
     * 排序
     */
    @ExportByExcel("排序")
    private Long sort;

    /**
     * 备注
     */
    @ExportByExcel("备注")
    private String remark;

    /**
     * 更新时间
     */
    @ExportByExcel(value = "更新时间", handler = SimpleDateFormatExportByExcelHandler.class)
    private Date gmtModified;

    /**
     * 创建时间
     */
    @ExportByExcel(value = "创建时间", handler = SimpleDateFormatExportByExcelHandler.class)

    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueSlug() {
        return valueSlug;
    }

    public void setValueSlug(String valueSlug) {
        this.valueSlug = valueSlug;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getDictionaryCategoryId() {
        return dictionaryCategoryId;
    }

    public void setDictionaryCategoryId(Long dictionaryCategoryId) {
        this.dictionaryCategoryId = dictionaryCategoryId;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    @Override
    public String toString() {
        return "DictionaryEntity{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", keyName='" + keyName + '\'' +
                ", valueName='" + valueName + '\'' +
                ", valueSlug='" + valueSlug + '\'' +
                ", value='" + value + '\'' +
                ", enable=" + enable +
                ", dictionaryCategoryId=" + dictionaryCategoryId +
                ", sort=" + sort +
                ", remark='" + remark + '\'' +
                ", gmtModified=" + gmtModified +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}
