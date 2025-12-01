package com.yueyo.yueyoraicodemother.core.parser;

/**
 * 代码解析器
 * 策略模式
 * @param <T>
 */
public interface CodeParser<T> {


    /**
     * 解析代码
     * @param codeContent
     * @return
     */
    T parseCode(String codeContent);
}
