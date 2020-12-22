# ShareMaster
集微信、支付宝、抖音等分享于一身,便捷使用分享功能

# 使用

## . 初始化

#### 微信相关

```kotlin
ShareMaster.initWxSdk(this){
            appId = "wxb4bbf0651d312ab6" //传入项目在微信开放平台上申请开通微信分享的 APPId
        }
```



#### 调用微信分享

```kotlin
ShareMaster.wxShare(resultHandler){
                    wxEntryActivityClass = WXEntryActivity::class.java
                    shareToScene = AShareData.DataConfigs.SCENE_TO_WX_FAVORITE
                    shareDataType = AShareData.DataConfigs.DATA_TYPE_TEXT
                    shareText = "我想把本段文本给分享出去"
                }
```

