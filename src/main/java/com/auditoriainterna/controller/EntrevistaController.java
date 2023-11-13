package com.auditoriainterna.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.auditoriainterna.model.Empleado;
import com.auditoriainterna.model.Entrevista;


import com.auditoriainterna.service.EmpleadoService;
import com.auditoriainterna.service.EntrevistaService;


@Controller
public class EntrevistaController {

	@Autowired
	private EntrevistaService entrevistaService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	UserDetailsService userdetailsService;
	
	@GetMapping("/entrevista/{codigoAuditoria}")
    public String listarEntrevistasPorCodigoAuditoria(@PathVariable int codigoAuditoria, Model model, Principal principal) {
		UserDetails userDetails = userdetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
        List<Entrevista>entrevistas= entrevistaService.obtenerEntrevistasPorCodigoAuditoria(codigoAuditoria);
        int codigo=codigoAuditoria;
        model.addAttribute("codigo", codigo);
        model.addAttribute("entrevistas", entrevistas);
        return "entrevista";
    }
	
	//Para Entrevistas busqueda de dni y llenado de campos
		@GetMapping("/entrevista/crear/{codigo}")
		public String formularioEntrevista(@PathVariable int codigo,Model model) {
			int _codigo=codigo;
			model.addAttribute("codigo", _codigo);
			model.addAttribute("empleado", new Empleado());
			return "formEntrevista";
		}
		//aca podriamos cambiar
		@PostMapping("/entrevista/crear/{codigo}")
	    public String buscarPorDNI(@PathVariable int codigo,@ModelAttribute Empleado e, Model model) {
			
			Empleado empleado = empleadoService.obtenerEmpleadoPorDni(e.getDNI());
	        if (empleado != null) {
	            model.addAttribute("empleadoDni", empleado);
	            int _codigo=codigo;
				model.addAttribute("codigo", _codigo);
				model.addAttribute("entrevista", new Entrevista());
				
				String pregunta1="", pregunta2="", pregunta3="";
				/*AREA DE TI*/
				if(empleado.getCargo().equals("Analista de Datos")) {
					pregunta1="¿Cómo se recopilan, almacenan y procesan los datos de la empresa?";
					pregunta2="¿Qué medidas se toman para garantizar la integridad y la seguridad de los datos analizados?";
					pregunta3="¿Cómo se utiliza la información obtenida para tomar decisiones empresariales estratégicas?";
				}
				else if(empleado.getCargo().equals("Especialista en Desarrollo Web")) {
					pregunta1="¿Cómo se garantiza la funcionalidad y la usabilidad de los sitios web y las aplicaciones desarrolladas?";
					pregunta2="¿Qué medidas se toman para optimizar el rendimiento y la velocidad de los sitios web y las aplicaciones?";
					pregunta3="¿Cómo se abordan las preocupaciones de seguridad en el desarrollo y mantenimiento de sitios web y aplicaciones?";
				}
				else if(empleado.getCargo().equals("Especialista en Soporte Técnico")) {
					pregunta1="¿Cómo se gestionan y se priorizan los problemas reportados por los usuarios?";
					pregunta2="¿Qué medidas se toman para asegurar una comunicación clara y efectiva con los usuarios durante el proceso de resolución de problemas?";
					pregunta3="¿Cómo se realiza el seguimiento de la satisfacción del cliente después de la resolución de problemas?";
				}
				else if(empleado.getCargo().equals("Administrador de Sistemas")) {
					pregunta1="¿Cómo se gestiona la infraestructura de hardware y software de la empresa, incluyendo servidores, dispositivos de almacenamiento y sistemas operativos?";
					pregunta2="¿Qué medidas se toman para garantizar la seguridad y la integridad de los sistemas de la empresa?";
					pregunta3="¿Cómo se realizan las actualizaciones y el mantenimiento preventivo de los sistemas para evitar interrupciones en las operaciones comerciales?";				
				}
				else if(empleado.getCargo().equals("Ingeniero de Redes")) {
					pregunta1="¿Cómo se diseña, se implementa y se administra la red de comunicaciones de la empresa?";
					pregunta2="¿Qué medidas se toman para garantizar la seguridad de la red y protegerla contra amenazas internas y externas?";
					pregunta3="¿Cómo se monitorea y se optimiza el rendimiento de la red para garantizar una conectividad estable y de alta velocidad?";
				}
				/*AREA DE VENTAS*/
				else if(empleado.getCargo().equals("Director de Ventas")) {
					pregunta1="¿Cómo se establecen los objetivos de ventas y cómo se alinean con los objetivos generales de la empresa?";
					pregunta2="¿Cuáles son las estrategias clave implementadas para el desarrollo y la expansión de la cartera de clientes";
					pregunta3="¿Cómo se fomenta la innovación en las estrategias de ventas para mantener la competitividad en el mercado?";
					
				}else if(empleado.getCargo().equals("Ejecutivo de Ventas")) {
					pregunta1="¿Cómo se gestionan y se mantienen las relaciones con los clientes actuales y potenciales?";
					pregunta2="¿Qué tácticas se utilizan para cerrar ventas y superar posibles objeciones de los clientes?";
					pregunta3="¿Cómo se adapta la estrategia de ventas a diferentes tipos de clientes y mercados, y cómo se evalúa la efectividad de estas adaptaciones?";
				
				}else if(empleado.getCargo().equals("Analista de ventas")) {
					pregunta1="¿Cómo se recopilan y analizan los datos de ventas para identificar tendencias y patrones en el comportamiento del mercado?";
					pregunta2="¿Qué métricas se utilizan para evaluar el rendimiento de ventas y cómo se comunican estos datos a los responsables de la toma de decisiones?";
					pregunta3="¿Cómo se utilizan las herramientas tecnológicas y de análisis de datos para mejorar la efectividad de las estrategias de ventas y la toma de decisiones?";
				
				}else if(empleado.getCargo().equals("Representante de ventas")) {
					pregunta1="¿Cómo se maneja el proceso de venta, desde la presentación del producto hasta el cierre de la venta?";
					pregunta2="¿Cómo se abordan las preocupaciones y preguntas de los clientes durante el proceso de venta?";
					pregunta3="¿Cómo se realiza el seguimiento de los clientes después de la venta para fomentar relaciones a largo plazo y oportunidades de venta adicionales?";
				
				/*AREA DE RRHH*/
				}else if(empleado.getCargo().equals("Gerente de Recursos Humanos")) {
					pregunta1="¿Cómo se asegura el cumplimiento de las políticas y procedimientos de recursos humanos en toda la empresa?";
					pregunta2="¿Cómo se fomenta la retención de empleados y se impulsa la satisfacción laboral dentro de la organización?";
					pregunta3="¿Cómo se mide y se analiza el clima laboral y cómo se utilizan estos datos para implementar mejoras en el lugar de trabajo?";
					
				}else if(empleado.getCargo().equals("Especialista en contratación y reclutamiento")) {
					pregunta1="¿Cómo se identifican y se atraen candidatos cualificados para las posiciones vacantes?";
					pregunta2="¿Qué medidas se toman para garantizar un proceso de contratación justo y transparente?";
					pregunta3="¿Cómo se utiliza la tecnología y las plataformas de reclutamiento en línea para mejorar la eficiencia del proceso de contratación y para atraer talento diverso?";
				}
				else if(empleado.getCargo().equals("Especialista en formación y desarrollo")) {
					pregunta1="¿Cómo se determinan las necesidades de formación de los empleados y se diseñan programas de formación efectivos?";
					pregunta2="¿Cómo se evalúa el impacto de los programas de formación en el desarrollo profesional de los empleados y en el rendimiento de la empresa?";
					pregunta3="¿Cómo se fomenta el aprendizaje continuo y el crecimiento profesional dentro de la empresa, y cómo se mide su impacto en la retención de empleados y en el compromiso laboral?";
				}
				else if(empleado.getCargo().equals("Especialista en relaciones laborales")) {
					pregunta1="¿Cómo se manejan los problemas y conflictos laborales dentro de la empresa?";
					pregunta2="¿Qué medidas se toman para fomentar un ambiente laboral positivo y para promover la comunicación efectiva entre la dirección y los empleados?";
					pregunta3="¿Cómo se aplican y se interpretan las leyes laborales y los reglamentos gubernamentales en las decisiones y políticas de recursos humanos de la empresa?";
				}
				
				/*AREA MARKETING*/
				else if(empleado.getCargo().equals("Gerente de Marketing")) {
					pregunta1="¿Cómo se establecen los objetivos de marketing y cómo se alinean con los objetivos generales de la empresa?";
					pregunta2="¿Cómo se monitorea y se evalúa el desempeño de las campañas de marketing y las actividades promocionales?";
					pregunta3="¿Cómo se fomenta la innovación en las estrategias de marketing para mantener la competitividad en el mercado?";
				}
				else if(empleado.getCargo().equals("Especialista en Marketing Digital")) {
					pregunta1="¿Cómo se seleccionan las plataformas de marketing digital adecuadas para alcanzar los objetivos de la empresa?";
					pregunta2="¿Cómo se mide y se analiza el retorno de la inversión (ROI) de las campañas de marketing digital?";
					pregunta3="¿Cómo se mantienen actualizadas las estrategias de marketing digital en respuesta a los cambios en las tendencias de búsqueda y las preferencias de los consumidores?";
				}
				else if(empleado.getCargo().equals("Especialista en Comunicaciones de Marketing")) {
					pregunta1="¿Cómo se garantiza la coherencia en la comunicación de la marca y los mensajes de marketing en todos los canales?";
					pregunta2="¿Cómo se evalúa la efectividad de las estrategias de comunicación de marketing en la generación de leads y en el aumento de la conciencia de la marca?";
					pregunta3="¿Cómo se adaptan los mensajes de marketing a diferentes segmentos de la audiencia y cómo se evalúa la efectividad de esta personalización?";
				}
				else if(empleado.getCargo().equals("Analista de Mercado")) {
					pregunta1="¿Cómo se recopilan y se analizan los datos de mercado para identificar oportunidades y amenazas potenciales?";
					pregunta2="¿Qué metodologías se utilizan para evaluar la demanda del mercado y las preferencias del consumidor?";
					pregunta3="¿Cómo se utilizan los hallazgos del análisis de mercado para ajustar y mejorar las estrategias de marketing existentes?";
				}

				
				model.addAttribute("pregunta1", pregunta1);
				model.addAttribute("pregunta2", pregunta2);
				model.addAttribute("pregunta3", pregunta3);
				
	         } else {
	            model.addAttribute("mensaje", "Empleado no encontrado");
	        }
	        return "formEntrevista";
	    }
	
		@PostMapping("/entrevista/guardar")
		public String  guardarEntrevista(@ModelAttribute("entrevista") Entrevista entrevista) {
			entrevistaService.crearEntrevista(entrevista);
			int codigo= entrevista.getAuditoria().getCodigo_auditoria();
			 System.out.println("Valor de código: " + codigo);
			return "redirect:/entrevista/"+codigo;
		}
		
		@GetMapping("/entrevista/editar/{id}/{dni}")
		public String editarEntrevista(@PathVariable int id,@PathVariable int dni, Model model) {
			Optional<Entrevista> entrevista=entrevistaService.listaId(id);			
			model.addAttribute("entrevista", entrevista);
			
			Empleado empleado = empleadoService.obtenerEmpleadoPorDni(dni);
			model.addAttribute("empleado", empleado);
					
			//aca tambien va
			String pregunta1="", pregunta2="", pregunta3="";
			/*AREA DE TI*/
			if(empleado.getCargo().equals("Analista de Datos")) {
				pregunta1="¿Cómo se recopilan, almacenan y procesan los datos de la empresa?";
				pregunta2="¿Qué medidas se toman para garantizar la integridad y la seguridad de los datos analizados?";
				pregunta3="¿Cómo se utiliza la información obtenida para tomar decisiones empresariales estratégicas?";
			}
			else if(empleado.getCargo().equals("Especialista en Desarrollo Web")) {
				pregunta1="¿Cómo se garantiza la funcionalidad y la usabilidad de los sitios web y las aplicaciones desarrolladas?";
				pregunta2="¿Qué medidas se toman para optimizar el rendimiento y la velocidad de los sitios web y las aplicaciones?";
				pregunta3="¿Cómo se abordan las preocupaciones de seguridad en el desarrollo y mantenimiento de sitios web y aplicaciones?";
			}
			else if(empleado.getCargo().equals("Especialista en Soporte Técnico")) {
				pregunta1="¿Cómo se gestionan y se priorizan los problemas reportados por los usuarios?";
				pregunta2="¿Qué medidas se toman para asegurar una comunicación clara y efectiva con los usuarios durante el proceso de resolución de problemas?";
				pregunta3="¿Cómo se realiza el seguimiento de la satisfacción del cliente después de la resolución de problemas?";
			}
			else if(empleado.getCargo().equals("Administrador de Sistemas")) {
				pregunta1="¿Cómo se gestiona la infraestructura de hardware y software de la empresa, incluyendo servidores, dispositivos de almacenamiento y sistemas operativos?";
				pregunta2="¿Qué medidas se toman para garantizar la seguridad y la integridad de los sistemas de la empresa?";
				pregunta3="¿Cómo se realizan las actualizaciones y el mantenimiento preventivo de los sistemas para evitar interrupciones en las operaciones comerciales?";				
			}
			else if(empleado.getCargo().equals("Ingeniero de Redes")) {
				pregunta1="¿Cómo se diseña, se implementa y se administra la red de comunicaciones de la empresa?";
				pregunta2="¿Qué medidas se toman para garantizar la seguridad de la red y protegerla contra amenazas internas y externas?";
				pregunta3="¿Cómo se monitorea y se optimiza el rendimiento de la red para garantizar una conectividad estable y de alta velocidad?";
			}
			/*AREA DE VENTAS*/
			else if(empleado.getCargo().equals("Director de Ventas")) {
				pregunta1="¿Cómo se establecen los objetivos de ventas y cómo se alinean con los objetivos generales de la empresa?";
				pregunta2="¿Cuáles son las estrategias clave implementadas para el desarrollo y la expansión de la cartera de clientes";
				pregunta3="¿Cómo se fomenta la innovación en las estrategias de ventas para mantener la competitividad en el mercado?";
				
			}else if(empleado.getCargo().equals("Ejecutivo de Ventas")) {
				pregunta1="¿Cómo se gestionan y se mantienen las relaciones con los clientes actuales y potenciales?";
				pregunta2="¿Qué tácticas se utilizan para cerrar ventas y superar posibles objeciones de los clientes?";
				pregunta3="¿Cómo se adapta la estrategia de ventas a diferentes tipos de clientes y mercados, y cómo se evalúa la efectividad de estas adaptaciones?";
			
			}else if(empleado.getCargo().equals("Analista de ventas")) {
				pregunta1="¿Cómo se recopilan y analizan los datos de ventas para identificar tendencias y patrones en el comportamiento del mercado?";
				pregunta2="¿Qué métricas se utilizan para evaluar el rendimiento de ventas y cómo se comunican estos datos a los responsables de la toma de decisiones?";
				pregunta3="¿Cómo se utilizan las herramientas tecnológicas y de análisis de datos para mejorar la efectividad de las estrategias de ventas y la toma de decisiones?";
			
			}else if(empleado.getCargo().equals("Representante de ventas")) {
				pregunta1="¿Cómo se maneja el proceso de venta, desde la presentación del producto hasta el cierre de la venta?";
				pregunta2="¿Cómo se abordan las preocupaciones y preguntas de los clientes durante el proceso de venta?";
				pregunta3="¿Cómo se realiza el seguimiento de los clientes después de la venta para fomentar relaciones a largo plazo y oportunidades de venta adicionales?";
			
			/*AREA DE RRHH*/
			}else if(empleado.getCargo().equals("Gerente de Recursos Humanos")) {
				pregunta1="¿Cómo se asegura el cumplimiento de las políticas y procedimientos de recursos humanos en toda la empresa?";
				pregunta2="¿Cómo se fomenta la retención de empleados y se impulsa la satisfacción laboral dentro de la organización?";
				pregunta3="¿Cómo se mide y se analiza el clima laboral y cómo se utilizan estos datos para implementar mejoras en el lugar de trabajo?";
				
			}else if(empleado.getCargo().equals("Especialista en contratación y reclutamiento")) {
				pregunta1="¿Cómo se identifican y se atraen candidatos cualificados para las posiciones vacantes?";
				pregunta2="¿Qué medidas se toman para garantizar un proceso de contratación justo y transparente?";
				pregunta3="¿Cómo se utiliza la tecnología y las plataformas de reclutamiento en línea para mejorar la eficiencia del proceso de contratación y para atraer talento diverso?";
			}
			else if(empleado.getCargo().equals("Especialista en formación y desarrollo")) {
				pregunta1="¿Cómo se determinan las necesidades de formación de los empleados y se diseñan programas de formación efectivos?";
				pregunta2="¿Cómo se evalúa el impacto de los programas de formación en el desarrollo profesional de los empleados y en el rendimiento de la empresa?";
				pregunta3="¿Cómo se fomenta el aprendizaje continuo y el crecimiento profesional dentro de la empresa, y cómo se mide su impacto en la retención de empleados y en el compromiso laboral?";
			}
			else if(empleado.getCargo().equals("Especialista en relaciones laborales")) {
				pregunta1="¿Cómo se manejan los problemas y conflictos laborales dentro de la empresa?";
				pregunta2="¿Qué medidas se toman para fomentar un ambiente laboral positivo y para promover la comunicación efectiva entre la dirección y los empleados?";
				pregunta3="¿Cómo se aplican y se interpretan las leyes laborales y los reglamentos gubernamentales en las decisiones y políticas de recursos humanos de la empresa?";
			}
			
			/*AREA MARKETING*/
			else if(empleado.getCargo().equals("Gerente de Marketing")) {
				pregunta1="¿Cómo se establecen los objetivos de marketing y cómo se alinean con los objetivos generales de la empresa?";
				pregunta2="¿Cómo se monitorea y se evalúa el desempeño de las campañas de marketing y las actividades promocionales?";
				pregunta3="¿Cómo se fomenta la innovación en las estrategias de marketing para mantener la competitividad en el mercado?";
			}
			else if(empleado.getCargo().equals("Especialista en Marketing Digital")) {
				pregunta1="¿Cómo se seleccionan las plataformas de marketing digital adecuadas para alcanzar los objetivos de la empresa?";
				pregunta2="¿Cómo se mide y se analiza el retorno de la inversión (ROI) de las campañas de marketing digital?";
				pregunta3="¿Cómo se mantienen actualizadas las estrategias de marketing digital en respuesta a los cambios en las tendencias de búsqueda y las preferencias de los consumidores?";
			}
			else if(empleado.getCargo().equals("Especialista en Comunicaciones de Marketing")) {
				pregunta1="¿Cómo se garantiza la coherencia en la comunicación de la marca y los mensajes de marketing en todos los canales?";
				pregunta2="¿Cómo se evalúa la efectividad de las estrategias de comunicación de marketing en la generación de leads y en el aumento de la conciencia de la marca?";
				pregunta3="¿Cómo se adaptan los mensajes de marketing a diferentes segmentos de la audiencia y cómo se evalúa la efectividad de esta personalización?";
			}
			else if(empleado.getCargo().equals("Analista de Mercado")) {
				pregunta1="¿Cómo se recopilan y se analizan los datos de mercado para identificar oportunidades y amenazas potenciales?";
				pregunta2="¿Qué metodologías se utilizan para evaluar la demanda del mercado y las preferencias del consumidor?";
				pregunta3="¿Cómo se utilizan los hallazgos del análisis de mercado para ajustar y mejorar las estrategias de marketing existentes?";
			}

			
			model.addAttribute("pregunta1", pregunta1);
			model.addAttribute("pregunta2", pregunta2);
			model.addAttribute("pregunta3", pregunta3);
			
			
			return "formActualizarEntrevista";
		}
		
		@GetMapping("/entrevista/eliminar/{id}/{codigo}")
		public String eliminar(Model model, @PathVariable int id,@PathVariable int codigo) {
			entrevistaService.eliminar(id);
			return "redirect:/entrevista/"+codigo; 
		}
		
		
		
}
