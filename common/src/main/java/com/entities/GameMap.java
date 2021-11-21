package com.entities;

import com.core.enums.ArithmeticActions;
import com.core.Defaults;
import com.core.State;
import com.entities.portals.*;
import com.entities.tiles.Tile;
import com.factories.tile.DefaultTileCreator;
import com.factories.tile.TileCreator;
import com.utils.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.*;

public class GameMap {
    private final ArrayList<GameObject> mapTiles = new ArrayList<>();

    private static GameMap instance = null;
    private GameMap() { }

    public static GameMap getInstance() {
        if(instance == null) {
            instance = new GameMap();
        }
        return instance;
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

            Defaults.setDimension(UtilityMethods.preciseArithmetics(2f, mapWidth, ArithmeticActions.DIV));

            // NodeList tileset =
            //     ((Element)doc.getElementsByTagName("tileset").item(0)).getElementsByTagName("image");
            //
            // for (int i = 0; i < tileset.getLength(); i++) {
            //     Node node = tileset.item(i);
            //     if (node.getNodeType() != Node.ELEMENT_NODE)
            //         continue;
            //
            //     Element element = (Element) node;
            //     // System.out.println(element.getAttribute("source"));
            //     // i+1 == gid
            // }

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
                        Defaults.getDimension()
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
                            Defaults.getDimension()
                        );
                    State.getInstance().addBox(tile);
                }
                c++;
            }

            // Portal objects
            HashMap<Integer, WaypointPortal> portalLinks = new HashMap<>();
            NodeList portals = doc.getElementsByTagName("object");

            for (int i = 0; i < portals.getLength(); i++) {
                Node node = portals.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;
                String gid = element.getAttribute("gid");
                int portal_x = Integer.parseInt(element.getAttribute("x"));
                int portal_y = Integer.parseInt(element.getAttribute("y"));

                int mapSize = tileWidth * mapWidth;
                Tile portal = creator.createTile(gid,
                            new Position(
                                UtilityMethods.preciseArithmetics(portal_x * 2 + tileWidth - mapSize,
                                    mapSize, ArithmeticActions.DIV),
                                UtilityMethods.preciseArithmetics(-portal_y * 2 + tileHeight + mapSize,
                                    mapSize, ArithmeticActions.DIV)).snap(),
                            Defaults.getDimension()
                        );
                State.getInstance().addPortal(portal);

                // portal linking
                if (element.hasChildNodes()) {
                    NodeList properties = element.getChildNodes().item(1).getChildNodes();

                    for (int j = 0; j < properties.getLength(); j++) {
                        Node nj = properties.item(j);
                        if (nj.getNodeType() != Node.ELEMENT_NODE)
                            continue;

                        Element e2 = (Element) nj;
                        String name = e2.getAttribute("name");

                        switch (name) {
                            case "portalID":
                                int id = Integer.parseInt(e2.getAttribute("value"));
                                if (portalLinks.containsKey(id)) {
                                    var other = portalLinks.get(id);
                                    other.setLinkedPortalPosition(portal.getPosition());
                                    ((WaypointPortal)portal).setLinkedPortalPosition(other.getPosition());
                                } else {
                                    portalLinks.put(id, (WaypointPortal) portal);
                                }
                                break;
                            case "effect":
                                int effect = Integer.parseInt(e2.getAttribute("value"));
                                ((Portal) portal).setPortalEffect(effect);
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return mapTiles;
    }
}
