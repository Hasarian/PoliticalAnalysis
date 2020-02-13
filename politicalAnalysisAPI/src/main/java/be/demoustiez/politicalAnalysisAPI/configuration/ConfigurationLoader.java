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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ConfigurationLoader {
    private static final String publicationTypeFile="publicationTypes.xml";
    private static final String circonscriptionsFile="circonscriptions.xml";
    private static final String urlFile="urls.xml";


    private HashMap<String,String> publicationTypes=new HashMap<>();
    private List<String> circonsciptions= new ArrayList<>();
    private HashMap<String,String> urls = new HashMap<>();

    public ConfigurationLoader(){
        loadResource(circonscriptionsFile,"circonscription",this::addCircToList);
        loadResource(publicationTypeFile,"type",this::putTypeInHashmap);
        loadResource(urlFile,"url",this::putURLInHashMap);
    }

    public void loadResource(String filename,String nodeName,Consumer<Element> action){
        Document xmlConfiguration = getStAXParsedDocument(filename);
        Element rootNode = xmlConfiguration.getRootElement();
        rootNode.getChildren(nodeName).forEach(action);
    }
    private Document getStAXParsedDocument(String filename){
        Document document= null;
        try{
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLEventReader reader = factory.createXMLEventReader(new FileReader(be.demoustiez.politicalAnalysisAPI.configuration.Constants.RESOURCES_PATH+filename));
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
    private void addCircToList(Element circ){
        circonsciptions.add(circ.getText());
    }
    private void putURLInHashMap(Element type){
        urls.put(type.getAttributeValue("code"),type.getText());
    }
    public HashMap<String, String> getPublicationTypes() {
        return publicationTypes;
    }
    public List<String> getCirconsciptions(){
        return this.circonsciptions;
    }
    public String getURL(String code){
        if(!urls.containsKey(code)) return  null;
        return urls.get("baseURL")+urls.get(code);
    }

}
