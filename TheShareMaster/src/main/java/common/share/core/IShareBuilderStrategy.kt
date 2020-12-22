package common.share.core

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * 策略模式
 * 根据当前的 分享数据实体，来构建分享启动流程
 * </p>
 * ******************(^_^)***********************
 */
interface IShareBuilderStrategy<D : AShareData<D>> {
    fun buildAndShare(theShareDat: D, resultCallback: IEventResultCallback?): Boolean
}