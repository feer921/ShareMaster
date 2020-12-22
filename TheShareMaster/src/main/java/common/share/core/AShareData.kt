package common.share.core

import android.content.Context
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 要分享的数据实体
 * 目前实现的分享数据类型：网页、图片、文本、音乐、视频
 * </p>
 * ******************(^_^)***********************
 */
open class AShareData<I : AShareData<I>>{
    var mContext: Context? = null
    /**
     * 数据体所持有、指定的分享构建策略
     */
    var theShareBuilderStrategy: IShareBuilderStrategy<I>? = null

    /**
     * 分享的数据类型：
     * 因为微信SDK定义的比较全，则直接使用微信SDK定义的范围
     */
    var shareDataType = DATA_TYPE_UNKNOW

    /**
     * 所分享的消息的标题:
     * eg.: 号外号外
     */
    var shareTitle: String = ""

    /**
     * 所分享的消息的 描述
     * eg.: X先生想要戒烟怕患上肺炎，开始沿着马路每天跑步，结果成功患上了肺炎
     */
    var shareDesc: String = ""

    /**
     * 要分享的 网页 URL 地址
     */
    var shareUrl: String = ""

    /**
     * 分享的消息 所带的缩略图
     * 适用与：小程序、网页、音乐、视频等类型的数据
     */
    var shareThumbImgData: ByteArray? = null

    /**
     * 分享到的 场景
     * eg.: 微信分享时：可分享到 微信聊天窗口、微信朋友圈、微信的收藏
     */
    var shareToScene = -1

    /**
     * 本次分享的事务ID
     */
    protected var shareTransactionId: String = ""

    companion object DataConfigs {
        /**
         * 分享数据类型：未知
         */
        const val DATA_TYPE_UNKNOW = WXMediaMessage.IMediaObject.TYPE_UNKNOWN

        /**
         * 分享数据类型：文本
         */
        const val DATA_TYPE_TEXT = WXMediaMessage.IMediaObject.TYPE_TEXT

        /**
         * 分享数据类型：图片
         */
        const val DATA_TYPE_IMAGE = WXMediaMessage.IMediaObject.TYPE_IMAGE

        /**
         * 分享数据类型：网页/URL
         */
        const val DATA_TYPE_URL = WXMediaMessage.IMediaObject.TYPE_URL

        /**
         * 分享数据类型：音乐
         */
        const val DATA_TYPE_MUSIC = WXMediaMessage.IMediaObject.TYPE_MUSIC

        /**
         * 分享数据类型：视频
         */
        const val DATA_TYPE_VIDEO = WXMediaMessage.IMediaObject.TYPE_VIDEO

        /**
         * 分享数据类型：文件
         */
        const val DATA_TYPE_FILE = WXMediaMessage.IMediaObject.TYPE_FILE


        /**
         * 分享到: 微信聊天窗口
         */
        const val SCENE_TO_WX_SESSION = 0

        /**
         * 分享到：微信朋友圈
         */
        const val SCENE_TO_WX_TIMELINE = 1

        /**
         * 分享到：微信收藏
         */
        const val SCENE_TO_WX_FAVORITE = 2


    }


    /**
     * 分享
     * 注：可多次调用
     * @param shareBuilderStrategy 调用处指定的分享构建策略
     * @param eventResultCallback 事件结果回调
     */
    fun share(
        shareBuilderStrategy: IShareBuilderStrategy<I>?,
        eventResultCallback: IEventResultCallback? = null,
    ): Boolean {
        if (shareBuilderStrategy != null) {
            return shareBuilderStrategy.buildAndShare(self(), eventResultCallback)
        }
        return theShareBuilderStrategy?.buildAndShare(self(), eventResultCallback) ?: false
    }

    private fun self(): I{
        return this as I
    }



}