package cn.stylefeng.guns.modular.note.dto;

import lombok.Data;

@Data
public class QxPayResult {
	String sn;
	Long payerId;
	Long payeeId;
	Integer price; // 金币
}
