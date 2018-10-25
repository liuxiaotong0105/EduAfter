package cc.mrbird.system.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.dao.AdvertisingMapper;
import cc.mrbird.system.domain.Advertising;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.service.AdvertisingService;
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
@Service("advertisingService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AdvertisingServiceImpl extends BaseService<Advertising> implements AdvertisingService {

    private Logger log = LoggerFactory.getLogger(this.getClass());



    @Override
    public List<Advertising> findAllAdvertising(Advertising advertising, QueryRequest request) {
        try {
            Example example = new Example(Dict.class);
            Example.Criteria criteria = example.createCriteria();
            //模糊查询
            if (StringUtils.isNotBlank(advertising.getAdvName())) {
                criteria.andCondition("advName like", "%" + advertising.getAdvName() + "%");
            }
            if (StringUtils.isNotBlank(advertising.getCompName())) {
                criteria.andCondition("compName like", "%" + advertising.getCompName() + "%");
            }
            //设置 order  排序
            example.setOrderByClause("advId");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取广告信息失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void addAdvertising(Advertising advertising) {
        this.save(advertising);
    }
}
