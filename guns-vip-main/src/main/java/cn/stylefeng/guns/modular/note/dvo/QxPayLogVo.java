package cn.stylefeng.guns.modular.note.dvo;

import java.math.BigDecimal;

import cn.stylefeng.guns.core.constant.ProjectConstants;
import lombok.Data;

@Data
public class QxPayLogVo {
	private Long id;
	private BigDecimal amount;
	private String type;
	
	public void setType(String type) {
		String name = ProjectConstants.FINANCE_LOG_MAP.get(type);
		this.type = name;
	}
}
