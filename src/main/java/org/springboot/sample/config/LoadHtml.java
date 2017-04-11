package org.springboot.sample.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;


public class LoadHtml {
	private static Logger logger = Logger.getLogger(LoadHtml.class.getName());
	private static final String INDEX_HTMLTXT;
	
	static{
		InputStream is =null;
		InputStreamReader in=null;
		StringBuilder input = new StringBuilder();
		try {
			is =LoadHtml.class.getClassLoader().getResourceAsStream(
						"index.html");
			in = new InputStreamReader(is, "utf-8");
			int ch;
			while ((ch = in.read()) != -1) {
				input.append((char) ch);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}finally{
			try {
				is.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
		INDEX_HTMLTXT=input.toString();
	}
	
	public static String setReplace(String txt){
		return INDEX_HTMLTXT.replace("481dc9ce-a75c-4fee-847b-b0de2f4a4c3e", txt);
	}
	
	public static void loadIndex(String path,String txtHtml){
		File dest = new File(path+"/"+"index.html");
		FileOutputStream  st = null;
	 	OutputStreamWriter wt=null;
		try {
			st = new FileOutputStream(dest);
			wt = new OutputStreamWriter(st, "utf-8");
			wt.write(txtHtml);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(wt!=null){
				try {
					wt.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if(st!=null){
				try {
					st.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}
