import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }


    @Override
    public boolean onLocation() {


        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol! Burada  : " + obsNumber + " tane " + this.getObstacle().getName() + " var ");
        System.out.print("<S>avaş veya <K>aç : ");

        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz");
            String award = this.getAward();
            if (award.equals("food")) {
                if (!this.getPlayer().isHasFood()) {
                    this.getPlayer().setHasFood(true);
                    System.out.println("Food (Yemek) envanterinize eklendi!");
                }
            } else if (award.equals("firewood")) {
                if (!this.getPlayer().isHasFirewood()) {
                    this.getPlayer().setHasFirewood(true);
                    System.out.println("Firewood (Odun) envanterinize eklendi!");
                }
            } else if (award.equals("water")) {
                if (!this.getPlayer().isHasWater()) {
                    this.getPlayer().setHasWater(true);
                    System.out.println("Water (Su) envanterinize eklendi!");
                }
            }
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz ! ");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            if (!firstHit()) {
                return false;  // oyuncu kaçtıysa savaş iptal, onLocation()'e dön
            }
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
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
                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz");

                System.out.println(this.getObstacle().getAward() + " Para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            } else {
                return false;
            }

        }
        return true;
    }


    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println("---------");
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri ");
        System.out.println("------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInvertory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInvertory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInvertory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i) {
        System.out.println(i + " - " + this.getObstacle().getName() + " Değerleri ");
        System.out.println("------------------------- ");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
        System.out.println();
    }


    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

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
                System.out.println("Şans canavardan yana canavar vurdu");
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


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
