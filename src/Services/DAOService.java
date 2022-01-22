/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Etudiant;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author junior
 */
public class DAOService {
    private String path = "jdbc:mysql://localhost:3306/dbtest";
    private String user = "root";
    private String password = "";
    private Connection con = null;

    public DAOService() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(path, user, password);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public boolean estConnecte(){
        if(con != null){
            return true;
        }
        return false;
    }
    public void close(){
        if(estConnecte()){
           try{
                con.close();            
            }catch(SQLException e){
                e.printStackTrace();
            } 
        }
    }
    public ArrayList<Etudiant> getEtudiants(){
        ArrayList<Etudiant> tab = new ArrayList();
        try{
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM Etudiant");
            while(rs.next()){
                Etudiant ne = new Etudiant(
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );
                tab.add(ne);
            }
            st.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return tab;
    }   
    public boolean estExistant(String matricule){
        ResultSet rs = null;
        boolean a = false;
        try{
            String sql = "SELECT * FROM etudiant WHERE matricule = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, matricule);
            
            rs = ps.executeQuery();
            
            a = rs.next();
        }catch(SQLException e){ 
            e.printStackTrace();
        }
        return a;
    }
    
    public void add(String matricule, String nom, String prenom){
        if(!estExistant(matricule)){
            try{
                String sql = "INSERT INTO ETUDIANT VALUES(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,matricule);
                ps.setString(2, nom);
                ps.setString(3, prenom);

                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("Cet utilisateur existe deja !!");
        }
            
    }
    
    public void update(String matricule, String nom, String prenom){
        if(estExistant(matricule)){
            try{
                String sql = "UPDATE Etudiant SET nom = ?, prenom = ? where matricule = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, matricule);

                ps.executeUpdate();
                System.out.println("Etudiant modifié avec success");
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("Cet etudiant n'existe pas !!!");
        }
    }
    
    public void remove(String matricule){
        if(estExistant(matricule)){
            try{
                String sql = "DELETE FROM etudiant WHERE matricule = ? ";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, matricule);
                ps.executeUpdate();
                System.out.println("Supprimé avec success!!!");
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("Cet etudiant n'existe pas !!!");
        }
        
    }
    
    public String getData(String matricule){
        if(estExistant(matricule)){
            String result = "";
            try{
                String sql = "SELECT nom, prenom FROM etudiant WHERE matricule = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, matricule);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    result = result + rs.getString("nom") + " "+rs.getString("prenom");
                }
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return result;
        }else{
            return "Cet etudiant n'existe pas !!!";
             
        }
    }
    public Etudiant find(String matricule){
        
        if(estExistant(matricule)){
            Etudiant e = new Etudiant();
            try{
                String sql = "SELECT * FROM etudiant WHERE matricule = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, matricule);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    e.setNcin(rs.getString("matricule"));
                    e.setNom(rs.getString("nom"));
                    e.setPrenom(rs.getString("prenom"));
                }
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return e;
        }else{
            return null;
        }
        
    }
    public int count(){
        return getEtudiants().size();
    }
}
