package com.fee.sharemaster

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.fee.sharemaster.databinding.ActivityMainBinding
import com.fee.sharemaster.wxapi.WXEntryActivity
import common.share.ShareMaster
import common.share.core.AShareData
import common.share.core.AbsEventResultHandler
import common.share.core.IEventResultCallback
import common.share.wx.AWxShareData

class MainActivity : AppCompatActivity(),View.OnClickListener{
    private val TAG = "MainActivity"
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, " --> onCreate() ")

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.btnWxShare.setOnClickListener(this)
        activityMainBinding.btnSysShare.setOnClickListener(this)
        activityMainBinding.btnAliShare.setOnClickListener(this)
        ShareMaster.initWxSdk(this){
            appId = "wxb4bbf0651d312ab6"
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnWxShare -> {//微信分享
                val resultHandler = object :AbsEventResultHandler(){
                    override fun onEventResp(
                        isEventOk: Boolean,
                        respCode: Int,
                        respSubCode: Int,
                        respMsg: String,
                        theRespAssignId: String
                    ) {

                    }
                }
                resultHandler.eventTransactionId = "分享测试"
                ShareMaster.wxShare(resultHandler){
                    wxEntryActivityClass = WXEntryActivity::class.java
                    shareToScene = AShareData.DataConfigs.SCENE_TO_WX_FAVORITE
                    shareDataType = AShareData.DataConfigs.DATA_TYPE_TEXT
                    shareText = "我想把本段文本给分享出去"
                }
            }
            R.id.btnSysShare ->{//系统原生 的 分享
                ShareMaster.systemShare {
                    shareTitle = "我要分享文本啊"
                    shareText = "我想把本段文本给分享出去"
                    shareDataType = AShareData.DATA_TYPE_TEXT
                    mContext = this@MainActivity
                }
            }
            R.id.btnAliShare ->{//支付宝的分享

            }
            else -> {
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(TAG, " --> onConfigurationChanged()  newConfig = $newConfig")
    }
    override fun onStop() {
        super.onStop()
        Log.i(TAG, " --> onStop() ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, " --> onDestroy() ")
    }
}