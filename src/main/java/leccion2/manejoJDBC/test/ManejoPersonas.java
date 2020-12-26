package leccion2.manejoJDBC.test;

import leccion2.manejoJDBC.datos.PersonaJDBC;
import leccion2.manejoJDBC.domain.Persona;

import java.util.List;

/**
 * @author Jorge Luis Pérez Canto
 * @date 23/12/2020 19:03
 */

public class ManejoPersonas {
    public static void main(String[] args) {
        PersonaJDBC personaJDBC = new PersonaJDBC();
        List<Persona> personas = personaJDBC.select();

        for (Persona persona : personas) {
            System.out.println("Persona: " + persona);
        }


/*
        Persona persona = new Persona();
        persona.setNombre("María");
        persona.setApellido("Lara");
        persona.setEmail("mlara@gmail.com");
        persona.setTelefono("56879468");
        personaJDBC.insert(persona);
*/

/*
        Persona persona2 = new Persona();
        persona2.setId_persona(6);
        persona2.setNombre("Mayra");
        persona2.setApellido("Lara");
        persona2.setEmail("mlara@mail.com");
        persona2.setTelefono("56879468");
        personaJDBC.update(persona2);
*/

/*
        Persona persona3 = new Persona();
        persona3.setId_persona(7);
        personaJDBC.delete(persona3);
        */
    }
}
