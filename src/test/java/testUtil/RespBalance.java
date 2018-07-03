package testUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "CMBSDKPGK")
public class RespBalance {
	private RespBalanceHead head;
	private RespBalanceBody body;

	public RespBalanceHead getHead() {
		return head;
	}

	@XmlElement(name = "INFO")
	public void setHead(RespBalanceHead head) {
		this.head = head;
	}

	public RespBalanceBody getBody() {
		return body;
	}

	@XmlElement(name = "NTQACINFZ")
	public void setBody(RespBalanceBody body) {
		this.body = body;
	}

	
	@Override
	public String toString() {
		return "RespBalance [head=" + head + ", body=" + body + "]";
	}

	public static void main(String[] args) throws JAXBException {

//		JAXBContext context = JAXBContext.newInstance(RespBalance.class, RespBalanceBody.class, RespBalanceHead.class);
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		//unmarshaller.setProperty(, "GBK");
//		RespBalance object = (RespBalance)unmarshaller.unmarshal(new File("/tmp/balance2.xml"));
//		System.out.println("object:" + object.toString());
		String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
	}
}
