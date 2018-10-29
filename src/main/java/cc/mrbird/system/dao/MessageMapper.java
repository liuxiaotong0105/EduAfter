package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Message;
import cc.mrbird.system.domain.RegisteredUser;

import java.util.List;

public interface MessageMapper extends MyMapper<Message> {
    List<RegisteredUser> findAllRegisteredUser();

    void deleteMessage(List<String> list);
}
