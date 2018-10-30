package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Message;
import cc.mrbird.system.domain.Money;

import java.util.List;

public interface MoneyMapper extends MyMapper<Money> {

    List<Money> findAllMoney();

    Money findAllMoneySum();
}
