package common.share.core

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 分享、支付 事件结果回调
 * </p>
 * ******************(^_^)***********************
 */
abstract class AbsEventResultHandler :IEventResultCallback{
    var assignEventId: String = ""

    override var eventTransactionId: String
        get() = assignEventId
        set(value) {}

}