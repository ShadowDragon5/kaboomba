package com.entities;

import com.core.State;
import com.utils.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.xml.parsers.*;

public class GameMap {
    private GameObject[] gameObjects;

    public GameMap() {
    }

    public void loadMap(String filePath, State state) {
        var dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));

            doc.getDocumentElement().normalize();

            NodeList tileset = ((Element)doc.getElementsByTagName("tileset").item(0)).getElementsByTagName("image");
            for (int i = 0; i < tileset.getLength(); i++) {
                Node node = tileset.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println(element.getAttribute("source"));
                    // i+1 == gid
                }
            }

            // Layer 0
            NodeList list = ((Element)doc.getElementsByTagName("data").item(0)).getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // TileCreator tc;
                    // tile = tc.createTile(element.getAttribute("gid"));
                    // gameObjects.append(tile);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
