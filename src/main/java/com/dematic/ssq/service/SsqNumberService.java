package com.dematic.ssq.service;

import com.dematic.ssq.entity.SsqNumber;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang,haoxiang
 * @since 2022-11-11
 */
public interface SsqNumberService extends IService<SsqNumber> {
   public void updateNewSSQ();
}
