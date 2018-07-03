package testUtil;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CMBSDKPGK")
public class BalanceBody {
     private BalanceHead head;
     private Balance body;
	public BalanceHead getHead() {
		return head;
	}
	@XmlElement(name="INFO")
	public void setHead(BalanceHead head) {
		this.head = head;
	}
	public Balance getBody() {
		return body;
	}
	@XmlElement(name="SDKACINFX")
	public void setBody(Balance body) {
		this.body = body;
	}
     
	
	public static void main(String[] args) throws JAXBException {
		Balance balance=new Balance();
		balance.setACCNBR("110916297010402");
		balance.setBBKNBR("10");
		BalanceHead head=new BalanceHead();
		head.setDATTYP("2");
		head.setFUNNAM("GetAccInfo");
		head.setLGNNAM("mifengjbud");
		BalanceBody body=new BalanceBody();
		body.setBody(balance);
		body.setHead(head);
		
		JAXBContext context=JAXBContext.newInstance(Balance.class,BalanceHead.class,BalanceBody.class);
		Marshaller marshaller=context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		marshaller.marshal(body, baos);
		String xmlObj=new String(baos.toByteArray());
		System.out.println(xmlObj);
	}
}
