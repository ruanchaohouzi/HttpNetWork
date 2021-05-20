package com.shuwen.network.base;

/**
 * 服务端响应处理结果回调接口，主要是为了使activity和fragment尽量不处理业务，只是拿到数据展示数据对应的视图
 * 后面如果还有一些通用的处理过程需求，可以在该接口中新增对应的过程方法
 * @version 1.0
 */
public interface BaseResponseHandleCallback<T> {
    /**
     * 展示正在加载视图
     */
    void showLoading();

    /**
     * 业务处理成功后回调展示
     * @param t 视图需要的数据
     */
    void loadSuccess(T t);

    /**
     * 异常捕获回调
     * @param e 异常
     */
    void loadException(Throwable e);

    /**
     * 业务处理失败回调展示
     * @param msg 服务端返回的失败信息
     */
    void loadFail(String msg);

    /**
     * 如果需要强制登陆的，可以通过该接口处理登陆逻辑
     */
    void login();
}
