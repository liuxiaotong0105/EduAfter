package cc.mrbird.system.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.dao.MessageMapper;
import cc.mrbird.system.dao.MoneyMapper;
import cc.mrbird.system.domain.Advertising;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.domain.Money;
import cc.mrbird.system.service.AdvertisingService;
import cc.mrbird.system.service.MoneyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>项目名称：project
 * 类名称：AdvertisingServiceImpl
 * 类描述：
 * 创建人：刘晓彤
 * 创建时间：2018/10/23  19:25
 * 手机号：18511777907&16619767907
 * 备注：生命不息，车轮不止
 */
@Service("moneyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MoneyServiceImpl extends BaseService<Money> implements MoneyService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public List<Money> findAllMoney(QueryRequest request) {
        return moneyMapper.findAllMoney();
    }

    @Override
    public Money findAllMoneySum() {
        return moneyMapper.findAllMoneySum();
    }
}