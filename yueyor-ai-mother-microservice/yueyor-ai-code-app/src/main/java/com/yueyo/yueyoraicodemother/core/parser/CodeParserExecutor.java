package com.yueyo.yueyoraicodemother.core.parser;

import com.yueyo.yueyoraicodemother.exception.BusinessException;
import com.yueyo.yueyoraicodemother.exception.ErrorCode;
import com.yueyo.yueyoraicodemother.model.enums.CodeGenTypeEnum;

public class CodeParserExecutor {

    // 初始化html代码解析类
    private static final HtmlCodeParser htmlParser = new HtmlCodeParser();

    // 初始化multiFileParser
    private static final MultiFileCodeParser multiFileParser = new MultiFileCodeParser();


    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {
        return switch (codeGenType) {
            case HTML -> htmlParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileParser.parseCode(codeContent);
            default -> {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持的代码生成类型: "+codeGenType);
            }
        };
    }
}
