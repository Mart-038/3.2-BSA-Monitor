package bsa_monitor.project.controller;

import bsa_monitor.project.model.*;

import java.util.Scanner;

public class BSAMonitorLauncher {

    private static final double ONDERGRENS_CIJFER = 1.0;
    private static final double BOVENGRENS_CIJFER = 10.0;

    public static void main(String[] args) {
        Scanner toetsenbord = new Scanner(System.in);

        Vak[] vakken = {
            new Vak("Project Fasten Your Seatbelts", 12, 5.5),
            new Vak("Programming", 3, 5.5),
            new Vak("Databases", 3, 5.5),
            new Vak("Personal Skills", 2, 5.5),
            new Vak("Project Skills", 2, 5.5),
            new Vak("OOP", 3, 5.5),
            new Vak("User Interaction", 3, 5.5)
        };

        int totaalMogelijkBehaaldeStudiepunten = 0;
        for (int vak = 0; vak < vakken.length; vak++) {
            totaalMogelijkBehaaldeStudiepunten += vakken[vak].getPunten();
        }

        System.out.println("Voer behaalde cijfers in:");
        Score[] scores = new Score[vakken.length];
        int behaaldeStudiepunten = 0;
        for (int vak = 0; vak < vakken.length; vak++) {
            System.out.printf("%s: ", vakken[vak].getNaam());
            double behaaldCijfer = toetsenbord.nextDouble();
            while (behaaldCijfer < ONDERGRENS_CIJFER || behaaldCijfer > BOVENGRENS_CIJFER) {
                System.out.printf(
                        "Een cijfer moet altijd tussen de %.1f en %.1f liggen.\nGeef nieuwe waarde: ",
                        ONDERGRENS_CIJFER,
                        BOVENGRENS_CIJFER);
                behaaldCijfer = toetsenbord.nextDouble();
            }
            scores[vak] = new Score(vakken[vak], behaaldCijfer);
            behaaldeStudiepunten += scores[vak].getBehaaldePunten();
        }

        System.out.println();
        for (int vak = 0; vak < vakken.length; vak++) {
            System.out.printf("%12s %-30s %8s %3.1f %16s %2d\n",
                    "Vak/project:",
                    vakken[vak].getNaam(),
                    "Cijfer:",
                    scores[vak].getCijfer(),
                    "Behaalde punten:",
                    scores[vak].getBehaaldePunten()
            );
        }

        System.out.println();
        System.out.printf("Totaal behaalde studiepunten: %d/%d\n",
                behaaldeStudiepunten,
                totaalMogelijkBehaaldeStudiepunten
                );
        if (behaaldeStudiepunten < ((5.0/6) * totaalMogelijkBehaaldeStudiepunten)) {
            System.out.println("PAS OP: je ligt op schema voor een negatief BSA!");
        }
    }
}
