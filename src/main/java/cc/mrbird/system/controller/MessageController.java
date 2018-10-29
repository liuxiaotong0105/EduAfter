package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.sms.ConstantConf;
import cc.mrbird.sms.HttpClientUtil;
import cc.mrbird.sms.Md5Util;
import cc.mrbird.sms.TimeUtil;
import cc.mrbird.system.domain.RegisteredUser;
import cc.mrbird.system.domain.Message;
import cc.mrbird.system.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.solr.client.solrj.SolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>项目名称：project
 * 类名称：MessageController
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/29  8:49
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */


@Controller
public class MessageController extends BaseController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @Autowired
    private SolrClient client;

    private static final String ON = "on";

    @Log("获取推送信息")
    @RequestMapping("message")
    @RequiresPermissions("message:list")
    public String index(Model model) {
        return "system/message/message";
    }


    @RequestMapping("message/list")
    @RequiresPermissions("message:list")
    @ResponseBody
    public Map<String, Object> messageList(QueryRequest request, Message message) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Message> list = this.messageService.findAllMessage(message, request);
        PageInfo<Message> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }



    @RequestMapping("addMessage/add")
    @RequiresPermissions("message:add")
    @ResponseBody
    public ResponseBo addMessage(Message message) {
        try {
            message.setMessageTime(new Date());
            this.messageService.addMessage(message);
            return ResponseBo.ok("新增推送成功！");
        } catch (Exception e) {
            log.error("新增推送失败", e);
            return ResponseBo.error("新增推送失败，请联系网站管理员！");
        }
    }

    @RequestMapping("message/sendSMS")
    @RequiresPermissions("message:add")
    @ResponseBody
    public ResponseBo advertisingFdfs(String messageInfo) {
        try {
            List<RegisteredUser> list = this.messageService.findAllRegisteredUser();
            for (int i = 0; i < list.size() ; i++) {
                HashMap<String, Object> param = new HashMap<>();
                String timestamp = TimeUtil.fromat(new Date());
                param.put("accountSid", ConstantConf.ACCOUNTSID);
                param.put("templateid", ConstantConf.TEMPLATEID);
                //对应模板的 验证码  {1}   数据需要转为字符串  或者加引号
                param.put("param", messageInfo);
                param.put("timestamp", timestamp);
                //发送的手机号
                param.put("to", list.get(i).getPhone());
                param.put("sig", Md5Util.getMd532(ConstantConf.ACCOUNTSID+ConstantConf.AUTH_TOKEN+timestamp));
                String post = HttpClientUtil.post(ConstantConf.SMS_URL, param);
                System.out.println(post);
            }
            return ResponseBo.ok("下发活动推送成功！");
        } catch (Exception e) {
            log.error("下发活动推送失败", e);
            return ResponseBo.error("下发活动推送失败，请联系网站管理员！");
        }
    }


    @Log("删除用户")
    @RequiresPermissions("message:delete")
    @RequestMapping("message/delete")
    @ResponseBody
    public ResponseBo deleteMessage(String ids) {
        try {
            messageService.deleteMessage(ids);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }


}
