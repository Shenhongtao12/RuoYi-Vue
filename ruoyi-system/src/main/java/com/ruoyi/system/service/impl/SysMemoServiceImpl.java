package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMemoMapper;
import com.ruoyi.system.domain.SysMemo;
import com.ruoyi.system.service.ISysMemoService;

import javax.annotation.PostConstruct;

/**
 * 备忘录Service业务层处理
 *
 * @author aaron
 * @date 2022-07-23
 */
@Service
public class SysMemoServiceImpl implements ISysMemoService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysMemoMapper sysMemoMapper;

    @Autowired
    private RedisCache redisCache;

    Lock lock = new ReentrantLock();

    @PostConstruct
    public void init() {
        Integer inventory = sysMemoMapper.getInventoryById(10);
        redisCache.setCacheObject("inventory-" + 10, inventory);
        inventory = sysMemoMapper.getInventoryById(11);
        redisCache.setCacheObject("inventory-" + 11, inventory);
    }

    /**
     * 查询备忘录
     *
     * @param noticeId 备忘录主键
     * @return 备忘录
     */
    @Override
    public SysMemo selectSysMemoByNoticeId(Long noticeId) {
        return sysMemoMapper.selectSysMemoByNoticeId(noticeId);
    }

    /**
     * 查询备忘录列表
     *
     * @param sysMemo 备忘录
     * @return 备忘录
     */
    @Override
    public List<SysMemo> selectSysMemoList(SysMemo sysMemo) {
        return sysMemoMapper.selectSysMemoList(sysMemo);
    }

    /**
     * 新增备忘录
     *
     * @param sysMemo 备忘录
     * @return 结果
     */
    @Override
    public int insertSysMemo(SysMemo sysMemo) {
        sysMemo.setCreateTime(DateUtils.getNowDate());
        return sysMemoMapper.insertSysMemo(sysMemo);
    }

    /**
     * 修改备忘录
     *
     * @param sysMemo 备忘录
     * @return 结果
     */
    @Override
    public int updateSysMemo(SysMemo sysMemo) {
        sysMemo.setUpdateTime(DateUtils.getNowDate());
        return sysMemoMapper.updateSysMemo(sysMemo);
    }

    /**
     * 批量删除备忘录
     *
     * @param noticeIds 需要删除的备忘录主键
     * @return 结果
     */
    @Override
    public int deleteSysMemoByNoticeIds(Long[] noticeIds) {
        //sysMemoMapper.deleteSysMemoByNoticeIds(noticeIds);
        return sysMemoMapper.deleteBatchIds(Arrays.asList(noticeIds));
    }

    /**
     * 删除备忘录信息
     *
     * @param noticeId 备忘录主键
     * @return 结果
     */
    @Override
    public int deleteSysMemoByNoticeId(Long noticeId) {
        //sysMemoMapper.deleteSysMemoByNoticeId(noticeId)
        return sysMemoMapper.deleteById(noticeId);
    }

    @Override
    public AjaxResult updateInventoryById(Integer id, Integer num) {
        Integer cacheObject = redisCache.getCacheObject("inventory-" + id);

        if (cacheObject == null) {
            Integer inventory = sysMemoMapper.getInventoryById(id);
            redisCache.setCacheObject("inventory-" + id, inventory);

            if (inventory <= 0) {
                return new AjaxResult(400, "库存不足");
            }
        }
        Integer in = cacheObject;
        logger.info(id + " ======= " + in);
        if (in <= 0) {
            return new AjaxResult(400, "库存不足111");
        }
        redisCache.setCacheObject("inventory-" + id, in - num);

        if (!deductInventoryBySync(id, num)) {
            return new AjaxResult(400, "库存不足222");
        }

        /*
        this.lock.lock();
        try {
            if (!deductInventoryByLock(id, num)) {
                return new AjaxResult(400, "库存不足222");
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }*/

        return new AjaxResult(200, "成功");
    }

    private synchronized boolean deductInventoryBySync(Integer id, Integer num) {
        Integer inventory = sysMemoMapper.getInventoryById(id);
        if (inventory <= 0) {
            return false;
        }
        logger.info(id + "------ ");
        return sysMemoMapper.updateInventorybyId(id, num) > 0;
    }

    private boolean deductInventoryByLock(Integer id, Integer num) {
        Integer inventory = sysMemoMapper.getInventoryById(id);
        if (inventory <= 0) {
            return false;
        }
        logger.info(id + "------ ");
        return sysMemoMapper.updateInventorybyId(id, num) > 0;
    }
}
