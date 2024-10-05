package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 电商-公司收发货地址表 前端控制器
 * </p>
 *
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/4 16:58
 */
@Api(tags = "收货地址模块")
@RestController
@RequestMapping("/api/address/v1/")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation("根据id查找收获地址详情")
    @GetMapping("find/{address_id}")
    public Object detail(@ApiParam @PathVariable("address_id") int addressId) {
        return addressService.detail(addressId);
    }

}

