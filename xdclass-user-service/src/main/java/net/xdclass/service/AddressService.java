package net.xdclass.service;

import net.xdclass.model.AddressDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电商-公司收发货地址表 服务类
 * </p>
 *
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/4 16:58
 */
public interface AddressService extends IService<AddressDO> {
    AddressDO detail(int id);

}
