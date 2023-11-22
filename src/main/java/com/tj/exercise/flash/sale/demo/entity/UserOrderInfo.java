package com.tj.exercise.flash.sale.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tj
 * @since 2023-11-22
 */
@TableName("user_order_info")
public class UserOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @TableField("user_id")
    private Long userId;

    @TableField("scenic_id")
    private Long scenicId;

    @TableField("scenic_name")
    private String scenicName;

    @TableField("nick_name")
    private String nickName;

    @TableField("order_name")
    private String orderName;

    @TableField("order_status")
    private Integer orderStatus;

    @TableField("appointment_time")
    private LocalDateTime appointmentTime;

    @TableField("ticket_num")
    private String ticketNum;

    @TableField("refused_reson")
    private String refusedReson;

    @TableField("ticket_content")
    private String ticketContent;

    @TableField("order_time")
    private LocalDateTime orderTime;

    @TableField("bk1")
    private String bk1;

    @TableField("bk2")
    private String bk2;

    @TableField("bk3")
    private String bk3;

    @TableField("bk4")
    private String bk4;

    @TableField("bk5")
    private String bk5;

    @TableField("is_delete")
    private Integer isDelete;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("modify_time")
    private LocalDateTime modifyTime;

    @TableField("card_id")
    private Long cardId;

    @TableField("business_id")
    private String businessId;

    @TableField("ticket_price")
    private String ticketPrice;

    @TableField("`source`")
    private String source;

    @TableField("use_status")
    private String useStatus;

    @TableField("is_notice")
    private String isNotice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }
    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }
    public String getRefusedReson() {
        return refusedReson;
    }

    public void setRefusedReson(String refusedReson) {
        this.refusedReson = refusedReson;
    }
    public String getTicketContent() {
        return ticketContent;
    }

    public void setTicketContent(String ticketContent) {
        this.ticketContent = ticketContent;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public String getBk1() {
        return bk1;
    }

    public void setBk1(String bk1) {
        this.bk1 = bk1;
    }
    public String getBk2() {
        return bk2;
    }

    public void setBk2(String bk2) {
        this.bk2 = bk2;
    }
    public String getBk3() {
        return bk3;
    }

    public void setBk3(String bk3) {
        this.bk3 = bk3;
    }
    public String getBk4() {
        return bk4;
    }

    public void setBk4(String bk4) {
        this.bk4 = bk4;
    }
    public String getBk5() {
        return bk5;
    }

    public void setBk5(String bk5) {
        this.bk5 = bk5;
    }
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }
    public String getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(String isNotice) {
        this.isNotice = isNotice;
    }

    @Override
    public String toString() {
        return "UserOrderInfo{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", scenicId=" + scenicId +
                ", scenicName=" + scenicName +
                ", nickName=" + nickName +
                ", orderName=" + orderName +
                ", orderStatus=" + orderStatus +
                ", appointmentTime=" + appointmentTime +
                ", ticketNum=" + ticketNum +
                ", refusedReson=" + refusedReson +
                ", ticketContent=" + ticketContent +
                ", orderTime=" + orderTime +
                ", bk1=" + bk1 +
                ", bk2=" + bk2 +
                ", bk3=" + bk3 +
                ", bk4=" + bk4 +
                ", bk5=" + bk5 +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", cardId=" + cardId +
                ", businessId=" + businessId +
                ", ticketPrice=" + ticketPrice +
                ", source=" + source +
                ", useStatus=" + useStatus +
                ", isNotice=" + isNotice +
                "}";
    }
}
