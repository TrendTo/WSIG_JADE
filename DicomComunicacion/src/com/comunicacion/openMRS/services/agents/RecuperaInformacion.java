package com.comunicacion.openMRS.services.agents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Logger;
import jade.content.AgentAction;
import jade.content.ContentElement;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FIPAManagementOntology;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;


import com.comunicacion.openMRS.Beans.Atiende;
import com.comunicacion.openMRS.Beans.AtiendeCabecera;
import com.comunicacion.openMRS.Beans.ProveedorCabecera;
import com.comunicacion.openMRS.Beans.Proveedores;
import com.comunicacion.openMRS.Beans.UsuarioCabecera;
import com.comunicacion.openMRS.Beans.Usuarios;
import com.comunicacion.openMRS.controler.ConsultaAtiende;
import com.comunicacion.openMRS.controler.ConsultaPaciente;
import com.comunicacion.openMRS.controler.ConsultaProveedores;
import com.comunicacion.openMRS.entidades.AgentInforBean;
import com.comunicacion.openMRS.entidades.ConsultaHl7Bean;
import com.comunicacion.util.UtilComunicacion;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser; 

public class RecuperaInformacion extends Agent{
	public static final String WSIG_FLAG = "wsig";
	public static final String WSIG_MAPPER = "wsig-mapper";
	public static final String WSIG_PREFIX = "wsig-prefix";
	private java.util.List<UsuarioCabecera> listUsuarios = new java.util.ArrayList<UsuarioCabecera>();
	private java.util.List<ProveedorCabecera> listProveedores = new java.util.ArrayList<ProveedorCabecera>();
	private java.util.List<AtiendeCabecera> listAtiende = new java.util.ArrayList<AtiendeCabecera>();
	private Logger log = Logger.getLogger(RecuperaInformacion.class.getName());
	public static AID myAID = null;
	private SLCodec codec = new SLCodec();
	private Date startDate;
	private String resultadoHl7;

	protected void setup() {
		log.info("La Recuperacion Informacion is ejecutandoce.....");
		log.info("Nombre del agente: "+getLocalName());
		Object[] args = getArguments();
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(FIPAManagementOntology.getInstance());
		getContentManager().registerOntology(RecuperaInformacionOntology.getInstance());
				DFAgentDescription dfad = new DFAgentDescription();
				dfad.setName(this.getAID());
				dfad.addLanguages(codec.getName());
				dfad.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
				ServiceDescription sd;
				sd = new ServiceDescription();
				sd.addLanguages(codec.getName());
				sd.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
				sd.setType("RecuperaInformacion");
				sd.setOwnership("RecuperaInformacionOwner");
				sd.addOntologies(RecuperaInformacionOntology.getInstance().getName());
				// WSIG properties
				sd.addProperties(new Property(WSIG_FLAG, "true"));
				// Service name
				//Nombre del servicio WEB que se va mostarr 
				String wsigServiceName = "RecuperaInformacion"; 
				if (args.length >= 1) {
					wsigServiceName = (String)args[0];
				}
				log.info("Service name: "+wsigServiceName);
				sd.setName(wsigServiceName);
				// Mapper
				boolean isMapperPresent = false; 
				if (args.length >= 2) {
					isMapperPresent = Boolean.parseBoolean((String)args[1]);
				}
				log.info("Mapper present: "+isMapperPresent);
				if (isMapperPresent) {
					sd.addProperties(new Property(WSIG_MAPPER, "om.comunicacion.openMRS.services.agents.RecuperaInformacionOntologyMapper"));
				}
				// Prefix
				String wsigPrefix = ""; 
				if (args.length >= 3) {
					wsigPrefix = (String)args[2];
				}
				log.info("Prefix: "+wsigPrefix);
				if (wsigPrefix != null && !wsigPrefix.equals("")) {
					sd.addProperties(new Property(WSIG_PREFIX, wsigPrefix));
				}
				dfad.addServices(sd);
				// DF registration
				try {
					DFService.register(this, dfad);
				} catch (Exception e) {
					log.error("Problem during DF registration", e);
					doDelete();
				}

				log.debug("A ConsultaInformacion is started.");
				startDate = new Date();
				
				// Add math behaviour
				this.addBehaviour(new CyclicBehaviour(this) {
					private MessageTemplate template = MessageTemplate.MatchOntology(RecuperaInformacionOntology.getInstance().getName());
					
					public void action() {
						ACLMessage msg = myAgent.receive(template);
						if (msg != null) {
							Action actExpr;
							try {
								actExpr = (Action) myAgent.getContentManager().extractContent(msg);
								AgentAction action = (AgentAction) actExpr.getAction();
							
							if (action instanceof GetAgentInfor) {
								serveGetAgentInfoAction((GetAgentInfor) action, actExpr, msg);
									
							} else if (action instanceof GetAgentUsuarios) {
								serveGetAgentUsuarios((GetAgentUsuarios) action, actExpr, msg);
							
							} else if (action instanceof GetAgentProveedor) {
								serveGetAgentProveedor((GetAgentProveedor) action, actExpr, msg);
								
							} else if (action instanceof GetAgentAtiende) {
							   serveGetAgentAtiende((GetAgentAtiende) action, actExpr, msg);
									
							} else if (action instanceof GetAgentVersionHL7) {
								serveGetAgentVersionHL7((GetAgentVersionHL7) action, actExpr, msg);
								/*}else if (action instanceof GetComponents) {
								serveRecuparaImagen((GetComponents) action, actExpr, msg);
								
							}else if (action instanceof GetModulosDCM4CH) {
								serveRecuparaModulos((GetModulosDCM4CH) action, actExpr, msg);
								
							}else if (action instanceof ConsultaDicomBean) {
								serveRecuparaDroid((ConsultaDicomBean) action, actExpr, msg);
							*/}
								
							} catch (Exception e) {
								log.error("Exception: " + e.getMessage(), e);
							}
						} else {
							block();

					}
				}
	
				});
}

	 /*private void serveRecuperaAction(ConsultaBean sum, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveSumAction");
		String result = "Informacion"+sum.getFirstElement() + sum.getSecondElement()+sum.getThreeElement();
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	private void serveGetAgentRoles(GetAgentRoles getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveSumAction");
		String result = "Informacion";
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	private void serveGetAgentUsuarios(GetAgentUsuarios getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentUsuarios");
		String result = "Informacion";
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	private void serveGetAgentInfoAction(GetAgentInfor getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentInfoAction");
		String result = "Informacion";	
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	
	private void serveRecuparaModulos(GetModulosDCM4CH getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveRecuparaModulos");
		String result = "Informacion";		
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}

	private void serveRecuparaImagen(GetComponents getComponets, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentInfoAction");
		String result = "Informacion";		
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	*/
	
	private void serveGetAgentVersionHL7(GetAgentVersionHL7 getAgentInfo, Action actExpr, ACLMessage msg) {
	//log.debug("RecuperaInformacion.serveGetAgentVersionHL7");	
		ConsultaHl7Bean result = new ConsultaHl7Bean();	
		
		
		 File file = new File("/data/Hl7/"+getAgentInfo.getVersionHl7()+".txt");

	        FileInputStream fis = null;
	        String str = "";

	        try {
	            fis = new FileInputStream(file);
	            int content;
	            while ((content = fis.read()) != -1) {
	                // convert to char and display it
	                str += (char) content;
	            }

	            System.out.println("After reading file");
	            System.out.println(str);
	            resultadoHl7=str;

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (fis != null)
	                    fis.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    
	       
	

		PipeParser ourPipeParser = new PipeParser();
		Boolean resultvalor = ourPipeParser.getParserConfiguration().isAllowUnknownVersions();
		System.out.println("Version del Mensaje11111111" +ourPipeParser.getEncoding(resultadoHl7));  
		try {
			result.setVersion(ourPipeParser.getVersion(resultadoHl7));
			
			  Message hl7Message = ourPipeParser.parse(resultadoHl7);

		        // instantiate an XML parser
		        //HAPI provides 
		        XMLParser xmlParser = new DefaultXMLParser();

		      String  xmlMessageString = xmlParser.encode(hl7Message);
		      
		      System.out.println("Version del Mensaje11111111"+ xmlMessageString );
	       
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	
		
       
		
		result.setEncoding(ourPipeParser.getEncoding(resultadoHl7));
		result.setConfiguration(resultvalor.toString());
	
		
		
	
			
	        result.setAgentAid(getAID());
			result.setStartDate(startDate); 
			
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
		
	}
	
	
	private void serveGetAgentAtiende(GetAgentAtiende getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentAtiende");
		Atiende result = new Atiende();	
		ConsultaAtiende consulta = new ConsultaAtiende("1", getAgentInfo.getIdPacienteWeb());
		jade.util.leap.List listA = new jade.util.leap.ArrayList();
		listAtiende = consulta.getResultado();
		for(int i =0 ;i < listAtiende.size();i++){
					
					System.out.println(listAtiende.get(i).getPerson_id());
					
					result.setPerson_id(listAtiende.get(i).getPerson_id());
					result.setFamily_name(listAtiende.get(i).getFamily_name());
					result.setGiven_name(listAtiende.get(i).getGiven_name());
					result.setEncounter_id(listAtiende.get(i).getEncounter_id());
					result.setIdentifier(listAtiende.get(i).getIdentifier());

					
				}
				
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	
	private void serveGetAgentProveedor(GetAgentProveedor getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentUsuarios");
		Proveedores result = new Proveedores();	
		ConsultaProveedores consulta = new ConsultaProveedores("1", getAgentInfo.getIdProveedorWeb());
		jade.util.leap.List listA = new jade.util.leap.ArrayList();
		listProveedores = consulta.getResultado();
        for(int i =0 ;i < listProveedores.size();i++){
			
			System.out.println(listProveedores.get(i).getPerson_id());
			
			result.setPerson_id(listProveedores.get(i).getPerson_id());
			result.setFamily_name(listProveedores.get(i).getFamily_name());
			result.setGiven_name(listProveedores.get(i).getGiven_name());
			result.setName(listProveedores.get(i).getName());
			result.setUsername(listProveedores.get(i).getUsername());

			
		}
		
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);

		
		
	}
	
	
	private void serveGetAgentUsuarios(GetAgentUsuarios getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentUsuarios");
		Usuarios result = new Usuarios();	
		
		ConsultaPaciente consulta = new ConsultaPaciente("1", getAgentInfo.getIdUsuarioWeb());
		
		if(consulta.getResultado() == null) {

			result.setPerson_id("NA");
			result.setFamily_name("NA");
			result.setGiven_name("USUARIO NO EXISTE");
			result.setIdentifier("NA");
			result.setValue("NA");

			
		}else {
			
			jade.util.leap.List listA = new jade.util.leap.ArrayList();
			listUsuarios = consulta.getResultado();
			
			for(int i =0 ;i < listUsuarios.size();i++){
				
				System.out.println(listUsuarios.get(i).getPerson_id());
				result.setPerson_id(listUsuarios.get(i).getPerson_id());
				result.setFamily_name(listUsuarios.get(i).getFamily_name());
				result.setGiven_name(listUsuarios.get(i).getGiven_name());
				result.setIdentifier(listUsuarios.get(i).getIdentifier());
				result.setValue(listUsuarios.get(i).getValue());
			}
						
		}
			
		
		
		
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	private void serveGetAgentInfoAction(GetAgentInfor getAgentInfo, Action actExpr, ACLMessage msg) {
		log.debug("RecuperaInformacion.serveGetAgentInfoAction");
		AgentInforBean result = new AgentInforBean();	
		
		boolean imei = UtilComunicacion.esValidoIMEI(Long.valueOf(getAgentInfo.getImei()));
		if ( !imei ) {
		System.out.println("it's flase");
		result.setNombreEquipo("ERROR");	
		}
		else {
		System.out.println("it's true");
		result.setNombreEquipo("VALIDADO");	
		}
		result.setAgentAid(getAID());
		result.setStartDate(startDate);
		
		sendNotification(actExpr, msg, ACLMessage.INFORM, result);
	}
	
	
	
	private void sendNotification(Action actExpr, ACLMessage request, int performative, Object result) {
		// Send back a proper reply to the requester
		ACLMessage reply = request.createReply();
		if (performative == ACLMessage.INFORM) {
			reply.setPerformative(ACLMessage.INFORM);
			try {
				ContentElement ce = null;
				if (result != null) {
					// If the result is a java.util.List, convert it into a jade.util.leap.List t make the ontology "happy"
					if (result instanceof java.util.List) {
						ArrayList l = new ArrayList();
						l.fromList((java.util.List) result);
						result = l;
					}
					ce = new Result(actExpr, result);
				} else {
					ce = new Done(actExpr);
				}
				getContentManager().fillContent(reply, ce);
			}
			catch (Exception e) {
				log.error("Agent " + getName() + ": Unable to send notification" + e);
				e.printStackTrace();
			}
		} else {
			reply.setPerformative(performative);

		}
		reply.addUserDefinedParameter(ACLMessage.IGNORE_FAILURE, "true");
		send(reply);
	}
	
	protected void takeDown() {
		//deregister itself from the DF
		try {
			DFService.deregister(this);
		} catch (Exception e) {
			log.error(e);
		}

		log.debug("A MathAgent is taken down now.");
	}
}
