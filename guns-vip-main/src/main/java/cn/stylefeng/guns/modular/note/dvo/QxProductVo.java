package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class QxProductVo {
	// 商品信息
	private Long id;
	private Date createdTime;
    private String productName;
    private String headImages;
    private String detailImages;
    private Integer price;
    private Integer stock;
    // 最近兑换用户
    private List<QxUserVo> userVos;
}
