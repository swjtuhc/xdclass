package net.xdclass.service.impl;

import net.xdclass.model.UserDO;
import net.xdclass.mapper.UserMapper;
import net.xdclass.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/4 16:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
