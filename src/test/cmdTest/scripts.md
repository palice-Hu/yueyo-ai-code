

流式接口调用测试
```
# 1. 用户登录
curl -X POST "http://localhost:8123/api/user/login" \
-H "Content-Type: application/json" \
-d '{
"userAccount": "yueyor",
"userPassword": "12345678"
}' \
-c cookies.txt

# 2. 调用生成代码接口（流式）
curl -G "http://localhost:8123/api/app/chat/gen/code" \
--data-urlencode "appId=350401910533517312" \
--data-urlencode "message=我需要一个简单的任务记录工具网站" \
-H "Accept: text/event-stream" \
-H "Cache-Control: no-cache" \
-b cookies.txt \
--no-buffer
```

