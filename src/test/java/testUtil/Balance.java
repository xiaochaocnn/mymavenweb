package testUtil;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="SDKACINFX")
public class Balance {
	
	private String ACCNBR;
	private String BBKNBR;
	
	public Balance() {}
	public Balance(String aCCNBR, String bBKNBR) {
		super();
		ACCNBR = aCCNBR;
		BBKNBR = bBKNBR;
	}
	public String getACCNBR() {
		return ACCNBR;
	}
	@XmlElement(name="ACCNBR")
	public void setACCNBR(String aCCNBR) {
		ACCNBR = aCCNBR;
	}
	public String getBBKNBR() {
		return BBKNBR;
	}
	@XmlElement(name="BBKNBR")
	public void setBBKNBR(String bBKNBR) {
		BBKNBR = bBKNBR;
	}
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext context=JAXBContext.newInstance(Balance.class);
		Marshaller marshaller=context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		marshaller.marshal(new Balance("110916297010402","10"), baos);
		String xmlObj=new String(baos.toByteArray());
		System.out.println(xmlObj);
	}

}
