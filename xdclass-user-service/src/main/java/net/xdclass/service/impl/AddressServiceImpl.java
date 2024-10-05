package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import net.xdclass.model.AddressDO;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电商-公司收发货地址表 服务实现类
 * </p>
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/4 16:58
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressDO> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public AddressDO detail(long id) {
        AddressDO addressDO = addressMapper.selectOne(new QueryWrapper<AddressDO>().eq("id", id));
        return addressDO;
    }
}
