import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "drivers")
public class ArrayListPilotos {
    private ArrayList<Piloto> pilotos = null;
    @XmlElement(name = "driver")
    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }
    public void setPilotos(ArrayList<Piloto> pilotos)  {
        this.pilotos = pilotos;
    }
}
