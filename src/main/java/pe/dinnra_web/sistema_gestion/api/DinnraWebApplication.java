package pe.dinnra_web.sistema_gestion.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
/* Habilita trabajar la paginacion con Dtos, page, size, sort, sin necesidad de crear logica para estos */
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
/* Inicializa el sistema de auditoria en toda la aplicacion, para entidades en las que se use
   @EntityListeners(AuditingEntityListener.class)
*/
@EnableJpaAuditing
public class DinnraWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinnraWebApplication.class, args);

	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new java.util.Locale("es"));
		return slr;
	}

	@Bean
	public WebMvcConfigurer localeInterceptor() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
				lci.setParamName("lang");
				registry.addInterceptor(lci);
			}
		};
	}

}


