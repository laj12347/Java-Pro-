package com.mloger;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
/**
* 仅仅使用slf4j 实现日志功能.
* <br>疑问：看看其他的框架是如何是用日志系统的
* @spring 也是想像这样使用日志框架的，只是它使用的是org.apache.commons.logging.LogFactory这个包中的，
* @疑问：那如果框架中不依赖Common-Logging  会不会出问题那
* @答： jcl-over-slf4j-1.5.2.jar包提供了Commons-Logging接口，底层还是由SLF4J来决定哪种实现机制
* @author liyuan
* @date 2015年7月11日 下午6:02:05
 */
public class HelloWorld {  
  public static void main(String[] args) {  
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);  
    logger.info("Hello World");  
  }  
} 
