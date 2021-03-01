package com.xiaomo.file_sys.common.factory;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mc
 * @description 默认的分页参数创建
 * @date 2020-01-01
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));     //每页多少条数据
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));   //
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        if (StrUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(pageNum, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>(pageNum, pageSize, sort);
            //与ElementUI 排序对应 ascending
            page.setAsc("ascending".equals(order));
            return page;
        }
    }
}
