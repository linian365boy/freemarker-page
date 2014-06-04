package com.nian.ftl;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	
	public static Template getTemplate(String name){
		try{
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(FreemarkerUtil.class, "/template");
			Template temp = cfg.getTemplate(name);
			return temp;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void print(String name,Map<String,Object> root){
		try{
			Template temp = getTemplate(name);
			temp.process(root, new PrintWriter(System.out));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void fprint(String name,Map<String,Object> root,String outFile,String fileName){
		FileWriter out = null;
		try {
			File file = new File(outFile);
			if(!file.exists()){
				file.mkdirs();
			}
			out = new FileWriter(file+File.separator+fileName);
			Template temp = getTemplate(name);
			temp.setEncoding("UTF-8");
			temp.process(root,out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
