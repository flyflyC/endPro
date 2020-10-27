package com.vedu.service;

import com.vedu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vedu.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);
}
