package cn.stylefeng.guns.core.exception;

public class ServiceException extends cn.stylefeng.roses.kernel.model.exception.ServiceException {
	
	private static final long serialVersionUID = 1L;

	public ServiceException(String errorMessage) {
		super(400, errorMessage);
	}

}
