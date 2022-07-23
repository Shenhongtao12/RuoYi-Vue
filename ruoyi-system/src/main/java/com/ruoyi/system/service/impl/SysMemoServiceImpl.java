package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMemoMapper;
import com.ruoyi.system.domain.SysMemo;
import com.ruoyi.system.service.ISysMemoService;

/**
 * 备忘录Service业务层处理
 * 
 * @author aaron
 * @date 2022-07-23
 */
@Service
public class SysMemoServiceImpl implements ISysMemoService 
{
    @Autowired
    private SysMemoMapper sysMemoMapper;

    /**
     * 查询备忘录
     * 
     * @param noticeId 备忘录主键
     * @return 备忘录
     */
    @Override
    public SysMemo selectSysMemoByNoticeId(Long noticeId)
    {
        return sysMemoMapper.selectSysMemoByNoticeId(noticeId);
    }

    /**
     * 查询备忘录列表
     * 
     * @param sysMemo 备忘录
     * @return 备忘录
     */
    @Override
    public List<SysMemo> selectSysMemoList(SysMemo sysMemo)
    {
        return sysMemoMapper.selectSysMemoList(sysMemo);
    }

    /**
     * 新增备忘录
     * 
     * @param sysMemo 备忘录
     * @return 结果
     */
    @Override
    public int insertSysMemo(SysMemo sysMemo)
    {
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
    public int updateSysMemo(SysMemo sysMemo)
    {
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
    public int deleteSysMemoByNoticeIds(Long[] noticeIds)
    {
        return sysMemoMapper.deleteSysMemoByNoticeIds(noticeIds);
    }

    /**
     * 删除备忘录信息
     * 
     * @param noticeId 备忘录主键
     * @return 结果
     */
    @Override
    public int deleteSysMemoByNoticeId(Long noticeId)
    {
        return sysMemoMapper.deleteSysMemoByNoticeId(noticeId);
    }
}
