package be.demoustiez.politicalAnalysisAPI.configuration;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.StAXEventBuilder;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigurationLoader {
    private static final String publicationTypeFile="publicationTypes.xml";


    private HashMap<String,String> publicationTypes=new HashMap<>();

    public void loadPublicationTypes(){
        Document xmlConfiguration = getStAXParsedDocument();
        Element rootNode = xmlConfiguration.getRootElement();
        rootNode.getChildren("type").forEach(this::putTypeInHashmap);
    }

    private Document getStAXParsedDocument(){
        Document document= null;
        try{
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLEventReader reader = factory.createXMLEventReader(new FileReader(main.java.be.demoustiez.politicalAnalysisAPI.configuration.Constants.RESOURCES_PATH+publicationTypeFile));
            StAXEventBuilder builder = new StAXEventBuilder();
            document = builder.build(reader);
        }catch (JDOMException | IOException | XMLStreamException e){
            e.printStackTrace();
        }
        return document;
    }
    private void putTypeInHashmap(Element type){
        publicationTypes.put(type.getAttributeValue("code"),type.getText());
    }

    public HashMap<String, String> getPublicationTypes() {
        return publicationTypes;
    }
}
