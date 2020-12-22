package common.share.wx

import com.tencent.mm.opensdk.modelmsg.*
import common.share.core.AShareData
import common.share.core.IEventResultCallback
import common.share.core.IShareBuilderStrategy

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 微信平台的分享构建 策略
 * </p>
 * ******************(^_^)***********************
 */
open class WxShareBuilderStrategy : IShareBuilderStrategy<AWxShareData> {
    override fun buildAndShare(
        theShareDat: AWxShareData,
        resultCallback: IEventResultCallback?
    ): Boolean {
        val wxMediaObj: WXMediaMessage.IMediaObject? =
            when (theShareDat.shareDataType) {
                AShareData.DATA_TYPE_TEXT -> {//分享文本
                    WXTextObject(theShareDat.shareText)
                    //这里 数据类型的情况下，需要 title、desc吗？
                }
                AShareData.DATA_TYPE_URL -> {//分享网页/URL
                    WXWebpageObject(theShareDat.shareUrl)
                }
                AShareData.DATA_TYPE_IMAGE -> {
                    val wxImageObject = WXImageObject()
//                    wxImageObject.
                    // TODO: 2020/12/17 ???
                    wxImageObject
                }
                else -> {
                    null
                }
            }
        wxMediaObj?.let { mediaObj->
            val wxMediaMessage = WXMediaMessage()
            wxMediaMessage.title = theShareDat.shareTitle
            wxMediaMessage.description = theShareDat.shareDesc
            wxMediaMessage.mediaObject = mediaObj
            wxMediaMessage.thumbData = theShareDat.shareThumbImgData//缩略图

            val toWxReq = SendMessageToWX.Req()
            toWxReq.let {
                it.scene = theShareDat.shareToScene
                it.message = wxMediaMessage
                it.transaction = resultCallback?.eventTransactionId
                return WxSdk.sendReq(it)
            }
        }
        return false
    }


}