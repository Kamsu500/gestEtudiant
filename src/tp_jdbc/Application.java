/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_jdbc;

import Model.Etudiant;
import Services.DAOService;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author junior
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DAOService ds = new DAOService();
        Scanner input = new Scanner(System.in);
        int res;
        do{
            int choix;
            System.out.println("#$#$#$#$#$#$#$\t\tSystem de gestion des etudiants\t\t#$#$#$#$#$#$#$#$#$");
            System.out.println("\n\nMENU"
                    + "\n1- Nouvel etudiant"
                    + "\n2- Tous les etudiants"
                    + "\n3- rechercher un etudiant"
                    + "\n4- Modifier un etudiant"
                    + "\n5- Supprimer un etudiant"
                    + "\n6- Nombre d'etudiants total");
            choix = input.nextInt();
            do{
                switch(choix){
                    case 1:
                        String mat, nom, prenom;
                        System.out.print("Matricule: ");
                        mat = input.next();
                        System.out.print("Nom: ");
                        nom = input.next();  
                        System.out.print("Prenom: ");
                        prenom = input.next();                      
                        
                        ds.add(mat, nom, prenom);
                        
                        break;
                    case 2:
                        ArrayList<Etudiant> data = ds.getEtudiants();
                        for(int i=0;i<data.size()-1;i++){
                            System.out.println(
                                    "Matricule: "+data.get(i).getNcin()+
                                    " Nom: "+ data.get(i).getNom()+
                                    " Prenom: "+ data.get(i).getPrenom()
                            );
                        }
                        break;
                    case 3:
                        
                        System.out.print("Entrez le matricule de l'etudiant concerné: ");
                        mat = input.next();
                        
                        Etudiant search = ds.find(mat);
                        System.out.println(
                                "Matricule: "+search.getNcin()+
                                " Nom: "+ search.getNom()+
                                " Prenom: "+ search.getPrenom()
                        );
                        break;
                    case 4:
                        System.out.print("Matricule de l'etudiant a modifié: ");
                        mat = input.next();
                        System.out.print("Nouveau Nom: ");
                        nom = input.next();  
                        System.out.print("Nouveau Prenom: ");
                        prenom = input.next();
                        ds.update(mat, nom, prenom);
                        break;
                    case 5:
                        System.out.print("Matricule de l'etudiant a supprimé: ");
                        mat = input.next();
                        ds.remove(mat);
                        break;
                    case 6:
                        System.out.println("Le nombre d'etudiant total est de "+ds.count() + " etudiants");
                        break;
                }
            }while(choix < 1 || choix > 6);
            System.out.println("\nVoulez-vous continuer?"
                    + "\n1- Oui"
                    + "\n2- Non");
            res = input.nextInt();
        }while(res == 1);
        ds.close();
    }
    
}
