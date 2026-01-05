package org.example;
import Grafo.Grafo;
import Materia.Materia;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ArrayList<Materia> materias = new ArrayList<>();
        Grafo<Materia> grafo = new Grafo<Materia>(true);

        try (BufferedReader br = new BufferedReader(new FileReader("infoMaterias.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("codigo")) continue;
                String[] parts = line.split(";");

                Integer codigo = Integer.parseInt(parts[0].trim());
                String nombre = parts[1].trim();

                Materia materia = new Materia(codigo, nombre);

                for (int i = 2; i < parts.length; i++) {
                    materia.addDependenciesCode(Integer.parseInt(parts[i].trim()));
                }
                materias.add(materia);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (Materia m : materias) {
//            System.out.println("Materia: " + m.getName());
//            System.out.println("Codigo: " + m.getCode());
//            System.out.println("Correlativas codigo: " + m.getDependenciesCode());
//            System.out.println("--------");
//        }

        for (Materia m : materias) {
            grafo.addNode(m.getCode(), m);
        }

        for (Materia m : materias) {
            for (Integer correlativa : m.getDependenciesCode()) {
                grafo.addEdge(correlativa, m.getCode());
            }
        }

//        ArrayList<Materia> materiasCorrelaivas = grafo.getAdyacentesById(3249);
//
//        System.out.println("Materia de codigo: 3249");
//        System.out.println("Correlativas: ");
//        for (Materia correlativas : materiasCorrelaivas) {
//            System.out.println(correlativas.getCode() + " - " + correlativas.getName());
//        }

        for (int i = 3000; i < 4000 ; i++) {
            Materia m = grafo.getNode(i);
            if (m != null) {
                System.out.println("Materia: " + m.getName());
                System.out.println("Abre las materias de: ");
                ArrayList<Materia> materiasQueAbre = grafo.getAdyacentesById(m.getCode());
                for (Materia w : materiasQueAbre) {
                            System.out.println("   " + w.getName());
                }
                System.out.println("--------");
            }

        }
    }
}