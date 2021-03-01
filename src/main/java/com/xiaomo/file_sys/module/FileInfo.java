package com.xiaomo.file_sys.module;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DJ
 * @className FileInfo
 * @Description
 * @date 2021-02-26 10:33
 */
@Data
@TableName("file_info")
@Builder
public class FileInfo extends Model<FileInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;

	/**
	 * 文件名字
	 */
	private String filename;

	/**
	 * 文件请求地址
	 */
	private String fileReqPath;

	/**
	 * 文件真实地址(绝对路劲)
	 */
	private String fileRealPath;

	/**
	 * 创建时间
	 */
	private Long createTime;



	@Override
	protected Serializable pkVal() {
		return null;
	}
}
