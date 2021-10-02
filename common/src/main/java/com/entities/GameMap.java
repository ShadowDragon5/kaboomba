package com.entities;

import com.core.State;
import com.utils.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;

public class GameMap {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public GameMap() {
    }

    public void loadMap(String filePath, State state) {
        var dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(filePath);
            Document doc = db.parse(file);

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
            ArrayList<String> gids = new ArrayList<>();

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    gids.add(element.getAttribute("gid"));
                }
            }

            Creator creator = new TileCreator();
            double dimension = Math.sqrt(gids.size());
            float dd = 2f/(float)dimension;
            float x = -1.05f;
            float y = 0.95f;
            for(int i = 0; i<gids.size(); i++){
                x += dd;

                if((i%dimension == 0 && i!=0)){
                    x = -0.95f;
                    y -= dd;
                }

                Position position = new Position(x,y);
                Tile tile = creator.createFactory(gids.get(i), position, (float)dimension);
                gameObjects.add(tile);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
