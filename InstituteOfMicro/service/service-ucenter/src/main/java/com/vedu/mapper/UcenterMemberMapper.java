package com.vedu.mapper;

import com.vedu.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-10-20
 */
@Repository
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    Integer getRegisterNumber(String date);
}
