package common.share.system

import android.content.Context
import android.content.Intent
import common.share.ShareMaster
import common.share.core.AShareData
import common.share.core.IEventResultCallback
import common.share.core.IShareBuilderStrategy

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 启用系统自带 的 分享构建策略
 * </p>
 * ******************(^_^)***********************
 */
class SystemShareBuilderStrategy : IShareBuilderStrategy<SystemShareData> {
    override fun buildAndShare(
        theShareData: SystemShareData,
        resultCallback: IEventResultCallback?
    ): Boolean {
        var errorCode: Int = IEventResultCallback.CODE_ERR_COMM
        val sendIntent: Intent? = when (theShareData.shareDataType) {
                AShareData.DATA_TYPE_TEXT -> {
                    if (theShareData.shareText.isBlank()) {
                        errorCode = IEventResultCallback.CODE_SHARE_DATA_ERROR
                        null
                    } else {
                        val sendIntent = Intent(Intent.ACTION_SEND)
                        sendIntent.type = "text/plain"
                        sendIntent.putExtra(Intent.EXTRA_TEXT, theShareData.shareText)
                        sendIntent
                    }
                }
                AShareData.DATA_TYPE_IMAGE -> {//分享图片

                    null
                }
                else -> {
                    errorCode = IEventResultCallback.CODE_ERR_UNSUPPORT
                    null
                }
            }
        val isStartOk = if (sendIntent != null) {
            val context: Context? = theShareData.mContext ?: ShareMaster.getContext()
            if (context != null) {
                context.startActivity(Intent.createChooser(sendIntent,theShareData.shareTitle))
                true
            }
            else{
                errorCode = IEventResultCallback.CODE_ERR_SENT_FAILED
                false
            }
        }
        else{
            false
        }
        if (!isStartOk) {
            resultCallback?.onEventResp(
                false,
                errorCode,
                respSubCode = 0,
                respMsg = IEventResultCallback.respCodeDesc(errorCode)
            )
        }
        return isStartOk
    }

}