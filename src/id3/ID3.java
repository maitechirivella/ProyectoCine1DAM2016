/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id3;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evaristo
 */
public class ID3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File directorio= new File("c:\\Funambulista");        
        DataInputStream leer = null;
        PrintWriter escribir = null;
        ArrayList <File> archivos = new ArrayList<>();
        ArrayList <File> directorios = new ArrayList<>();
        ArrayList <Cancion> biblioteca = new ArrayList<>();
        String datasong;
        String palabra;
        String dir;
        Cancion song = new Cancion();
         int opcion = 1;
        Scanner teclado = new Scanner(System.in);
        
        do {
            muestraMenu();
            System.out.println("Introduce una opción:");
            opcion = teclado.nextInt();
            switch(opcion){
                case 1:{
                    if(directorio.exists()){
                        File[] ficheros = directorio.listFiles();
                        cargaFiles(archivos, directorios, ficheros);
                    }else
                        System.out.println("El fichero o directorio no existe");

                    try {
                        for (int i = 0; i < archivos.size(); i++) {
                            song = new Cancion();
                            leer = new  DataInputStream(new FileInputStream(archivos.get(i)));
                            leer.skip(archivos.get(i).length()-125);
                            datasong =  leer.readLine();
                            dir = archivos.get(i).getParent();
                            song.rellenaCancion(datasong,dir);                                          
                            biblioteca.add(song);                           
                        }   

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ID3.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ID3.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        leer.close();
                    }        
                    escribir= new PrintWriter("catalogo.csv");
                    imprimirCsv(escribir, biblioteca);
                    System.out.println();
                    System.out.println("El catálogo se ha cargado con éxito");
                    break;
                }
                case 2:{
                    System.out.println("Introduce una palabra para ver las canciones que la contengan");
                    palabra = teclado.nextLine();
                    palabra = teclado.nextLine();
                    for (int i = 0; i < biblioteca.size(); i++) {
                        biblioteca.get(i).buscaEnCancion(palabra);
                    }
                     
                    break;
                }
                case 3:{
                    for (int i = 0; i < biblioteca.size(); i++) {
                        biblioteca.get(i).imprime();
                    }
                    break;
                }
                case 4:{
                    System.out.println("Ha decidido salir del programa");
                    break;
                }                              
                default:{
                    System.out.println("La opción introducida no es válida");
                    break;
                }   
            }
         }while (opcion != 4);
 
    }
    
    public static void muestraMenu(){
        System.out.println("1.- Leer directorio");
        System.out.println("2.- Buscar canciones");
        System.out.println("3.- Mostrar catalogo");
        System.out.println("4.- Salir");                
    }
    
    public static void imprimirCsv(PrintWriter pw,ArrayList<Cancion> canciones){
        
        for (int i = 0; i < canciones.size(); i++) {            
            canciones.get(i).imprimeCsv(pw);
        }
        
    }
    
    public static void cargaFiles(ArrayList<File> canciones,ArrayList<File> directorios,File [] files){
        File [] subfiles = null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if (files[i].getName().endsWith(".mp3")) {
                    canciones.add(files[i]);
                }
            }else                
                subfiles = files[i].listFiles();
                if(subfiles!= null){
                    cargaFiles(canciones, directorios, subfiles);
                }
        }
    }
    
}
