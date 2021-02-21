package com.naown.aop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.naown.aop.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mybtis-Plus Mapper
 * @USER: chenjian
 * @DATE: 2021/2/11 21:46 周四
 **/
@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {
}
