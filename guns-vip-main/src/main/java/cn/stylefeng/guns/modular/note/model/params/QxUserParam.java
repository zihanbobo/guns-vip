package cn.stylefeng.guns.modular.note.model.params;

import java.io.Serializable;
import java.util.Date;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxUserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 标识
     */
    private Long id;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 性别 0-男;1-女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 0-正常；1-禁用
     */
    private String status;

    /**
     * 信用分
     */
    private Integer score;

    /**
     * 金币余额
     */
    private Integer balance;
    
    /**
     * 冻结金币
     */
    private Integer freeze;
    
    /**
     * 我的邀请码
     */
    private String inviteCode;
    
    /**
     * 邀请人ID
     */
    private Long parentId;
    
    @Override
    public String checkParam() {
        return null;
    }

}
