package org.example;
import Grafo.Grafo;
import Grafo.Recorrido;
import Materia.Materia;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

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


        for (Materia m : materias) {
            grafo.addNode(m.getCode(), m);
        }

        for (Materia m : materias) {
            for (Integer correlativa : m.getDependenciesCode()) {
                grafo.addEdge(correlativa, m.getCode());
            }
        }

        grafo.forEachNode(materia -> {
            System.out.println("Materia: " + materia.getName());
            System.out.println("Abre las materias de: ");
            grafo.forEachNeighbor(materia.getCode(), materiaQueAbre -> {
                System.out.println("   " + materiaQueAbre.getName());
            });
            System.out.println("--------");
        });

    }
}