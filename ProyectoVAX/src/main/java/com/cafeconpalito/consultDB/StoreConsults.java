/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeconpalito.consultDB;

import com.cafeconpalito.controllers.GameInfoController;
import com.cafeconpalito.entities.Biblioteca;
import com.cafeconpalito.staticElements.ConectionBBDD;
import com.cafeconpalito.entities.Juego;
import com.cafeconpalito.userLogedData.UserLogedInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Query;

/**
 *
 * @author produccion
 */
public class StoreConsults {

    private static ArrayList<GameInfoController> storeGames = new ArrayList<>();

    public static ArrayList<GameInfoController> getStoreGames() throws IOException {

        storeGames.clear();

        if (!UserLogedInfo.isUserIsLoged()) {
            Query q = ConectionBBDD.getEm().createNamedQuery("Juego.findAll");

            for (Juego j : (Collection<Juego>) q.getResultList()) {
                GameInfoController cg = new GameInfoController(j.getIdjuego(), j.getTitulo(), j.getNumdescargas() + "", j.getPrecio() + "", j.getImagen());
                storeGames.add(cg);
            }

            String imagenPruebas = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/FE0D3777-CA9C-4559-A163-7AEB639F39BA/Derivates/5D8A55BF-8D7A-4E9A-8171-7E7592C1665F.jpg";

            //BORRAR DATOS DE PRUEBA
            GameInfoController cg = new GameInfoController(1, "Papas Fritas", 555 + "", 777 + "", imagenPruebas);
            storeGames.add(cg);

            //mueve el orden de los juegos para que no siempre salgan en el mismo orden
            Collections.shuffle(storeGames);
            return storeGames;
        } else {
            Query q = ConectionBBDD.getEm().createNamedQuery("Juego.findAll");

            for (Juego j : (Collection<Juego>) q.getResultList()) {
                for (Biblioteca b : (Collection<Biblioteca>) j.getBibliotecaCollection()) {
                    if (b.getIdusuario().getIdusuario() != UserLogedInfo.getUserID()) {
                        GameInfoController cg = new GameInfoController(j.getIdjuego(), j.getTitulo(), j.getNumdescargas() + "", j.getPrecio() + "", j.getImagen());
                        storeGames.add(cg);
                    }
                }

            }
            Collections.shuffle(storeGames);
            return storeGames;
            /*
            ConectionBBDD.getEm();
            Query q = ConectionBBDD.getEm().createNativeQuery("SELECT j FROM Juego j LEFT JOIN Biblioteca b USING (idjuego) INNER JOIN Usuario u USING (idusuario) WHERE u.idusuario = :idusuario");

            //q.executeUpdate();
            for (Juego j : (Collection<Juego>) q.getResultList()) {
                GameInfoController cg = new GameInfoController(j.getIdjuego(), j.getTitulo(), j.getNumdescargas() + "", j.getPrecio() + "", j.getImagen());
                storeGames.add(cg);
            }
            
            Collections.shuffle(storeGames);
            return storeGames;
             */
        }

    }

    public static ArrayList<GameInfoController> filterStoreGames(String gameName, String gameGenre, String gamePrice) throws IOException {

        storeGames.clear();

        //"SELECT j FROM Juego j INNER JOIN juego_genero jg ON j.idjuego = jg.idjuego INNER JOIN genero g ON g.idgenero =jg.idgenero WHERE g.name LIKE ':genero' and j.titulo like ':titulo' AND j.precio<:precio";
        //BORRAR DATOS DE PRUEBA
        GameInfoController cg = new GameInfoController(1, "Papas Fritas", 555 + "", 777 + "", "data:image/jpeg;base64,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");
        storeGames.add(cg);

        //mueve el orden de los juegos para que no siempre salgan en el mismo orden
        Collections.shuffle(storeGames);
        return storeGames;

    }

}
