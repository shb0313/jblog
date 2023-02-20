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
* @Bean
*       - Spring(IoC) Container가 관리하는 자바 객체, Spring Bean Container에 존재하는 객체
*       - Spring(IoC, Inversion of Control)에 의해 인스턴스화, 관리, 생성 됨
*       - Bean Container는 의존성 주입을 통해 Bean 객체를 사용 할 수 있도록 함
*       - Spring에서 Bean은 보통 Singleton으로 존재
*          - Singleton : 어떤 Class가 최초 한번만 메모리를 할당(static)하고, 그 메모리에 객체를 만들어 사용하는 디자인 패턴
*       - Spring(IoC) Container에 Bean을 등록하도록 하는 Meta Data를 기입 하는 어노테이션
*       - method에만 넣을 수 있음
*       - 보통 method이름이 곧 bean이름이 됨
*/