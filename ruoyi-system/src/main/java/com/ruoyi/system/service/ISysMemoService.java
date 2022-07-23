package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysMemo;

/**
 * 备忘录Service接口
 * 
 * @author aaron
 * @date 2022-07-23
 */
public interface ISysMemoService 
{
    /**
     * 查询备忘录
     * 
     * @param noticeId 备忘录主键
     * @return 备忘录
     */
    public SysMemo selectSysMemoByNoticeId(Long noticeId);

    /**
     * 查询备忘录列表
     * 
     * @param sysMemo 备忘录
     * @return 备忘录集合
     */
    public List<SysMemo> selectSysMemoList(SysMemo sysMemo);

    /**
     * 新增备忘录
     * 
     * @param sysMemo 备忘录
     * @return 结果
     */
    public int insertSysMemo(SysMemo sysMemo);

    /**
     * 修改备忘录
     * 
     * @param sysMemo 备忘录
     * @return 结果
     */
    public int updateSysMemo(SysMemo sysMemo);

    /**
     * 批量删除备忘录
     * 
     * @param noticeIds 需要删除的备忘录主键集合
     * @return 结果
     */
    public int deleteSysMemoByNoticeIds(Long[] noticeIds);

    /**
     * 删除备忘录信息
     * 
     * @param noticeId 备忘录主键
     * @return 结果
     */
    public int deleteSysMemoByNoticeId(Long noticeId);
}
