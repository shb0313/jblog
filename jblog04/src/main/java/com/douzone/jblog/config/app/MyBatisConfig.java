package com.douzone.jblog.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception{
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/douzone/jblog/app/mybatis/configuration.xml"));
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		
		return new SqlSessionTemplate(sqlSessionFactory);
	}	
	
}

/*
* <!-- MyBatis SqlSessionFactory --> 
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean"> 
		<property name="dataSource" ref="dataSource" /> 
		<property name="configLocation" value="classpath:mybatis/configuration.xml" /> 
	</bean>
	
	- SqlSessionFactory : sql 서버와 MyBatis를 연결해주는 객체
	                      DBConfig의 dataSource 참조
* 
* 
* 
* <!-- MyBatis SqlSession --> 
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactory" />
	</bean>
* 
*/