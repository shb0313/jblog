package com.douzone.jblog.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/douzone/jblog/app/jdbc.properties")
public class DBConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
		
		return dataSource;
	}
}

/*  
 * <!-- Connection Pool DataSource -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="org.mariadb.jdbc.Driver" />
		<property name="url" value="jdbc:mariadb://192.168.10.115:3307/jblog?charset=utf8" />
		<property name="username" value="jblog" />
		<property name="password" value="jblog" />
	</bean>
 
   - 커넥션 풀, 데이터베이스와 애플리케이션 효율적인 연결
   - pom.xml : <!-- Common DBCP -->
  
 * @PropertySource : jdbc.properties 위치
 * Environment : Xml 파일의 객체를 불러올 때 사용 		
 * jdbc.properties : DB 정보를 Xml 파일에 저장해 불러옴으로 필요 시마다 참조, 변경 시 Xml 파일만 변경
*/

/*
 * @Bean
       - Spring(IoC) Container가 관리하는 자바 객체, Spring Bean Container에 존재하는 객체
       - Spring(IoC, Inversion of Control)에 의해 인스턴스화, 관리, 생성됨
       - Bean Container는 의존성 주입을 통해 Bean 객체를 사용 할 수 있도록 함
       - Spring에서 Bean은 보통 Singleton으로 존재
          - Singleton : 어떤 Class가 최초 한번만 메모리를 할당(static)하고, 그 메모리에 객체를 만들어 사용하는 디자인 패턴
       - Spring(IoC) Container에 Bean을 등록하도록 하는 Meta Data를 기입 하는 어노테이션
       - method에만 넣을 수 있음
       - 보통 method이름이 곧 bean이름이 됨
 */

