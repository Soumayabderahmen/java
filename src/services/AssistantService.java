/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Assistant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class AssistantService implements IService<Assistant>{
Connection cnx;

    public AssistantService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
public void ajouter(Assistant t) throws SQLException {
 // Ajout de l'utilisateur
        String req = "INSERT INTO user(email, roles, password, nom, prenom, cin, sexe, telephone, gouvernorat, adresse, confirm_password, Type,image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getEmail());
        pst.setString(2, String.join(",", t.getRoles()));
        pst.setString(3, t.getPassword());
        pst.setString(4, t.getNom());
        pst.setString(5, t.getPrenom());
        pst.setInt(6, t.getCin());
        pst.setString(7, t.getSexe());
        pst.setString(8, t.getTelephone());
        pst.setString(9, t.getGouvernorat());
        pst.setString(10, t.getAdresse());
        pst.setString(11, t.getConfirm_password());
         pst.setString(12,"assistant");
        pst.setString(13, t.getImage());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
    if (rs.next()) {
        t.setId(rs.getInt(1));
    }

    String req2 = "INSERT INTO assistant(id, medecin_id) VALUES(?, ?)";
    PreparedStatement pst2 = cnx.prepareStatement(req2);
    pst2.setInt(1, t.getId());
    pst2.setInt(2, t.getMedecin().getId());
    pst2.executeUpdate();
    }

  
  

   
    



    @Override
    public void modifier(Assistant t) throws SQLException {
     String req = "UPDATE user SET email=?, password=?, nom=?, prenom=?, cin=?, sexe=?, telephone=?, gouvernorat=?, adresse=?, confirm_password=?, image=? WHERE id="+t.getId();
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, t.getEmail());
        pst.setString(2, t.getPassword());
        pst.setString(3, t.getNom());
        pst.setString(4, t.getPrenom());
        pst.setInt(5, t.getCin());
        pst.setString(6, t.getSexe());
        pst.setString(7, t.getTelephone());
        pst.setString(8, t.getGouvernorat());
        pst.setString(9, t.getAdresse());
        pst.setString(10, t.getConfirm_password());
        pst.setString(11, t.getImage());
        
       
        pst.executeUpdate();
        
            
    }

    @Override
    public void supprimer(Assistant t) throws SQLException {
       String req = "DELETE FROM user WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setInt(1, t.getId());
    pst.executeUpdate();
    }

@Override
public List<Assistant> recuperer() throws SQLException{
String req = "SELECT * FROM user u JOIN assistant a ON u.id = a.id";
    PreparedStatement pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();

    List<Assistant> assistants = new ArrayList<>();

    while (rs.next()) {
        Assistant assistant = new Assistant();
        assistant.setId(rs.getInt("id"));
        assistant.setEmail(rs.getString("email"));
        //List<Medecin> assistants = new ArrayList<Medecin>(Arrays.asList(tab));
        assistant.setPassword(rs.getString("password"));
        assistant.setNom(rs.getString("nom"));
        assistant.setPrenom(rs.getString("prenom"));
        assistant.setCin(rs.getInt("cin"));
        assistant.setSexe(rs.getString("sexe"));
        assistant.setTelephone(rs.getString("telephone"));
        assistant.setGouvernorat(rs.getString("gouvernorat"));
        assistant.setAdresse(rs.getString("adresse"));
        assistant.setConfirm_password(rs.getString("confirm_password"));
        assistant.setImage(rs.getString("image"));
     
 String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        assistant.setRoles(roles);
        assistants.add(assistant);
    }

    return assistants;

    }



   public Assistant recupererById(int id) throws SQLException {
    String req = "SELECT * FROM user u JOIN assistant p ON u.id = p.id WHERE u.id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    Assistant assistant = new Assistant();
    if (rs.next()) {
        assistant.setId(rs.getInt("id"));
        assistant.setEmail(rs.getString("email"));
        assistant.setPassword(rs.getString("password"));
        String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        assistant.setRoles(roles);
        assistant.setNom(rs.getString("nom"));
        assistant.setPrenom(rs.getString("prenom"));
        assistant.setCin(rs.getInt("cin"));
        assistant.setSexe(rs.getString("sexe"));
        assistant.setTelephone(rs.getString("telephone"));
        assistant.setGouvernorat(rs.getString("gouvernorat"));
        assistant.setAdresse(rs.getString("adresse"));
        assistant.setConfirm_password(rs.getString("confirm_password"));
        assistant.setImage(rs.getString("image"));
        
    }
    return assistant;
    }
   
}
