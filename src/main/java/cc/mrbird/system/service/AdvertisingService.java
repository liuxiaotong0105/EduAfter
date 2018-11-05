package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Advertising;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "AdvertisingService")
public interface AdvertisingService extends IService<Advertising> {

    List<Advertising> findAllAdvertising(Advertising advertising, QueryRequest request);

    void addAdvertising(Advertising advertising);

    List<Advertising> queryAllAdvertising();

    void deleteAdvByIds(List<Advertising> deleteAdvList);
}
