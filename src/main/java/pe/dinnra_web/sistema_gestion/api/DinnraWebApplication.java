package pe.dinnra_web.sistema_gestion.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

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

}
