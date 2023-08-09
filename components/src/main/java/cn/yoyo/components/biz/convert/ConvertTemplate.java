package cn.yoyo.components.biz.convert;

import java.util.List;

public interface ConvertTemplate<E, T> {
    T e2t(E t);

    E t2e(T t);

    List<T> e2t(List<E> cars);

    List<E> t2e(List<T> cars);

}
