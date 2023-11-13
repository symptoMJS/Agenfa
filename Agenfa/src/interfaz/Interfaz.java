package interfaz;
import dominio.*;
import java.io.*;
import java.util.*;
public class Interfaz {

    private ArrayList<Contacto> contactos = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    Libreta libreta = new Libreta ("Mi libreta personal");

    public boolean procesarPeticion(String peticion) {
        String[] p = peticion.split(" ");

        if (p.length == 1) {
            if (p[0].equals("addContacto")) {
                aniadirContacto();

            } else if (p[0].equals("read")) {
                leer();

            } else if (p[0].equals("list")) {
                mostrar();

            } else if (p[0].equals("help")) {
                System.out.println("introduzca una de las siguientes peticiones: \n addContacto: añadir contacto\n list: listar el contenido\n read: lectura inicial\n exit: salir\n borrar : borrar contacto");

            } else if (p[0].equals("exit")) {
                grabar();
                return false;

            } else {
                System.out.println("petición errónea");
                procesarPeticion("help");
            }

        } else {
            System.out.println("petición errónea");
            procesarPeticion("help");
        }

        return true;
    }

    public void aniadirContacto() {
        System.out.println("Introduzca el nombre del contacto:");
        String nombre = sc.nextLine();
        System.out.println("Introduzca el teléfono del contacto:");
        int telefono = sc.nextInt();
        Contacto contacto = new Contacto(nombre, telefono);
        contactos.add(contacto);
    }

    public void leer() {
        try {
            FileInputStream fis = new FileInputStream("contactos.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            contactos = (ArrayList<Contacto>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public void mostrar() {
        for (Contacto contacto : contactos) {
            System.out.println(contacto);
        }
    }

    public void grabar() {
        try {
            FileOutputStream fos = new FileOutputStream("contactos.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactos);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al grabar el archivo");
        }
    }

    public void borrar(String nombre){
        libreta.borrar(new Contacto(nombre));
        
    }
}