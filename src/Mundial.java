import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mundial {
    private static final String COMMA_DELIMITER =",";

    /*public static List<Piloto> leerPilotos() {

    }*/
    
    public static List<Circuito> leerCircuitos() {
        List<Circuito> circuito = new ArrayList<>();
        try (FileReader fr = new FileReader("data/circuitos.csv"); BufferedReader br = new BufferedReader(fr)) {
            String linea;
            //Se lee la primera línea del csv pues no aporta datos de la carrera
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String circ[] = linea.split(COMMA_DELIMITER);
                Circuito c = new Circuito();
                c.setRonda(Integer.parseInt(circ[0]));
                c.setPais(circ[1]);
                //c.setFechaCarrera(LocalDate.parse(circ[2]));
                circuito.add(c);
            }
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return circuito;
    }

    //public static List<Resultado> leerResultados(List<Circuito> circuitos, List<Piloto> pilotos) {

    //}

    public static void imprimirClasificacionFinal(List<Resultado> resultados) {

    }

    public static void imprimirMayoresde30(List<Piloto> pilotos) {

    }

    public static void main(String[] args) {
        //List<Piloto> pilotos = leerPilotos();
		// System.out.println(pilotos);

        List<Circuito> circuitos = leerCircuitos();
		System.out.println(circuitos);

        //List<Resultado> resultados = leerResultados(circuitos, pilotos);

        //imprimirClasificacionFinal(resultados);

        //imprimirMayoresde30(pilotos);
    }
}
