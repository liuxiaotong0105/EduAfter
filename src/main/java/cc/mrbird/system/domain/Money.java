package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <pre>项目名称：project
 * 类名称：Money
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/30  14:17
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@Table(name = "t_user_video_course")
public class Money implements Serializable {

    private static final long serialVersionUID = 7780123202335870010L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "moneyId")
    @ExportConfig(value = "编号")
    private Long moneyId;

    @Column(name = "userName")
    @ExportConfig(value = "用户名称")
    private String  userName;

    @Column(name = "videoName")
    @ExportConfig(value = "视频名称")
    private String  videoName;

    @Column(name = "videoPrice")
    @ExportConfig(value = "视频价格")
    private String  videoPrice;


    private String  sums;

    public String getSums() {
        return sums;
    }

    public void setSums(String sums) {
        this.sums = sums;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(String videoPrice) {
        this.videoPrice = videoPrice;
    }
}
