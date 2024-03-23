package pro.walkin.framework.global;

import pro.walkin.framework.tracer.BizBaggageHolder;

/**
 * 超级管理员工具类
 */
public class SuperAdminKit {

    public static String SUPER_ADMIN_ID = "1";

    /**
     * 判断是否是超级管理员
     *
     * @param userId
     * @return
     */
    public static boolean isSuperAdmin(String userId) {
        return SUPER_ADMIN_ID.equals(userId);
    }

    /**
     * 判断当前请求是否是超级管理员
     *
     * @return
     */
    public static boolean isSuperAdmin() {
        return SUPER_ADMIN_ID.equals(BizBaggageHolder.getContext().userKey());
    }

}
