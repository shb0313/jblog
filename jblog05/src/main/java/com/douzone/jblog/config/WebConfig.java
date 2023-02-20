package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.jblog.config.web.FileuploadConfig;
import com.douzone.jblog.config.web.MessageSourceConfig;
import com.douzone.jblog.config.web.MvcConfig;
import com.douzone.jblog.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.douzone.jblog.controller")
@Import({MvcConfig.class, FileuploadConfig.class, MessageSourceConfig.class, SecurityConfig.class})
public class WebConfig {	
	
}
