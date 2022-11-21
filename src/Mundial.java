import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mundial {
    private static final String COMMA_DELIMITER = ",";

    public static List<Piloto> leerPilotos(){
        List<Piloto> pilotos = new ArrayList<>();
        Path ficero = Path.of("data/pilotos.xml");
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ArrayListPilotos.class);
            Unmarshaller jaxUnmarshaller = context.createUnmarshaller();
            ArrayListPilotos pilots = (ArrayListPilotos) jaxUnmarshaller.unmarshal(ficero.toFile());
            ArrayList<Piloto> pil = pilots.getPilotos();
            for (Piloto p : pil) {
                Piloto pi = new Piloto();
                pi.setFechaNacimiento(p.getFechaNacimiento());
                pi.setNumero(p.getNumero());
                pi.setNombre(p.getNombre());
                pi.setEquipo(p.getEquipo());
                pilotos.add(pi);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return pilotos;
    }

    public static List<Circuito> leerCircuitos() {
        //Formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        //Inidco el idioma
        formatter = formatter.withLocale(Locale.ENGLISH);
        //Variable LocalDate
        LocalDate date;
        List<Circuito> circuito = new ArrayList<>();
        try (FileReader fr = new FileReader("data/circuitos.csv"); BufferedReader br = new BufferedReader(fr)) {
            String linea;
            //Se lee la primera línea del csv pues no aporta datos de la carrera
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] circ = linea.split(COMMA_DELIMITER);
                Circuito c = new Circuito();
                c.setRonda(Integer.parseInt(circ[0]));
                c.setPais(circ[1]);
                //La variable de date tendrá el formato d MMM yyyy y el contenido de circ[2]
                date = LocalDate.parse(circ[2], formatter);
                //FechaCarrera será date.
                c.setFechaCarrera(date);
                circuito.add(c);
            }
            return circuito;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Resultado> leerResultados(List<Circuito> circuitos, List<Piloto> pilotos) {
        List<Resultado> resultado = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("data/resultados.json")));
            Gson gson = new Gson();
            resultado = Arrays.stream(gson.fromJson(data, Resultado[].class)).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public static void imprimirClasificacionFinal(List<Resultado> resultados) {
        HashMap<String, Double> clasificacion = new HashMap<>();
        for (Resultado res : resultados)  {
            if (!clasificacion.containsKey(res.getPiloto().getNombre())) {
                clasificacion.put(res.getPiloto().getNombre(), res.getPuntos());
            } else {
                clasificacion.put(res.getPiloto().getNombre(), clasificacion.get(res.getPiloto().getNombre())+res.getPuntos());
            }
        }
        clasificacion.entrySet().stream().filter(p -> p.getValue()>=0).sorted((p1, p2) ->Double.compare(p2.getValue(), p1.getValue())).forEach(System.out::println);
    }

    public static void imprimirMayoresde30(List<Piloto> pilotos) {
    }

    public static void main(String[] args) throws JAXBException {
        List<Piloto> pilotos = leerPilotos();
        System.out.println(pilotos);

        List<Circuito> circuitos = leerCircuitos();
        System.out.println(circuitos);

        List<Resultado> resultados = leerResultados(circuitos, pilotos);

        imprimirClasificacionFinal(resultados);

        imprimirMayoresde30(pilotos);
    }
}
