package com.entities;

import com.core.ArithmeticActions;
import com.core.Globals;
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
    private ArrayList<GameObject> mapTiles = new ArrayList<>();
    private State state = State.getInstance();

    public GameMap() {
    }

    public void loadMap(String filePath) {
        var dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(filePath);
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            Element mapElement = (Element)doc.getElementsByTagName("map").item(0);

            // map dimensions in tiles
            int mapWidth = Integer.parseInt(mapElement.getAttribute("width"));
            int mapHeight = Integer.parseInt(mapElement.getAttribute("height"));

            int tileWidth = Integer.parseInt(mapElement.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(mapElement.getAttribute("tileheight"));

            Globals.setDefaultDimension(0.1f);

            // System.out.println(tileWidth + "x" + tileHeight);
            // System.out.println(mapWidth + "x" + mapHeight);

            NodeList tileset =
                ((Element)doc.getElementsByTagName("tileset").item(0)).getElementsByTagName("image");
            for (int i = 0; i < tileset.getLength(); i++) {
                Node node = tileset.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;
                // System.out.println(element.getAttribute("source"));
                // i+1 == gid
            }

            NodeList data = doc.getElementsByTagName("data");

            TileCreator creator = new DefaultTileCreator();

            // Layer 0 - background
            NodeList layer0 = ((Element)data.item(0)).getChildNodes();

            final float x = (1 - mapWidth);
            final float y = (mapHeight - 1);
            int c = 0;

            // TODO fix loop to use i as counter
            for (int i = 0; i < layer0.getLength(); i++) {
                Node node = layer0.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;

                Tile tile = creator.createTile(
                        element.getAttribute("gid"),
                        new Position(
                            UtilityMethods.preciseArithmetics(x + (c % mapWidth) * 2,
                                mapWidth, ArithmeticActions.DIV),
                            UtilityMethods.preciseArithmetics(y - (c / mapHeight) * 2,
                                mapHeight, ArithmeticActions.DIV)),
                        // tileWidth
                        UtilityMethods.preciseArithmetics(2f,
                            mapWidth, ArithmeticActions.DIV)
                    );
                mapTiles.add(tile);
                c++;
            }

            // Layer 1 - boxes
            NodeList layer1 = ((Element)data.item(1)).getChildNodes();
            c = 0;
            for (int i = 0; i < layer1.getLength(); i++) {
                Node node = layer1.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;
                String gid = element.getAttribute("gid");

                if (!gid.equals("")) {
                    Tile tile = creator.createTile(
                            gid,
                            // new Position(x + (c % mapWidth) * tileWidth, y + (c / mapHeight) * tileHeight),
                            new Position(
                                UtilityMethods.preciseArithmetics(x + (c % mapWidth) * 2,
                                    mapWidth, ArithmeticActions.DIV),
                                UtilityMethods.preciseArithmetics(y - (c / mapHeight) * 2,
                                    mapHeight, ArithmeticActions.DIV)),
                            UtilityMethods.preciseArithmetics(2f,
                                mapWidth, ArithmeticActions.DIV)
                        );
                    state.addBox(tile);
                }
                c++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return mapTiles;
    }
}
