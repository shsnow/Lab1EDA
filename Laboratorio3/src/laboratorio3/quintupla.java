
package Laboratorio3;


public class quintupla implements Comparable
{
    private String categoria;
    private String nombre_producto;
    private int conteo;
    private float stars;
    private float precioMax;
    private int conteoPrecioMax;
    
    public quintupla()
    {
        categoria = new String();
        nombre_producto = new String();
        conteo = 0;
    }

    public quintupla(String c, String n)
    {
        categoria = c;
        nombre_producto = n;
        conteo = 1;
    }

    public quintupla(String c, String n, int x)
    {
        categoria = c;
        nombre_producto = n;
        conteo = x;
    }
    
    public quintupla(String c, String n, float star, float precio){
        categoria = c;
        nombre_producto = n;
        conteo = 1;
        stars = star;
        precioMax = precio;
    }

    public void set_categoria(String c)
    {
        categoria = c;
    }

    public String get_categoria()
    {
        return categoria;
    }

    public void set_producto(String p)
    {
        nombre_producto = p;
    }

    public String get_producto()
    {
        return nombre_producto;
    }

    public void incConteo()
    {
        conteo++;
    }

    public int get_conteo()
    {
        return conteo;
    }
    
    public float get_stars(){
        return stars;
    }
    
    public void set_stars(float s){
        stars = s;
    }
    
    public void newPromStar(float s){
        float aux = stars;
        
        stars = (aux*(conteo-1) + s)/conteo;
        
    }
    
    public float get_precioMax(){
        return precioMax;
    }
    
    public void set_precioMax(float p){
        precioMax = p;
    }
    
    public void newPromPrecio(float p){
        float aux = precioMax;
      precioMax = (aux*(conteoPrecioMax-1) + p)/conteoPrecioMax;
      //Profe si esta leyendo esta linea, hola.
    }
    public void incConteoPrecioMax(){
        conteoPrecioMax++;
    }
    @Override 
    public int compareTo(Object t2) 
    {
        int t2count = ((quintupla)t2).get_conteo();
        return this.get_conteo() - t2count;
    }
    
    public static void main(String[] args) {
        
        
    }
    
}