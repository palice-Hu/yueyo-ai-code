package com.yueyo.yueyoraicodemother.core;

import com.yueyo.yueyoraicodemother.ai.AiCodeGeneratorService;
import com.yueyo.yueyoraicodemother.ai.model.HtmlCodeResult;
import com.yueyo.yueyoraicodemother.ai.model.MultiFileCodeResult;
import com.yueyo.yueyoraicodemother.exception.BusinessException;
import com.yueyo.yueyoraicodemother.exception.ErrorCode;
import com.yueyo.yueyoraicodemother.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;

/**
 * @author yueyo
 * @date 2025/11/20
 * 创建代码生成器外观类
 **/
@Slf4j
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



    /**
     * 创建代码生成器外观类（流式）
     * @param userMessage 用户输入
     * @param codeGenType 生成类型
     * @return 生成的代码文件
     */
    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenType) {
        if(codeGenType == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"生成类型为空");
        }

        return switch (codeGenType) {
            case HTML -> generateAndSaveHtmlCodeStream(userMessage);
            case MULTI_FILE -> generateAndSaveMutiFileCodeStream(userMessage);
            default -> {
                String errMsg = "不支持的生成类型" + codeGenType.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,errMsg);
            }
        };
    }







    /**
     * 创建HTML代码生成器（流式）
     * @param userMessage 用户输入
     * @return 生成的代码文件
     */
    private Flux<String> generateAndSaveHtmlCodeStream(String userMessage) {
        Flux<String> result = aiCodeGeneratorService.generateHtmlCodeStream(userMessage);
        StringBuilder stringBuilder = new StringBuilder();
        // 创建HTML代码生成器
        return result
                .doOnNext(chunk->{
                    // 将结果保存到stringBuilder中
                    stringBuilder.append(chunk);
                })
                .doOnComplete(()-> {
                    try {
                        // 将结果保存到文件
                        String completeHtmlCode = stringBuilder.toString();
                        HtmlCodeResult htmlCodeResult = CodeParser.parseHtmlCode(completeHtmlCode);
                        // 保存多文件代码结果
                        File saveDir = CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
                        log.info("保存HTML代码结果成功，保存目录为：{}",saveDir.getAbsolutePath());
                    } catch (Exception e) {
                        log.error("保存HTML代码结果失败",e);
                    }
                });
    }


    /**
     * 创建多文件代码生成器（流式）
     * @param userMessage 用户输入
     * @return 生成的代码文件
     */
    private Flux<String> generateAndSaveMutiFileCodeStream(String userMessage) {
        Flux<String> result = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
        StringBuilder stringBuilder = new StringBuilder();
        // 创建多文件代码生成器
        return result
                .doOnNext(chunk->{
                    // 将结果保存到stringBuilder中
                    stringBuilder.append(chunk);
                })
                .doOnComplete(()-> {
                    try {
                        // 将结果保存到文件
                        String completeMultiFileCode = stringBuilder.toString();
                        MultiFileCodeResult multiFileCodeResult = CodeParser.parseMultiFileCode(completeMultiFileCode);
                        // 保存多文件代码结果
                        File saveDir = CodeFileSaver.saveMultiFileCodeResult(multiFileCodeResult);
                        log.info("保存多文件代码结果成功，保存目录为：{}",saveDir.getAbsolutePath());
                    } catch (Exception e) {
                        log.error("保存多文件代码结果失败",e);
                    }
                });
    }
}
