package common.share.system

import common.share.core.AShareData

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 使用 系统自带的 分享功能的 分享数据体
 * </p>
 * ******************(^_^)***********************
 */
/**
 * 可配置的字段:
 * [shareTitle]
 * [shareDataType]
 * [shareText]
 * [mContext]
 */
class SystemShareData: AShareData<SystemShareData>() {
    /**
     * 微信中的分享 文本
     */
    var shareText: String = ""

    init {
        theShareBuilderStrategy = SystemShareBuilderStrategy()
    }
}