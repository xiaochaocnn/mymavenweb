package testUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="INFO")
public class BalanceHead {
	private String FUNNAM;
	private String DATTYP;
	private String LGNNAM;
	public String getFUNNAM() {
		return FUNNAM;
	}
	@XmlElement(name="FUNNAM")
	public void setFUNNAM(String fUNNAM) {
		FUNNAM = fUNNAM;
	}
	public String getDATTYP() {
		return DATTYP;
	}
	@XmlElement(name="DATTYP")
	public void setDATTYP(String dATTYP) {
		DATTYP = dATTYP;
	}
	public String getLGNNAM() {
		return LGNNAM;
	}
	@XmlElement(name="LGNNAM")
	public void setLGNNAM(String lGNNAM) {
		LGNNAM = lGNNAM;
	}
	
	
	

}
