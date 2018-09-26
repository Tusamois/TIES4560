package Ties4560.Demo3.exceptionHandling;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String documentation;
	public ErrorMessage(){}
	public ErrorMessage(String errMes, int errCode, String doc) {
		super();
		this.errorMessage = errMes;
		this.errorCode = errCode;
		this.documentation = doc;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}
