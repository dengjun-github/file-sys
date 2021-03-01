package com.xiaomo.file_sys.common.result;
/**
 * 返回值code枚举
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(0, "成功"),
    FAIL(2, "失败"),
    ERROR(3, "错误"),
    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "用户名或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被冻结"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_SIGN_ERROR(20006, "用户登录异常"),
	USER_HAS_NOT_ROLE(20007, "用户未配置角色"),
    USER_OLD_PWD_ERROR(20008, "旧密码错误"),
    ROLE_CAN_NOT_DELETE(20009, "管理员角色不能删除"),
    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
    ADD_SUSPICIOUS(30002, "创建可疑人像失败，请检查远程接口"),
    SEARCH_PERSON(30003, "检索人像失败，请检查远程接口"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO(401,"无权限,请重新登录"),
    PERMISSION_NO_DATA(4002,"无数据权限"),
    PERMISSION_NO_ACCESS(70001, "权限不足"),
    PERMISSION_LOST(70002, "登录信息已过期"),

    /* 文件错误：80001-89999 */
    FILE_NOT_FOUND(80001,"文件未找到"),
    FILE_ERROR(80002, "文件错误"),
    FILE_NAME_EXIST(80003, "文件名重复"),

    /* 工作流错误：90000-99999 */
    FLOW_NOT_REPEAT_START(90001,"不能重复启动"),
    FLOW_OTHER_HANDLED(90002,"该业务已由其他人处理"),
    FLOW_EDIT_ERROR(90003,"流程已经启动，不能更改和删除"),
    FLOW_FIRST_ACT(90004,"此业务处于第一个节点，不能驳回");
    private Integer code;
    private String msg;


    ResultCode(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }
    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.msg;
    }

    public static ResultCode valueOfCode(Integer code){
        for (ResultCode item : ResultCode.values()) {
            if (item.code().equals(code)) {
                return item;
            }
        }
        return SUCCESS;
    }
    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.msg;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
