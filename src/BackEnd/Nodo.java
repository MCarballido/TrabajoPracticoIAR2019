package BackEnd;

public class Nodo
{
    private Comparable info;
    private Nodo next;

    public Nodo ( )
    {
    }

    public Nodo (Comparable x, Nodo p)
    {
        info = x;
        next = p;
    }

    public Nodo getNext()
    {
        return next;
    }

    public void setNext(Nodo p)
    {
        next = p;
    }

    public Comparable getInfo()
    {
        return info;
    }


    public void setInfo(Comparable p)
    {
        info = p;
    }

    @Override
    public String toString()
    {
        return info.toString();   
    }
}

