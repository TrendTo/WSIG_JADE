package com.comunicacion.openMRS.services.agents;


import java.util.Date;

import com.comunicacion.openMRS.Beans.Atiende;
import com.comunicacion.openMRS.Beans.MsjHl7;
import com.comunicacion.openMRS.Beans.Proveedores;
import com.comunicacion.openMRS.Beans.Roles;
import com.comunicacion.openMRS.Beans.Usuarios;
import com.comunicacion.openMRS.entidades.AgentInforBean;
import com.comunicacion.openMRS.entidades.ConsultaHl7Bean;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;
import jade.content.schema.TermSchema;
import jade.core.AID;
import jade.util.leap.List;


public class RecuperaInformacionOntology extends Ontology implements RecuperaInformacionVocabulary {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Ontology theInstance = new RecuperaInformacionOntology();
	
	public final static Ontology getInstance() {
		return theInstance;
	}
	public RecuperaInformacionOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());
		
		// TODO Auto-generated constructor stub
		try {
			add(new AgentActionSchema(GETAGENTUSUARIOS), GetAgentUsuarios.class); 
			add(new AgentActionSchema(GETAGENTINFOR), GetAgentInfor.class);
			add(new AgentActionSchema(GETAGENTPROVEEDORES), GetAgentProveedor.class);
			add(new AgentActionSchema(GETAGENTATIENDE), GetAgentAtiende.class);
			add(new AgentActionSchema(GETAGENTMSJHL7), GetAgentVersionHL7.class);
			
			
			
			add(new ConceptSchema(USUARIOS), Usuarios.class);
			add(new ConceptSchema(AGENTINFO), AgentInforBean.class);
			add(new ConceptSchema(PROVEEDORES), Proveedores.class);
			add(new ConceptSchema(ATIENDE), Atiende.class);
			add(new ConceptSchema(MSJHL7), ConsultaHl7Bean.class);
			
			
						
			AgentActionSchema as = (AgentActionSchema) getSchema(GETAGENTUSUARIOS);
		    as.add(USUARIOID, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
			as.setResult((ConceptSchema)getSchema(USUARIOS));
			
			as = (AgentActionSchema) getSchema(GETAGENTINFOR);
			as.add(IMEI, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
			as.setResult((ConceptSchema)getSchema(AGENTINFO));
			
			as = (AgentActionSchema) getSchema(GETAGENTPROVEEDORES);
		    as.add(PROVEEDRORID, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
			as.setResult((ConceptSchema)getSchema(PROVEEDORES));
			
			as = (AgentActionSchema) getSchema(GETAGENTATIENDE);
		    as.add(ATIENDEID, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
			as.setResult((ConceptSchema)getSchema(ATIENDE));
			
			
			as = (AgentActionSchema) getSchema(GETAGENTMSJHL7);
		    as.add(MSJTX, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
			as.setResult((ConceptSchema)getSchema(MSJHL7));
			
					
			ConceptSchema cs = (ConceptSchema) getSchema(USUARIOS);
			cs.add(PERSON_ID, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(IDENTIFIER, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(GIVEN_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(FAMILY_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(VALUE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
			cs = (ConceptSchema) getSchema(MSJHL7);
			cs.add(AGENTAID, (TermSchema) getSchema(BasicOntology.AID));
			cs.add(STARTDATE, (PrimitiveSchema) getSchema(BasicOntology.DATE));
			cs.add(VERSIONHL7, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(ENCODIGHL7, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(CONFIGURACIONHL7, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
	
			
			
			cs = (ConceptSchema) getSchema(AGENTINFO);
			cs.add(AGENTAID, (TermSchema) getSchema(BasicOntology.AID));
			cs.add(STARTDATE, (PrimitiveSchema) getSchema(BasicOntology.DATE));
			cs.add(NOMBREEQUIPO, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
			
			cs = (ConceptSchema) getSchema(PROVEEDORES);
			cs.add(PERSON_ID_1, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(GIVEN_NAME_1, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(FAMILY_NAME_1, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(USERNAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
			cs = (ConceptSchema) getSchema(ATIENDE);
			cs.add(ENCOUNTER_ID, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(IDENTIFIER_1, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(PERSON_ID_2, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(GIVEN_NAME_2, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(FAMILY_NAME_2, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
		
			
			
			
		}catch (OntologyException oe) {
			oe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
