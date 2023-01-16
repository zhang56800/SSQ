package com.dematic.ssq.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang,haoxiang
 * @since 2022-11-11
 */
@TableName("SSQ_NUMBER")
public class SsqNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String number;

    private Integer count;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SsqNumber{" +
            "number=" + number +
            ", count=" + count +
        "}";
    }
}
