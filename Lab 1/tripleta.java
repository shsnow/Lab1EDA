package laboratorio1;

public class tripleta implements Comparable //interfaz
{
    private String categoria;
    private String nombre_producto;
    private int conteo;

    public tripleta()
    {
        categoria = new String();
        nombre_producto = new String();
        conteo = 0;
    }

    public tripleta(String c, String n)
    {
        categoria = c;
        nombre_producto = n;
        conteo = 1;
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

    @Override 
    public int compareTo(Object t2) 
    {
        int t2count = ((tripleta)t2).get_conteo();
        return t2count - this.get_conteo();
    }
}