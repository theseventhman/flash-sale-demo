package com.tj.exercise.flash.sale.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tj
 * @since 2023-11-22
 */
@TableName("ticket_info")
public class TicketInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ticket_id", type = IdType.AUTO)
    private Long ticketId;

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    @TableField("ticket_remark")
    private String ticketRemark;

    @TableField("ticket_num")
    private Integer ticketNum;

    @TableField(value = "scene_id")
    private Long sceneId;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    public String getTicketRemark() {
        return ticketRemark;
    }

    public void setTicketRemark(String ticketRemark) {
        this.ticketRemark = ticketRemark;
    }
    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    @Override
    public String toString() {
        return "TicketInfo{" +
                "ticketId=" + ticketId +
                ", ticketRemark=" + ticketRemark +
                ", ticketNum=" + ticketNum +
                "}";
    }
}
