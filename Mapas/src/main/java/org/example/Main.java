package org.example;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import javax.swing.*;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import javax.swing.event.MouseInputListener;

public class Main {
    public static void main(String[] args) {
       TileFactoryInfo info = new TileFactoryInfo(
               0, 15, 17, 256,
               true, true,
               "http://ecn.t0.tiles.virtualearth.net/tiles/h",
               "it", "g", "m"
       ){
           @Override
           public String getTileUrl(int x, int y, int zoom){
               int z = 17 - zoom;
               return this.baseURL + quadKey(x,y,z) + ".jpeg?g=90";
           }

           private String quadKey(int x, int y, int z){
               StringBuilder key = new StringBuilder();
               for (int i = z; i > 0; i--){
                   int digit = 0;
                   int mask = 1 << (i -1);
                   if ((x & mask) != 0) digit++;
                   if ((y & mask) != 0) digit += 2;
                   key.append(digit);
               }
               return key.toString();
           }
       };

       DefaultTileFactory tileFactory = new DefaultTileFactory(info);

       JXMapViewer mapa = new JXMapViewer();
       mapa.setTileFactory(tileFactory);

       GeoPosition sp = new GeoPosition(-23.5500, -46.6333);
       mapa.setZoom(6);
       mapa.setAddressLocation(sp);

       // -- mexer e balançar com o mouse, dance amigo, dance --
        MouseInputListener mil = new PanMouseInputListener(mapa);
        mapa.addMouseListener(mil);
        mapa.addMouseMotionListener(mil);

        // Aqui é o zoom, zoom iinnn, zoom ouuut
        mapa.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapa));

        // Minha janelona braba
        JFrame frame = new JFrame("Marques's Map");

        frame.getContentPane().add(mapa);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}