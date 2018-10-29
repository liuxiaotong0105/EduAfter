package cc.mrbird.system.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.dao.MessageMapper;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.domain.Message;
import cc.mrbird.system.domain.RegisteredUser;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.service.MessageService;
import cc.mrbird.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>项目名称：project
 * 类名称：MessageServiceImpl
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/29  8:53
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */

@Service("messageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MessageServiceImpl extends BaseService<Message> implements MessageService {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> findAllMessage(Message message, QueryRequest request) {
        try {
            Example example = new Example(Message.class);
            Example.Criteria criteria = example.createCriteria();
            //模糊查询
            if (StringUtils.isNotBlank(message.getMessageName())) {
                criteria.andCondition("messageName like", "%" + message.getMessageName() + "%");
            }
            if (StringUtils.isNotBlank(message.getMessageInfo())) {
                criteria.andCondition("messageInfo like", "%" + message.getMessageInfo() + "%");
            }
            //设置 order  排序
            example.setOrderByClause("messageId");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取推送信息失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addMessage(Message message) {
        this.save(message);
    }

    @Override
    public List<RegisteredUser> findAllRegisteredUser() {

        return messageMapper.findAllRegisteredUser();
    }

    @Override
    public void deleteMessage(String ids) {
        List<String> list =  new ArrayList<String>();
        String[] idss = ids.split(",");
        for (int i=0 ;i<idss.length ; i++){
            list.add(idss[i]);
        }
        messageMapper.deleteMessage(list);
    }
}
