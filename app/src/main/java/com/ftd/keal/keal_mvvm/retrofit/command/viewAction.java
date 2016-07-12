package com.ftd.keal.keal_mvvm.retrofit.command;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by sdhuang on 16/7/4 16:47.
 */
public class ViewAction<T> {
    /* Action0 是 RxJava 的一个接口，它只有一个方法 call()，这个方法是无参无返回值的；
       由于 onCompleted() 方法也是无参无返回值的，因此 Action0 可以被当成一个包装对象，
       将 onCompleted() 的内容打包起来将自己作为一个参数传入 subscribe() 以实现不完整
       定义的回调。这样其实也可以看做将 onCompleted() 方法作为参数传进了 subscribe()，
       相当于其他某些语言中的『闭包』。 Action1 也是一个接口，它同样只有一个方法 call(T param)，
       这个方法也无返回值，但有一个参数；与 Action0 同理，由于 onNext(T obj) 和 onError(Throwable error)
       也是单参数无返回值的，因此 Action1 可以将 onNext(obj) 和 onError(error) 打包起来传入 subscribe() 以实现不完整定义的回调。
       相当于自定义的subscriber订阅者
     */
    private Action0 action0;
    private Action1<T> action1;

    public ViewAction(Action0 action) {
        this.action0 = action;
    }

    public ViewAction(Action1<T> action) {
        this.action1 = action;
    }

    public void call(){
        if(action0 !=null) action0.call();
    }

    public void call(T t){
        if(action1 !=null) action1.call(t);
    }
}
