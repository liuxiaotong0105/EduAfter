package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>项目名称：project
 * 类名称：Message
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/29  8:52
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@Table(name = "t_message")
public class Message implements Serializable {

    private static final long serialVersionUID = -4852732317765810959L;


    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "messageId")
    private Long messageId;

    @Column(name = "messageName")
    @ExportConfig(value = "信息名称")
    private String messageName;

    @Column(name = "messageInfo")
    private String messageInfo;

    @Column(name = "messageTime")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
    private Date messageTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

}
