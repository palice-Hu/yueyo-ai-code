package com.yueyo.yueyoraicodemother.core;

import com.yueyo.yueyoraicodemother.ai.AiCodeGeneratorService;
import com.yueyo.yueyoraicodemother.ai.model.HtmlCodeResult;
import com.yueyo.yueyoraicodemother.ai.model.MultiFileCodeResult;
import com.yueyo.yueyoraicodemother.exception.BusinessException;
import com.yueyo.yueyoraicodemother.exception.ErrorCode;
import com.yueyo.yueyoraicodemother.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author yueyo
 * @date 2025/11/20
 * 创建代码生成器外观类
 **/
@Service
public class AiCodeGeneratorFacade {


    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;


    /**
     * 创建代码生成器外观类
     * @param userMessage 用户输入
     * @param codeGenType 生成类型
     * @return 生成的代码文件
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenType) {
        if(codeGenType == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"生成类型为空");
        }

        return switch (codeGenType) {
            case HTML -> generateAndSaveHtmlCode(userMessage);
            case MULTI_FILE -> generateAndSaveMutiFileCode(userMessage);
            default -> {
                String errMsg = "不支持的生成类型" + codeGenType.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,errMsg);
            }
        };
    }

    /**
     * 创建多文件代码生成器
     * @param userMessage 用户输入
     * @return 生成的代码文件
     */
    private File generateAndSaveMutiFileCode(String userMessage) {
        MultiFileCodeResult multiFileCodeResult = aiCodeGeneratorService.generateMultiFileCode(userMessage);
        return CodeFileSaver.saveMultiFileCodeResult(multiFileCodeResult);


    }


    /**
     * 创建HTML代码生成器
     * @param userMessage 用户输入
     * @return 生成的代码文件
     */
    private File generateAndSaveHtmlCode(String userMessage) {
        HtmlCodeResult htmlCodeResult = aiCodeGeneratorService.generateHtmlCode(userMessage);
        return CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
    }
}
