import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "diver")
public class Piloto {
    @XmlElement(name = "name")
    private String nombre;
    @XmlAttribute(name = "number")
    private int numero;
    @XmlElement(name = "team")
    private String equipo;
    @XmlElement(name = "dob")
    private LocalDate fechaNacimiento;
    public Piloto() {

    }

    // Getters
    public String getNombre() { return nombre; }
    public int getNumero() { return numero;}
    public String getEquipo() { return equipo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setEquipo(String equipo) { this.equipo = equipo; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return "Piloto{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", equipo='" + equipo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                '}';
    }
}
