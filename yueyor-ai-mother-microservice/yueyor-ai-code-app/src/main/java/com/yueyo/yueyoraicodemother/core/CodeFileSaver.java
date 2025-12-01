package com.yueyo.yueyoraicodemother.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.yueyo.yueyoraicodemother.ai.model.HtmlCodeResult;
import com.yueyo.yueyoraicodemother.ai.model.MultiFileCodeResult;
import com.yueyo.yueyoraicodemother.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Deprecated
public class CodeFileSaver {

    // 保存代码文件的根目录
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 保存 HTML 代码结果
     *
     * @param result HTML 代码结果
     * @return 保存后的目录
     */
    public static File saveHtmlCodeResult(HtmlCodeResult result) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeToFile(baseDirPath,"index.html", result.getHtmlCode());
        return new File(baseDirPath);
    }

    /**
     * 保存多文件代码结果
     *
     * @param result 多文件代码结果
     * @return 保存后的目录
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult result) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(baseDirPath,"index.html",result.getHtmlCode());
        writeToFile(baseDirPath,"style.css",result.getCssCode());
        writeToFile(baseDirPath,"script.js",result.getJsCode());
        return new File(baseDirPath);
    }


    /**
     * 构建一个唯一的目录名称
     *
     * @param bizType 业务类型
     * @return 唯一的目录名称
     */
    private static String buildUniqueDir(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.getSnowflakeNextIdStr());
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }


    /**
     * 将内容写入文件
     *
     * @param baseDirPath 基础目录路径
     * @param fileName 文件名
     * @param content 文件内容
     */
    private static void writeToFile(String baseDirPath, String fileName, String content) {
        String filePath = baseDirPath + File.separator + fileName;
        FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
    }
}
