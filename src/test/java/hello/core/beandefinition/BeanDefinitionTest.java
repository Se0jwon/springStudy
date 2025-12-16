package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    // (현대 개발에서 사용). 설정 메타 정보: 순수한 Java 언어와 어노테이션 (@Configuration, @Bean)을 사용.
    // 1. 타입 안전성, 2. 리팩토링 용이, 3. 환경설정
    // 단점 : XML 보다 설정 코드 길어질 수 있음
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 과거 Spring 개발에서 사용되던 방식입니다.
    // 설정 메타 정보: XML 파일의 정해진 문법(beans, bean, constructor-arg 등)을 사용.
    // 초기 Spring 버전에서 표준으로 사용되었기 때문에 레거시 시스템과 호환성이 좋습니다. 간결함.
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName: " + beanDefinitionName
                + ", beanDefinition = " + beanDefinition);
            }
        }
    }
}
