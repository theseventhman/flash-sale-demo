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
 * @since 2023-11-13
 */
@TableName("scene_info")
public class SceneInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scene_id", type = IdType.AUTO)
    private Long sceneId;

    @TableField("scene_name")
    private String sceneName;

    @TableField("flash_sale_ticket_count")
    private Integer flashSaleTicketCount;

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }
    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
    public Integer getFlashSaleTicketCount() {
        return flashSaleTicketCount;
    }

    public void setFlashSaleTicketCount(Integer flashSaleTicketCount) {
        this.flashSaleTicketCount = flashSaleTicketCount;
    }

    @Override
    public String toString() {
        return "SceneInfo{" +
                "sceneId=" + sceneId +
                ", sceneName=" + sceneName +
                ", flashSaleTicketCount=" + flashSaleTicketCount +
                "}";
    }
}
