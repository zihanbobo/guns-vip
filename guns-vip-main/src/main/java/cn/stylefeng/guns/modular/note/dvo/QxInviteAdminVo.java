package cn.stylefeng.guns.modular.note.dvo;

import cn.stylefeng.guns.modular.note.entity.QxInvite;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QxInviteAdminVo extends QxInvite{
	private static final long serialVersionUID = 1L;
	private String inviterName;
	private String inviterMobile;
	private String inviteeName;
	private String inviteeMobile;
	private String inviteTypeName;
	private String giftName;
}
