import java.util.Scanner;

public class Player {

    private int damage;
    private int health;
    private int orijinalHealth;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Invertory invertory;
    private String award;
    private boolean hasFood;
    private boolean hasFirewood;
    private boolean hasWater;
    private boolean a;

    public Player(String name) {
        this.name = name;
        this.invertory = new Invertory();
        this.a =false;
        this.hasFood = false;
        this.hasFirewood = false;
        this.hasWater = false;

    }


    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("KARAKTERLER");
        System.out.println("--------------------------------------------------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID : " + gameChar.getId() +
                    "\t Karakter : " + gameChar.getName() +
                    "\t Hasar : " + gameChar.getDamage() +
                    "\t Sağlık : " + gameChar.getHealth() +
                    "\t Para : " + gameChar.getMoney());
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.print("Lütfen bir karakter seçiniz  : ");
        int selectChar =  input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        /**     System.out.println("Karakter : " + this.getCharName() +
         ", Hasar : " + this.getDamage() +
         ", Sağlık " + this.getHealth() +
         ", Para : " + this.getMoney());
         **/
    }


    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrijinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }


    public void printInfo() {
        System.out.println(
                " Silahınız : " + this.getInvertory().getWeapon().getName() +
                        " ,Zırh : " + this.getInvertory().getArmor().getName() +
                        ", Bloklama : " + this.getInvertory().getArmor().getBlock() +
                        ", Hasar : " + this.getTotalDamage() +
                        ", Sağlık " + this.getHealth() +
                        ", Para : " + this.getMoney());
        System.out.println("------ Ödüller ------");
                        this.printInventory();


    }
    public void printInventory() {
        System.out.println("Envanter Durumu:");
        System.out.println("Yemek: " + (isHasFood() ? "Var" : "Yok"));
        System.out.println("Odun: " + (isHasFirewood() ? "Var" : "Yok"));
        System.out.println("Su: " + (isHasWater() ? "Var" : "Yok"));
    }


    public int getTotalDamage() {
        return damage + this.getInvertory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Invertory getInvertory() {
        return invertory;
    }

    public void setInvertory(Invertory invertory) {
        this.invertory = invertory;
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public boolean isHasFood() {
        return hasFood;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    public boolean isHasFirewood() {
        return hasFirewood;
    }

    public void setHasFirewood(boolean hasFirewood) {
        this.hasFirewood = hasFirewood;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }
}
