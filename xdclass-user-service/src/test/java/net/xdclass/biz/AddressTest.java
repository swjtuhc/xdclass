package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/5 21:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;
    @Test
    public void testAddressDetail(){
        AddressDO addressDO = addressService.detail(39L);
        log.info(addressDO.toString());
        Assert.assertNotNull(addressDO);
    }
}
