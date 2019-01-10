import acm.program.DialogProgram;

public class Rules extends DialogProgram {

    double setSpeed() {
        int s;
        do {
            s = readInt("Введіть складність від 1 до 5: \n 1 - дитячий \n 2 - посильно \n 3 - нормальний \n 4 - складно \n 5 - нічне жахіття");
        } while (s < 0 || s > 5);

        switch (s) {
            case 1: {
                return -0.05;
            }
            case 2: {
                return -0.1;
            }
            case 3: {
                return -0.15;
            }
            case 4: {
                return -0.21;
            }
            case 5: {
                return -0.26;
            }

        }
        return 0;
    }

    void begin() {
        println("Click anywhere to start!");
    }

    void end(int count) {
        if (count != 0) {
            println("You have missed " + count + " houses! Good job!");
        } else {
            println("You have won!!!");
        }
    }
}
