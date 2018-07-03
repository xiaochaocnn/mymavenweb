package testUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="NTQACINFZ")
public class RespBalanceBody {
	private String ACCBLV;
	private String ACCNAM;
	private String ACCNBR;
	private String AVLBLV;

	public String getACCBLV() {
		return ACCBLV;
	}
   @XmlElement(name="ACCBLV")
	public void setACCBLV(String aCCBLV) {
		ACCBLV = aCCBLV;
	}

	public String getACCNAM() {
		return ACCNAM;
	}
	@XmlElement(name="ACCNAM")
	public void setACCNAM(String aCCNAM) {
		ACCNAM = aCCNAM;
	}

	public String getACCNBR() {
		return ACCNBR;
	}
	@XmlElement(name="ACCNBR")
	public void setACCNBR(String aCCNBR) {
		ACCNBR = aCCNBR;
	}

	public String getAVLBLV() {
		return AVLBLV;
	}
	@XmlElement(name="AVLBLV")
	public void setAVLBLV(String aVLBLV) {
		AVLBLV = aVLBLV;
	}
	@Override
	public String toString() {
		return "RespBalanceBody [ACCBLV=" + ACCBLV + ", ACCNAM=" + ACCNAM + ", ACCNBR=" + ACCNBR + ", AVLBLV=" + AVLBLV
				+ "]";
	}
    
}
