package common.share.core

import com.tencent.mm.opensdk.modelbase.BaseResp
import java.io.Serializable

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 事件(分享、支付等) 的结果回调
 * </p>
 * ******************(^_^)***********************
 */
interface IEventResultCallback : Serializable {
//    var eventTransactionId: String

    companion object Info{
        const val CODE_OK = BaseResp.ErrCode.ERR_OK //0
        const val CODE_ERR_COMM = BaseResp.ErrCode.ERR_COMM //-1
        const val CODE_ERR_USER_CANCEL = BaseResp.ErrCode.ERR_USER_CANCEL //-2
        const val CODE_ERR_SENT_FAILED = BaseResp.ErrCode.ERR_SENT_FAILED // -3
        const val CODE_ERR_AUTH_DENIED = BaseResp.ErrCode.ERR_AUTH_DENIED // -4
        const val CODE_ERR_UNSUPPORT = BaseResp.ErrCode.ERR_UNSUPPORT//-5
        const val CODE_ERR_BAN = BaseResp.ErrCode.ERR_BAN //-6
        const val CODE_SHARE_DATA_ERROR = CODE_ERR_BAN - 1;

        fun respCodeDesc(theCode: Int):String {
            return when (theCode) {
                CODE_OK -> {
                    "0,成功"
                }
                CODE_ERR_COMM -> {
                    "-1,通用异常"
                }
                CODE_ERR_USER_CANCEL->{
                    "-2,用户取消"
                }
                CODE_ERR_SENT_FAILED->{
                    "-3,请求发送失败"
                }
                CODE_ERR_AUTH_DENIED->{
                    "-4,授权被拒绝"
                }
                CODE_ERR_UNSUPPORT->{
                    "-5,不支持的操作/请求"
                }
                CODE_ERR_BAN->{
                    "-6,被禁止"
                }
                CODE_SHARE_DATA_ERROR->{
                    "-7,分享的数据错误"
                }
                else -> {
                    "未知"
                }
            }
        }
    }

    var eventTransactionId: String

    /**
     * @param isEventOk 本次事件是否成功
     * @param respCode 各平台响应的 code
     * @param respSubCode 各平台响应的 子 code
     * @param respMsg 平台响应的消息
     * @param theRespAssignId 例如说 分享事件：如果调用处启动分享时传入了指定的区分分享id,则分享响应也会返回过来,回调处可以对比一下是否为本次分享
     */
    fun onEventResp(
        isEventOk: Boolean,
        respCode: Int,
        respSubCode: Int = 0,
        respMsg: String = "",
        theRespAssignId: String = eventTransactionId
    )
}