package com.xiaomo.file_sys.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaomo.file_sys.module.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

/**
 * @author DJ
 * @className FileInfoMapper
 * @Description
 * @date 2021-02-26 10:44
 */
@Mapper
@Resource
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
