package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Advertising;
import cc.mrbird.system.domain.Money;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "MoneyService")
public interface MoneyService extends IService<Money> {


    List<Money> findAllMoney(QueryRequest request);

    Money findAllMoneySum();
}
