package com.kuril.logindemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuril.logindemo.pojo.MusicData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicDataMapper extends BaseMapper<MusicData> {
}
