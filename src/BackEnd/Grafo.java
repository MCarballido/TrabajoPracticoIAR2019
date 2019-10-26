package BackEnd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo
{
    // atributos esenciales
    protected Object   []nodos;    // vector de nodos
    protected Arco     [][]ady;    // matriz de adyacencias

    // requerido por el algoritmo de Warshall
    protected boolean  [][]trans;  // matriz para almacenar el cierre transitivo

    // usada como auxiliar en el algoritmo para calcular el orden topológico
    private int indice;   

    /**
     * Inicializa el grafo para un máximo de 10 nodos.
     */
    public Grafo ()
    {
      this(10);
    }

    public Grafo ( int n )
    {
        // el vector de nodos, con todas su casillas valiendo null...
        nodos = new Object[n];

        // la matriz de adyacencias...
        ady   = new Arco [n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                //... llena de Arcos con "existe" valiendo false
                ady[i][j] = new Arco();
            }
        }

        // la matriz de cierre transitivo, con todos sus casilleros valiendo false...
        trans = new boolean [n][n];
    }

    public int length()
    {
        return nodos.length;   
    }

    public boolean unir ( Object n1, Object n2 )
    {
        return unir(n1, n2, 0);
    }

    public boolean unir ( Object n1, Object n2, int p )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
           ady[i1][i2] = new Arco(p);
           out = true;
        }
        return out;
    }

    public boolean cortar ( Object n1, Object n2 )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
            ady[i1][i2].set(false);
            out = true;
        }
        return out;
    }
     
    public boolean hayArco ( Object n1, Object n2 )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
           out = ady[i1][i2].exists();
        }
        return out;
    }

    public int getPeso ( Object n1, Object n2 )
    {
        int out = 0;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
           out = ady[i1][i2].getPeso();
        }
        return out;
    }

    public boolean setNodo(Object x)
    {
        if( x != null )
        {
            if( nodos[0] != null && x.getClass() != nodos[0].getClass() ) return false;

            for (int i=0; i<nodos.length; i++)
            { 
                if(nodos[i] == null)
                {
                   nodos[i] = x;
                   return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString ()
    {
        StringBuilder cad = new StringBuilder("    ");  
        int i, j, n = nodos.length;

        for(i=0; i<n; i++) 
        {
            cad.append("  " + nodos[i] + "      ");
        }

        cad.append("\n\nMatriz de Adyacencias\n");

        for (i=0; i<n; i++)
        {
            cad.append(nodos[i] + "  ");
            for ( j=0; j<n; j++) 
            {
                String c = "";
                if (ady[i][j].exists()) c += "(1,"; else c = "(0,";
                int valor = ady[i][j].getPeso();
                c = c + valor + ")";
                cad.append("  " + c + "  "); 
            }
            cad.append("\n");
        }

        cad.append("\n\nCierre Transitivo\n");
        for (i=0; i<n; i++)
        {
            cad.append(nodos[i] + "  ");
            for (j=0; j<n; j++)
            { 
                int valor = ( trans[i][j] )? 1 : 0;
                cad.append("  " + valor + "  "); 
            }
            cad.append("\n");
        }

        return cad.toString();
    }

    protected int buscar (Object n1)
    {
        if(n1 != null)
        {
            if( nodos[0] != null && n1.getClass() == nodos[0].getClass() )
            {
                for (int i=0; i<nodos.length; i++) 
                { 
                    if ( nodos[i].equals(n1) ) { return i; } 
                }
            }
        }
        return -1;
    }
    
    public String buscarCaminoDFS(Object start, Object end)
    {
        if (start == null || end == null) {
            return "Datos no validos";
        }
        
        if (start.equals(end)) {
            return "Ambos valores apuntan al mismo nodo";
        }
        
        boolean[] visitados = new boolean[nodos.length];
        LinkedList<Object> camino = new LinkedList<>();
        
        int indx = -1;
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].equals(start)) {
                indx = i;
                break;
            }
        }

        
        if (indx != -1) {
            int ultimoK = visitarDFS(indx, visitados, camino, end);
            if (ultimoK >= 0) camino.addFirst(nodos[ultimoK]);
        }
        
        if (camino.isEmpty()) {
            return "Camino no encontrado...";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camino.size(); i++) {
            if (i != 0) sb.append(" -> "); 
            sb.append(camino.get(i));
        }

        return sb.toString();
    }

    private int visitarDFS (int k, boolean []visitados, LinkedList<Object> camino, Object end)
    {
        int t, n = nodos.length;
        visitados[k] = true;
        
        if (nodos[k].equals(end)) {
            return k;
        }
        
        for (t = 0; t < n; t++)
        {
            if( ady[k][t].exists() )
            {
                if ( !visitados[t] ) {
                    int val = visitarDFS(t, visitados, camino, end);
                    if (val >= 0) {
                        camino.addFirst(nodos[val]);
                        return k;
                    }
                }
            }
        }
        
        return -1;
    }
    
    public String buscarCaminoBFS(Object start, Object end)
    {
        if (start == null || end == null) {
            return "Datos no validos";
        }
        
        if (start.equals(end)) {
            return "Ambos valores apuntan al mismo nodo";
        }
        
        int[] visitados = new int[nodos.length]; // en cada slot se guarda el padre
        for (int i = 0; i < visitados.length; i++) {
            visitados[i] = -1;
        }

        return visitarBFS(this.buscar(start), this.buscar(end), visitados);
    }
    
    private String visitarBFS(int s, int e, int []visitados)
    {
        int k, t, n = nodos.length;
        boolean found = false;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visitados[s] = Integer.MIN_VALUE; // aca iria el nombre del nodo padre
        
        while (!queue.isEmpty()) {
            k = queue.remove();
            
            if (k == e) {
                found = true;
                break;
            }
            
            for (t = 0; t < n; t++)
            {
                if( ady[k][t].exists() )
                {
                    if ( visitados[t] == -1 ) {
                        queue.add(t);
                        visitados[t] = k;
                    }
                }
            }
        }
        
        LinkedList<Integer> camino = new LinkedList<>();
        
        int x;
        if (found) {
            x = e;
            while (x != Integer.MIN_VALUE) {
                camino.addFirst(x);
                x = visitados[x];
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < camino.size(); i++) {
                if (i != 0) sb.append(" -> ");
                sb.append(this.nodos[ camino.get(i) ].toString());
            }
            return sb.toString();
        } else {
            return "Camino no encontrado...";
        }
        
    }
}
