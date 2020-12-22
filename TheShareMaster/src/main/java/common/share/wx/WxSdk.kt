package common.share.wx

import android.content.Context
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 微信SDK封装
 * </p>
 * ******************(^_^)***********************
 */
object WxSdk {
    private var iWxApi: IWXAPI? = null

    private var wxSdkConfig: WxSdkConfig? = null

    private var appContext: Context? = null


    fun initSdk(context: Context,wxSdkConfig: WxSdkConfig): Boolean {
        this.appContext = context.applicationContext
        var needReInit = false
        if(this.wxSdkConfig == null|| !wxSdkConfig.appId.equals(this.wxSdkConfig!!.appId)){
            needReInit = true
        }
        this.wxSdkConfig = wxSdkConfig
        if (iWxApi == null || needReInit) {
            iWxApi = WXAPIFactory.createWXAPI(
                appContext,
                wxSdkConfig.appId,
                wxSdkConfig.needCheckSignature
            )
        }
        /**
         * //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
        }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
         */
        return iWxApi?.registerApp(wxSdkConfig.appId) ?: false
    }

    fun peekWxApi(): IWXAPI? {
        if (iWxApi == null) {
            if (appContext != null) {
                this.wxSdkConfig?.let {
                    iWxApi = WXAPIFactory.createWXAPI(appContext, it.appId, it.needCheckSignature)
                }
            }
        }
        return iWxApi
    }


    /**
     * APP向微信发出请求数据
     * 微信处理完后场景可返回APP
     */
    fun sendReq(wxReq: BaseReq): Boolean {
        return peekWxApi()?.sendReq(wxReq) ?: false
    }

    /**
     * APP 向微信 响应数据：
     * APP处理完后场景会返回微信
     */
    fun sendResp(wxResp: BaseResp): Boolean {
        return peekWxApi()?.sendResp(wxResp) ?: false
    }

    fun isWxAppInstalled(): Boolean {
        return peekWxApi()?.isWXAppInstalled ?: false
    }

    /**
     * 启动微信APP
     */
    fun openWxApp() = peekWxApi()?.openWXApp() ?: false

    /**
     * 获取当前 微信APP的支持 API版本号
     * 需要注意的是，SendMessageToWX.Req 的 scene 成员，如果 scene 填 WXSceneSession，
     * 那么消息会发送至微信的会话内。如果 scene 填 WXSceneTimeline（微信 4.2 以上支持，com.tencent.mm.opensdk.constants.Build.java 里面定义了各个功能支持的版本号，如果需要检查微信版本支持 API 的情况， 可调用 IWXAPI 的 getWXAppSupportAPI 方法,比如，要判断微信是否支持分享到朋友圈功能，可以如下所示进行判断：

        if (api.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT) {
                //do share
        }
     */
    fun wxSupportApi() = peekWxApi()?.wxAppSupportAPI ?: 0

}