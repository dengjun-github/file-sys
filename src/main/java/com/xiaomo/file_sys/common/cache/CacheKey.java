package com.xiaomo.file_sys.common.cache;

/**
 * cacheKey
 */
public interface CacheKey {

    String USER_MAP = "user_map";
    /**
     * 角色SET
     */
    String ROLE_SET = "role_set_";
    /**
     * 权限SET
     */
    String PERM_SET = "perm_set_";
    /**
     * 权限CODE&URL
     */
    String PERM_URL_LIST = "PERM_URL_LIST";
    /**
     * GROUP_SET
     */
    String GROUP_SET = "group_set_";
    /**
     * 项目SET
     */
    String PROJECT_SET = "project_set_";
    /**
     * 标段SET
     */
    String SECTION_SET = "section_set_";
    /**
     * 工区SET
     */
    String REGION_SET = "region_set_";
    /**
     * 项目树
     */
    String PROJECT_TREE = "project_tree_";
    /**
     * 字典
     */
    String DICT_LIST = "dict_list_";

    /**
     * 定位
     * */
    String LOCATION_LIST = "location_list";
}
