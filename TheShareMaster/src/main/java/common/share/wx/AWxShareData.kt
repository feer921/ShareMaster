package common.share.wx

import common.share.core.AShareData

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 适合微信的分享数据
 * </p>
 * ******************(^_^)***********************
 */
class AWxShareData : AShareData<AWxShareData>() {
    /**
     * 微信中的分享 文本
     */
    var shareText: String = ""

    /**
     * 由于 微信的分享、授权登录等，是一定要 APP所申请时的包名下有一个 WXEntryActivity；
     * 所以由外部传过来
     */
    var wxEntryActivityClass: Class<*>? = null

    init {
        //指定 微信分享的构建策略
        theShareBuilderStrategy = WxShareBuilderStrategy()
    }

}