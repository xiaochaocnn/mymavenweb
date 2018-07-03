package testUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.XML;

import com.alibaba.fastjson.JSONObject;

//String data="<?xml version=\"1.0\" encoding=\"GBK\"?> <CMBSDKPGK> <INFO>
// <FUNNAM>GetAccInfo</FUNNAM> <DATTYP>2</DATTYP>\n" +
// "<LGNNAM>mifengjbud</LGNNAM> </INFO> <SDKACINFX>
// <ACCNBR>110916297010402</ACCNBR> <BBKNBR>10</BBKNBR> </SDKACINFX>\n" +
// "</CMBSDKPGK>";
public class bcdlSocketTest {

	public static void main(String[] args) throws IOException, JAXBException {
		System.out.println("give yourself a smile");

		Balance balance = new Balance();
		balance.setACCNBR("110916297010402");
		balance.setBBKNBR("10");
		BalanceHead head = new BalanceHead();
		head.setDATTYP("2");
		head.setFUNNAM("GetAccInfo");
		head.setLGNNAM("mifengjbud");
		BalanceBody body = new BalanceBody();
		body.setBody(balance);
		body.setHead(head);

		JAXBContext context = JAXBContext.newInstance(Balance.class, BalanceHead.class, BalanceBody.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(body, baos);
		String data = new String(baos.toByteArray());
		System.out.println(data);
		Socket socket = new Socket("172.16.160.15", 656);
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		String strLen = String.valueOf(data.getBytes("gbk").length) + new String("        ".getBytes(), "gbk");
		outputStream.write((strLen.substring(0, 8) + data).getBytes("gbk"));
		outputStream.flush();
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		StringBuffer result = new StringBuffer();
		String line = null;
		while ((line = inputStream.readLine()) != null) {
			result.append(new String(line.getBytes("ISO8859-1"), "GBK"));
		}
		//System.out.println("result:" + result.toString());
		line = result.toString().trim();
        line = line.substring(line.indexOf("<"), line.length());
        //System.out.println("line:"+line.toString());
		//JSONObject xmljson=XML.toJSONObject(result.toString());
		
        JAXBContext context2 = JAXBContext.newInstance(RespBalance.class, RespBalanceBody.class, RespBalanceHead.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		StringReader reader=new StringReader(line);
		//System.out.println(reader.);
		RespBalance object = (RespBalance)unmarshaller.unmarshal(new StringReader(line));
		System.out.println("object:"+object.toString());
	}
}
