package task2;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class ValidationXML {
	
	private boolean flag =  true;
	

	public ValidationXML() {	
	}
	
	public void validate(String xmlFile, String validationFile) throws SAXException, IOException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		((schemaFactory.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource(new File(xmlFile)));
	}
}
