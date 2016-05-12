package hello;

	/*
	 * Simple example makes call to Omniture API to get a companies report suites
	 * 
	 * Requires the following libraries
	 * 
	 *  jakarta commons-lang 2.4
	 *  jakarta commons-beanutils 1.7.0
	 *  jakarta commons-collections 3.2
	 *  jakarta commons-logging 1.1.1
	 *  ezmorph 1.0.6
	 *  json-lib-2.3-jdk13
	 *  
	 *
	 *  @author Lamont Crook
	 *  @email lamont@adobe.com
	 * 
	 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

	public class SiteCatalyst {

	    private static String USERNAME = Constants.USERNAME;
	    private static String PASSWORD = Constants.PASSWORD;
	    private static String ENDPOINT = "https://api.omniture.com/admin/1.4/rest/"; 
	    

	    public static String callMethod(String method, String data) throws IOException { 
	    	if (Constants.PROXY_ENABLED) {
	        System.setProperty("https.proxyHost", "irvcache");
	        System.setProperty("https.proxyPort", String.valueOf(Constants.PROXY_PORT));
	    	}

	        URL url = new URL(ENDPOINT + "?method=" + method);
	        URLConnection connection = url.openConnection();
	        connection.addRequestProperty("X-WSSE", getHeader());

	        connection.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        InputStream in = connection.getInputStream();
	        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));

	        StringBuffer sBuffer = new StringBuffer();
	        String inputLine;
	        while ((inputLine = res.readLine()) != null)
	            sBuffer.append(inputLine);

	        res.close();

	        return sBuffer.toString();
	    }

	    public static String getHeader()  {
	        StringBuffer wsseHeader = new StringBuffer();

	        try {
	            SecureRandom rand = new SecureRandom();
	            // Base64 b64 = new Base64();
	            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	            String created = dateFormatter.format(new Date());
	            ByteArrayOutputStream digest = new ByteArrayOutputStream(40);
	            byte[] nonce = new byte[20];
	            rand.nextBytes(nonce);
	            digest.write(nonce);
	            digest.write(created.getBytes());
	            digest.write(PASSWORD.getBytes());
	            wsseHeader.append("UsernameToken Username=\"");
	            wsseHeader.append(USERNAME);
	            wsseHeader.append("\", PasswordDigest=\"");
	            wsseHeader.append(Base64.encodeBase64String(toSHA1(digest.toByteArray())));
	            wsseHeader.append("\", Nonce=\"");
	            wsseHeader.append(Base64.encodeBase64String(nonce));
	            wsseHeader.append("\", Created=\"");
	            wsseHeader.append(created);
	            wsseHeader.append("\"");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }   return wsseHeader.toString();
	    }

	    private static byte[] toSHA1(byte[] convertme) {
	        MessageDigest md = null;
	        try {
	            md = MessageDigest.getInstance("SHA-1");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return md.digest(convertme);
	    }
	}


