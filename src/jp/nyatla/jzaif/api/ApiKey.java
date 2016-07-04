package jp.nyatla.jzaif.api;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import jp.nyatla.nyansat.utils.xml.XPathUtil;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 認証の必要なAPIに使うAPIキーです。
 */
public class ApiKey
{
	final public String key;
	final public String secret;
	public ApiKey(String i_key,String i_secret)
	{
		this.key=i_key;
		this.secret=i_secret;
	}
	/**
	 * XMLファイルからAPIキーとシークレットキーのペアを読みだします。
	 * <pre>
	 * &lt;root&gt;
	 * &lt;key&gt;YOUR API KEY&lt;/key&gt;
	 * &lt;secret&gt;YOUR SECRET KEY&lt;/secret&gt;
	 * &lt;/root&gt;
	 * </pre>
	 * @param i_file
	 * @return
	 */
	public static ApiKey loadFromXml(File i_file)
	{
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc=builder.parse(i_file);
			XPathUtil xpath=new XPathUtil(doc);
			return new ApiKey(
					xpath.selectSingleElement("//key").getTextContent(),
					xpath.selectSingleElement("//secret").getTextContent());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException(e);
		} catch (DOMException | XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}	
	
	/**
	 * SHA512で署名
	 * @param i_message
	 * @param i_secret
	 * @return
	 */
	public String makeSignedHex(String i_message)
	{
		SecretKeySpec signingKey = new SecretKeySpec(this.secret.getBytes(),"HmacSHA512");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA512");
			mac.init(signingKey);
			StringBuffer sb=new StringBuffer();
			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(i_message.getBytes());
		    for (byte b :rawHmac) {
		        String hex = String.format("%02x", b);
		        sb.append(hex);
		    }
		    return sb.toString();
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
}
