import java.util.Random;

public class Coal extends BattleLoc {


    public Coal(Player player) {
        super(player, "maden", new Snake(player), "yok", 5);
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol! Burada  : " + obsNumber + " tane Yılan var ");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = Location.input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz");
            if (!this.getPlayer().isA()) {
                this.getPlayer().setA(true);
            }

            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz ! ");
            return false;
        }
        return true;
    }

    @Override
    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            Snake snake = new Snake(this.getPlayer()); // Her döngüde yeni yılan
            this.setObstacle(snake);
            playerStats();
            obstacleStats(i);
            System.out.println(i + ". Yılanla savaş başlıyor!");

            if (!firstHit()) {
                return false;  // oyuncu kaçtıysa savaş iptal, onLocation()'e dön
            }

            while (this.getObstacle().getHealth() > 0 && this.getPlayer().getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç : ");
                String sselectCombat = Location.input.nextLine().toUpperCase();

                if (sselectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHitCustom();

                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Yılan size vurdu !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInvertory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHitCustom();
                    }
                } else {
                    System.out.println("Savaştan kaçtınız!");
                    return false;
                }
            }

            if (this.getObstacle().getHealth() <= 0) {
                System.out.println("Yılanı yendiniz!");

                if (snake.randomArmor() == 3) {
                    Armor selectedArmor;
                    Random r = new Random();
                    int rastgeleSayi = r.nextInt(100);
                    if (rastgeleSayi < 20) {
                        System.out.println("Tebrikler Ağır Zırh Kazandınız");
                        selectedArmor = Armor.getArmorObjByID(3);


                    } else if (rastgeleSayi < 50) {
                        System.out.println("Tebrikler Orta Zırh Kazandınız");
                        selectedArmor = Armor.getArmorObjByID(2);

                    } else {
                        System.out.println("Tebrikler Hafif Zırh Kazandınız");
                        selectedArmor = Armor.getArmorObjByID(1);
                    }
                    this.getPlayer().getInvertory().setArmor(selectedArmor);
                }

                if (snake.randomWeapon() == 4) {
                    Weapon selectedWeapon;
                    Random r = new Random();
                    int rastgeleSayi = r.nextInt(100);
                    if (rastgeleSayi < 20) {
                        System.out.println("Tebrikler Tüfek kazandınız");
                        selectedWeapon = Weapon.getWeaponObjByID(3);

                    } else if (rastgeleSayi < 50) {
                        System.out.println("Tebrikler Kılıç kazandınız");
                        selectedWeapon = Weapon.getWeaponObjByID(2);

                    } else {
                        System.out.println("Tebrikler Tabanca kazandınız");
                        selectedWeapon = Weapon.getWeaponObjByID(1);
                    }
                    this.getPlayer().getInvertory().setWeapon(selectedWeapon);
                }

                System.out.println("Para kazandınız : " + this.getObstacle().getAward());
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            }

            if (this.getPlayer().getHealth() <= 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean firstHit() {
        if (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {

            Random random = new Random();
            boolean sansVarMi = random.nextBoolean();
            if (sansVarMi) {
                System.out.println("Şanlısınız ilk vuruş hakkı sizin");
                System.out.print("<V>ur veya <K>aç : ");
                String sselectCombat = input.nextLine().toUpperCase();
                if (sselectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar Size Vurdu");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInvertory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    return true;
                } else {
                    System.out.println("Savaştan kaçtınız!");
                    return false; // Savaş iptal, menüye dön
                }

            } else if (this.getObstacle().getHealth() > 0) {
                System.out.println();
                System.out.println("Şans canavardan yana.Canavar vurdu");
                int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInvertory().getArmor().getBlock();
                if (obstacleDamage < 0) {
                    obstacleDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                afterHit();
                return true;
            }


        }
        return true;
    }

    public void afterHitCustom() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println("Yılanın Canı : " + this.getObstacle().getHealth());
        System.out.println("---------");
    }

}
