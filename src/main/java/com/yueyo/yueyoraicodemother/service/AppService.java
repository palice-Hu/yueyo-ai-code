package com.yueyo.yueyoraicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.yueyo.yueyoraicodemother.model.dto.AppQueryRequest;
import com.yueyo.yueyoraicodemother.model.entity.App;
import com.yueyo.yueyoraicodemother.model.vo.AppVO;

import java.util.List;

/**
 * app 服务层。
 *
 * @author <a href="https://github.com/paliceHu">yueyor</a>
 */
public interface AppService extends IService<App> {


    /**
     * 获取查询包装类
     *
     * @param appQueryRequest 应用查询请求
     * @return 查询包装类
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取应用 VO 列表
     *
     * @param appList 应用列表
     * @return 应用 VO 列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 获取应用 VO
     *
     * @param app 应用
     * @return 应用 VO
     */
    AppVO getAppVO(App app);
}

