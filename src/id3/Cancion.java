/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id3;

import java.io.PrintWriter;

/**
 *
 * @author Evaristo
 */
public class Cancion {
    String directori;
    String titulo;
    String artista;
    String album;
    String anyo;
    String comentario;
    String genero;

    public Cancion() {
    }

    public String getDirectori() {
        return directori;
    }

    public void setDirectori(String directori) {
        this.directori = directori;
    }
    
    
    public void rellenaCancion(String cancion, String dir){
        this.directori = dir;
        this.titulo = cancion.substring(0, 29);
        this.artista = cancion.substring(30, 59);
        this.album = cancion.substring(60, 89);
        this.anyo = cancion.substring(90, 94);
        this.comentario = cancion.substring(95, 122);
        this.genero = cancion.substring(123);
    }
    
    public void imprime(){
        System.out.println("Ubicación: "+this.getDirectori());
        System.out.println("Título: "+this.getTitulo());
        System.out.println("Autor: "+this.getArtista());
        System.out.println("Álbum: "+this.getAlbum());
        System.out.println("Año: "+this.getAnyo());
        System.out.println("Comentarios: "+this.getComentario());
        System.out.println("Género: "+this.getGenero());
        System.out.println();
    }
    
    public void imprimeCsv(PrintWriter escribe){
        escribe.print(this.getDirectori()+";");
        escribe.print(this.getTitulo()+";");
        escribe.print(this.getArtista()+";");
        escribe.print(this.getAlbum()+";");
        escribe.print(this.getAnyo()+";");
        escribe.print(this.getComentario()+",");
        escribe.print(this.getGenero()+";");
        escribe.println();
    }
    public void buscaEnCancion(String buscada){
        if (this.getDirectori().contains(buscada)||this.getTitulo().contains(buscada)||this.getArtista().contains(buscada)||this.getAlbum().contains(buscada)||this.getAnyo().contains(buscada)||this.getComentario().contains(buscada)) {
            this.imprime();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}
