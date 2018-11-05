package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Advertising;

import java.util.List;

public interface AdvertisingMapper extends MyMapper<Advertising> {
    List<Advertising> queryAllAdvertising();

    void deleteAdvByIds(List<Advertising> deleteAdvList);
}
