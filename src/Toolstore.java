import java.util.Scanner;

public class Toolstore extends NormalLoc {
    public Toolstore(Player player) {
        super(player, "mağaza");
    }


    @Override
    public boolean onLocation() {
        System.out.println("---------Mağazaya hoş geldiniz---------");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkı yap");
            System.out.print("Seçiminiz : ");
            int selectCase = input.nextInt();
            input.nextLine();

            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz değer, tekrar deneyiniz :");
                selectCase = input.nextInt();

            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu = false;
                    break;



            }

        }
        return true;
    }


    public void printWeapon() {
        System.out.println("--------Silahlar--------");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + "<Para : " + w.getPrice() + " , Hasar : " + w.getDamage() + ">");
        }
        System.out.println("0 - Çıkış yap");

    }

    public void buyWeapon() {
        System.out.print("Bir silah seçiniz : ");

        int selectWeaponID = input.nextInt();
        input.nextLine();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.print("Geçersiz değer, tekrar deneyiniz :");
            selectWeaponID = input.nextInt();

        }
        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır");
                } else {
                    // satın almanın gerçekleştiği alan
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    this.getPlayer().getInvertory().setWeapon(selectedWeapon);

                }
            }
            if (selectedWeapon == null) {
                System.out.println("hata null geldi");
            }
        }


    }


    public void printArmor() {
        System.out.println("--------Zırhlar--------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    "<para : " + a.getPrice() + ", Zırh : " + a.getBlock() + " > ");
        }
        System.out.println("0 - Çıkış yap");
    }

    public void buyArmor() {
        System.out.print("Bir zırh seçiniz : ");

        int selectArmorID = input.nextInt();
        input.nextLine();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("Geçersiz değer, tekrar deneyiniz :");
            selectArmorID = input.nextInt();

        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır");
                } else {
                    // satın almanın gerçekleştiği alan
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    this.getPlayer().getInvertory().setArmor(selectedArmor);
                    System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());

                }
            }
        }

    }

}
