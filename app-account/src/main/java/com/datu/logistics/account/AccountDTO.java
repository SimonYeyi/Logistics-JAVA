package com.datu.logistics.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("账户信息")
@Data
@AllArgsConstructor
public class AccountDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("账户令牌")
    private String token;
    @ApiModelProperty("账户刷新令牌")
    private String refreshToken;
}
