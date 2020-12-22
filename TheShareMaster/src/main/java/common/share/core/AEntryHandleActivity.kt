package common.share.core

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import common.share.L
import common.share.wx.WxSdk
import java.lang.ref.WeakReference

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 分享、微信的登录等的过渡 Activity
 * </p>
 * ******************(^_^)***********************
 */
open class AEntryHandleActivity : AppCompatActivity(),IWXAPIEventHandler {

    protected val TAG = javaClass.simpleName
    private var resultHandlerRef: WeakReference<IEventResultCallback?>? = null

    companion object Starter{
        const val INTENT_KEY_RESULT_HANDLER = "intent_key_result_handler"

        fun startEntry(wxEntryActivityClass: Class<in AEntryHandleActivity>? = null) {


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.i(TAG,"--> onCreate()")
        intent?.let {
            val intentKeyEventHandler = INTENT_KEY_RESULT_HANDLER
            if(it.hasExtra(intentKeyEventHandler)){
                val handler = it.getSerializableExtra(intentKeyEventHandler) as IEventResultCallback
                resultHandlerRef = WeakReference(handler)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        L.i(TAG,"--> onStart()")
    }

    override fun onResume() {
        super.onResume()
        L.i(TAG,"--> onResume()")
    }
    override fun onRestart() {
        super.onRestart()
        L.i(TAG,"--> onRestart()")
    }

    override fun onPause() {
        super.onPause()
        L.i(TAG,"--> onPause()")
    }

    override fun onStop() {
        super.onStop()
        L.i(TAG,"--> onStop()")
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        L.i(TAG,"--> onNewIntent()")
        //需要判断 是否当前为 微信相关的功能？？
        WxSdk.peekWxApi()?.handleIntent(intent, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        L.i(TAG,"--> onDestroy() ")
    }

    override fun onReq(req: BaseReq?) {
        L.i(TAG, "--> onReq() req = $req")
    }

    override fun onResp(resp: BaseResp?) {
        L.i(TAG, "--> onResp()  resp = $resp")

    }

    private fun peakResultHandler(): IEventResultCallback? {
        return resultHandlerRef?.get()
    }
}