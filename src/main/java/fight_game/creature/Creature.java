package fight_game.creature;


import fight_game.choice.Choosable;
import fight_game.interfaces.Descriptible;
import fight_game.skills.Skills;

import java.util.List;

public abstract class Creature implements Comparable<Creature>, Descriptible, Choosable {

    protected String name;
    protected int lifePoint;
    private int damage;
    private int armor;
    private int maxLifePoint;
    private List<Skills> skills;


    public Creature(String name, int lifePoint, int damage, int armor) {
        this.name = name;
        this.lifePoint = lifePoint;
        this.damage = damage;
        this.armor = armor;
        this.maxLifePoint = lifePoint;

    }


    public void attack(Creature creature) {
        // Calcul des dégâts
        // Si l'armure est à 0, les dégâts sont infligés à la vie de la créature.
        if (creature.getArmor() == 0) {
            creature.sufferDamage(this.damage);
        } else {
            // Si l'armure est supérieur aux dégâts, l'armure est diminuée des dégâts.
            if (creature.getArmor() > this.damage) {
                creature.setArmor(creature.getArmor() - this.damage);
            } else {
                // Si l'armure est inférieur aux dégâts, l'armure est mise à 0 et les dégâts sont
                // infligés à la vie de la créature.
                creature.sufferDamage(this.damage - creature.getArmor());
                creature.setArmor(0);
            }
        }
        System.out.println(creature.getName() + " a subit " + this.damage + " points de dégâts");
    }


    public void sufferDamage(int damage) {
        this.lifePoint -= damage;
        if (this.lifePoint < 0) {
            this.lifePoint = 0;
        }
    }


    public String toString() {
        return "Bonjour, je m'appelle " + this.name + " je suis un " + this.getClass().getSimpleName();
    }


    public abstract boolean isAlive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMaxLifePoint() {
        return maxLifePoint;
    }

    public void setMaxLifePoint(int maxLifePoint) {
        this.maxLifePoint = maxLifePoint;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    public int compareTo(Creature creature) {
        if (this.getDamage() > creature.getDamage()) {
            return 1;
        } else if (this.getDamage() < creature.getDamage()) {
            return -1;
        }
        return 0;
    }

    public String describe() {
        return this.getClass().getSimpleName() + " : " + this.getName() + " a " + this.getLifePoint() + " points de vie, " + this.getDamage() + " points de dégâts et " + this.getArmor() + " points d'armure";
    }
}
