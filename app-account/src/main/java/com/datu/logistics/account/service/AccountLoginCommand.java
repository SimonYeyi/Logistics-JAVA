package com.datu.logistics.account.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账户登录参数")
@Data
public class AccountLoginCommand {
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("账户密码")
    private String password;
}
