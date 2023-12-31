/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeconpalito.consultDB;

import com.cafeconpalito.entities.Usuario;
import com.cafeconpalito.staticElements.ConectionBBDD;
import com.cafeconpalito.registerUserData.userRegisterInfo;
import com.google.common.io.Files;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author CafeConPalito
 */
public class RegisterConsults {
    
    /**
     * Consulta en la BBDD si el Email ya existe para poder registrar al nuevo usuario
     * @param email email para consultar en la BBDD
     * @return Devuelve True si el Email no esta registrado
     */
    public static boolean emailExists(String email){
        Query q = ConectionBBDD.getEm().createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", email);
        ArrayList<Usuario> l = (ArrayList<Usuario>) q.getResultList();
        
        if (!l.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Consulta en la BBDD si el Alias ya existe para poder registrar al nuevo usuario
     * @param nickName Alias para consultar en la BBDD
     * @return Devuelve True si el Alias no esta registrado
     */
    public static boolean nickNameExists(String nickName){
        Query q = ConectionBBDD.getEm().createNamedQuery("Usuario.findByAlias");
        q.setParameter("alias", nickName);
        ArrayList<Usuario> l = (ArrayList<Usuario>) q.getResultList();
        
        if (!l.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Inserta los datos de un nuevo usuario en la BBDD
     */
    public static void insercion() {
        //insercion
        EntityManager em = ConectionBBDD.getEm();
        
        Query insercion = em.createNativeQuery("INSERT INTO usuario (alias, email, pwd, nombre, apellido1, apellido2, fechanac, imagen, region, rol) VALUES (:alias, :email, :pwd, :nombre, :apellido1, :apellido2, :fechanac, :imagen, :region, :rol);");
        em.getTransaction().begin();
        
        insercion.setParameter("alias", "@" + userRegisterInfo.getNickname());
        insercion.setParameter("email", userRegisterInfo.getEmail());
        insercion.setParameter("pwd", userRegisterInfo.getPassword());
        insercion.setParameter("nombre", userRegisterInfo.getFirstName());
        insercion.setParameter("apellido1", userRegisterInfo.getFirstSurname());
        insercion.setParameter("apellido2", userRegisterInfo.getSecondSurname());
        insercion.setParameter("fechanac", userRegisterInfo.getBirthDate());
        insercion.setParameter("imagen", userRegisterInfo.getNickname().replaceAll("\\s+","") +"."+Files.getFileExtension(userRegisterInfo.getImage())); //quitamos los espacios para poder cargar la url de la imagen
        insercion.setParameter("region", userRegisterInfo.getRegionNumber());
        insercion.setParameter("rol", userRegisterInfo.getRolenumber());
        
        
        insercion.executeUpdate();
        em.clear();
        em.getTransaction().commit();
    }
}
