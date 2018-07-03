package testUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="INFO")
public class RespBalanceHead {
	
	private String DATTYP;
	private String ERRMSG;
	private String FUNNAM;
	private String LGNNAM;
	private String RETCOD;
	public String getDATTYP() {
		return DATTYP;
	}
	@XmlElement(name="DATTYP")
	public void setDATTYP(String dATTYP) {
		DATTYP = dATTYP;
	}
	public String getERRMSG() {
		return ERRMSG;
	}
	@XmlElement(name="ERRMSG")
	public void setERRMSG(String eRRMSG) {
		ERRMSG = eRRMSG;
	}
	public String getFUNNAM() {
		return FUNNAM;
	}
	@XmlElement(name="FUNNAM")
	public void setFUNNAM(String fUNNAM) {
		FUNNAM = fUNNAM;
	}
	public String getLGNNAM() {
		return LGNNAM;
	}
	@XmlElement(name="LGNNAM")
	public void setLGNNAM(String lGNNAM) {
		LGNNAM = lGNNAM;
	}
	public String getRETCOD() {
		return RETCOD;
	}
	@XmlElement(name="RETCOD")
	public void setRETCOD(String rETCOD) {
		RETCOD = rETCOD;
	}
	@Override
	public String toString() {
		return "RespBalanceHead [DATTYP=" + DATTYP + ", ERRMSG=" + ERRMSG + ", FUNNAM=" + FUNNAM + ", LGNNAM=" + LGNNAM
				+ ", RETCOD=" + RETCOD + "]";
	}
	
	

}
