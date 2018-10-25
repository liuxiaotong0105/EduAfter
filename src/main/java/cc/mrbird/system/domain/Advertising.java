package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>项目名称：project
 * 类名称：Advertising
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/23  19:31
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@Table(name = "t_advertisiang")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 7780120231535870010L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "advId")
    @ExportConfig(value = "编号")
    private Long advId;

    @Column(name = "advName")
    @ExportConfig(value = "广告名称")
    private String advName;

    @Column(name = "advTimes")
    @ExportConfig(value = "起始时间")
    private String advTimes;

    @Column(name = "advTimee")
    @ExportConfig(value = "结束时间")
    private String advTimee;

    @Column(name = "compName")
    @ExportConfig(value = "公司名称")
    private String compName;

    @Column(name = "advUrl")
    @ExportConfig(value = "广告链接")
    private String advUrl;


    // 用于搜索条件中的时间字段
    @Transient
    private String timeField;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvTimes() {
        return advTimes;
    }

    public void setAdvTimes(String advTimes) {
        this.advTimes = advTimes;
    }

    public String getAdvTimee() {
        return advTimee;
    }

    public void setAdvTimee(String advTimee) {
        this.advTimee = advTimee;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public String getTimeField() {
        return timeField;
    }

    public void setTimeField(String timeField) {
        this.timeField = timeField;
    }
}
