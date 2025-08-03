import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera oyununa hoş geldiniz !");
        System.out.println("Lütfen bir isim giriniz : ");
        //   String playerName = input.nextLine();
        Player player = new Player("Mustafa");
        System.out.println("Sayın " + player.getName() + " Karanlık ve Sisli adaya hoş geldiniz. Burada yaşananların hepsi gerçek ! !");
        System.out.println("Lütfen bir karakter seçiniz !");
        System.out.println("------------------------------------------------------------");
        player.selectChar();
        Location location = null;

        while (Location.gameActive) {
            player.printInfo();
            System.out.println();
            System.out.println("##########Bölgeler##########");
            System.out.println();
            System.out.println("Bölgeler");
            System.out.println("1 - Güvenli ev --> Burası sizin için güvenli bir ev, düşman yoktur");
            System.out.println("2 - Eşya Dükkanı --> Silah veya Zırh satın alabilirsiniz");
            System.out.println("3 - Mağara --> Ödül <Yemek> gir, dikkatli ol zombi çıkabilir");
            System.out.println("4 - Orman --> Ödül <Odun> gir, dikkatli ol vampir çıkabilir");
            System.out.println("5 - Nehir --> Ödül <Su> gir, dikkatli ol ayı çıkabilir");
            System.out.println("6 - Maden --> Ödül <Para> , <Silah> veya <Zırh> , dikkatli ol Yılan çıkabilir");
            System.out.println("0 - Çıkış yap --> Oyunu sonlandır");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz :");
            int selectLoc = input.nextInt();
            input.nextLine();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Toolstore(player);
                    break;
                case 3:
                    if (player.isHasFood()) {
                        System.out.println("Ödül kazanılan bölgeye giriş yapılamaz");
                        location = null;
                        break;
                    }
                    location = new Cave(player);
                    break;
                case 4:
                    if (player.isHasFirewood()) {
                        System.out.println("Ödül kazanılan bölgeye giriş yapılamaz");
                        location = null;
                        break;
                    }
                    location = new Forest(player);
                    break;
                case 5:
                    if (player.isHasWater()) {
                        System.out.println("Ödül kazanılan bölgeye giriş yapılamaz");
                        location = null;
                        break;
                    }
                    location = new River(player);
                    break;
                case 6:
                    if (player.isA()){
                        System.out.println("Tüm canavarları yendiğiniz için giriş yapılamaz");
                        location = null;
                        break;
                    }
                    location = new Coal(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir değer giriniz");
            }


            if (location == null && selectLoc == 0) {
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin");
                break;

            }
            if (location == null) {
                continue;

            }

            if (!(location.onLocation())) {

                System.out.println("GAME OVER");
                break;
            }


        }
    }

}
