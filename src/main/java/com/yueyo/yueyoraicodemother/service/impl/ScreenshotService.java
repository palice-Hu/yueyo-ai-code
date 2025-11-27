package com.yueyo.yueyoraicodemother.service.impl;

public interface ScreenshotService {
    String generateAndUploadScreenshot(String webUrl);

    String uploadScreenshotToCos(String localScreenshotPath);

    String generateScreenshotKey(String fileName);

    void cleanupLocalFile(String localFilePath);
}
