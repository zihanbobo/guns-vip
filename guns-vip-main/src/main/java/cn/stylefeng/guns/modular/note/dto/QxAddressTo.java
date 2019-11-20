package cn.stylefeng.guns.modular.note.dto;

import lombok.Data;

@Data
public class QxAddressTo {
	private Long id;
	private String name;
    private String contact;
    private String area;
    private String address;
    private Boolean isDefault;
}
