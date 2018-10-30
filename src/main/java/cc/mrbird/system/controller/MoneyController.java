package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.system.domain.Advertising;
import cc.mrbird.system.domain.Money;
import cc.mrbird.system.service.AdvertisingService;
import cc.mrbird.system.service.MoneyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <pre>项目名称：project
 * 类名称：MoneyController
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/30  14:04
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@Controller
public class MoneyController extends BaseController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoneyService moneyService;

    @Log("获取资金信息")
    @RequestMapping("money")
    @RequiresPermissions("money:list")
    public String index() {
        return "system/money/money";
    }

    @RequestMapping("money/list")
    @RequiresPermissions("money:list")
    @ResponseBody
    public Map<String, Object> moneyList(QueryRequest request, Model model) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Money> list = this.moneyService.findAllMoney(request);
        PageInfo<Money> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }


    @RequestMapping("money/sums")
    @RequiresPermissions("money:list")
    @ResponseBody
    public Money moneysums() {
        return moneyService.findAllMoneySum();
    }

    @RequestMapping("money/excel")
    @ResponseBody
    public ResponseBo moneyExcel(Money money) {
        try {
            List<Money> list = this.moneyService.findAllMoney( null);
            return FileUtils.createExcelByPOIKit("资金表", list, Money.class);
        } catch (Exception e) {
            log.error("导出资金信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }


}
