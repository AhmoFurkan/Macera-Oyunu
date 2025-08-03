import java.util.Random;

public class Snake extends Obstacle {

    public Snake(Player player) {
        super(4, "Yılan", 0, 12, 0);

        this.setDamage(randomDamage());


        if (randomAwardMoney() == 1) {
            Random r = new Random();
            int rastgeleSayi = r.nextInt(100);

            if (rastgeleSayi < 20) {
                this.setAward(10);
            } else if (rastgeleSayi < 30) {
                this.setAward(5);
            } else if (rastgeleSayi < 50) {
                this.setAward(1);
            }
        } else if (randomANothing() == 2) {
            this.setAward(0);

        }


    }

    public int randomDamage() {
        Random r = new Random();
        return r.nextInt(4) + 3;  // 3-6 arası sabit bir değer

    }

    public int randomAwardMoney() {
        Random r = new Random();
        int rastgele = r.nextInt(100);
        return (rastgele < 25) ? 1 : 0;
    }

    public int randomANothing() {
        Random r = new Random();
        int rastgele = r.nextInt(100);
        return (rastgele < 45) ? 2 : 0;
    }

    public int randomArmor() {
        Random r = new Random();
        int rastgele = r.nextInt(100);
        return (rastgele < 15) ? 3 : 0;
    }
    public int randomWeapon(){
        Random r = new Random();
        int rastgele = r.nextInt(100);
        return (rastgele < 15) ? 4 : 0;
    }

}
