package TALLER_1;

import EJEMPLO_1.Objetos.Persona;
import TALLER_1.Objetos.Jugadores;
import TALLER_1.Objetos.Paises;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Main {

    private static final String ODB_NAME = "BD_taller.neodatis";

    private static void borrarBaseDatosActual()
    {
        Objects objects;

        // Open the database
        ODB odb = null;

        try {
            odb = ODBFactory.open(ODB_NAME);
            objects = odb.getObjects(Persona.class);
            while (objects.hasNext()) {
                odb.delete(objects.next());
            }
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public static void main(String[] args) {

        borrarBaseDatosActual();

        // LINEA DE CONEXION CON LA BASE DE DATOS
        ODB odb = ODBFactory.open(ODB_NAME);

        // COMO SE CREA UN OBJETO DE TIPO PAIS
        Paises pais_1 = new Paises(1, "España");

        // CREE 3 PAISES MAS


        // COMO SE CREA UN OBJETO DE TIPO JUDADOR
        Jugadores j1 = new Jugadores("Maria", "voleibol", pais_1, 14);

        // CREE 3 JUGADORES MAS

        // COMO SE GUARDA EN LA BASE DE DATOS EL JUGADOR
        odb.store ( j1 );

        // GUARDE CADA UNO DE LOS NUEVOS JUGADORES DE LA MISMO MODO



        // VERIFIQUE QUE LOS JUGADORES SE CREARON EN LA BASE DE DATOS

        Objects<Jugadores> objectsJug = odb.getObjects(Jugadores.class);
        System.out.println("Hay "+ objectsJug.size()+" Jugadores en la BD:");

        int i = 1;

        // IMPRIMA LOS DATOS DE CADA UNO DE LOS JUGADORES CREDOS PREVIAMENTE
        while(objectsJug.hasNext()) {
            // GUARDA DE MANERA LOCAL EL JUGADOR ACTUAL
            Jugadores jug = objectsJug.next();

            // Imprimo las propiedades que me interesan de ese objeto
            System.out.println((i++) +" - "+"Nombre: "+jug.getNombre()+", Deporte: "+ jug.getDeporte()+", Pais: "+ jug.getPais().getNombrePais()+", Edad: "+ jug.getEdad());
        }

        // CON BASE EN EL EJEMPLO ANTERIOR AHORA CONSULTE Y MUESTRE LA LISTA DE LOS PAISES


        // CIERRE DE LA BASE DE DATOS
        odb.close();
    }
}
