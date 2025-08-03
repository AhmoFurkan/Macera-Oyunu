public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz");
        System.out.println("Canınız yenilendi");
        this.getPlayer().setHealth(this.getPlayer().getOrijinalHealth());
            controlAward();

        return true;


    }

    public void controlAward() {
        if (this.getPlayer().isHasFood() && this.getPlayer().isHasWater() && this.getPlayer().isHasFirewood() && this.getPlayer().isA()) {
            System.out.println("Tebrikler oyunu kazandınız !!");
            gameActive =false;
        }
    }


}

