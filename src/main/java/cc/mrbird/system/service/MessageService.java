package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Message;
import cc.mrbird.system.domain.RegisteredUser;
import cc.mrbird.system.domain.User;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * <pre>项目名称：project
 * 类名称：MessageService
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/29  8:51
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@CacheConfig(cacheNames = "MessageService")
public interface MessageService extends IService<Message> {


    List<Message> findAllMessage(Message message, QueryRequest request);

    void addMessage(Message message);

    List<RegisteredUser> findAllRegisteredUser();

    void deleteMessage(String ids);
}
