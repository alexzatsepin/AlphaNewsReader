package alphanews.newsapp.engine.alpha;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import alphanews.newsapp.domain.alpha.News;
import alphanews.newsapp.engine.Parser;

/**
 * Created by Zatsepin on 14.08.2014.
 */
class AlphaXmlDomParser implements Parser {
    @Override
    public List<News> parse(String rawData) throws Exception {

        List<News> result = new ArrayList<News>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new ByteArrayInputStream(rawData.getBytes("windows-1251"))));

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element && node.getNodeName().equals("channel")) {
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    News news = new News();
                    Node child = childNodes.item(j);
                    if (child instanceof Element && child.getNodeName().equals("item")) {
                        NodeList newsChilds = child.getChildNodes();
                        for (int g = 0; g < newsChilds.getLength(); g++) {
                            Node doubleChild = newsChilds.item(g);
                            String content = doubleChild.getLastChild().getTextContent().trim();
                            if (doubleChild.getNodeName().equals("title")) {
                                news.setTitle(content);
                            }
                        }
                    }
                    result.add(news);
                }
            }
        }
        return result;
    }
}
