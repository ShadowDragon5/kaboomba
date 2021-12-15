package com.entities;

import com.core.Defaults;
import com.core.State;
import com.entities.portals.*;
import com.entities.tiles.Tile;
import com.factories.tile.DefaultTileCreator;
import com.factories.tile.TileCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.xml.parsers.*;

public class GameMap {
    private final ArrayList<GameObject> mapTiles = new ArrayList<>();
    private final ArrayList<Rectangle> spawnPoints = new ArrayList<>();

	private int mapWidth;
	private int mapHeight;
	private int tileHeight;
	private int tileWidth;

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

            mapWidth = Integer.parseInt(mapElement.getAttribute("width"));
            mapHeight = Integer.parseInt(mapElement.getAttribute("height"));

            tileWidth = Integer.parseInt(mapElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(mapElement.getAttribute("tileheight"));

            Defaults.setDimension(tileWidth);

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

            // have to stay as local variables
            NodeList data = doc.getElementsByTagName("data");
            TileCreator creator = new DefaultTileCreator();

            // Layer 0 - background
            readTileLayer(0, data, creator, obj -> mapTiles.add(obj));

            // Layer 1 - boxes
            readTileLayer(1, data, creator, obj -> State.getInstance().addBox(obj));

            // Portal objects
            HashMap<Integer, WaypointPortal> portalLinks = new HashMap<>();
            NodeList objects = doc.getElementsByTagName("objectgroup");

            NodeList portals = objects.item(0).getChildNodes();

            for (int i = 0; i < portals.getLength(); i++) {
                Node node = portals.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;
                String gid = element.getAttribute("gid");
                int portal_x = Integer.parseInt(element.getAttribute("x"));
                int portal_y = Integer.parseInt(element.getAttribute("y"));

                Tile portal = creator.createTile(gid,
                            new Rectangle(portal_x, portal_y - tileHeight, tileWidth, tileHeight));
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
                                    other.setLinkedPortalRectangle(portal.getRectangle());
                                    ((WaypointPortal)portal).setLinkedPortalRectangle(other.getRectangle());
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

            NodeList spawns = objects.item(1).getChildNodes();

            for (int i = 0; i < spawns.getLength(); i++) {
                Node node = spawns.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element element = (Element) node;
                float spawn_x = Float.parseFloat(element.getAttribute("x"));
                float spawn_y = Float.parseFloat(element.getAttribute("y"));

                spawnPoints.add(new Rectangle(spawn_x, spawn_y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void readTileLayer(int layerNr, NodeList data, TileCreator creator, Consumer<GameObject> result) {

		NodeList layer = ((Element)data.item(layerNr)).getElementsByTagName("tile");

        int i;
		for (i = 0; i < layer.getLength(); i++) {
		    Node node = layer.item(i);
		    if (node.getNodeType() != Node.ELEMENT_NODE)
		        continue;

		    Element element = (Element) node;
            String gid = element.getAttribute("gid");

            if (!gid.equals("")) {
                Tile tile = creator.createTile(
                    gid,
                    new Rectangle((i % mapWidth) * tileWidth, (i / mapHeight) * tileHeight,
                        tileWidth, tileHeight)
                );
                result.accept(tile);
            }
		}
	}

    public int getMapWidth() {
        return mapWidth * tileWidth;
    }

    public ArrayList<GameObject> getGameObjects() {
        return mapTiles;
    }

    public ArrayList<Rectangle> getSpawnPoints() {
        return spawnPoints;
    }
}
